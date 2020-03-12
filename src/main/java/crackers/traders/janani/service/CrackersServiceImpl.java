package crackers.traders.janani.service;

import java.time.ZonedDateTime;
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

import crackers.traders.janani.dao.CatagoryDao;
import crackers.traders.janani.dao.ProductDao;
import crackers.traders.janani.dao.SupplierDao;
import crackers.traders.janani.dao.querydao.QueryDao;
import crackers.traders.janani.entity.ParamEntity;
import crackers.traders.janani.table.CatagoryMst;
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
	public Map<String, Object> getAllData() {
		Map<String, Object> allDataMap = new HashMap<>();
		allDataMap.put("catagory", catagoryDao.findAll());
		allDataMap.put("supplier", supplierDao.findAll());
		allDataMap.put("product", productDao.findAll());
		return allDataMap;
	}
	
	@Override
	public Object insertCatagoryData(ParamEntity entity) {
		CatagoryMst catagoryEntity = new CatagoryMst();
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
				return "success";
			} else {
				return "exist";
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
				return "success";
			} else {
				return "notexist";
			}
		} else {
			return catagoryDao.findByCatagoryId(entity.getCatagoryId());
		}
	}
	
	@Override
	public Object insertSupplierData(ParamEntity entity) {
		if(entity.getMode().equals("add")) {
			boolean existFlag = Objects.nonNull(supplierDao.findBySupplierName(entity.getSupplierName()));
			if(existFlag) {
				return "exist";
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
				return "success";
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
				return "success";
			} else {
				return "notExist";
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
	public List<SupplierMst> searchSupplierData(String supplierName, List<String> searchField) {
		return queryDao.searchSupplierData(supplierName, searchField);
	}	
}
