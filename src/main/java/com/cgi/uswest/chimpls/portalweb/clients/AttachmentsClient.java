package com.cgi.uswest.chimpls.portalweb.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cgi.uswest.chimpls.portalweb.objects.Attachment;

import feign.Headers;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;

@FeignClient(name = "attachments", configuration = AttachmentsClientConfiguration.class)
public interface AttachmentsClient {

    @RequestMapping(method = RequestMethod.GET, value = "/attachmentsByMessage/{idmessage}", 
    		consumes = "application/json")
    List<Attachment> getAttachmentsByMessage(@PathVariable("idmessage") String idmessage);
	
    @RequestMapping(method = RequestMethod.GET, value = "/attachmentById/{id}", 
    		consumes = "application/octet-stream")
    byte[] getAttachmentById(@PathVariable("id") String id);
    
    @RequestMapping(method = RequestMethod.POST, value = "/add/{idmessage}/{filename}")
    @Headers("Content-Type: application/octet-stream")
    String postAttachmentToMessage(@PathVariable("idmessage") String idmessage,
    		@PathVariable("filename") String filename,
    		@RequestBody byte[] binary);
    
    
}
