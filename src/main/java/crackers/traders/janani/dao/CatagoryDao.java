package crackers.traders.janani.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import crackers.traders.janani.table.CatagoryMst;

@Repository
public interface CatagoryDao extends JpaRepository<CatagoryMst, String>{
	CatagoryMst findByCatagoryName(String catagoryName);

	CatagoryMst findByCatagoryId(UUID catagoryId);

	@Query("select new crackers.traders.janani.table.CatagoryMst(catgory.catagoryId,catgory.catagoryName,catgory.catagoryShort,catgory.catagoryCommodityCode,catgory.catagoryCst,catgory.catagoryVat,catgory.createdUser,catgory.createdDate,catgory.updatedUser,catgory.updatedDate) from CatagoryMst catgory where lower(catgory.catagoryName) like lower(concat('%', ?1, '%')) or lower(catgory.catagoryCommodityCode) like lower(concat('%', ?1, '%'))")
	List<CatagoryMst> searchCatagoryData(String name);
}
