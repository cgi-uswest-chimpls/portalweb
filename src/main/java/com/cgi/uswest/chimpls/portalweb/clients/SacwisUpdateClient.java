package com.cgi.uswest.chimpls.portalweb.clients;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cgi.uswest.chimpls.portalweb.objects.SacwisUpdate;

@FeignClient(name = "sacwisupdate", configuration = SacwisUpdateClientConfiguration.class)
public interface SacwisUpdateClient {
	@RequestMapping(method = RequestMethod.GET, value = "/sacwisupdate/all", 
    		consumes = "application/json")
	List<SacwisUpdate> getAllSacwisUpdates();
	
	@RequestMapping(method = RequestMethod.POST, value = "/sacwisupdate/add", 
    		consumes = "application/json")
	String addSacwisUpdates(
			@PathVariable("id_grp") BigDecimal id_grp,
		    @PathVariable("cd_grp") String	cd_grp,
			@PathVariable("cd_type") BigDecimal cd_type, 
			@PathVariable("cd_type") String tx_update,
			@PathVariable("id_cr") BigDecimal id_cr,
			@PathVariable("cd_stat") String cd_stat);
}
