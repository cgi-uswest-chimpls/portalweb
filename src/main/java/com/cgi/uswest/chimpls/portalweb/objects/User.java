package com.cgi.uswest.chimpls.portalweb.objects;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class User {

	@Id
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@GeneratedValue(generator = "uuid2")
	@Column(columnDefinition = "BINARY(16)")
	private UUID id;

	
	@NotEmpty
	private String sub;
	private String email;
	private String idprvdorg;
	private String county;
	
	User() {}

	public User(String sub, String email, String idprvdorg, String county) {
		super();
		this.sub = sub;
		this.email = email;
		this.idprvdorg = idprvdorg;
		this.county = county;
	}

	public String getSub() {
		return sub;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdprvdorg() {
		return idprvdorg;
	}

	public void setIdprvdorg(String idprvdorg) {
		this.idprvdorg = idprvdorg;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}
	
}
