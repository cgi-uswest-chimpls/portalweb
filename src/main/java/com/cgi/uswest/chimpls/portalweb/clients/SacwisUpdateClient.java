package com.cgi.uswest.chimpls.portalweb.clients;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cgi.uswest.chimpls.portalweb.objects.SacwisUpdate;

@FeignClient(name = "sacwisupdate", configuration = SacwisUpdateClientConfiguration.class)
public interface SacwisUpdateClient {
	@RequestMapping(method = RequestMethod.GET, value = "/sacwisupdate/all", 
    		consumes = "application/json")
	List<SacwisUpdate> getAllSacwisUpdates();
	
	@RequestMapping(method = RequestMethod.POST, value = "/sacwisupdate/add", 
    		consumes = "application/json")
	String addSacwisUpdates(
			@RequestParam(value="id_grp") String id_grp,
		    @RequestParam(value="cd_grp") String	cd_grp,
			@RequestParam(value="cd_type") String cd_type, 
			@RequestParam(value="tx_update") String tx_update,
			@RequestParam(value="id_cr") String id_cr,
			@RequestParam(value="cd_stat") String cd_stat);
}
