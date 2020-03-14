package crackers.traders.janani.dao.querydao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import crackers.traders.janani.dao.MasterDefDao;
import crackers.traders.janani.table.CatagoryMst;
import crackers.traders.janani.table.SupplierMst;
import crackers.traders.janani.table.component.MasterDef;


public class QueryDaoImpl implements QueryDao{

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	DataSource source;

	@Autowired
	MasterDefDao masterDef;

	@Override
	public List<CatagoryMst> searchCatagoryData(String value, List<String> searchField) {
		String catagoryQuery = "select new crackers.traders.janani.table.CatagoryMst(catgory.catagoryId,catgory.catagoryName,"
				+ "catgory.catagoryShort,catgory.catagoryCommodityCode,catgory.catagoryCst,catgory.catagoryVat,catgory.createdUser,"
				+ "catgory.createdDate,catgory.updatedUser,catgory.updatedDate) from CatagoryMst catgory";
		if(StringUtils.isEmpty(value)) {
			return entityManager.createQuery(catagoryQuery, CatagoryMst.class).getResultList();
		} else {
			StringBuilder builder = new StringBuilder(catagoryQuery).append(" where ");
			buildLikeCondition(value, searchField, "catgory", builder);
			TypedQuery<CatagoryMst> query = entityManager.createQuery(builder.toString(), CatagoryMst.class);
			return query.getResultList();
		}
	}

	@Override
	public List<SupplierMst> searchSupplierData(String value, List<String> searchField) {
		String supplierQuery = "select new crackers.traders.janani.table.SupplierMst(supply.supplierId,supply.supplierName,supply.supplierShortName,"
				+ "supply.supplierAddress,supply.supplierCity,supply.supplierPincode, supply.supplierPhoneno, supply.supplierEmail,supply.supplierTin,"
				+ "supply.supplierCst,supply.supplierPan,supply.createdUser,supply.createdDate,supply.updatedUser,supply.updatedDate) from SupplierMst supply";
		if(StringUtils.isEmpty(value)) {
			return entityManager.createQuery(supplierQuery, SupplierMst.class).getResultList();
		} else {
			StringBuilder builder = new StringBuilder(supplierQuery).append(" where ");
			buildLikeCondition(value, searchField, "supply", builder);
			TypedQuery<SupplierMst> query = entityManager.createQuery(builder.toString(), SupplierMst.class);
			return query.getResultList();
		}
	}

	private void buildLikeCondition(String value, List<String> searchField, String tableShort, StringBuilder builder) {
		value = StringUtils.isEmpty(value) ? "" : value;
		String[] splitedData = value.split(" +");
		for(int valueIncrement=0; valueIncrement<splitedData.length; valueIncrement++) {
			for(int searchIncrement =0 ; searchIncrement< searchField.size() ; searchIncrement++) {
				if(searchIncrement == 0) {
					builder.append(" (lower(cast(").append(tableShort).append(".").append(searchField.get(searchIncrement))
					.append(" as text)) like concat('%', lower('").append(splitedData[valueIncrement]).append("'), '%')");						
				} else {
					builder.append(" or lower(cast(").append(tableShort).append(".").append(searchField.get(searchIncrement))
					.append(" as text)) like concat('%', lower('").append(splitedData[valueIncrement]).append("'), '%')");						
				}
			}
			if(valueIncrement < splitedData.length - 1) {
				builder.append(") and ");
			} else {
				builder.append(") ");
			}
		}
	}

