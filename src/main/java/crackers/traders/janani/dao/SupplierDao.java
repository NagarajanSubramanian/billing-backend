package crackers.traders.janani.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import crackers.traders.janani.table.SupplierMst;

@Repository
public interface SupplierDao extends JpaRepository<SupplierMst, String>{

}
