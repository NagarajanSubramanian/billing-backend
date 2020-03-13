package crackers.traders.janani.dao.querydao;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import crackers.traders.janani.table.CatagoryMst;
import crackers.traders.janani.table.SupplierMst;

public interface QueryDao {

	List<CatagoryMst> searchCatagoryData(String value, List<String> searchField);

	List<SupplierMst> searchSupplierData(String value, List<String> searchField);

	Map<String, Object> masterSearch(String value, List<String> searchField, int offset, int size) throws JsonMappingException, JsonProcessingException;

}
