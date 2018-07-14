package com.cgi.uswest.chimpls.portalweb.objects;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Payment {

	
	private String idpayment;
	private Timestamp dtpayment;
	private String idprsn;
	private BigDecimal amount;
	private String idprvdorg;
	private String txpersonname;
	
	Payment() {}
	
	public Payment(String idpayment, Timestamp dtpayment, String idprsn, BigDecimal amount,
			String idprvdorg, String txpersonname) {
		
		super();
		this.idpayment = idpayment;
		this.dtpayment = dtpayment;
		this.idprsn = idprsn;
		this.amount = amount;
		this.idprvdorg = idprvdorg;
		this.txpersonname = txpersonname;
	}

	public String getIdpayment() {
		return idpayment;
	}

	public void setIdpayment(String idpayment) {
		this.idpayment = idpayment;
	}

	public Timestamp getDtpayment() {
		return dtpayment;
	}

	public void setDtpayment(Timestamp dtpayment) {
		this.dtpayment = dtpayment;
	}

	public String getIdprsn() {
		return idprsn;
	}

	public void setIdprsn(String idprsn) {
		this.idprsn = idprsn;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getIdprvdorg() {
		return idprvdorg;
	}

	public void setIdprvdorg(String idprvdorg) {
		this.idprvdorg = idprvdorg;
	}

	public String getTxpersonname() {
		return txpersonname;
	}

	public void setTxpersonname(String txpersonname) {
		this.txpersonname = txpersonname;
	}
	
}
