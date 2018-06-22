package com.cgi.uswest.chimpls.portalweb.objects;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Assignment {

	private String id_assignment;
	private String id_prsn;
	private String id_case_prvd;
	private String cd_type;
	private String cd_role;
	private String nm_prsn;
	private Timestamp dt_start;
	private Timestamp dt_end;
	
	Assignment() {}
	
	public Assignment(String id_assignment, String id_prsn, String id_case_prvd, String cd_type,
			String cd_role, String nm_prsn, Timestamp dt_start, Timestamp dt_end) {
		super();
		this.id_assignment = id_assignment;
		this.id_prsn = id_prsn;
		this.id_case_prvd = id_case_prvd;
		this.cd_type = cd_type;
		this.cd_role = cd_role;
		this.nm_prsn = nm_prsn;
		this.dt_start = dt_start;
		this.dt_end = dt_end;
	}

	public String getId_assignment() {
		return id_assignment;
	}

	public void setId_assignment(String id_assignment) {
		this.id_assignment = id_assignment;
	}

	public String getId_prsn() {
		return id_prsn;
	}

	public void setId_prsn(String id_prsn) {
		this.id_prsn = id_prsn;
	}

	public String getId_case_prvd() {
		return id_case_prvd;
	}

	public void setId_case_prvd(String id_case_prvd) {
		this.id_case_prvd = id_case_prvd;
	}

	public String getCd_type() {
		return cd_type;
	}

	public void setCd_type(String cd_type) {
		this.cd_type = cd_type;
	}

	public String getCd_role() {
		return cd_role;
	}

	public void setCd_role(String cd_role) {
		this.cd_role = cd_role;
	}

	public String getNm_prsn() {
		return nm_prsn;
	}

	public void setNm_prsn(String nm_prsn) {
		this.nm_prsn = nm_prsn;
	}

	public Timestamp getDt_start() {
		return dt_start;
	}

	public void setDt_start(Timestamp dt_start) {
		this.dt_start = dt_start;
	}

	public Timestamp getDt_end() {
		return dt_end;
	}

	public void setDt_end(Timestamp dt_end) {
		this.dt_end = dt_end;
	}
	
}
