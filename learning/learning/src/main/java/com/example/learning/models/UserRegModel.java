package com.example.learning.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserRegModel {

	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String contact;
	private String address;
	private String roadAddress;
	private String role;
	private String project;
	private Date reg_date;
	private Date reg_update;
}
