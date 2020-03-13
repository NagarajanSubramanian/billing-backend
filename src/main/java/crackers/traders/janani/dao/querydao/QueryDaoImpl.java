package crackers.traders.janani.dao.querydao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import crackers.traders.janani.table.CatagoryMst;
import crackers.traders.janani.table.SupplierMst;


public class QueryDaoImpl implements QueryDao{

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	DataSource source;

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
			String[] splitedData = value.split(" +");
			TypedQuery<CatagoryMst> query = entityManager.createQuery(builder.toString(), CatagoryMst.class);
			for(int valueIncrement=0; valueIncrement<splitedData.length; valueIncrement++) {
				query.setParameter(valueIncrement, splitedData[valueIncrement]);
			}
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
			String[] splitedData = value.split(" +");
			TypedQuery<SupplierMst> query = entityManager.createQuery(builder.toString(), SupplierMst.class);
			for(int valueIncrement=0; valueIncrement<splitedData.length; valueIncrement++) {
				query.setParameter(valueIncrement, splitedData[valueIncrement]);
			}
			return query.getResultList();
		}
	}

	private void buildLikeCondition(String value, List<String> searchField, String tableShort, StringBuilder builder) {
		String[] splitedData = value.split(" +");
		for(int valueIncrement=0; valueIncrement<splitedData.length; valueIncrement++) {
			for(int searchIncrement =0 ; searchIncrement< searchField.size() ; searchIncrement++) {
				if(searchIncrement == 0) {
						builder.append(" (lower(cast(").append(tableShort).append(".").append(searchField.get(searchIncrement))
						.append(" as text)) like concat('%', lower(?").append(valueIncrement).append("), '%')");						
				} else {
						builder.append(" or lower(cast(").append(tableShort).append(".").append(searchField.get(searchIncrement))
						.append(" as text)) like concat('%', lower(?").append(valueIncrement).append("), '%')");						
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
	public Map<String, Object> masterSearch(String value, List<String> searchField, int offset, int size) throws JsonMappingException, JsonProcessingException {
		String catagoryQuery = "select new crackers.traders.janani.table.CatagoryMst(catgory.catagoryId,catgory.catagoryName,"
				+ "catgory.catagoryShort,catgory.catagoryCommodityCode,catgory.catagoryCst,catgory.catagoryVat,catgory.createdUser,"
				+ "catgory.createdDate,catgory.updatedUser,catgory.updatedDate) from CatagoryMst catgory";
		Map<String, Object> returnMap= new HashMap<String, Object>();
		
		List<CatagoryMst> result;
		ObjectMapper oMapper = new ObjectMapper();
		if(StringUtils.isEmpty(value)) {
			returnMap.put("count", entityManager.createQuery(catagoryQuery, CatagoryMst.class).getResultList().size());
			result = entityManager.createQuery(catagoryQuery, CatagoryMst.class).setFirstResult(offset * size).setMaxResults(size).getResultList();
		} else {
			StringBuilder builder = new StringBuilder(catagoryQuery).append(" where ");
			buildLikeCondition(value, searchField, "catgory", builder);
			String[] splitedData = value.split(" +");
			TypedQuery<CatagoryMst> query = entityManager.createQuery(builder.toString(), CatagoryMst.class);
			for(int valueIncrement=0; valueIncrement<splitedData.length; valueIncrement++) {
				query.setParameter(valueIncrement, splitedData[valueIncrement]);
			}
			returnMap.put("count", query.getResultList().size());
			result = query.setFirstResult(offset).setMaxResults(size * size).getResultList();

		}
		List<Map<String, Object>> resultMap = oMapper.readValue(oMapper.writeValueAsString(result), new TypeReference<List<Map<String, Object>>>() {});
		resultMap.stream().forEach(data -> {
			data.put("id", data.get("catagoryId"));
			data.put("name", data.get("catagoryName"));
		});
		returnMap.put("value", resultMap);
		return returnMap;
	}
}
