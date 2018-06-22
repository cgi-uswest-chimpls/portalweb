var handlebars_placementsTemplate;
var handlebars_providerDetailsTemplate;

function loadProvider() {
	
	handlebars_placementsTemplate = Handlebars.compile($('#placementsTemplate').html());
	handlebars_providerDetailsTemplate = Handlebars.compile($('#providerDetailsTemplate').html());
	
	registerHandlebarsHelpers();
	
	var idprvdorg = 7000000; // get dynamically after login page is developed

	loadProviderDetails(idprvdorg);
	
	loadProviderPlacements(idprvdorg);


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
        },
        error: function () {
        	$('#divProviderDetail').html("<div style='color:white;'>An error occurred trying to access the endpoint " + 'providerdetail/' + idprvdorg + "</div>");
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
        	}
        	
        	
        },
        error: function () {
        	$('#divChildrenInPlacement').html("An error occurred trying to access the endpoint " + 'placements/episodesByProvider/' + idprvdorg);
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
        	
        	$('#divChildName'+idprsn).html(result.nmfrst + ' ' + result.nmlst);
        	$('#divChildPlan'+idprsn).html(result.nmfrst + "'s Plan");   
        	$('#divChildAge'+idprsn).html('Age: ' + result.qtage);
        	$('#divChildImage'+idprsn).html('<img src="'+result.tximagelink+'" class="img-circle cw-portal-navbar-image" ></img>');
        },
        error: function () {
        	$('#divChildName'+idprsn).html("An error occurred trying to access the endpoint " + 'person/' + idprsn);
        }
    });	
	
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