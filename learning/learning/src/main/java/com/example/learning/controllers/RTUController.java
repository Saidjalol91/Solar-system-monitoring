package com.example.learning.controllers;

import com.example.learning.BaseException;
import com.example.learning.Dao.SolarSunRCVDao;
import com.example.learning.models.SunDataRCVModel;
import com.example.learning.services.RtuDbService;
import com.example.learning.services.SunDataRCVService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.learning.services.SolarEnergyService;

@RestController
@CrossOrigin

public class RTUController {
	@Autowired
	SolarEnergyService solarEnergyService;

	@Autowired
	SunDataRCVService sunDataRCVService;

	@RequestMapping(value = {"/api/auth/rtu/data.do"})
	public String recieveData(@RequestParam Map<String, Object> param, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		model.addAllAttributes (solarEnergyService.receiveData (param, model, request,response));
		HttpStatus statusCode = HttpStatus.OK;
		return statusCode.toString ();
	}

	@RequestMapping(value = "/api/auth/getListAllData", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Object>  getList(Model model, HttpServletRequest req, HttpServletResponse re) throws BaseException {
		Map<String, Object> result = new HashMap<String, Object> ();
		HttpStatus statusCode = HttpStatus.OK;

		SunDataRCVModel param = new SunDataRCVModel ();
		List<SunDataRCVModel> res = sunDataRCVService.getSunDataRCVList(param);

		result.put("myRecord", res);

		return new ResponseEntity<Object> (result,statusCode);
	}
}
