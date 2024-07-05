package com.example.learning.Dao;

import com.example.learning.commons.AbstractDao;
import com.example.learning.models.SunDataRCVModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("SunDataRCVDao")
public class SunDataRCVDao extends AbstractDao {
	public List<SunDataRCVModel> getSunDataRCVList(SunDataRCVModel param){return selectList ("getSunDataRCVList", param);}
}
