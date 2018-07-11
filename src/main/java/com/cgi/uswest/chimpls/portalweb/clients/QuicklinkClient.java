package com.cgi.uswest.chimpls.portalweb.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cgi.uswest.chimpls.portalweb.objects.Quicklink;

@FeignClient(name = "quicklinks", configuration = QuicklinkClientConfiguration.class)
public interface QuicklinkClient {

    @RequestMapping(method = RequestMethod.GET, value = "/quicklinks/{county}", 
    		consumes = "application/json")
    List<Quicklink> getQuicklinksByCounty(@PathVariable("county") String county);
	
}
