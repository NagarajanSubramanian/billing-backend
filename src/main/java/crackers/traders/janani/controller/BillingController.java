package crackers.traders.janani.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import crackers.traders.janani.entity.ParamEntity;
import crackers.traders.janani.service.CrackersService;
import crackers.traders.janani.table.CatagoryMst;
import crackers.traders.janani.table.SupplierMst;

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
	public Object insertCatagory(@RequestBody ParamEntity entity) {
			return crackerService.insertCatagoryData(entity);
	}
	
	@RequestMapping("/insertSupplier")
	@ResponseBody
	public Object insertSupplier(@RequestBody ParamEntity entity) {
			return crackerService.insertSupplierData(entity);
	}
	
	@RequestMapping("/searchCatgoryData")
	@ResponseBody
	public List<CatagoryMst> searchCatgoryData(@RequestBody ParamEntity entity) {
		return crackerService.searchCatagoryData(entity.getCatagoryName(), entity.getSearchField());
	}
	
	@RequestMapping("/searchSupplierData")
	@ResponseBody
	public List<SupplierMst> searchSupplierData(@RequestBody ParamEntity entity) {
		return crackerService.searchSupplierData(entity.getSupplierName(), entity.getSearchField());
	}
}
