package com.cgi.uswest.chimpls.portalweb.clients;

import java.math.BigDecimal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cgi.uswest.chimpls.portalweb.objects.Address;


@FeignClient(name = "address", configuration = AddressClientConfiguration.class)

public interface AddressClient {
	
	@RequestMapping(method = RequestMethod.GET, value = "/address/caseAddress/{idcase}", 
    		consumes = "application/json")
	Address getCaseAddressData(@PathVariable("idcase") BigDecimal idcase);
	
	@RequestMapping(method = RequestMethod.GET, value = "/address/personAddress/{idprsn}", 
    		consumes = "application/json")
	Address getPersonAddressData(@PathVariable("idprsn") BigDecimal idprsn);
    
	@RequestMapping(method = RequestMethod.GET, value = "/address/providerAddress/{idprvd}", 
    		consumes = "application/json")
	Address getProviderAddressData(@PathVariable("idprvd") BigDecimal idprvd);

    
}
