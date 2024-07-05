package com.example.learning.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@Data
@EqualsAndHashCode(callSuper=false)
public class SolarEnergyRCVModel {
	private long rtuEnrgyIdx;		// 에너지설비 번호
	private String imei;			// IMEI
	private String enrgy;			// 에너지타입
	private String machn;			// 설비
	private String imsi;			// IMSI
	private String multi;			// MULTI
	private int    period;			// 반복호출 간격
	private String fwVer;			// 펌웨어 버전
	private String errCode;		// 수신DATA 에러코드
	private int comErr;			// 통신에러상태
	private String comStatus;		// 통신상태
    private RtuResModel rtuResModel;
	private String data;			// RTU로부터 수신DATA
	private String ipAddr;			// RTU IP ADDRESS
	private SunDataRCVModel sunDataRCVModel;

	private Map<String,String> header;	// RTU 수신DATA HEADER정보
	private String originalData;		// RTU 수신 전체 DATA
	private int responseCode;			// REMS로부터 받은 수신코드

	public String getCtn() {
		String ctn = null;
		if(this.imsi!=null && this.imsi.length()>=5 && this.imsi.startsWith("45006")) {
			ctn = "0"+this.imsi.substring(5);
		}
		return ctn;
	}
}
