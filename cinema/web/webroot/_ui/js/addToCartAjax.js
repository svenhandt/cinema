$(document).ready(function() {
	
	if($("#totalsum").text() == '') {
        $("#gotobooking").attr('style', 'display:none');
	}
	else {
        $("#gotobooking").attr('style', 'display:inline; position: absolute; right: 20px; font-size: 18px');
	}

	$("input[id^=seat]").click(function() {
		$("#addpresentationform").submit();
	});
	
	$("#addpresentationform").submit(function(data) {
		$.ajax({
			url : $(this).attr('action'),
			type : $(this).attr('method'),
			data : $(this).serialize(),
			dataType: 'json',
			success : function(data) {
				var priceInformation = data.price;
				if(priceInformation == '') {
                    $("#gotobooking").attr('style', 'display:none');
				}
				else {
                    $("#gotobooking").attr('style', 'display:inline; position: absolute; right: 20px; font-size: 18px');
				}
				$("#totalsum").text(priceInformation);
			}
		});
		return false;
	});

});
