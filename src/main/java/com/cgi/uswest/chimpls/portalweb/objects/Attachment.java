package com.cgi.uswest.chimpls.portalweb.objects;

import java.math.BigDecimal;
import java.sql.Blob;

public class Attachment {

	private BigDecimal id;
	
	private String idmessage;
	
	private String filename;
	
	private Blob binaryfile;
	
	
	Attachment() {}
	
	public Attachment(BigDecimal id, String idmessage, String filename, Blob binaryfile) {
		super();
		this.id = id;
		this.idmessage = idmessage;
		this.filename = filename;
		this.binaryfile = binaryfile;
	}
	
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getIdmessage() {
		return idmessage;
	}

	public void setIdmessage(String idmessage) {
		this.idmessage = idmessage;
	}

	public Blob getBinaryfile() {
		return binaryfile;
	}

	public void setBinaryfile(Blob binaryfile) {
		this.binaryfile = binaryfile;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}
	
}
