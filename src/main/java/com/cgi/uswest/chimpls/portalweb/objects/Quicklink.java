package com.cgi.uswest.chimpls.portalweb.objects;

public class Quicklink {

	private String txtext;
	private String txlink;
	private String county;
	
	Quicklink() {};
	
	public Quicklink(String txtext, String txlink, String county) {
		super();
		this.txtext = txtext;
		this.txlink = txlink;
		this.county = county;
	}

	public String getTxtext() {
		return txtext;
	}

	public void setTxtext(String txtext) {
		this.txtext = txtext;
	}

	public String getTxlink() {
		return txlink;
	}

	public void setTxlink(String txlink) {
		this.txlink = txlink;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	
}
