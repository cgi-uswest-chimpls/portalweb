package com.cgi.uswest.chimpls.portalweb.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cgi.uswest.chimpls.portalweb.objects.Episode;
import com.cgi.uswest.chimpls.portalweb.objects.ProviderDetail;

@FeignClient(name = "providerdetail", configuration = ProviderDetailClientConfiguration.class)
public interface ProviderDetailClient {

    @RequestMapping(method = RequestMethod.GET, value = "/providerdetail/all", 
    		consumes = "application/json")
    List<ProviderDetail> getProviderDetailsAll();
    
}
