package com.cgi.uswest.chimpls.portalweb.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cgi.uswest.chimpls.portalweb.objects.Episode;

@FeignClient(name = "placements", configuration = PlacementsClientConfiguration.class)
public interface PlacementsClient {

    @RequestMapping(method = RequestMethod.GET, value = "/episodesByProvider/{idprvdorg}", 
    		consumes = "application/json")
    List<Episode> getEpisodesByProvider(@PathVariable("idprvdorg") String idprvdorg);
    
}
