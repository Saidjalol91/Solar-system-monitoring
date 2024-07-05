package com.example.learning.services;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.learning.Dao.SolarSunRCVDao;
import com.example.learning.libraries.HttpCall;
import com.example.learning.models.RtuResModel;
import com.example.learning.models.SolarEnergyRCVModel;
import com.example.learning.models.SunDataRCVModel;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import java.time.LocalDate;
import java.time.LocalTime;

import java.io.IOException;
import java.io.InputStream;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Enumeration;
import java.util.logging.Logger;


@Service
public class SolarEnergyService {

	@Resource(name = "solarSunRCVDao")
	private SolarSunRCVDao solarSunRCVDao;
	Logger log = Logger.getLogger(String.valueOf (this.getClass()));
	@Autowired
	RtuDbService rtuDbService;
	public Map<String, String> receiveData(Map<String, Object> param, ModelMap model, HttpServletRequest request,
										   HttpServletResponse response) throws Exception {
		Map<String, String> resultMap = new HashMap<String, String> ();
		Map<String, String> statusMap = new HashMap<String, String>();

		SolarEnergyRCVModel solarEnergyRCVModel = null;
		solarEnergyRCVModel = receiveDataFromRTU(request);


		RtuResModel rtuResModel = (solarEnergyRCVModel.getRtuResModel() == null ? new RtuResModel() : solarEnergyRCVModel.getRtuResModel());
		solarEnergyRCVModel.setRtuResModel(rtuResModel);


		HttpCall http = new HttpCall();
		http.setTimeout(10 * 1000);

		rtuResModel.setSuccess(true);
		rtuResModel.setFW_UPDATE(0);
		rtuResModel.setFW_URL("");
		rtuResModel.setRESET(0);

		System.out.println (solarEnergyRCVModel.getSunDataRCVModel ());
		String iMEI = solarEnergyRCVModel.getSunDataRCVModel ().getIMEI ();


		solarSunRCVDao.insertSolarSunRCV(solarEnergyRCVModel.getSunDataRCVModel ());

		try {
			//rtuDbService.saveMessageData(solarEnergyRCVModel);
		} catch (Exception e) {
			System.out.println ("The process is not complete properly");
		}
		log.info("#######  Receive End.");

		return resultMap;
	}

