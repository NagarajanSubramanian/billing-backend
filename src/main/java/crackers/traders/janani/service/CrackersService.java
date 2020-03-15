package crackers.traders.janani.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import crackers.traders.janani.entity.ParamEntity;
import crackers.traders.janani.table.CatagoryMst;
import crackers.traders.janani.table.SupplierMst;

public interface CrackersService {

	Map<String, Object> getAllData() throws SQLException;

	Object insertCatagoryData(ParamEntity entity);

	Object insertSupplierData(ParamEntity entity);

	List<CatagoryMst> searchCatagoryData(String catagoryName, List<String> searchField);

	List<SupplierMst> searchSupplierData(String supplierName, List<String> searchField);

	Map<String, Object> masterSearch(String searchValue, String masterId, int offset, int size, boolean checkCount) throws SQLException;

	Map<String, Object> validate(String searchValue, String masterId) throws SQLException;

	Object insertProduct(ParamEntity entity) throws SQLException;

	List<Map<String, String>> searchProduct(String catagoryName, List<String> searchField) throws SQLException;

}
