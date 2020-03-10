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
@Table(name="supplier_mst")
@AllArgsConstructor
@NoArgsConstructor
public class SupplierMst {
	
	@Id
	@Column(name="supplier_id")
	public UUID supplierId;
	
	@Column(name="supplier_name")
	public String supplierName;
	
	@Column(name="supplier_short_name")
	public String supplierShortName;
	
	@Column(name="supplier_address")
	public String supplierAddress;
	
	@Column(name="supplier_city")
	public String supplierCity;
	
	@Column(name="supplier_pincode")
	public int supplierPincode;
	
	@Column(name="supplier_phoneno")
	public int supplierPhoneno;
	
	@Column(name="supplier_email")
	public String supplierEmail;
	
	@Column(name="supplier_tin")
	public String supplierTin;
	
	@Column(name="supplier_cst")
	public String supplierCst;
	
	@Column(name="supplier_pan")
	public String supplierPan;
	
	@Column(name="created_user")
	public String createdUser;
	
	@Column(name="created_date")
	public ZonedDateTime createdDate;
	
	@Column(name="updated_user")
	public String updatedUser;
	
	@Column(name="updated_date")
	public ZonedDateTime updatedDate;
	
}
