package crackers.traders.janani.dao.querydao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import crackers.traders.janani.table.CatagoryMst;
import crackers.traders.janani.table.SupplierMst;

public interface QueryDao {

	List<CatagoryMst> searchCatagoryData(String value, List<String> searchField);

	List<SupplierMst> searchSupplierData(String value, List<String> searchField);

	Map<String, Object> masterSearch(String value, String masterId, int offset, int size, boolean checkCount) throws SQLException;

	Map<String, Object> validate(String value, String masterId) throws SQLException;

	List<Map<String, String>> loadProductData(String searchValue, List<String> searchList) throws SQLException;

}
