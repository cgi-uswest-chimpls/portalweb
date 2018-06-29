package com.cgi.uswest.chimpls.portalweb.controller;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.uswest.chimpls.portalweb.objects.Address;
import com.cgi.uswest.chimpls.portalweb.objects.Assignment;
import com.cgi.uswest.chimpls.portalweb.objects.Episode;
import com.cgi.uswest.chimpls.portalweb.objects.Person;
import com.cgi.uswest.chimpls.portalweb.objects.ProviderDetail;
import com.cgi.uswest.chimpls.portalweb.objects.User;
import com.cgi.uswest.chimpls.portalweb.repository.UserRepository;
import com.cgi.uswest.chimpls.portalweb.clients.AddressClient;
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
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AddressClient addressClient;
	
	  @RequestMapping("currentUser")
	  public User user(Principal principal) {
		  
		  Map<String, Object> userDetails = 
				  (Map<String, Object>) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();

		  return userRepository.findUserBySub((String) userDetails.get("sub"));
		  
	  }
	
	  @RequestMapping("user/sub")
	  public String userSub(Principal principal) {
		  
		  Map<String, Object> userDetails = 
				  (Map<String, Object>) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();

		  return ((String) userDetails.get("sub"));
		  
	  }
	  
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
	  
	  @RequestMapping("caseAddress/{idcase}")
	   public Address findCaseAddress(@PathVariable("idcase") BigDecimal idcase) {
		  return addressClient.getCaseAddressData(idcase);
	  }
	  
	  @RequestMapping("personAddress/{idprsn}")
	   public Address findPersonAddress(@PathVariable("idprsn") BigDecimal idprsn) {
		  return addressClient.getPersonAddressData(idprsn);
	  }
}
