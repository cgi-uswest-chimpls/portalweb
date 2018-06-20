package com.cgi.uswest.chimpls.portalweb.objects;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class ProviderDetail {


	
	private BigDecimal id_provider_org;
	private String nm_prvd;
	private String tx_license;
	private String tximagelink;
	
	ProviderDetail() {}

	public ProviderDetail(BigDecimal id_provider_org, String nm_prvd, String tx_license,
			String tximagelink) {
		super();
		this.id_provider_org = id_provider_org;
		this.nm_prvd = nm_prvd;
		this.tx_license = tx_license;
		this.tximagelink = tximagelink;
	}
	
	public BigDecimal getId_provider_org() {
	return id_provider_org;
	}
	public void setId_provider_org(BigDecimal id_provider_org) {
	this.id_provider_org = id_provider_org;
	}
	public String getNm_prvd() {
	return nm_prvd;
	}
	public void setNm_prvd(String nm_prvd) {
	this.nm_prvd = nm_prvd;
	}
	public String getTx_license() {
	return tx_license;
	}
	public void setTx_license(String tx_license) {
	this.tx_license = tx_license;
	}

	public String getTximagelink() {
		return tximagelink;
	}

	public void setTximagelink(String tximagelink) {
		this.tximagelink = tximagelink;
	}	
	
}
