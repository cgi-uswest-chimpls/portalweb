var handlebars_placementsTemplate;
var handlebars_providerDetailsTemplate;
var handlebars_quicklinksTemplate;
var handlebars_messagesTemplate;
var handlebars_sentMessagesTemplate;
var handlebars_meetingsTemplate;

$.ajaxSetup({
	beforeSend : function(xhr, settings) {
	  if (settings.type == 'POST' || settings.type == 'PUT'
	      || settings.type == 'DELETE') {
	    if (!(/^http:.*/.test(settings.url) || /^https:.*/
	        .test(settings.url))) {
	      // Only send the token to relative URLs i.e. locally.
	      xhr.setRequestHeader("X-XSRF-TOKEN",
	          Cookies.get('XSRF-TOKEN'));
	    }
	  }
	}
});

function logout() {
    $.post("/logout", function() {
        $(".unauthenticated").show();
        $(".authenticated").hide();
    });
    return true;
}

function loadProvider() {
		
	handlebars_placementsTemplate = Handlebars.compile($('#placementsTemplate').html());
	handlebars_providerDetailsTemplate = Handlebars.compile($('#providerDetailsTemplate').html());
	//handlebars_quicklinksTemplate = Handlebars.compile($('#quicklinksTemplate').html());
	handlebars_messagesTemplate = Handlebars.compile($('#messagesTemplate').html());
	handlebars_sentMessagesTemplate = Handlebars.compile($('#sentMessagesTemplate').html());
	handlebars_meetingsTemplate = Handlebars.compile($('#meetingsTemplate').html());
	
	registerHandlebarsHelpers();
	
	var idprvdorg;
	
    $.ajax({
        url: 'currentUser',
    	datatype: 'json',
        type: "get",
        contentType: "application/json",
        success: function (result) {
        	
            $(".authenticated").show();
            $(".unauthenticated").hide();
        	
        	$('#userCounty').val(result.county);
        	$('#userIdPrvdOrg').val(result.idprvdorg);
        	
        	idprvdorg = result.idprvdorg;
        	
        	loadMessagesToUser(idprvdorg);
        	loadProviderDetails(idprvdorg);
        	loadProviderPlacements(idprvdorg);
        },
        error: function () {
        	window.location = "/login";
        }
    });
	




}

function help() {
	/*
	var userCounty = $('#userCounty').val();
	
    $.ajax({
        url: 'quicklinks/' + userCounty ,
    	datatype: 'json',
        type: "get",
        contentType: "application/json",
        success: function (result) {
        	$('#divQuicklinks').html(handlebars_quicklinksTemplate(result));
        	
        	$('#quicklinksModal').modal('show')
        },
        error: function () {
        	$('#divQuicklinks').html("<div style='color:white;'>An error occurred trying to access the endpoint /quicklinks/" + userCounty);
        }
    });
    */
	$('#quicklinksModal').modal('show');
}

function paymentsByChild(idprsn) {

	   $.ajax({
	        url: 'paymentsByPerson/' + idprsn ,
	    	datatype: 'json',
	        type: "get",
	        contentType: "application/json",
	        success: function (result) {
	        	
	        	$('#paymentsByChildTitle').html('Payments for ' + result[0].txpersonname);
	        	
	        	$('#paymentsByChildTable').bootstrapTable('destroy');
	        	
	        	$('#paymentsByChildTable').bootstrapTable({
	        		data: result,
	        		classes: 'table table-hover table-striped',
	        		columns: [{
	        			field: 'dtpayment',
	        			title: 'Payment Date',
	        			formatter: formatDateSlashes,
	        			sortable: true
	        		}, {
	        			field: 'amount',
	        			title: 'Amount',
	        			formatter: formatDollars,
	        			sortable: true
	        		}]
	        	});
	        	
	        	$('#paymentsByChildModal').modal('show');
	        },
	        error: function () {
	        	$('#divPaymentsByChildTable').html("<div style='color:white;'>An error occurred trying to access the endpoint paymentsByPerson/" + idprsn);
	        }
	    });
}

