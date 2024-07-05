package com.example.learning.Dao;

import com.example.learning.commons.AbstractDao;
import com.example.learning.models.SunDataRCVModel;
import org.springframework.stereotype.Repository;

@Repository("solarSunRCVDao")
public class SolarSunRCVDao  extends AbstractDao {
	public void insertSolarSunRCV(SunDataRCVModel param) {
		insert ("insertSolarSunRCV", param);
	}
}
