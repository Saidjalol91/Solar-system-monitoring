package com.example.learning.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class SunDataRCVModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	 private Long ID;
     private String rtuEnergySource;
	 private String energyType;

	 private String iMEI;
	 private String currentDate;
	 private String currentTime;

	 private String inverter_Id;
	 private String com_status;

	 private double one_vlt_PV;
	 private double one_cur_PV;

	 private double one_PV_Out;
	 private double one_sys_V;
	 private double one_sys_Cur;
	 private double one_Cur_Out;
	 private double one_Pw_Fc;
	 private int one_Frq;
	 private double ttl_Gnr_Pwr;
	 private int fail_Sts;

	 private double three_vlt_PV;
	 private double three_cur_PV;
	 private double three_out_PV;
	 private double three_R_V;
	 private double three_S_V;
	 private double three_T_V;
	 private double three_R_C;
	 private double three_T_C;
	 private double three_S_C;
	 private double three_Cur_Out;
	 private double three_Pw_Fc;
	 private double three_Frq;
}