function paymentsByProvider() {

   var userIdPrvdOrg = $('#userIdPrvdOrg').val();
	
   $.ajax({
        url: 'paymentsByProvider/' + userIdPrvdOrg ,
    	datatype: 'json',
        type: "get",
        contentType: "application/json",
        success: function (result) {
        	
        	$('#paymentsByProviderTable').bootstrapTable({
        		data: result,
        		classes: 'table table-hover table-striped',
        		columns: [{
        			field: 'dtpayment',
        			title: 'Payment Date',
        			formatter: formatDateSlashes,
        			sortable: true
        		}, {
        			field: 'amount',
        			title: 'Amount',
        			formatter: formatDollars,
        			sortable: true
        		}, {
        			field: 'txpersonname',
        			title: 'Child Name',
        			sortable: true
        		}]
        	});
        	
        	$('#paymentsByProviderModal').modal('show');
        },
        error: function () {
        	$('#divPaymentsByProviderTable').html("<div style='color:white;'>An error occurred trying to access the endpoint paymentsByProvider/" + userIdPrvdOrg);
        }
    });
	
}

function members() {

	   var userIdPrvdOrg = $('#userIdPrvdOrg').val();
		
	   $.ajax({
	        url: 'membersByProvider/' + userIdPrvdOrg ,
	    	datatype: 'json',
	        type: "get",
	        contentType: "application/json",
	        success: function (result) {
	        	
	        	$('#membersTable').bootstrapTable({
	        		data: result,
	        		classes: 'table table-hover table-striped',
	        		columns: [{
	        			field: 'nmfrst',
	        			title: 'First Name',
	        			sortable: true
	        		}, {
	        			field: 'nmlst',
	        			title: 'Last Name',
	        			sortable: true
	        		}, {
	        			field: 'dtbrth',
	        			title: 'Date of Birth',
	        			formatter: formatDateSlashes,
	        			sortable: true
	        		}, {
	        			field: 'qtage',
	        			title: 'Age',
	        			sortable: true
	        		}, {
	        			field: 'flinhome',
	        			title: 'In Home',
	        			sortable: true
	        		}, {
	        			field: 'flinhome',
	        			title: 'Update',
	        		    sortable: false,
	        			formatter: LinkFormatter
	        		}]
	        	});
	        	
	        	$('#membersModal').modal('show');
	        },
	        error: function () {
	        	$('#membersTable').html("<div>An error occurred trying to access the endpoint membersByProvider/" + userIdPrvdOrg);
	        }
	    });
		
	}

function LinkFormatter(value, row, index) {
	var txLink ="";
	if (value == "Y"){
		txLink = "Update to Out of Home";
	} else {
		txLink = "Update to In Home";
	}
	//return "<a href='/userid/id:"+row.id+"'>"+txLink+"</a>";
	var m_flInhome = row.flinhome;
	
	if (m_flInhome == "Y"){
		m_flInhomeParm = 1;
	} else {
		m_flInhomeParm = 2;
	}
	
	var m_idprsn = row.idprsn;
	var m_idprvdorg = row.idprvdorg;
	
	return "<div id='memberUpdtLink"+index+"'><a onclick='saveMemberSacwisUpdateReq("+m_flInhomeParm+","+m_idprsn+","+m_idprvdorg +","+index + ");'>"+txLink+"</a></div>";
	
}


function saveMemberSacwisUpdateReq(flinhome, idprsn, idprvdorg, index){
	//alert(document.getElementById('id_grp').value);
	if (flinhome == "1"){
		m_flInhomeParm = "Y";
	} else {
		m_flInhomeParm = "N";
	}
	
	var m_txUpdate = '';
	var m_cdType = "";
	if (m_flInhomeParm == 'Y'){
		m_txUpdate = 'Person is out of home';
		m_cdType = 4; //Remove Member in Home
	} else {
		m_txUpdate = 'Person is in home';
		m_cdType = 3; //Add Member in Home
	}
	var m_url = 'ccwisupdate/add?id_grp=' + idprsn 
			    +'&cd_grp=P'
			    +'&cd_type='+m_cdType
			    +'&tx_update=' + m_txUpdate 
			    +'&id_cr='+idprvdorg 
			    +'&cd_stat=P';
	
	$.ajax({
        url: m_url,
        datatype: 'String',
        type: "POST",
        contentType: "application/json",
        success: function (result) {
//        	alert('saved');
        	document.getElementById('memberUpdtLink'+index).innerHTML = 'Saved';
        	document.getElementById('memberUpdtLink'+index).styleClass = '';
        },
        error: function () {
        	alert('error');
        }
    });
	
}

