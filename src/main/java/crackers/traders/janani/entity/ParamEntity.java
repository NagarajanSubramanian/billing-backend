package crackers.traders.janani.entity;

import java.sql.Date;

import lombok.Data;

@Data
public class ParamEntity {
	
	private String mode;
	
	private String catagoryId;
	
	private String catagoryName;
	
	private int catagoryShort;
	
	private String catagoryCommodityCode;
	
	private String catagoryCst;
	
	private String catagoryVat;
	
	private String createdUser;
	
	private Date createdDate;
	
	private String updatedUser;
	
	private Date updatedDate;
	
}
