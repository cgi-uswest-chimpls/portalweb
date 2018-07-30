package com.cgi.uswest.chimpls.portalweb.objects;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Member {

	private String idprsn;
	private String idprvdorg;
	private String flinhome;
	private String nmfrst;
	private String nmlst;
	private Timestamp dtbrth;
	private BigDecimal qtage;
	private String tximagelink;
	
	Member() {}
	
	public Member(String idprsn, String idprvdorg, String flinhome, String nmfrst,
			String nmlst, Timestamp dtbrth, BigDecimal qtage, String tximagelink) {
		
		super();
		this.idprsn = idprsn;
		this.idprvdorg = idprvdorg;
		this.flinhome = flinhome;
		this.nmfrst = nmfrst;
		this.nmlst = nmlst;
		this.dtbrth = dtbrth;
		this.qtage = qtage;
		this.tximagelink = tximagelink;
		
	}

	public String getIdprsn() {
		return idprsn;
	}

	public void setIdprsn(String idprsn) {
		this.idprsn = idprsn;
	}

	public String getIdprvdorg() {
		return idprvdorg;
	}

	public void setIdprvdorg(String idprvdorg) {
		this.idprvdorg = idprvdorg;
	}

	public String getFlinhome() {
		return flinhome;
	}

	public void setFlinhome(String flinhome) {
		this.flinhome = flinhome;
	}

	public String getNmfrst() {
		return nmfrst;
	}

	public void setNmfrst(String nmfrst) {
		this.nmfrst = nmfrst;
	}

	public String getNmlst() {
		return nmlst;
	}

	public void setNmlst(String nmlst) {
		this.nmlst = nmlst;
	}

	public Timestamp getDtbrth() {
		return dtbrth;
	}

	public void setDtbrth(Timestamp dtbrth) {
		this.dtbrth = dtbrth;
	}

	public BigDecimal getQtage() {
		return qtage;
	}

	public void setQtage(BigDecimal qtage) {
		this.qtage = qtage;
	}

	public String getTximagelink() {
		return tximagelink;
	}

	public void setTximagelink(String tximagelink) {
		this.tximagelink = tximagelink;
	}
	
	
	
}
