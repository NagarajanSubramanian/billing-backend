package crackers.traders.janani.service;

import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Persistent;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import crackers.traders.janani.dao.CatagoryDao;
import crackers.traders.janani.dao.ProductDao;
import crackers.traders.janani.dao.SupplierDao;
import crackers.traders.janani.dao.querydao.QueryDao;
import crackers.traders.janani.entity.ParamEntity;
import crackers.traders.janani.table.CatagoryMst;
import crackers.traders.janani.table.ProductMst;
import crackers.traders.janani.table.SupplierMst;

@Service
public class CrackersServiceImpl implements CrackersService{
	
	@Autowired
	CatagoryDao catagoryDao;
	
	@Autowired
	SupplierDao supplierDao;
	
	@Autowired
	ProductDao productDao;
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Autowired
	QueryDao queryDao;
	
	@Override
	public Map<String, Object> getAllData() throws SQLException {
		Map<String, Object> allDataMap = new HashMap<>();
		allDataMap.put("catagory", catagoryDao.findAll());
		allDataMap.put("supplier", supplierDao.findAll());
		allDataMap.put("product",queryDao.loadProductData("", Arrays.asList()));
		return allDataMap;
	}
	
	@Override
	public Object insertCatagoryData(ParamEntity entity) {
		CatagoryMst catagoryEntity = new CatagoryMst();
		Map<String, Object> returnMap = new HashMap<>();
		if(entity.getMode().equals("add")) {
			boolean existFlag = Objects.nonNull(catagoryDao.findByCatagoryName(entity.getCatagoryName()));
			if(!existFlag) {
				catagoryEntity.setCatagoryId(UUID.randomUUID());
				catagoryEntity.setCatagoryName(entity.getCatagoryName());
				catagoryEntity.setCatagoryCommodityCode(entity.getCatagoryCommodityCode());
				catagoryEntity.setCatagoryShort(entity.getCatagoryShort());
				catagoryEntity.setCatagoryCst(entity.getCatagoryCst());
				catagoryEntity.setCatagoryVat(entity.getCatagoryVat());
				catagoryEntity.setCreatedUser("admin");
				catagoryEntity.setUpdatedUser("admin");
				catagoryEntity.setCreatedDate(ZonedDateTime.now());
				catagoryEntity.setUpdatedDate(ZonedDateTime.now());
				catagoryDao.save(catagoryEntity);
				returnMap.put("status", "success");
				returnMap.put("catagory", queryDao.searchCatagoryData(entity.getSearchValue(), entity.getSearchField()));
				return returnMap;
			} else {
				returnMap.put("status", "exist");
				return returnMap;
			}
		} if(entity.getMode().equals("edit")) {
			CatagoryMst catagoryMst = catagoryDao.findByCatagoryId(entity.getCatagoryId());
			if(Objects.nonNull(catagoryMst)) {
				catagoryMst.setCatagoryName(entity.getCatagoryName());
				catagoryMst.setCatagoryCommodityCode(entity.getCatagoryCommodityCode());
				catagoryMst.setCatagoryShort(entity.getCatagoryShort());
				catagoryMst.setCatagoryCst(entity.getCatagoryCst());
				catagoryMst.setCatagoryVat(entity.getCatagoryVat());
				catagoryMst.setUpdatedUser("admin");
				catagoryMst.setUpdatedDate(ZonedDateTime.now());
				catagoryDao.save(catagoryMst);
				returnMap.put("status", "success");
				returnMap.put("catagory", queryDao.searchCatagoryData(entity.getSearchValue(), entity.getSearchField()));
				return returnMap;
			} else {
				returnMap.put("status", "notexist");
				return returnMap;
			}
		} else {
			return catagoryDao.findByCatagoryId(entity.getCatagoryId());
		}
	}
	
	
	@Override
	public Object insertProduct(ParamEntity entity) throws SQLException {
		ProductMst productEntity = new ProductMst();
		Map<String, Object> returnMap = new HashMap<>();
		if(entity.getMode().equals("add")) {
			boolean existFlag = Objects.nonNull(productDao.findByProductCodeAndProductName(entity.getProductCode(), entity.getProductName()));
			if(!existFlag) {
				productEntity.setProductCode(entity.getProductCode());
				productEntity.setProductName(entity.getProductName());
				productEntity.setCatagoryId(entity.getCatagoryId());
				productEntity.setSupplierId(entity.getSupplierId());
				productEntity.setProductMrp(entity.getProductMrp());
				productEntity.setProductPurchaseRate(entity.getProductPurchaseRate());
				productEntity.setProductMeasurementId(entity.getProductMeasurementId());
				productEntity.setProductQuantity(entity.getProductQuantity());
				productEntity.setProductQuantityScale(entity.getProductQuantityScale());
				productEntity.setCreatedUser("admin");
				productEntity.setUpdatedUser("admin");
				productEntity.setCreatedDate(ZonedDateTime.now());
				productEntity.setUpdatedDate(ZonedDateTime.now());
				productDao.save(productEntity);	
				returnMap.put("status", "success");
				returnMap.put("product", queryDao.loadProductData(entity.getSearchValue(), entity.getSearchField()));
				return returnMap;
			} else {
				returnMap.put("status", "exist");
				return returnMap;
			}
		} if(entity.getMode().equals("edit")) {
			ProductMst productMst = productDao.findByProductName(entity.getProductName());
			if(Objects.nonNull(productMst)) {
				productMst.setProductName(entity.getProductName());
				productMst.setCatagoryId(entity.getCatagoryId());
				productMst.setSupplierId(entity.getSupplierId());
				productMst.setProductMrp(entity.getProductMrp());
				productMst.setProductPurchaseRate(entity.getProductPurchaseRate());
				productMst.setProductMeasurementId(entity.getProductMeasurementId());
				productMst.setProductQuantity(entity.getProductQuantity());
				productMst.setProductQuantityScale(entity.getProductQuantityScale());
				productMst.setUpdatedUser("admin");
				productMst.setUpdatedDate(ZonedDateTime.now());
				productDao.save(productMst);
				returnMap.put("status", "success");
				returnMap.put("product", queryDao.loadProductData(entity.getSearchValue(), entity.getSearchField()));
				return returnMap;
			} else {
				returnMap.put("status", "notexist");
				return returnMap;
			}
		} else {
			return productDao.findByProductCode(entity.getProductCode());
		}
	}
	
