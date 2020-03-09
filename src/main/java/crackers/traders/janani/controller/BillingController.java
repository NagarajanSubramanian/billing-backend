package crackers.traders.janani.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import crackers.traders.janani.entity.ParamEntity;
import crackers.traders.janani.service.CrackersService;

@CrossOrigin
@Controller
public class BillingController {
	
	@Autowired
	CrackersService crackerService;
	

	@RequestMapping("/getAll")
	@ResponseBody
	public Map<String, Object> getAll() {
		return crackerService.getAllData();
	}
	@RequestMapping("/insertCatagory")
	@ResponseBody
	public String insertCatagory(@RequestBody ParamEntity entity) {
		if(crackerService.findByCatagoryName(entity.getCatagoryName())) {
			return "exist";
		} else {
			return crackerService.insertCatagoryData(entity);
		}
	}
}
