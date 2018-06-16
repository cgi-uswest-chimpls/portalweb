package com.cgi.uswest.chimpls.portalweb.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.uswest.chimpls.portalweb.objects.Episode;
import com.cgi.uswest.chimpls.portalweb.objects.ProviderDetail;
import com.cgi.uswest.chimpls.portalweb.clients.PlacementsClient;
import com.cgi.uswest.chimpls.portalweb.clients.ProviderDetailClient;

@RefreshScope
@RestController
public class PortalwebApplicationController {

	@Autowired
	private PlacementsClient placementsClient;
	
	@Autowired
	private ProviderDetailClient providerDetailClient;
	
	  @RequestMapping("placements/episodesByProvider/{idprvdorg}") 
	   public List<Episode> findEpisodesByProvider(@PathVariable("idprvdorg") String idprvdorg) {
		  return placementsClient.getEpisodesByProvider(idprvdorg);
	  }
	  
	  @RequestMapping("providerdetail/all")
	   public List<ProviderDetail> findProviderDetailsAll() {
		  return providerDetailClient.getProviderDetailsAll();
	  }
	  
	  @RequestMapping("providerdetail/{idprvdorg}")
	   public ProviderDetail findProviderDetailsForIdPrvdOrg(@PathVariable("idprvdorg") String idprvdorg) {
		  return providerDetailClient.getProviderDetailsForIdPrvdOrg(idprvdorg);
	  }
	  
}
