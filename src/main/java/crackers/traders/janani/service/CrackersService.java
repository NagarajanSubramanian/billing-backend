package crackers.traders.janani.service;

import java.util.Map;

import crackers.traders.janani.entity.ParamEntity;

public interface CrackersService {

	Map<String, Object> getAllData();

	String insertCatagoryData(ParamEntity entity);

	boolean findByCatagoryName(String catagoryName);

}
