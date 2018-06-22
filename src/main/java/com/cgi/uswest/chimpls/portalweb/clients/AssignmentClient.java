package com.cgi.uswest.chimpls.portalweb.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cgi.uswest.chimpls.portalweb.objects.Assignment;

@FeignClient(name = "assignment", configuration = AssignmentClientConfiguration.class)
public interface AssignmentClient {

    @RequestMapping(method = RequestMethod.GET, value = "/assignment/primaryAssignmentByIdPrvd/{idprvdorg}", 
    		consumes = "application/json")
    Assignment getProviderPrimaryAssignmentDataByIdprvdorg(@PathVariable("idprvdorg") String idprvdorg);
	
    @RequestMapping(method = RequestMethod.GET, value = "/assignment/primaryAssignmentByIdCase/{idcase}", 
    		consumes = "application/json")
    Assignment getCasePrimaryAssignmentDataByIdcase(@PathVariable("idcase") String idcase);
	
    
}
