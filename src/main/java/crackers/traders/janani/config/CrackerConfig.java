package crackers.traders.janani.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import crackers.traders.janani.dao.CatagoryDao;
import crackers.traders.janani.dao.ProductDao;
import crackers.traders.janani.dao.SupplierDao;
import crackers.traders.janani.dao.querydao.QueryDao;
import crackers.traders.janani.dao.querydao.QueryDaoImpl;

@Configuration
public class CrackerConfig {
	@Autowired
	CatagoryDao catagoryDao;

	@Autowired
	ProductDao productDao;

	@Autowired
	SupplierDao supplierDao;
	
	@Bean
	QueryDao queryDao() {
		System.out.println("Hiiii");
		return new QueryDaoImpl();
	}

}
