package com.cgi.uswest.chimpls.portalweb.objects;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Episode {

	private String idepsd;
	private String idprsn;
	private BigDecimal amrate;
	private String idprvdorg;
	private Timestamp dtbgn;
	private Timestamp dtend;
	private Timestamp dtrmvl;
	private String flrmvl;
	private String idcase;
	
	Episode() {}

	public Episode(String idepsd, String idprsn, BigDecimal amrate, String idprvdorg,
			Timestamp dtbgn, Timestamp dtend, Timestamp dtrmvl, String flrmvl, String idcase) {
		super();
		this.idepsd = idepsd;
		this.idprsn = idprsn;
		this.amrate = amrate;
		this.idprvdorg = idprvdorg;
		this.dtbgn = dtbgn;
		this.dtend = dtend;
		this.dtrmvl = dtrmvl;
		this.flrmvl = flrmvl;
		this.idcase = idcase;
	}
	
	public String getIdepsd() {
		return idepsd;
	}
	public void setIdEpsd(String idepsd) {
		this.idepsd = idepsd;
	}
	public String getIdprsn() {
		return idprsn;
	}
	public void setIdprsn(String idprsn) {
		this.idprsn = idprsn;
	}
	public BigDecimal getAmrate() {
		return amrate;
	}
	public void setAmrate(BigDecimal amrate) {
		this.amrate = amrate;
	}

	public String getIdprvdorg() {
		return idprvdorg;
	}

	public void setIdprvdorg(String idprvdorg) {
		this.idprvdorg = idprvdorg;
	}

	public Timestamp getDtbgn() {
		return dtbgn;
	}

	public void setDtbgn(Timestamp dtbgn) {
		this.dtbgn = dtbgn;
	}

	public Timestamp getDtend() {
		return dtend;
	}

	public void setDtend(Timestamp dtend) {
		this.dtend = dtend;
	}

	public Timestamp getDtrmvl() {
		return dtrmvl;
	}

	public void setDtrmvl(Timestamp dtrmvl) {
		this.dtrmvl = dtrmvl;
	}

	public String getFlrmvl() {
		return flrmvl;
	}

	public void setFlrmvl(String flrmvl) {
		this.flrmvl = flrmvl;
	}

	public String getIdcase() {
		return idcase;
	}

	public void setIdcase(String idcase) {
		this.idcase = idcase;
	}	
	
}
