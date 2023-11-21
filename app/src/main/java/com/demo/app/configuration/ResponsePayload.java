package com.demo.app.configuration;

import org.springframework.http.HttpStatus;

public class ResponsePayload {

	
	private boolean success;
	private String message;
	private HttpStatus ok;
	private int status;
	private Object result;
	
	public ResponsePayload() {
		
	}
	
	public ResponsePayload(String msg) {
		
		this.success = true;
		this.status = 200;
		this.message = msg;
		this.result = 	"";
		
	}
	
public ResponsePayload(String msg,Object result,HttpStatus ok,boolean success) {
				
		this.success = success;
		this.ok = ok;
		this.status = 200;
		this.message = msg;
		this.result = 	result;
		
	}


public ResponsePayload(String msg,Object result,int status,boolean success) {
	
	this.success = success;
	this.status = status;
	this.message = msg;
	this.result = 	result;
	
}


public ResponsePayload(String msg,int status,boolean success) {
	
	this.success = success;
	this.status = status;
	this.message = msg;
	
}

 public boolean isSuccess() {
	 return success;
 }

public String getMessage() {
	return message;
}

public void setMessage(String message) {
	this.message = message;
}

public int getStatus() {
	return status;
}

public void setStatus(int status) {
	this.status = status;
}

public Object getResult() {
	return result;
}

public void setResult(Object result) {
	this.result = result;
}

public void setSuccess(boolean success) {
	this.success = success;
}

 


}
