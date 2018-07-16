package com.cgi.uswest.chimpls.portalweb.objects;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class SacwisUpdate {
private BigDecimal id_sacwis_update;
	
    private String id_grp;
    private String	cd_grp; //C for case, P for person, F for Foster care
	private String cd_type; //1 for address, 2 for phone etc
	private String tx_update;
	private String id_cr;
	private Timestamp ts_cr;
	private String cd_stat;
	
	SacwisUpdate() {}
	
	public SacwisUpdate(
			String id_grp,
		    String	cd_grp,
			String cd_type,
			String tx_update,
			String id_cr,
			Timestamp ts_cr,
			String cd_stat
			) {
		super();
		this.id_grp = id_grp;
		this.cd_grp = cd_grp;
		this.cd_type = cd_type;
		this.tx_update = tx_update;
		this.id_cr = id_cr;
		this.ts_cr = ts_cr;
		this.cd_stat = cd_stat;
	}

	public BigDecimal getId_sacwis_update() {
		return id_sacwis_update;
	}

	public void setId_sacwis_update(BigDecimal id_sacwis_update) {
		this.id_sacwis_update = id_sacwis_update;
	}

	public String getId_grp() {
		return id_grp;
	}

	public void setId_grp(String id_grp) {
		this.id_grp = id_grp;
	}

	public String getCd_grp() {
		return cd_grp;
	}

	public void setCd_grp(String cd_grp) {
		this.cd_grp = cd_grp;
	}

	public String getCd_type() {
		return cd_type;
	}

	public void setCd_type(String cd_type) {
		this.cd_type = cd_type;
	}

	public String getTx_update() {
		return tx_update;
	}

	public void setTx_update(String tx_update) {
		this.tx_update = tx_update;
	}

	public String getId_cr() {
		return id_cr;
	}

	public void setId_cr(String id_cr) {
		this.id_cr = id_cr;
	}

	public Timestamp getTs_cr() {
		return ts_cr;
	}

	public void setTs_cr(Timestamp ts_cr) {
		this.ts_cr = ts_cr;
	}

	public String getCd_stat() {
		return cd_stat;
	}

	public void setCd_stat(String cd_stat) {
		this.cd_stat = cd_stat;
	}
	
}
