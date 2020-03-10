package crackers.traders.janani.table;

import java.time.ZonedDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="catagory_mst")
@AllArgsConstructor
@NoArgsConstructor
public class CatagoryMst {
	
	@Id
	@Column(name="catagory_id")
	public UUID catagoryId;
	
	@Column(name="catagory_name")
	public String catagoryName;
	
	@Column(name="catagory_short")
	public int catagoryShort;
	
	@Column(name="catagory_commodity_code")
	public String catagoryCommodityCode;
	
	@Column(name="catagory_cst")
	public String catagoryCst;
	
	@Column(name="catagory_vat")
	public String catagoryVat;
	
	@Column(name="created_user")
	public String createdUser;
	
	@Column(name="created_date")
	public ZonedDateTime createdDate;
	
	@Column(name="updated_user")
	public String updatedUser;
	
	@Column(name="updated_date")
	public ZonedDateTime updatedDate;
	
	
}
