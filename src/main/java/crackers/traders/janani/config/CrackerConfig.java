package crackers.traders.janani.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import crackers.traders.janani.dao.CatagoryDao;
import crackers.traders.janani.dao.ProductDao;
import crackers.traders.janani.dao.SupplierDao;

@ComponentScan
public class CrackerConfig {
	@Autowired
	CatagoryDao catagoryDao;

	@Autowired
	ProductDao productDao;

	@Autowired
	SupplierDao supplierDao;

}
