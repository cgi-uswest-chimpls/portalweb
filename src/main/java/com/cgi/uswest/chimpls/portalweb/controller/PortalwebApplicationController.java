package com.cgi.uswest.chimpls.portalweb.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.uswest.chimpls.portalweb.objects.Assignment;
import com.cgi.uswest.chimpls.portalweb.objects.Episode;
import com.cgi.uswest.chimpls.portalweb.objects.Person;
import com.cgi.uswest.chimpls.portalweb.objects.ProviderDetail;
import com.cgi.uswest.chimpls.portalweb.clients.AssignmentClient;
import com.cgi.uswest.chimpls.portalweb.clients.PersonClient;
import com.cgi.uswest.chimpls.portalweb.clients.PlacementsClient;
import com.cgi.uswest.chimpls.portalweb.clients.ProviderDetailClient;

@RefreshScope
@RestController
public class PortalwebApplicationController {

	@Autowired
	private PlacementsClient placementsClient;
	
	@Autowired
	private ProviderDetailClient providerDetailClient;
	
	@Autowired
	private PersonClient personClient;
	
	@Autowired
	private AssignmentClient assignmentClient;
	
	//@RequestMapping("currentUser")
	
	
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
	  
	  @RequestMapping("person/{idprsn}")
	   public Person findPersonDetailsForIdprsn(@PathVariable("idprsn") String idprsn) {
		  return personClient.getPersonDataByIdprsn(idprsn);
	  }
	  
	  @RequestMapping("providerPrimaryAssignment/{idprvdorg}")
	   public Assignment findPrimaryProviderAssignmentForIdPrvdOrg(@PathVariable("idprvdorg") String idprvdorg) {
		  return assignmentClient.getProviderPrimaryAssignmentDataByIdprvdorg(idprvdorg);
	  }
	  
	  @RequestMapping("casePrimaryAssignment/{idcase}")
	   public Assignment findPrimaryCaseAssignmentForIdCase(@PathVariable("idcase") String idcase) {
		  return assignmentClient.getCasePrimaryAssignmentDataByIdcase(idcase);
	  }
	  
}
