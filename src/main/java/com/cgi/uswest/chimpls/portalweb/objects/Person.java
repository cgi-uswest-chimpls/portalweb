package com.cgi.uswest.chimpls.portalweb.objects;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

public class Person {
	
	private String idprsn;
	private String nmfrst;
	private String nmlst;
	private Timestamp dtbrth;
	private BigDecimal qtage;
	private String tximagelink;
	
	private String gender;
	private String uscitizen;
	private String birthplace;
	private String maritalstat;
	private String religion;
	private String primarylang;
	private String secondlang;
	
	Person() {}
	
	public Person(String idprsn, String nmfrst, String nmlst, Timestamp dtbrth, BigDecimal qtage,
			String tximagelink) {
		super();

		this.idprsn = idprsn;
		this.nmfrst = nmfrst;
		this.nmlst = nmlst;
		this.dtbrth = dtbrth;
		this.qtage = qtage;
		this.tximagelink = tximagelink;

		this.gender = gender;
		this.uscitizen = uscitizen;
		this.birthplace = birthplace;
		this.maritalstat = maritalstat;
		this.religion = religion;
		this.primarylang = primarylang;
		this.secondlang = secondlang;
	}

	public String getIdprsn() {
		return idprsn;
	}

	public void setIdprsn(String idprsn) {
		this.idprsn = idprsn;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUscitizen() {
		return uscitizen;
	}

	public void setUscitizen(String uscitizen) {
		this.uscitizen = uscitizen;
	}

	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	public String getMaritalstat() {
		return maritalstat;
	}

	public void setMaritalstat(String maritalstat) {
		this.maritalstat = maritalstat;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getPrimarylang() {
		return primarylang;
	}

	public void setPrimarylang(String primarylang) {
		this.primarylang = primarylang;
	}

	public String getSecondlang() {
		return secondlang;
	}

	public void setSecondlang(String secondlang) {
		this.secondlang = secondlang;
	}
	
}