	@Override
	public Object insertSupplierData(ParamEntity entity) {
		Map<String, Object> returnMap = new HashMap<>();
		if(entity.getMode().equals("add")) {
			boolean existFlag = Objects.nonNull(supplierDao.findBySupplierName(entity.getSupplierName()));
			if(existFlag) {
				returnMap.put("status", "exist");
				return returnMap;
			} else {
				SupplierMst supplierMst = new SupplierMst();
				supplierMst.setSupplierId(UUID.randomUUID());
				supplierMst.setSupplierName(entity.getSupplierName());
				supplierMst.setSupplierShortName(entity.getSupplierShortName());
				supplierMst.setSupplierAddress(entity.getSupplierAddress());
				supplierMst.setSupplierCity(entity.getSupplierCity());
				supplierMst.setSupplierPincode(entity.getSupplierPincode());
				supplierMst.setSupplierPhoneno(entity.getSupplierPhoneno());
				supplierMst.setSupplierEmail(entity.getSupplierEmail());
				supplierMst.setSupplierTin(entity.getSupplierTin());
				supplierMst.setSupplierCst(entity.getSupplierCst());
				supplierMst.setSupplierPan(entity.getSupplierPan());
				supplierMst.setCreatedUser("admin");
				supplierMst.setCreatedDate(ZonedDateTime.now());
				supplierMst.setUpdatedUser("admin");
				supplierMst.setUpdatedDate(ZonedDateTime.now());
				supplierDao.save(supplierMst);
				returnMap.put("status", "success");
				returnMap.put("supplier", queryDao.searchSupplierData(entity.getSearchValue(), entity.getSearchField()));
				return returnMap;
			} 
		} else if(entity.getMode().equals("edit")) {
			SupplierMst supplierMst = supplierDao.findBySupplierId(entity.getSupplierId());
			if(Objects.nonNull(supplierMst)) {
				supplierMst.setSupplierName(entity.getSupplierName());
				supplierMst.setSupplierShortName(entity.getSupplierShortName());
				supplierMst.setSupplierAddress(entity.getSupplierAddress());
				supplierMst.setSupplierCity(entity.getSupplierCity());
				supplierMst.setSupplierPincode(entity.getSupplierPincode());
				supplierMst.setSupplierPhoneno(entity.getSupplierPhoneno());
				supplierMst.setSupplierEmail(entity.getSupplierEmail());
				supplierMst.setSupplierTin(entity.getSupplierTin());
				supplierMst.setSupplierCst(entity.getSupplierCst());
				supplierMst.setSupplierPan(entity.getSupplierPan());
				supplierMst.setUpdatedUser("admin");
				supplierMst.setUpdatedDate(ZonedDateTime.now());
				supplierDao.save(supplierMst);
				returnMap.put("status", "success");
				returnMap.put("supplier", queryDao.searchSupplierData(entity.getSearchValue(), entity.getSearchField()));
				return returnMap;
			} else {
				returnMap.put("status", "notexist");
				return returnMap;
			}
		} else {
			return supplierDao.findBySupplierId(entity.getSupplierId());
		}

	}

	@Override
	public List<CatagoryMst> searchCatagoryData(String catagoryName, List<String> searchField) {
		return queryDao.searchCatagoryData(catagoryName, searchField);
//		return catagoryDao.searchCatagoryData(catagoryName);
	}
	
	@Override
	public List<Map<String, String>> searchProduct(String catagoryName, List<String> searchField) throws SQLException {
		return queryDao.loadProductData(catagoryName, searchField);
//		return catagoryDao.searchCatagoryData(catagoryName);
	}

	@Override
	public List<SupplierMst> searchSupplierData(String supplierName, List<String> searchField) {
		return queryDao.searchSupplierData(supplierName, searchField);
	}
	
	@Override
	public Map<String, Object> masterSearch(String searchValue, String masterId, int offset, int size, boolean checkCount) throws  SQLException {
		return queryDao.masterSearch(searchValue, masterId, offset, size, checkCount);
//		return catagoryDao.searchCatagoryData(catagoryName);
	}
	
	@Override
	public Map<String, Object> validate(String searchValue, String masterId) throws  SQLException {
		return queryDao.validate(searchValue, masterId);
//		return catagoryDao.searchCatagoryData(catagoryName);
	}
}
