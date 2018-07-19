var handlebars_placementsTemplate;
var handlebars_providerDetailsTemplate;
var handlebars_quicklinksTemplate;

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
	
    $(".authenticated").show();
    $(".unauthenticated").hide();
	
	handlebars_placementsTemplate = Handlebars.compile($('#placementsTemplate').html());
	handlebars_providerDetailsTemplate = Handlebars.compile($('#providerDetailsTemplate').html());
	handlebars_quicklinksTemplate = Handlebars.compile($('#quicklinksTemplate').html());
	
	registerHandlebarsHelpers();
	
	var idprvdorg;
	
    $.ajax({
        url: 'currentUser',
    	datatype: 'json',
        type: "get",
        contentType: "application/json",
        success: function (result) {
        	$('#userCounty').val(result.county);
        	$('#userIdPrvdOrg').val(result.idprvdorg);
        	
        	idprvdorg = result.idprvdorg;
        	
        	loadProviderDetails(idprvdorg);
        	loadProviderPlacements(idprvdorg);
        },
        error: function () {
        	$('#divProviderDetail').html("<div style='color:white;'>An error occurred trying to access the endpoint /currentUser");
        }
    });
	




}

function help() {
	
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
}

function paymentsByChild(idprsn) {

	   $.ajax({
	        url: 'paymentsByPerson/' + idprsn ,
	    	datatype: 'json',
	        type: "get",
	        contentType: "application/json",
	        success: function (result) {
	        	
	        	$('#paymentsByChildTitle').html('Payments for ' + result[0].txpersonname);
	        	
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

function registerHandlebarsHelpers() {
	
	Handlebars.registerHelper('formatDateSlashes', function(text, url) {
		  text = Handlebars.Utils.escapeExpression(text);
		  url  = Handlebars.Utils.escapeExpression(url);

		  var year = text.substring(0,4);
		  var month = parseInt(text.substring(5,7));
		  var day = parseInt(text.substring(8,10));

		  return new Handlebars.SafeString(month + "/" + day + "/" + year);
	});
	
}

function formatDateSlashes(value, row, index, field) {
	  var year = value.substring(0,4);
	  var month = parseInt(value.substring(5,7));
	  var day = parseInt(value.substring(8,10));

	  return month + "/" + day + "/" + year;
}

function formatDollars(value, row, index, field) {
	return '$' + Number(Math.round(parseFloat(value)+'e2')+'e-2').toFixed(2);
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
        			+ '&nbsp;&nbsp;&nbsp;&nbsp;<a class="provider-license" href="#" onclick="editPrvdAddress('+idprvd+');">Edit</a>');
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
	var m_url = 'sacwisupdate/add?id_grp=' + document.getElementById('id_grp').value 
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
        	
        	var data = JSON.stringify(result);

        	$('#divChildrenInPlacement').html(handlebars_placementsTemplate(result));
        	
        	
        	for(var i = 0; i < result.length; i++) {
        	    var obj = result[i];

        	    loadChildInPlacementData(obj.idprsn);
        	    loadPrimaryCaseWorker(obj.idprsn, obj.idcase);
        	    loadChildAddressData(obj.idprsn);
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
        	
        	$('#divChildName'+idprsn).html(result.nmfrst + ' ' + result.nmlst + spanChildPaymentIcon(idprsn));
        	$('#divChildPlan'+idprsn).html(result.nmfrst + "'s Plan");   
        	$('#divChildAge'+idprsn).html('Age: ' + result.qtage);
        	$('#divChildImage'+idprsn).html('<img src="'+result.tximagelink+'" class="img-circle cw-portal-navbar-image" ></img>');
        },
        error: function () {
        	$('#divChildName'+idprsn).html("An error occurred trying to access the endpoint " + 'person/' + idprsn);
        }
    });	
	
}

function spanChildPaymentIcon(idprsn) {
	return "&nbsp;&nbsp;" +
	'<span id="spanChildPayments' + idprsn + '" onclick="paymentsByChild(' + idprsn + ');">' +
	'<span class="fa fa-hand-holding-usd"></span></span>';
}

function loadPrimaryCaseWorker(idprsn, idcase) {
    $.ajax({
        url: 'casePrimaryAssignment/' + idcase,
        datatype: 'json',
        type: "get",
        contentType: "application/json",
        success: function (result) {
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