function registerHandlebarsHelpers() {
	
	Handlebars.registerHelper("lessthan", function(conditional, options) {
	    if (conditional < options.hash.equals) {
	        return options.fn(this);
	    } else {
	        return options.inverse(this);
	    }
	});
	
	Handlebars.registerHelper("equal", function(conditional, options) {
	    if (conditional = options.hash.equals) {
	        return options.fn(this);
	    } else {
	        return options.inverse(this);
	    }
	});
	
	Handlebars.registerHelper('formatDateSlashes', function(text, url) {
		  text = Handlebars.Utils.escapeExpression(text);
		  url  = Handlebars.Utils.escapeExpression(url);

		  var year = text.substring(0,4);
		  var month = parseInt(text.substring(5,7));
		  var day = parseInt(text.substring(8,10));

		  return new Handlebars.SafeString(month + "/" + day + "/" + year);
	});
	
	Handlebars.registerHelper('formatDateSlashesWithTimestamp', function(text, url) {
	
		  var year = text.substring(0,4);
		  var month = parseInt(text.substring(5,7));
		  var day = parseInt(text.substring(8,10));

		  var hour = parseInt(text.substring(11,13));
		  var minute = text.substring(14,16);
		  
		  var ampm = "AM";
		  if (hour > 12) {
			  hour = hour - 12;
			  ampm = "PM";
		  }
		  
		  return new Handlebars.SafeString(month + "/" + day + "/" + year + 
				  " " + hour + ":" + minute + " " + ampm);
		
	});
	
	Handlebars.registerHelper('formatDateTimeOnly', function(text, url) {
		
		  var hour = parseInt(text.substring(11,13));
		  var minute = text.substring(14,16);
		  
		  var ampm = "AM";
		  if (hour > 12) {
			  hour = hour - 12;
			  ampm = "PM";
		  }
		  
		  return new Handlebars.SafeString(hour + ":" + minute + " " + ampm);
		
	});
	
	
}

function formatDateSlashes(value, row, index, field) {
	  var year = value.substring(0,4);
	  var month = parseInt(value.substring(5,7));
	  var day = parseInt(value.substring(8,10));

	  return month + "/" + day + "/" + year;
}

function formatDateTimeOnly(value, row, index, field) {
	  var hour = parseInt(value.substring(11,13));
	  var minute = value.substring(14,16);
	  
	  var ampm = "AM";
	  if (hour > 12) {
		  hour = hour - 12;
		  ampm = "PM";
	  }
	  
	  return new Handlebars.SafeString(hour + ":" + minute + " " + ampm);
}

function formatDateSlashesWithTimestamp(value, row, index, field) {
	  var year = value.substring(0,4);
	  var month = parseInt(value.substring(5,7));
	  var day = parseInt(value.substring(8,10));

	  var hour = parseInt(value.substring(11,13));
	  var minute = value.substring(14,16);
	  
	  var ampm = "AM";
	  if (hour > 12) {
		  hour = hour - 12;
		  ampm = "PM";
	  }
	  
	  return month + "/" + day + "/" + year + " " + hour + ":" + minute + " " + ampm;
}

function formatDollars(value, row, index, field) {
	return '$' + Number(Math.round(parseFloat(value)+'e2')+'e-2').toFixed(2);
}

function loadMessagesToUser(idprvdorg) {
	
    $.ajax({
        url: 'messages/tome/' + idprvdorg,
    	datatype: 'json',
        type: "get",
        contentType: "application/json",
        success: function (result) {
        	
        	var data = JSON.stringify(result);
        	//$('#divProviderDetail').html(data);
        	
        	$('#divMessages').html(handlebars_messagesTemplate(result));

        	for(var i = 0; i < result.length; i++) {
        		loadAttachmentsForMessage(result[i].id, "to", i);
        	}
        	
            setTimeout(loadMessagesFromUser(idprvdorg), 2000);

        },
        error: function () {
        	$('#divMessages').html("<div>An error occurred trying to access the endpoint " + 'messages/tome/' + idprvdorg + "</div>");
        }
    });
	
}

function loadAttachmentsForMessage(idmessage, fromTo, index) {
	
    $.ajax({
        url: 'attachmentsByMessage/' + idmessage,
    	datatype: 'json',
        type: "get",
        contentType: "application/json",
        success: function (result) {
        	
        	if (result.length > 0) {
        		
            	var title = "";
            	var id = "";
            	
            	for(var i = 0; i < result.length; i++) {
            		if (title != "") {
            			title += ",";
            		}
            		
            		title += result[i].filename;
            		id += result[i].id;
            	}

            	$('#' + fromTo + 'MessageAttachments' + index).attr('title', title).attr('data-attachmentids', id);
        		
        		$('#' + fromTo + 'MessageAttachments' + index).show();
        	}
        	else {
        		$('#' + fromTo + 'MessageAttachments' + index).hide();
        	}
        	

        },
        error: function () {
        	//$('#divMessages').html("<div>An error occurred trying to access the endpoint " + 'messages/tome/' + idprvdorg + "</div>");
        }
    });
}

