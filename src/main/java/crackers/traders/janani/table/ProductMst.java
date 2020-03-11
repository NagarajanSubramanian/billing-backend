package crackers.traders.janani.table;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name="product_mst")
public class ProductMst {
	
	@Id
	@Column(name="product_code")
	public String productCode;
	
	@Column(name="product_name")
	public String productName;
	
	@Column(name="catagory_id")
	public String catagoryId;
	
	@Column(name="supplier_id")
	public String supplierId;
	
	@Column(name="product_mrp")
	public Float productMrp;
	
	@Column(name="product_purchase_rate")
	public Float productPurchaseRate;
	
	@Column(name="product_measurement_id")
	public int productMeasurementId;
	
	@Column(name="product_quantity")
	public int productQuantity;
	
	@Column(name="product_quantity_scale")
	public int productQuantityScale;
	
	@Column(name="created_user")
	public String createdUser;
	
	@Column(name="created_date")
	public ZonedDateTime createdDate;
	
	@Column(name="updated_user")
	public String updatedUser;
	
	@Column(name="updated_date")
	public ZonedDateTime updatedDate;
	
	
}
