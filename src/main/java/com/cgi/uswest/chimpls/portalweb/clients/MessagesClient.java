package com.cgi.uswest.chimpls.portalweb.clients;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cgi.uswest.chimpls.portalweb.objects.Message;

@FeignClient(name = "messaging")
public interface MessagesClient {

	@RequestMapping(method = RequestMethod.GET, value = "/touser/1/{idprvdorg}", 
    		consumes = "application/json")
	List<Message> getMessagesToMe(@PathVariable("idprvdorg") BigDecimal idprvdorg);
	
	@RequestMapping(method = RequestMethod.GET, value = "/fromuser/0/{idprvdorg}", 
    		consumes = "application/json")
	List<Message> getMessagesFromMe(@PathVariable("idprvdorg") BigDecimal idprvdorg);
	
}