function showAttachment(spanid) {
	
	var id = $('#' + spanid).attr('data-attachmentids');
	var title = $('#' + spanid).attr('title');
	
	$('#attachmentDiv').empty();
	

	var data;
	
    $.ajax({
        url: 'attachmentById/' + id,
    	datatype: 'json',
        type: "get",
        contentType: "application/text",
        success: function (result) {
        	
        	if (title.indexOf('.pdf') > -1) {
        		var obj = $('<object>').attr('width', '100%').height('600px').attr('data', 'data:application/pdf;base64, ' + result).
        			attr('type', 'application/pdf');
        		
        		$('#attachmentDiv').append(obj);
        	}
        	else if (title.indexOf('.png') > -1){
        		
        		var img = $('<img>').attr('src', 'data:image/png;base64, ' + result);
        		$('#attachmentDiv').append(img);
        		
        	}

        },
        error: function () {
        	//$('#divMessages').html("<div>An error occurred trying to access the endpoint " + 'messages/tome/' + idprvdorg + "</div>");
        }
    });
		

	
	

	$('#singleAttachmentTitle').html(title);
	
	$('#singleAttachmentModal').modal('show');
	
}

function loadMessagesFromUser(idprvdorg) {
	
    $.ajax({
        url: 'messages/fromme/' + idprvdorg,
    	datatype: 'json',
        type: "get",
        contentType: "application/json",
        success: function (result) {
        	
        	var data = JSON.stringify(result);
        	//$('#divProviderDetail').html(data);
        	$('#totalSentMessages').val(result.length);
        	$('#sentMessagesHeader').html("Sent (" + result.length + ")");
        	$('#sentMessages').html(handlebars_sentMessagesTemplate(result));
        	
        	for(var i = 0; i < result.length; i++) {
        		loadAttachmentsForMessage(result[i].id, "from", i);
        	}

        },
        error: function () {
        	$('#sentMessages').html("<div>An error occurred trying to access the endpoint " + 'messages/fromme/' + idprvdorg + "</div>");
        }
    });
	
}

function showMessages() {
	$('#messages').show();
	$('#sentMessages').hide();
}

function showSentMessages() {
	$('#sentMessages').show();
	$('#messages').hide();
}

function toggleMessageBody(index) {
	if ($('#messageIcon'+index).hasClass('fa-envelope')) {
		$('#messageIcon'+index).removeClass('fa-envelope').addClass('fa-envelope-open');
		$('#messageBody'+index).show();
	}
	else {
		$('#messageIcon'+index).removeClass('fa-envelope-open').addClass('fa-envelope');
		$('#messageBody'+index).hide();
	}
}

function toggleSentMessageBody(index) {
	if ($('#sentMessageIcon'+index).hasClass('fa-envelope')) {
		$('#sentMessageIcon'+index).removeClass('fa-envelope').addClass('fa-envelope-open');
		$('#sentMessageBody'+index).show();
	}
	else {
		$('#sentMessageIcon'+index).removeClass('fa-envelope-open').addClass('fa-envelope');
		$('#sentMessageBody'+index).hide();
	}
}

function createMissingFromCareMessage(selectedPerson) {
	var kidname = $('#childName'+selectedPerson).val();
	createMessageToCaseWorker(selectedPerson, 'CRITICAL: ' + kidname + ' is missing from care');
}

function createMessage(selectedPerson, subject) {
	   var userIdPrvdOrg = $('#userIdPrvdOrg').val();
		
	   $.ajax({
	        url: 'messages/dropdown/' + userIdPrvdOrg ,
	    	datatype: 'json',
	        type: "get",
	        contentType: "application/json",
	        success: function (result) {

	        	var select = $('<select>').addClass('form-control').attr('id', 'selectMessageRecipient');

	        	for (var i = 0; i < result.length; i++) {
	        		var option = $('<option>').val(result[i].idPrsn).html(result[i].name);
	        		
	        		if (result[i].idPrsn == selectedPerson) {
	        			option.attr('selected', 'selected');
	        		}
	        		
	        		select.append(option);
	        		    		
	        	}
	        	
	        	if (selectedPerson != null && selectedPerson != 'undefined') {
	        		select.attr('disabled', 'true');
	        	}
	        	
	        	$('#divMessagesDropdown').html("").append(select);
	        	
	        	if (subject != null && subject != '') {
	        		$('#sendMessageSubject').val(subject);
	        	}
	        	
	        	$('#createMessageModal').modal('show');
	        },
	        error: function () {
	        	$('#divMessagesDropdown').html("An error occurred trying to access the endpoint messages/dropdown/" + userIdPrvdOrg);
	        }
	    });
}

