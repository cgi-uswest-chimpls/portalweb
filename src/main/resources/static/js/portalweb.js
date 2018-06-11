function loadProvider() {
	
	var idprvdorg = 9000000; // get dynamically
	
	loadProviderPlacements(idprvdorg);


}

function loadProviderPlacements(idprvdorg) {
	
    $.ajax({
        url: 'placements/episodesByProvider/' + idprvdorg,
        datatype: 'json',
        type: "get",
        contentType: "application/json",
        success: function (result) {
        	
        	var data = JSON.stringify(result);
        	
        	$('#divChildrenInPlacement').html(data);
        },
        error: function () {
        	$('#divChildrenInPlacement').html("An error occurred trying to access the endpoint " + 'placements/episodesByProvider/' + idprvdorg);
        }
    });
	
}