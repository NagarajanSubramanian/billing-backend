package crackers.traders.janani.entity;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class ParamEntity {
	
	private String mode;
	
	private UUID catagoryId;
	
	private String catagoryName;
	
	private int catagoryShort;
	
	private String catagoryCommodityCode;
	
	private String catagoryCst;
	
	private String catagoryVat;
	
	private String createdUser;
	
	private ZonedDateTime createdDate;
	
	private String updatedUser;
	
	private ZonedDateTime updatedDate;
	
	
	public UUID supplierId;
	
	public String supplierName;
	
	public String supplierShortName;
	
	public String supplierAddress;
	
	public String supplierCity;
	
	public int supplierPincode;
	
	public int supplierPhoneno;
	
	public String supplierEmail;
	
	public String supplierTin;
	
	public String supplierCst;
	
	public String supplierPan;
	
	public List<String> searchField;
	
	public int offset;
	
	public int size;
	
}
