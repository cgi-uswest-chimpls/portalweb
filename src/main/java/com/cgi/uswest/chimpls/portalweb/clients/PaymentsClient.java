package com.cgi.uswest.chimpls.portalweb.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cgi.uswest.chimpls.portalweb.objects.Payment;

@FeignClient(name = "payments", configuration = PaymentsClientConfiguration.class)
public interface PaymentsClient {

    @RequestMapping(method = RequestMethod.GET, value = "/paymentsByProvider/{idprvdorg}", 
    		consumes = "application/json")
    List<Payment> getPaymentsByProvider(@PathVariable("idprvdorg") String idprvdorg);
	
    @RequestMapping(method = RequestMethod.GET, value = "/paymentsByPerson/{idprsn}", 
    		consumes = "application/json")
    List<Payment> getPaymentsByPerson(@PathVariable("idprsn") String idprsn);
    
}
