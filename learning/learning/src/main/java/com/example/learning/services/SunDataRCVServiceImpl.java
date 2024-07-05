package com.example.learning.services;

import com.example.learning.BaseException;
import com.example.learning.Dao.SunDataRCVDao;
import com.example.learning.models.SunDataRCVModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SunDataRCVService")
public class SunDataRCVServiceImpl implements SunDataRCVService{

	@Autowired
	private SunDataRCVDao sunDataRCVDao;
	@Override
	public List<SunDataRCVModel> getSunDataRCVList (SunDataRCVModel param) throws BaseException {
		try {
			return sunDataRCVDao.getSunDataRCVList(param);
		} catch (Exception e) {
			throw new BaseException(e);
		}
	}
}
