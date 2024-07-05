package com.example.learning.services;

import com.example.learning.Dao.SolarSunRCVDao;
import com.example.learning.models.SolarEnergyRCVModel;
import com.example.learning.models.SunDataRCVModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RtuDbService {

	@Resource(name = "solarSunRCVDao")
	private SolarSunRCVDao solarSunRCVDao;



	public  SolarSunRCVDao insertSolarSunRCVDao () {
		solarSunRCVDao.insertSolarSunRCV(new SolarEnergyRCVModel ().getSunDataRCVModel ());
		return solarSunRCVDao;
	}


}
