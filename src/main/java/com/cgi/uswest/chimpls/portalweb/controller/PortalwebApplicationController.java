package com.cgi.uswest.chimpls.portalweb.controller;

import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.uswest.chimpls.portalweb.clients.AddressClient;
import com.cgi.uswest.chimpls.portalweb.clients.AssignmentClient;
import com.cgi.uswest.chimpls.portalweb.clients.AttachmentsClient;
import com.cgi.uswest.chimpls.portalweb.clients.PaymentsClient;
import com.cgi.uswest.chimpls.portalweb.clients.PersonClient;
import com.cgi.uswest.chimpls.portalweb.clients.PlacementsClient;
import com.cgi.uswest.chimpls.portalweb.clients.ProviderDetailClient;
import com.cgi.uswest.chimpls.portalweb.clients.QuicklinkClient;
import com.cgi.uswest.chimpls.portalweb.clients.SacwisUpdateClient;
import com.cgi.uswest.chimpls.portalweb.objects.Address;
import com.cgi.uswest.chimpls.portalweb.objects.Assignment;
import com.cgi.uswest.chimpls.portalweb.objects.Attachment;
import com.cgi.uswest.chimpls.portalweb.objects.Episode;
import com.cgi.uswest.chimpls.portalweb.objects.Message;
import com.cgi.uswest.chimpls.portalweb.objects.MessagesDropdownValue;
import com.cgi.uswest.chimpls.portalweb.objects.Payment;
import com.cgi.uswest.chimpls.portalweb.objects.Person;
import com.cgi.uswest.chimpls.portalweb.objects.ProviderDetail;
import com.cgi.uswest.chimpls.portalweb.objects.Quicklink;
import com.cgi.uswest.chimpls.portalweb.objects.SacwisUpdate;
import com.cgi.uswest.chimpls.portalweb.objects.User;
import com.cgi.uswest.chimpls.portalweb.repository.UserRepository;
import com.cgi.uswest.chimpls.portalweb.clients.AddressClient;
import com.cgi.uswest.chimpls.portalweb.clients.AssignmentClient;
import com.cgi.uswest.chimpls.portalweb.clients.MessagesClient;
import com.cgi.uswest.chimpls.portalweb.clients.PaymentsClient;
import com.cgi.uswest.chimpls.portalweb.clients.PersonClient;
import com.cgi.uswest.chimpls.portalweb.clients.PlacementsClient;
import com.cgi.uswest.chimpls.portalweb.clients.ProviderDetailClient;
import com.cgi.uswest.chimpls.portalweb.clients.QuicklinkClient;
import com.cgi.uswest.chimpls.portalweb.clients.SacwisUpdateClient;

@RefreshScope
@RestController
public class PortalwebApplicationController {

	@Autowired
	private PlacementsClient placementsClient;
	
	@Autowired
	private ProviderDetailClient providerDetailClient;
	
	@Autowired
	private PersonClient personClient;
	
	@Autowired
	private AssignmentClient assignmentClient;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AddressClient addressClient;
	
	@Autowired
	private QuicklinkClient quicklinkClient;
	
	@Autowired
	private PaymentsClient paymentsClient;

	@Autowired
	private SacwisUpdateClient sacwisUpdateClient;
	
	@Autowired
	private MessagesClient messagesClient;
	
	@Autowired
	private AttachmentsClient attachmentsClient;
	
	  @RequestMapping("currentUser")
	  public User user(Principal principal) {
		  
		  Map<String, Object> userDetails = 
				  (Map<String, Object>) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();

		  return userRepository.findUserBySub((String) userDetails.get("sub"));
		  
	  }
	
	  @RequestMapping("user/sub")
	  public String userSub(Principal principal) {
		  
		  Map<String, Object> userDetails = 
				  (Map<String, Object>) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();

		  return ((String) userDetails.get("sub"));
		  
	  }
	  
	  @RequestMapping("placements/episodesByProvider/{idprvdorg}") 
	   public List<Episode> findEpisodesByProvider(@PathVariable("idprvdorg") String idprvdorg) {
		  return placementsClient.getEpisodesByProvider(idprvdorg);
	  }
	  
	  @RequestMapping("providerdetail/all")
	   public List<ProviderDetail> findProviderDetailsAll() {
		  return providerDetailClient.getProviderDetailsAll();
	  }
	  
	  @RequestMapping("providerdetail/{idprvdorg}")
	   public ProviderDetail findProviderDetailsForIdPrvdOrg(@PathVariable("idprvdorg") String idprvdorg) {
		  return providerDetailClient.getProviderDetailsForIdPrvdOrg(idprvdorg);
	  }
	  
