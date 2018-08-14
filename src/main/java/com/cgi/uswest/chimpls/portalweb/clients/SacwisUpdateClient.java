package com.cgi.uswest.chimpls.portalweb.clients;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cgi.uswest.chimpls.portalweb.objects.SacwisUpdate;

@FeignClient(name = "ccwisUpdate", configuration = SacwisUpdateClientConfiguration.class)
public interface SacwisUpdateClient {
	@RequestMapping(method = RequestMethod.GET, value = "/sacwisupdate/all", 
    		consumes = "application/json")
	List<SacwisUpdate> getAllSacwisUpdates();
	
	@RequestMapping(method = RequestMethod.POST, value = "/sacwisupdate/add")
	@ResponseBody String addSacwisUpdates(
			@RequestParam BigDecimal id_grp,
		    @RequestParam String	cd_grp,
			@RequestParam BigDecimal cd_type, 
			@RequestParam String tx_update,
			@RequestParam BigDecimal id_cr,
			@RequestParam String cd_stat);
}
