package crackers.traders.janani.service;

import java.util.List;
import java.util.Map;

import crackers.traders.janani.entity.ParamEntity;
import crackers.traders.janani.table.CatagoryMst;
import crackers.traders.janani.table.SupplierMst;

public interface CrackersService {

	Map<String, Object> getAllData();

	Object insertCatagoryData(ParamEntity entity);

	Object insertSupplierData(ParamEntity entity);

	List<CatagoryMst> searchCatagoryData(String catagoryName);

	List<SupplierMst> searchSupplierData(String supplierName);

}