	  @RequestMapping("person/{idprsn}")
	   public Person findPersonDetailsForIdprsn(@PathVariable("idprsn") String idprsn) {
		  return personClient.getPersonDataByIdprsn(idprsn);
	  }
	  
	  @RequestMapping("providerPrimaryAssignment/{idprvdorg}")
	   public Assignment findPrimaryProviderAssignmentForIdPrvdOrg(@PathVariable("idprvdorg") String idprvdorg) {
		  return assignmentClient.getProviderPrimaryAssignmentDataByIdprvdorg(idprvdorg);
	  }
	  
	  @RequestMapping("casePrimaryAssignment/{idcase}")
	   public Assignment findPrimaryCaseAssignmentForIdCase(@PathVariable("idcase") String idcase) {
		  return assignmentClient.getCasePrimaryAssignmentDataByIdcase(idcase);
	  }
	  
	  @RequestMapping("caseAddress/{idcase}")
	   public Address findCaseAddress(@PathVariable("idcase") BigDecimal idcase) {
		  return addressClient.getCaseAddressData(idcase);
	  }
	  
	  @RequestMapping("personAddress/{idprsn}")
	   public Address findPersonAddress(@PathVariable("idprsn") BigDecimal idprsn) {
		  return addressClient.getPersonAddressData(idprsn);
	  }
	  
	  @RequestMapping("providerAddress/{idprvd}")
	   public Address findProviderAddress(@PathVariable("idprvd") BigDecimal idprvd) {
		  return addressClient.getProviderAddressData(idprvd);
	  }
	  
	  @RequestMapping("quicklinks/{county}")
	   public List<Quicklink> findQuicklinksByCounty(@PathVariable("county") String county) {
		  return quicklinkClient.getQuicklinksByCounty(county);
	  }
	  
	  @RequestMapping("paymentsByProvider/{idprvdorg}")
	   public List<Payment> findPaymentsByProvider(@PathVariable("idprvdorg") String idprvdorg) {
		  return paymentsClient.getPaymentsByProvider(idprvdorg);
	  }
	  
	  @RequestMapping("paymentsByPerson/{idprsn}")
	   public List<Payment> findPaymentsByPerson(@PathVariable("idprsn") String idprsn) {
		  return paymentsClient.getPaymentsByPerson(idprsn);
	  }

	  @RequestMapping("sacwisupdate/add")
	  public @ResponseBody String addSacwisUpdates(@RequestParam BigDecimal id_grp,
			    @RequestParam String	cd_grp,
				@RequestParam BigDecimal cd_type, 
				@RequestParam String tx_update,
				@RequestParam BigDecimal id_cr,
				@RequestParam String cd_stat) {
		  System.out.println("*****CONTROLLER: id_grp:" + id_grp);
		  return sacwisUpdateClient.addSacwisUpdates(id_grp,cd_grp,cd_type,tx_update,id_cr,cd_stat);

	  }

	  @RequestMapping("messages/send/{idprvdorg}/{idprsn}")
	  public ResponseEntity<String> sendMessage(@PathVariable("idprvdorg") String idprvdorg,
			  	@PathVariable("idprsn") String idprsn,
			    @RequestParam(value="title") String	title,
			    @RequestParam(value="content") String content) {
		  
		  String body = "{";
		  
		  body += "\"fromId\": " + "\"" + idprvdorg + "\",";
		  body += "\"fromUserType\": " + "\"" + "1" + "\",";
		  body += "\"toId\": " + "\"" + idprsn + "\",";
		  body += "\"toUserType\": " + "\"" + "0" + "\",";
		  body += "\"title\": " + "\"" + title + "\",";
		  body += "\"content\": " + "\"" + content + "\"";

		  body += "}";

		  return messagesClient.sendMessage(body);
	  }
	  
	  @RequestMapping("messages/tome/{idprvdorg}")
	  public List<Message> findMessagesToMe(@PathVariable("idprvdorg") String idprvdorg) {
		  List<Message> messages = messagesClient.getMessagesToMe(new BigDecimal(idprvdorg));
		  for (int i = 0; i < messages.size(); i++) {
			  
			  
			  // get person name from person service
			  
			  Message message = messages.get(i);

			  Timestamp timestamp;
			  
			  try {
				    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS");
				    Date parsedDate = dateFormat.parse(message.getCreatedDate());
				    timestamp = new java.sql.Timestamp(parsedDate.getTime());
				  
				  message.setCreatedDateTimestamp(timestamp);
			  }
			  catch (ParseException e) {
				  // leave the timestamp as null if cannot be parsed, comparator will handle
			  }

			  
			  
			  Person person = personClient.getPersonDataByIdprsn(message.getFromId());
			  message.setFromUserName(person.getNmlst() + ", " + person.getNmfrst());
			  
			  message.setCreatedDate(convertUTCToCentral(message.getCreatedDate()));

			  
		  }
		  Collections.reverse(messages);
		  return messages;
	  }
	  
