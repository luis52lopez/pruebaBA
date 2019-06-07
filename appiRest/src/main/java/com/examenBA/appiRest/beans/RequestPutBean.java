package com.examenBA.appiRest.beans;

public class RequestPutBean {
	private String id;
	private String[] valores;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String[] getValores() {
		return valores;
	}
	
	public void setValores(String[] valores) {
		this.valores = valores;
	}
}
