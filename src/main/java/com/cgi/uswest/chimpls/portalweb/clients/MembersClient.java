package com.cgi.uswest.chimpls.portalweb.clients;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cgi.uswest.chimpls.portalweb.objects.Member;

@FeignClient(name = "members", configuration = MembersClientConfiguration.class)
public interface MembersClient {

	@RequestMapping(method = RequestMethod.GET, value = "/membersByProvider/{idprvdorg}", 
    		consumes = "application/json")
	List<Member> getMembersByProvider(@PathVariable("idprvdorg") BigDecimal idprvdorg);
	
}
