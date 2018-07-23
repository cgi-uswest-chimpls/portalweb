package com.cgi.uswest.chimpls.portalweb.objects;

public class MessagesDropdownValue {

	private String idPrsn;
	private String name;
	
	MessagesDropdownValue() {}
	
	public MessagesDropdownValue(String idPrsn, String name) {
		super();
		this.idPrsn = idPrsn;
		this.name = name;
	}

	public String getIdPrsn() {
		return idPrsn;
	}

	public void setIdPrsn(String idPrsn) {
		this.idPrsn = idPrsn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