	@SuppressWarnings("unchecked")
	protected SolarEnergyRCVModel receiveDataFromRTU(HttpServletRequest request) throws IOException, IllegalAccessException {

		SolarEnergyRCVModel solarEnergyRCVModel = null;
		String rcvBodyString = getBodyString (request);
		Map<String, String> rcvHeaderMap = getHeaderMap (request);

		Map<String, Object> rcvBodyMap = (Map<String, Object>) jsonToObject(rcvBodyString);
		System.out.println (rcvBodyString);

		solarEnergyRCVModel = new SolarEnergyRCVModel ();

		solarEnergyRCVModel.setData ((String) rcvBodyMap.get ("DATA"));
		solarEnergyRCVModel.setImei ((String) rcvBodyMap.get ("IMEI"));

		solarEnergyRCVModel.setImsi ((String) rcvBodyMap.get ("IMSI"));
		solarEnergyRCVModel.setComErr ((int) rcvBodyMap.get ("COM_ERR"));
		solarEnergyRCVModel.setPeriod ((int) rcvBodyMap.get ("PERIOD"));

		String data = (String) rcvBodyMap.get ("DATA");



		SunDataRCVModel sunDataRCVModel = new SunDataRCVModel ();
		sunDataRCVModel.setIMEI (solarEnergyRCVModel.getImei ());

		sunDataRCVModel.setCurrentDate (LocalDate.now().toString ());
		sunDataRCVModel.setCurrentTime (LocalTime.now ().truncatedTo(ChronoUnit.SECONDS)
				.format(DateTimeFormatter.ISO_LOCAL_TIME));
		sunDataRCVModel.setRtuEnergySource (data.length () >= 4 ? data.substring(2, 4) : "");   //태양관
		sunDataRCVModel.setEnergyType (data.length () >= 6 ? data.substring(4, 6) : "");        //energy type 단산 삼상
		sunDataRCVModel.setInverter_Id (data.length () >= 8 ? data.substring(6, 8) : "");       // invereter code
		sunDataRCVModel.setCom_status (data.length () >= 10 ? data.substring(8, 10) : "");       // error code

		//단산 data converting
		if(sunDataRCVModel.getEnergyType ().equals ("01")){
			sunDataRCVModel.setOne_vlt_PV (data.length () >= 62 ? Integer.parseInt (data.substring(10, 14), 16) : 0);
			System.out.println (data.substring (8,12));
			sunDataRCVModel.setOne_cur_PV (data.length () >= 62 ? Integer.parseInt (data.substring(14, 18), 16) : 0);
			sunDataRCVModel.setOne_PV_Out (data.length () >= 62 ? Integer.parseInt (data.substring(18, 22), 16) : 0);
			sunDataRCVModel.setOne_sys_V (data.length () >= 62 ? Integer.parseInt (data.substring(22, 26), 16) : 0);
			sunDataRCVModel.setOne_sys_Cur (data.length () >= 62 ? Integer.parseInt (data.substring(26, 30), 16) : 0);
			sunDataRCVModel.setOne_Cur_Out (data.length () >= 62 ? Integer.parseInt (data.substring(30, 34), 16) : 0);
			sunDataRCVModel.setOne_Pw_Fc (data.length () >= 62 ? (Integer.parseInt (data.substring(34, 38), 16) / 10) : 0);
			sunDataRCVModel.setOne_Frq (data.length () >= 62 ? (Integer.parseInt (data.substring(38, 42), 16) / 10) : 0);
			sunDataRCVModel.setTtl_Gnr_Pwr (data.length () >= 62 ? Integer.parseInt (data.substring(42, 58), 16) : 0);
			sunDataRCVModel.setFail_Sts (data.length () >= 62 ? Integer.parseInt (data.substring(58, 62), 16) : 0);

		}
		//삼상 data converting
		else{
			sunDataRCVModel.setThree_vlt_PV (data.length () >= 86 ? Integer.parseInt (data.substring(10, 14), 16) : 0);
			sunDataRCVModel.setThree_cur_PV (data.length () >= 86 ? Integer.parseInt (data.substring(14, 18), 16) : 0);
			sunDataRCVModel.setThree_out_PV (data.length () >= 86 ? Integer.parseInt (data.substring(18, 26), 16) : 0);

			sunDataRCVModel.setThree_R_V (data.length () >= 86 ? Integer.parseInt (data.substring(26, 30), 16) : 0);
			sunDataRCVModel.setThree_S_V (data.length () >= 86 ? Integer.parseInt (data.substring(30, 34), 16) : 0);
			sunDataRCVModel.setThree_T_V (data.length () >= 86 ? Integer.parseInt (data.substring(34, 38), 16) : 0);

			sunDataRCVModel.setThree_R_C (data.length () >= 86 ? Integer.parseInt (data.substring(38, 42), 16) : 0);
			sunDataRCVModel.setThree_S_C (data.length () >= 86 ? Integer.parseInt (data.substring(42, 46), 16) : 0);
			sunDataRCVModel.setThree_T_C (data.length () >= 86 ? Integer.parseInt (data.substring(46, 50), 16) : 0);

			sunDataRCVModel.setThree_Cur_Out (data.length () >= 86 ? Integer.parseInt (data.substring(50, 58), 16) : 0);
			sunDataRCVModel.setThree_Pw_Fc (data.length () >= 86 ? (Integer.parseInt (data.substring(58, 62), 16) / 10) : 0);
			sunDataRCVModel.setThree_Frq (data.length () >= 86 ? (Integer.parseInt (data.substring(62, 66), 16) / 10) : 0);
			sunDataRCVModel.setTtl_Gnr_Pwr (data.length () >= 86 ? Integer.parseInt (data.substring(66, 82), 16) : 0);

			sunDataRCVModel.setFail_Sts (data.length () >= 86 ? Integer.parseInt (data.substring(82, 86), 16) : 0);

		}
		solarEnergyRCVModel.setSunDataRCVModel (sunDataRCVModel);

		return solarEnergyRCVModel;
	}

	// jsonni object qib beradigan functisa
	public static Object jsonToObject(String json) throws JsonProcessingException{
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		Object obj = mapper.readValue(json, new TypeReference<Object> () {
		});
		return obj;
	}

	public Map<String, String> getHeaderMap(HttpServletRequest request) {
		Map<String, String> headerMap = new HashMap<String, String>();

		Enumeration<String> headerEnumer = request.getHeaderNames();
		while (headerEnumer.hasMoreElements()) {
			String key = headerEnumer.nextElement();
			String value = request.getHeader(key);
			if (key == null || "".equals(key.trim()))
				continue;
			headerMap.put(key, value);
		}
		return headerMap;
	}
	public String getBodyString(HttpServletRequest request) throws IOException {
		StringBuffer body = new StringBuffer();
		int len = -1;
		byte[] b = new byte[1024];

		InputStream is = request.getInputStream();
		while ((len = is.read(b)) >= 0) {
			body.append(new String(b, 0, len));
		}
		is.close();
		return body.toString();
	}


}
