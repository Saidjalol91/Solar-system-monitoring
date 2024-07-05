package com.example.learning.services;

import com.example.learning.BaseException;
import com.example.learning.models.SunDataRCVModel;

import java.util.List;

public interface SunDataRCVService {
	List<SunDataRCVModel> getSunDataRCVList (SunDataRCVModel param) throws BaseException;

}
