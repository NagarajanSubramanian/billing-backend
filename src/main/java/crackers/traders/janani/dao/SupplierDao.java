package crackers.traders.janani.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import crackers.traders.janani.table.SupplierMst;

@Repository
public interface SupplierDao extends JpaRepository<SupplierMst, String>{
	SupplierMst findBySupplierName(String catagoryName);
	
	SupplierMst findBySupplierId(UUID catagoryId);
	
	@Query("select new crackers.traders.janani.table.SupplierMst(supply.supplierId,supply.supplierName,supply.supplierShortName,supply.supplierAddress,supply.supplierCity,supply.supplierPincode, supply.supplierPhoneno, supply.supplierEmail,supply.supplierTin,supply.supplierCst,supply.supplierPan,supply.createdUser,supply.createdDate,supply.updatedUser,supply.updatedDate) from SupplierMst supply where lower(supply.supplierName) like lower(concat('%', ?1, '%')) or lower(supply.supplierShortName) like lower(concat('%', ?1, '%')) or lower(supply.supplierCity) like lower(concat('%', ?1, '%'))")
	List<SupplierMst> searchSupplierData(String name);
}
