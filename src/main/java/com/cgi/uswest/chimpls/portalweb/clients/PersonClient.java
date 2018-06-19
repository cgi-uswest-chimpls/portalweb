package com.cgi.uswest.chimpls.portalweb.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cgi.uswest.chimpls.portalweb.objects.Person;

@FeignClient(name = "person", configuration = PersonClientConfiguration.class)
public interface PersonClient {

    @RequestMapping(method = RequestMethod.GET, value = "/people/{idprsn}", 
    		consumes = "application/json")
    Person getPersonDataByIdprsn(@PathVariable("idprsn") String idprsn);
	
}