	@Override
	public Map<String, Object> masterSearch(String value,String masterId, int offset, int size, boolean checkCount) throws SQLException {

		Optional<MasterDef> masterData = masterDef.findById(masterId);
		Map<String, Object> returnMap= new HashMap<String, Object>();
		if(masterData.isPresent()) {
			MasterDef masterDataValue = masterData.get();
			List<String> fetchFled = new ArrayList<>();
			String[] showField = masterDataValue.getShowFieldIds().split(",");
			for(String field: showField) {
				if(!StringUtils.isEmpty(field)) {
					fetchFled.add(field);	
				}
			}
			String keyField = masterDataValue.getKeyFieldId();
			String setField = masterDataValue.getNameFieldId();
			if(!fetchFled.contains(keyField) && !StringUtils.isEmpty(keyField) ) {
				fetchFled.add(keyField);
			}
			if(!fetchFled.contains(setField) && !StringUtils.isEmpty(setField)) {
				fetchFled.add(setField);
			}
			String[] filterField = masterDataValue.getFilterFieldIds().split(",");
			for(String field: filterField) {
				if(!fetchFled.contains(field) && !StringUtils.isEmpty(field)) {
					fetchFled.add(field);
				}
			}
			String tableShort = "master_search";
			StringBuilder builder = new StringBuilder("Select ");
			fetchFled.forEach(action -> 
			builder.append(tableShort).append(".").append(action).append(","));
			builder.deleteCharAt(builder.toString().length() - 1);
			builder.append(" from ").append(masterDataValue.getTableName()).append(" ").append(tableShort);
			List<String> showFieldList = new ArrayList<>();
			for(String field: showField) {
				if(!StringUtils.isEmpty(field)) {
					showFieldList.add(field);	
				}
			}
			if((showField.length > 0 && !StringUtils.isEmpty(value))|| filterField.length > 0) {
				builder.append(" where ");
				buildLikeCondition(value,showFieldList, tableShort, builder);

			}
			Connection connection = source.getConnection();
			
			if(checkCount) {
				PreparedStatement countstatement = connection.prepareStatement(builder.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, 
						ResultSet.CONCUR_READ_ONLY);
				ResultSet countSet = countstatement.executeQuery();
				countSet.last();
				returnMap.put("count", countSet.getRow());
			}

			PreparedStatement statement = connection.prepareStatement(builder.append(" offset ").append(offset * 5).append(" limit ").append(size).toString());

			ResultSetMetaData meta = statement.getMetaData();

			ResultSet resultSet = statement.executeQuery();
			List<Map<String, String>> listData = new ArrayList<>();
			while(resultSet.next()){
				Map<String, String> resultMap = new HashMap<>();
				for(String showData : showFieldList) {
					for(int columnIndex=1; columnIndex <= meta.getColumnCount(); columnIndex++) {
						if(meta.getColumnName(columnIndex).equals(keyField)) {
							resultMap.put("id", resultSet.getString(columnIndex));
						} else if(meta.getColumnName(columnIndex).equals(setField)) {
							resultMap.put("setText", resultSet.getString(columnIndex));
						} 
						if(showData.equals(meta.getColumnName(columnIndex))) {
							resultMap.put(meta.getColumnName(columnIndex), resultSet.getString(columnIndex));
						}
					}
					if(resultMap.containsKey(showData)) {
						String getData = resultMap.containsKey("name") ? resultMap.get("name") + "  " : "";  
						resultMap.put("name", getData + resultMap.get(showData));
						resultMap.remove(showData);
					}
				}
				listData.add(resultMap);
			}
			connection.close();
			returnMap.put("value", listData);
		}

		return returnMap;
	}

	@Override
	public Map<String, Object> validate(String value, String masterId) throws SQLException {
		Optional<MasterDef> masterData = masterDef.findById(masterId);
		Map<String, Object> returnMap= new HashMap<String, Object>();
		if(masterData.isPresent()) {
			MasterDef masterDataValue = masterData.get();
			List<String> fetchFled = new ArrayList<>();
			String[] showField = masterDataValue.getShowFieldIds().split(",");
			for(String field: showField) {
				if(!StringUtils.isEmpty(field)) {
					fetchFled.add(field);	
				}
			}
			String keyField = masterDataValue.getKeyFieldId();
			String setField = masterDataValue.getNameFieldId();
			if(!fetchFled.contains(keyField) && !StringUtils.isEmpty(keyField) ) {
				fetchFled.add(keyField);
			}
			if(!fetchFled.contains(setField) && !StringUtils.isEmpty(setField)) {
				fetchFled.add(setField);
			}
			String[] filterField = masterDataValue.getFilterFieldIds().split(",");
			for(String field: filterField) {
				if(!fetchFled.contains(field) && !StringUtils.isEmpty(field)) {
					fetchFled.add(field);
				}
			}
			String tableShort = "master_search";
			StringBuilder builder = new StringBuilder("Select ");
			fetchFled.forEach(action -> 
			builder.append(tableShort).append(".").append(action).append(","));
			builder.deleteCharAt(builder.toString().length() - 1);
			builder.append(" from ").append(masterDataValue.getTableName()).append(" ").append(tableShort);
			List<String> showFieldList = new ArrayList<>();
			for(String field: showField) {
				if(!StringUtils.isEmpty(field)) {
					showFieldList.add(field);	
				}
			}
			if((showField.length > 0 && !StringUtils.isEmpty(value))|| filterField.length > 0) {
				builder.append(" where ").append(tableShort).append(".").append(setField).append("='").append(value).append("'");
				

			}
			Connection connection = source.getConnection();

			PreparedStatement statement = connection.prepareStatement(builder.toString());

			ResultSetMetaData meta = statement.getMetaData();

			ResultSet resultSet = statement.executeQuery();
			List<Map<String, String>> listData = new ArrayList<>();
			if(resultSet.next()){
				Map<String, String> resultMap = new HashMap<>();
				for(String showData : showFieldList) {
					for(int columnIndex=1; columnIndex <= meta.getColumnCount(); columnIndex++) {
						if(meta.getColumnName(columnIndex).equals(keyField)) {
							resultMap.put("id", resultSet.getString(columnIndex));
						} else if(meta.getColumnName(columnIndex).equals(setField)) {
							resultMap.put("setText", resultSet.getString(columnIndex));
						} 
						if(showData.equals(meta.getColumnName(columnIndex))) {
							resultMap.put(meta.getColumnName(columnIndex), resultSet.getString(columnIndex));
						}
					}
					if(resultMap.containsKey(showData)) {
						String getData = resultMap.containsKey("name") ? resultMap.get("name") + "  " : "";  
						resultMap.put("name", getData + resultMap.get(showData));
						resultMap.remove(showData);
					}
				}
				listData.add(resultMap);
			}
			connection.close();
			returnMap.put("value", listData);
		}

		return returnMap;
	}

}