function sendMessage() {
	
		var userIdPrvdOrg = $('#userIdPrvdOrg').val();
		var selectedRecipient = $('#selectMessageRecipient').val();
		var title = $('#sendMessageSubject').val();
		var content = $('#sendMessageContent').val();
		
	   $.ajax({
	        url: 'messages/send/' + userIdPrvdOrg + "/" + selectedRecipient +
	        	'?title=' + title +
	        	'&content=' + content,
	        datatype: 'String',
	        type: "post",
	        contentType: "application/json",
	        success: function (result) {
	        	if ($('#attachmentUpload').val() != '') {
	        		sendAttachment(userIdPrvdOrg);
	        	}
	        	displaySentMessage(title,content);
	        	$('#createMessageModal').hide();
	        	clearMessageModal();
	        },
	        error: function () {
	        	alert("Send failed.");
	        	$('#createMessageModal').hide();
	        	clearMessageModal();
	        }
	    });
}

function sendAttachment(idprvdorg) {
	
	// get most recent message I sent
    $.ajax({
        url: 'messages/fromme/' + idprvdorg,
    	datatype: 'json',
        type: "get",
        contentType: "application/json",
        success: function (result) {
        	
        	// jquery ajax does not handle file upload, using xhr instead
        	
        	var id = result[0].id;
        	
        	var file = document.getElementById('attachmentUpload').files[0];
        	
        	var formData = new FormData();
        	
        	formData.append('file', file);
        	
        	var xhr = new XMLHttpRequest();
        	
        	xhr.onreadystatechange = function () {
        		  if(xhr.readyState === 4 && xhr.status === 200) {
        		    	loadAttachmentsForMessage(id, 'from', parseInt($('#totalSentMessages').val()) - 1);
        		    	document.getElementById("attachmentUpload").value = "";
        		  }
        	};
        	
        	xhr.open('POST', 'attachments/add/' + id + "/" + file.name, true);
        	
        	xhr.send(formData);

        },
        error: function () {
        	$('#sentMessages').html("<div>An error occurred trying to access the endpoint " + 'messages/fromme/' + idprvdorg + "</div>");
        }
    });
	
}

function currentDate() {
	
	var today = new Date();
	
	var mm = today.getMonth() + 1;
	var dd = today.getDate();
	var year = today.getFullYear();
	var curHour = today.getHours() > 12 ? today.getHours() - 12 : (today.getHours());
	var curMinute = today.getMinutes() < 10 ? "0" + today.getMinutes() : today.getMinutes();
	var curMeridiem = today.getHours() > 12 ? "PM" : "AM";
	
	return mm + '/' + dd + '/' + year + ' ' + curHour + ':' + curMinute + ' ' + curMeridiem;
	
}

function clearMessageModal() {
	$('#sendMessageSubject').val('');
	$('#sendMessageContent').val('');
}

function displaySentMessage(title,content) {
	
	var index = $('#totalSentMessages').val();
	
	$('#totalSentMessages').val(parseInt(index) + 1);
	
	$('#sentMessagesHeader').html("Sent (" + $('#totalSentMessages').val() + ")");
	
	var newSentHeader = $('<div>').attr('id', 'sentMessagesHeader' + index).addClass('message-read');
	
	var sentHeaderRow = $('<div>').addClass('row');
	
	var colEnvelope = $('<div>').addClass('col-sm-1').addClass('col-sm-offset-1').addClass('form-inline');
	
	var spanToggle = $('<span>').attr('onclick', 'toggleSentMessageBody('+index+')');
	
	var envelope = $('<span>').addClass('fa').addClass('fa-envelope').attr('id', 'sentMessageIcon' + index);
	
	spanToggle.append(envelope);
	
	colEnvelope.append(spanToggle);
	
	sentHeaderRow.append(colEnvelope);
	
	var colName = $('<div>').addClass('col-sm-2').html($('#selectMessageRecipient option:selected').text());
	
	sentHeaderRow.append(colName);
	
	var colTitle = $('<div>').addClass('col-sm-3').html(title + '&nbsp;');
	
	var paperclipWrapper = $('<span>').attr('onclick', 'showAttachment(\'fromMessageAttachments'+index+'\')');
	
	var paperclip = $('<span>').attr('id', 'fromMessageAttachments'+index).attr('style', 'display:none;').
		addClass('fa').addClass('fa-paperclip');
		
	paperclipWrapper.append(paperclip);
	
	colTitle.append(paperclipWrapper);
	
	sentHeaderRow.append(colTitle);
	
	var colDate = $('<div>').addClass('col-sm-3').html(currentDate());
	
	sentHeaderRow.append(colDate);
	
	newSentHeader.append(sentHeaderRow);
	
	var sentBodyDiv = $('<div>').attr('id', 'sentMessageBody' + index).attr('style', 'display:none;').addClass('message-body');
	
	var sentBodyRow = $('<div>').addClass('row');
	
	var sentBodyCol = $('<div>').addClass('col-sm-10').addClass('col-sm-offset-2').html(content);
	
	sentBodyRow.append(sentBodyCol);
	
	sentBodyDiv.append(sentBodyRow);
	
	$('#sentMessages').prepend(sentBodyDiv).prepend(newSentHeader);
	
	
}

