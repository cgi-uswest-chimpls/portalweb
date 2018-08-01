package com.cgi.uswest.chimpls.portalweb.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cgi.uswest.chimpls.portalweb.objects.Meeting;

@FeignClient(name = "meetings", configuration = MeetingsClientConfiguration.class)
public interface MeetingsClient {

    @RequestMapping(method = RequestMethod.GET, value = "/currentMeetingsByPerson/{idprsn}", 
    		consumes = "application/json")
    List<Meeting> getCurrentMeetingsByPerson(@PathVariable("idprsn") String idprsn);
	
    @RequestMapping(method = RequestMethod.GET, value = "/meetingsByPerson/{idprsn}", 
    		consumes = "application/json")
    List<Meeting> getAllMeetingsByPerson(@PathVariable("idprsn") String idprsn);
}
