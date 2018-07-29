package com.cgi.uswest.chimpls.portalweb.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cgi.uswest.chimpls.portalweb.objects.Attachment;

@FeignClient(name = "attachments", configuration = AttachmentsClientConfiguration.class)
public interface AttachmentsClient {

    @RequestMapping(method = RequestMethod.GET, value = "/attachmentsByMessage/{idmessage}", 
    		consumes = "application/json")
    List<Attachment> getAttachmentsByMessage(@PathVariable("idmessage") String idmessage);
	
    @RequestMapping(method = RequestMethod.GET, value = "/attachmentById/{id}", 
    		consumes = "application/octet-stream")
    byte[] getAttachmentById(@PathVariable("id") String id);
    
}
