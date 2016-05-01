package com.xdidian.keryhu.useraccount.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage {
	
	private int code;
	private String message;
	
	

}
