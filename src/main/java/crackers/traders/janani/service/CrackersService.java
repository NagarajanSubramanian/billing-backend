package crackers.traders.janani.service;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import crackers.traders.janani.entity.ParamEntity;
import crackers.traders.janani.table.CatagoryMst;
import crackers.traders.janani.table.SupplierMst;

public interface CrackersService {

	Map<String, Object> getAllData();

	Object insertCatagoryData(ParamEntity entity);

	Object insertSupplierData(ParamEntity entity);

	List<CatagoryMst> searchCatagoryData(String catagoryName, List<String> searchField);

	List<SupplierMst> searchSupplierData(String supplierName, List<String> searchField);

	Map<String, Object> masterSearch(String catagoryName, List<String> searchField, int offset, int size) throws JsonMappingException, JsonProcessingException;

}
