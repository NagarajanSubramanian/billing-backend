package crackers.traders.janani.service;

import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crackers.traders.janani.dao.CatagoryDao;
import crackers.traders.janani.dao.ProductDao;
import crackers.traders.janani.dao.SupplierDao;
import crackers.traders.janani.entity.ParamEntity;
import crackers.traders.janani.table.CatagoryMst;

@Service
public class CrackersServiceImpl implements CrackersService{
	
	@Autowired
	CatagoryDao catagoryDao;
	
	@Autowired
	SupplierDao supplierDao;
	
	@Autowired
	ProductDao productDao;
	
	@Override
	public boolean findByCatagoryName(String catagoryName) {
		return Objects.nonNull(catagoryDao.findByCatagoryName(catagoryName));
	}

	@Override
	public Map<String, Object> getAllData() {
		Map<String, Object> allDataMap = new HashMap<>();
		allDataMap.put("catagory", catagoryDao.findAll());
		allDataMap.put("supplier", supplierDao.findAll());
		allDataMap.put("product", productDao.findAll());
		return allDataMap;
	}
	
	@Override
	public String insertCatagoryData(ParamEntity entity) {
		CatagoryMst catagoryEntity = new CatagoryMst();
		if(entity.getMode().equals("add")) {
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
		}
		return "success";
	}
}