function loadProviderDetails(idprvdorg) {
	
    $.ajax({
        url: 'providerdetail/' + idprvdorg,
    	datatype: 'json',
        type: "get",
        contentType: "application/json",
        success: function (result) {
        	
        	var data = JSON.stringify(result);
        	//$('#divProviderDetail').html(data);
        	
        	$('#divProviderDetail').html(handlebars_providerDetailsTemplate(result));
        	$('#divProviderDetailImage').html('<img alt="' + result.nm_prvd
        			+ '" class="img-circle cw-portal-navbar-image" src="'
        			+ result.tximagelink + '" />');
        	
        	loadPrimaryProviderWorker(idprvdorg);
        	loadProviderAddress(idprvdorg);
        },
        error: function () {
        	$('#divProviderDetail').html("<div style='color:white;'>An error occurred trying to access the endpoint " + 'providerdetail/' + idprvdorg + "</div>");
        }
    });
	
}

function loadProviderAddress(idprvd) {

    $.ajax({
        url: 'providerAddress/' + idprvd,
        datatype: 'json',
        type: "get",
        contentType: "application/json",
        success: function (result) {
        	
        	$('#divProviderAddress').html('Address: '+result.tx_adress 
        			+ '&nbsp;&nbsp;&nbsp;&nbsp;<a class="provider-license" href="#" onclick="editPrvdAddress('+idprvd+');">Edit</a>'
        			+ '&nbsp;&nbsp;&nbsp;&nbsp;<a class="provider-license" href="#" onclick="loadSacwisUpdateReq('+idprvd+');">CCWIS Update History</a>');
        },
        error: function () {
        	$('#divProviderAddress').html("An error occurred trying to access the endpoint " + 'address/providerAddress/' + idprvd);
        }
    });	
	
}

function editPrvdAddress(p_idPrvd){
	//alert('PrvdId: ' + p_idPrvd);
	document.getElementById('id_grp').value = p_idPrvd;
	document.getElementById('dataContainer').style.display = 'inline';
	document.getElementById('dataSavedContainer').style.display = 'none';
	document.getElementById('updateType').value = "";
	document.getElementById('txUpdt').value = "";
	$('#sacwisUpdateModal').modal('show');
}

function loadSacwisUpdateReq(p_idPrvd) {

   $.ajax({
        url: 'ccwisupdate/all' ,
    	datatype: 'json',
        type: "get",
        contentType: "application/json",
        success: function (result) {
        	
        	$('#sacwisUpdateReqTable').bootstrapTable({
        		data: result,
        		classes: 'table table-hover table-striped',
        		columns: [{
        			field: 'id_grp',
        			title: 'Provider Id',
        			sortable: true
        		}, {
        			field: 'cd_type',
        			title: 'Update Type',
        			formatter: typeFormat,
        			sortable: true
        		}, {
        			field: 'tx_update',
        			title: 'Update Requested',
        			sortable: true
        		}, {
        			field: 'ts_cr',
        			title: 'Date Requested',
        			formatter: formatDateSlashes,
        			sortable: true
        		}, {
        			field: 'cd_stat',
        			title: 'Status',
        			sortable: true
        		}]
        	});
        	
        	$('#sacwisUpdateReqModal').modal('show');
        },
        error: function () {
        	$('#sacwisUpdateReqTable').html("<div style='color:white;'>An error occurred trying to access the endpoint ccwisupdate/all");
        }
    });
    
	
}

