package crackers.traders.janani.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import crackers.traders.janani.table.ProductMst;

@Repository
public interface ProductDao extends JpaRepository<ProductMst, String>{
	ProductMst findByProductCodeAndProductName(String productCode, String productName);
	ProductMst findByProductName(String productName);
	ProductMst findByProductCode(String productCode);
}