	  @RequestMapping("messages/fromme/{idprvdorg}")
	  public List<Message> findMessagesFromMe(@PathVariable("idprvdorg") String idprvdorg) {
		  List<Message> messages = messagesClient.getMessagesFromMe(new BigDecimal(idprvdorg));
		  for (int i = 0; i < messages.size(); i++) {
			  Message message = messages.get(i);
			  
			  Timestamp timestamp;
			  
			  try {
				    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS");
				    Date parsedDate = dateFormat.parse(message.getCreatedDate());
				    timestamp = new java.sql.Timestamp(parsedDate.getTime());
				  
				  message.setCreatedDateTimestamp(timestamp);
			  }
			  catch (ParseException e) {
				  // leave the timestamp as null if cannot be parsed, comparator will handle
			  }

			  
			  Person person = personClient.getPersonDataByIdprsn(message.getToId());
			  message.setToUserName(person.getNmlst() + ", " + person.getNmfrst());

			message.setCreatedDate(convertUTCToCentral(message.getCreatedDate()));

			  
		  }
		  
		  Collections.reverse(messages);
		  return messages;
	  }
	  
	  public String convertUTCToCentral(String utcDate) {
		  
		  String utcFormat = "yyyy-MM-dd'T'HH:mm:ss";
		  
		  LocalDateTime ldt = LocalDateTime.parse(utcDate, DateTimeFormatter.ofPattern(utcFormat));
		  
		  ZoneId utcZoneId = ZoneId.of("UTC");
		  
		  ZonedDateTime UTCDateTime = ldt.atZone(utcZoneId);
		  
		  ZoneId centralZoneId = ZoneId.of("America/Chicago");
		  
		  ZonedDateTime centralZonedDateTime = UTCDateTime.withZoneSameInstant(centralZoneId);
		  
		  DateTimeFormatter format = DateTimeFormatter.ofPattern(utcFormat);
		  
		  return format.format(centralZonedDateTime);
		  
	  }
	  
	  @RequestMapping("messages/dropdown/{idprvdorg}")
	  public List<MessagesDropdownValue> findMessagesDropdownValues(@PathVariable("idprvdorg") String idprvdorg) {
		  
		  Assignment providerAssignment = assignmentClient.getProviderPrimaryAssignmentDataByIdprvdorg(idprvdorg);
		  
		  List<Episode> placements = placementsClient.getEpisodesByProvider(idprvdorg);
		  
		  List<String> workerIds = new ArrayList<String>();
		  
		  if (!workerIds.contains(providerAssignment.getId_prsn())) {
			  workerIds.add(providerAssignment.getId_prsn());
		  }
		  
		  for(int i = 0; i < placements.size(); i++) {
			  
			  String idcase = ((Episode) placements.get(i)).getIdcase();
			  
			  Assignment caseAssignment = assignmentClient.getCasePrimaryAssignmentDataByIdcase(idcase);
			  
			  if (!workerIds.contains(caseAssignment.getId_prsn())) {
				  workerIds.add(caseAssignment.getId_prsn());
			  }
			  
		  }
		  
		  List<MessagesDropdownValue> values = new ArrayList<MessagesDropdownValue>();
		  
		  for(int j = 0; j < workerIds.size(); j++) {
			  
			  Person person = personClient.getPersonDataByIdprsn((String)(workerIds.get(j)));
			  
			  if (person != null) {
				  String personName = person.getNmlst() + ", " + person.getNmfrst();
				  
				  MessagesDropdownValue value = new MessagesDropdownValue(workerIds.get(j), personName);
				  
				  values.add(value);  
			  }
			  
		  }
		  
		  return values;
	  }
	  
	  @RequestMapping("sacwisupdate/all")
	   public List<SacwisUpdate> findSacwisUpdateByProvider() {
		  return sacwisUpdateClient.getAllSacwisUpdates();
	  }
	  
	  @RequestMapping("attachmentsByMessage/{idmessage}")
	  public List<Attachment> findAttachmentsByMessage(@PathVariable String idmessage) {
		  return attachmentsClient.getAttachmentsByMessage(idmessage);
	  }

	  @RequestMapping("attachmentById/{id}")
	  public String findAttachmentById(@PathVariable String id) {
		  byte[] bytes = attachmentsClient.getAttachmentById(id);
		  
		  byte[] encoded = Base64.getEncoder().encode(bytes);
		  return new String(encoded);
	      

	  }

}
