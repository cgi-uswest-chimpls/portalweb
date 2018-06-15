var handlebars_placementsTemplate;

function loadProvider() {
	
	handlebars_placementsTemplate = Handlebars.compile($('#placementsTemplate').html());
	
	registerHandlebarsHelpers();
	
	var idprvdorg = 7000001; // get dynamically

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
        //url: 'providerdetail/' + idprvdorg,
        url: 'providerdetail/all',
    	datatype: 'json',
        type: "get",
        contentType: "application/json",
        success: function (result) {
        	
        	var data = JSON.stringify(result);
        	$('#divProviderDetail').html(data);
        	
        	//$('#divChildrenInPlacement').html(handlebars_placementsTemplate(result));
        },
        error: function () {
        	$('#divProviderDetail').html("An error occurred trying to access the endpoint " + 'providerdetail/all');
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
        	//$('#divChildrenInPlacement').html(data);
        	
        	$('#divChildrenInPlacement').html(handlebars_placementsTemplate(result));
        },
        error: function () {
        	$('#divChildrenInPlacement').html("An error occurred trying to access the endpoint " + 'placements/episodesByProvider/' + idprvdorg);
        }
    });
	
}