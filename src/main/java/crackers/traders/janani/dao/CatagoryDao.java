package crackers.traders.janani.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import crackers.traders.janani.table.CatagoryMst;

@Repository
public interface CatagoryDao extends JpaRepository<CatagoryMst, String>{
	CatagoryMst findByCatagoryName(String catagoryName);
}