function typeFormat(value, row, index){
	if (value=='1'){
		return 'Address';
	} else if (value=='2'){
		return 'Phone';
	} else if (value=='3') {
		return 'Add Member in Home';
	} else if (value=='4') {
		return 'Remove Member in Home';
	} else if (value=='5') {
		return 'Change Member Name';
	}
	else {
		return '';
	}
}

function enblDsblTxt(){
	if (document.getElementById('updateType').value==""){
		document.getElementById('txUpdt').disabled = true;
		document.getElementById('txUpdt').value = '';
		document.getElementById('btnUpdtSave').disabled = true;
	} else {
		document.getElementById('txUpdt').disabled = false;
		document.getElementById('btnUpdtSave').disabled = false;
	}
}

function saveSacwisUpdate(){
	//alert(document.getElementById('id_grp').value);
	var m_url = 'ccwisupdate/add?id_grp=' + document.getElementById('id_grp').value 
			    +'&cd_grp=F'
			    +'&cd_type=' + document.getElementById('updateType').value 
			    +'&tx_update=' + document.getElementById('txUpdt').value 
			    +'&id_cr='+document.getElementById('id_grp').value 
			    +'&cd_stat=P';
	
	$.ajax({
        url: m_url,
        datatype: 'String',
        type: "POST",
        contentType: "application/json",
        success: function (result) {
//        	alert('saved');
        	document.getElementById('dataContainer').style.display = 'none';
        	document.getElementById('dataSavedContainer').style.display = 'inline';
        },
        error: function () {
        	alert('error');
        }
    });
	
}

function loadProviderPlacements(idprvdorg) {

    $.ajax({
        url: 'placements/episodesByProvider/' + idprvdorg,
        datatype: 'json',
        type: "get",
        contentType: "application/json",
        success: function (result) {
        	
        	$('#divChildrenInPlacement').html(handlebars_placementsTemplate(result));
        	
        	
        	for(var i = 0; i < result.length; i++) {
        	    var obj = result[i];

        	    loadChildInPlacementData(obj.idprsn);
        	    loadPrimaryCaseWorker(obj.idprsn, obj.idcase);
        	    // remove child address load
        	    // loadChildAddressData(obj.idprsn);
        	    loadCurrentMeetings(obj.idprsn);
        	}
        	
        	
        },
        error: function () {
        	$('#divChildrenInPlacement').html("An error occurred trying to access the endpoint " + 'placements/episodesByProvider/' + idprvdorg);
        }
    });
	
}

function loadChildAddressData(idprsn) {

    $.ajax({
        url: 'personAddress/' + idprsn,
        datatype: 'json',
        type: "get",
        contentType: "application/json",
        success: function (result) {
        	
        	$('#divChildAddress'+idprsn).html('Address: '+result.tx_adress);
        },
        error: function () {
        	$('#divChildAddress'+idprsn).html("An error occurred trying to access the endpoint " + 'address/personAddress/' + idprsn);
        }
    });	
	
}

function loadChildInPlacementData(idprsn) {

    $.ajax({
        url: 'person/' + idprsn,
        datatype: 'json',
        type: "get",
        contentType: "application/json",
        success: function (result) {
        	
        	$('#childName'+idprsn).val(result.nmfrst + ' ' + result.nmlst);
        	$('#divChildName'+idprsn).html('<a onclick=showDemographicInfo('+idprsn+');>'+result.nmfrst + ' ' + result.nmlst +'</a>' + spanChildPaymentIcon(idprsn));
        	//$('#divChildPlan'+idprsn).html(result.nmfrst);   
        	$('#divChildAge'+idprsn).html('Age: ' + result.qtage);
        	$('#divChildImage'+idprsn).html('<img src="'+result.tximagelink+'" class="img-circle cw-portal-navbar-image" ></img>');
        },
        error: function () {
        	$('#divChildName'+idprsn).html("An error occurred trying to access the endpoint " + 'person/' + idprsn);
        }
    });	
	
}

function showDemographicInfo(idprsn){
	//alert(idprsn);
	//$('#demographicInfoModal').modal('show');
	 $.ajax({
	        url: 'person/' + idprsn,
	        datatype: 'json',
	        type: "get",
	        contentType: "application/json",
	        success: function (result) {
	        	$('#demographicInfo').html(
	        			'<div class="row">'+
	        			'<div class="col-sm-5">Gender: '+result.gender+'</div>'+
	        			'<div class="col-sm-5">U.S. Citizen: '+result.uscitizen+'</div>'+
	        			'</div>'+
	        			'<div class="row">'+
	        			'<div class="col-sm-5">Birth Place: '+result.birthplace+'</div>'+
	        			'<div class="col-sm-5">Religion: '+result.religion+'</div>'+
	        			'</div>'+
	        			'<div class="row">'+
	        			'<div class="col-sm-5">Primary Language: '+result.primarylang+'</div>'+
	        			'<div class="col-sm-5">Secondary Language: '+result.secondlang+'</div>'+
	        			'</div>'
	        			
	        	);
	        	$('#demographicInfoModal').modal('show');
	        },
	        error: function () {
	        	$('#demographicInfo'+idprsn).html("An error occurred trying to access the endpoint " + 'person/' + idprsn);
	        }
	    });	
}

