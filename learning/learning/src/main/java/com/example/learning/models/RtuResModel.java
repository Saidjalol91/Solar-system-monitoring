package com.example.learning.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RtuResModel {
	private boolean success = false;
	private int     FW_UPDATE = 0;
	private String  FW_URL;
	private int     RESET = 0;
}
