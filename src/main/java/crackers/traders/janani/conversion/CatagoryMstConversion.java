package crackers.traders.janani.conversion;

import java.time.ZonedDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;

import lombok.AllArgsConstructor;

public interface CatagoryMstConversion {
	
	@Value("#{target.catagoryId}")
	UUID getCatagoryId();
	
	@Value("#{target.catagoryName}")
	String getCatagoryName();
	
	@Value("#{target.catagoryShort}")
	Integer getCatagoryShort();
	
	@Value("#{target.catagoryCommodityCode}")
	String getCatagoryCommodityCode();
	
	@Value("#{target.catagoryCst}")
	String getCatagoryCst();
	
	@Value("#{target.catagoryVat}")
	String getCatagoryVat();
	
	@Value("#{target.createdUser}")
	String getCreatedUser();
	
	@Value("#{target.createdDate}")
	ZonedDateTime getCreatedDate();
	
	@Value("#{target.updatedUser}")
	String getUpdatedUser();
	
	@Value("#{target.updatedDate}")
	ZonedDateTime getUpdatedDate();
}