function spanChildPaymentIcon(idprsn) {
	return "&nbsp;&nbsp;" +
	'<a href="#" id="spanChildPayments' + idprsn + '" onclick="paymentsByChild(' + idprsn + ');">' +
	'<span class="fa fa-hand-holding-usd"></span></a>';
}

function loadCurrentMeetings(idprsn) {
    $.ajax({
        url: 'meetings/currentMeetingsByPerson/' + idprsn,
        datatype: 'json',
        type: "get",
        contentType: "application/json",
        success: function (result) {
        	$('#divMeetings'+idprsn).html(handlebars_meetingsTemplate(result));
        	
        },
        error: function () {
        	$('#divMeetings'+idprsn).html("An error occurred trying to access the endpoint " + 'meetings/currentMeetingsByPerson/' + idprsn);
        }
    });	
}

function viewAllMeetings(idprsn) {
	
	   $.ajax({
	        url: 'meetings/allMeetingsByPerson/' + idprsn,
	    	datatype: 'json',
	        type: "get",
	        contentType: "application/json",
	        success: function (result) {
	        	
	        	$('#allMeetingsTable').bootstrapTable({
	        		data: result,
	        		classes: 'table table-hover table-striped',
	        		rowStyle: meetingsTableRowStyle,
	        		columns: [{
	        			field: 'txtype',
	        			title: 'Type',
	        			sortable: true
	        		}, {
	        			field: 'dtstart',
	        			title: 'Date',
	        			formatter: formatDateSlashes,
	        			sortable: true
	        		}, {
	        			field: 'dtstart',
	        			title: 'Start Time',
	        			formatter: formatDateTimeOnly,
	        			sortable: true
	        		}, {
	        			field: 'dtend',
	        			title: 'End Time',
	        			formatter: formatDateTimeOnly,
	        			sortable: true
	        		}, {
	        			field: 'txlocation',
	        			title: 'Location',
	        			sortable: true
	        		}]
	        	});
	        	
	        	$('#allMeetingsModal').modal('show');
	        },
	        error: function () {
	        }	        
	    });
	
}

function meetingsTableRowStyle(row, index) {
	if (row.current == 'N') {
		return {classes: "meeting-gray"};
	}
	else {
		return {};
	}
}

function clearMeetingsModal() {
	$('#divAllMeetings').html('');
	var table = $('<table>').attr('id', 'allMeetingsTable');
	$('#divAllMeetings').append(table);
}

function createMessageToCaseWorker(idprsn_kid, subject) {
	var idprsn_worker = $('#idPrimaryCaseWorker'+idprsn_kid).val();
	createMessage(idprsn_worker, subject);
}

function loadPrimaryCaseWorker(idprsn, idcase) {
    $.ajax({
        url: 'casePrimaryAssignment/' + idcase,
        datatype: 'json',
        type: "get",
        contentType: "application/json",
        success: function (result) {
        	// idprsn passed in is the id of the kid
        	// result.id_prsn is the id of the case worker
        	$('#idPrimaryCaseWorker'+idprsn).val(result.id_prsn);
        	$('#divPrimaryCaseWorker'+idprsn).html("Case Worker: " + result.nm_prsn);
        },
        error: function () {
        	$('#divPrimaryCaseWorker'+idprsn).html("An error occurred trying to access the endpoint " + 'casePrimaryAssignment/' + idcase);
        }
    });	
}

function loadPrimaryProviderWorker(idprvdorg) {
    $.ajax({
        url: 'providerPrimaryAssignment/' + idprvdorg,
        datatype: 'json',
        type: "get",
        contentType: "application/json",
        success: function (result) {
        	$('#divPrimaryProviderWorker').html("Provider Worker: " + result.nm_prsn);
        },
        error: function () {
        	$('#divPrimaryProviderWorker').html("<div style='color:white;'>An error occurred trying to access the endpoint " + 'providerPrimaryAssignment' + idprvdorg + "</div>");
        }
    });	
}