package com.cgi.uswest.chimpls.portalweb.objects;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

public class Meeting {

	private String id;
	
	private BigDecimal idmeeting;
	private BigDecimal idprsn;
	private BigDecimal cdtype;
	private String txtype;
	private Timestamp dtstart;
	private Timestamp dtend;
	private String txlocation;
	private String current;
	
	Meeting() {}
	
	public Meeting(String id, BigDecimal idmeeting, BigDecimal idprsn, BigDecimal cdtype, String txtype,
			Timestamp dtstart, Timestamp dtend, String txlocation, String current) {
		
		super();
		this.id = id;
		this.idmeeting = idmeeting;
		this.idprsn = idprsn;
		this.cdtype = cdtype;
		this.txtype = txtype;
		this.dtstart = dtstart;
		this.dtend = dtend;
		this.txlocation = txlocation;
		this.current = current;
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getIdmeeting() {
		return idmeeting;
	}

	public void setIdmeeting(BigDecimal idmeeting) {
		this.idmeeting = idmeeting;
	}

	public BigDecimal getIdprsn() {
		return idprsn;
	}

	public void setIdprsn(BigDecimal idprsn) {
		this.idprsn = idprsn;
	}

	public BigDecimal getCdtype() {
		return cdtype;
	}

	public void setCdtype(BigDecimal cdtype) {
		this.cdtype = cdtype;
	}

	public String getTxtype() {
		return txtype;
	}

	public void setTxtype(String txtype) {
		this.txtype = txtype;
	}

	public Timestamp getDtstart() {
		return dtstart;
	}

	public void setDtstart(Timestamp dtstart) {
		this.dtstart = dtstart;
	}

	public Timestamp getDtend() {
		return dtend;
	}

	public void setDtend(Timestamp dtend) {
		this.dtend = dtend;
	}

	public String getTxlocation() {
		return txlocation;
	}

	public void setTxlocation(String txlocation) {
		this.txlocation = txlocation;
	}

	public String getCurrent() {
		return current;
	}

	public void setCurrent(String current) {
		this.current = current;
	}
	
}
