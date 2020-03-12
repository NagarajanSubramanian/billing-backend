package crackers.traders.janani.dao.querydao;

import java.util.List;

import crackers.traders.janani.table.CatagoryMst;
import crackers.traders.janani.table.SupplierMst;

public interface QueryDao {

	List<CatagoryMst> searchCatagoryData(String value, List<String> searchField);

	List<SupplierMst> searchSupplierData(String value, List<String> searchField);

}
