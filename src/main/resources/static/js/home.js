$("#form-ask").submit(function(evt) {
	evt.preventDefault();

	var question = {};
	question.prompt = $("#prompt").val();

	if (question.prompt.length <= 0)
		return;

	$.ajax({
		method : "POST",
		url : "/ask",
		data : question,
		beforeSend : function() {
			$("#response-message").text("");
			$("#response").hide();
			$('#btn-submit').prop('disabled', true);
			$('#btn-submit').hide();
			$('#loading').show();
		},
		success : function(data) {
			$("#response").show();
			$("#response-message").text(data.choices[0].text);

		},
		statusCode : {
			429 : function(xhr) {
				console.log('> error 429:', xhr.status);
				$("#response").show();
				$("#response-message").text("error getting response, try again!");
			}
		},
		error : function(xhr) {
			console.log("> error:", xhr.responseText);
			$("#response").show();
			$("#response-message").text("error getting response, try again!");
		},
		complete : function() {
			$('#btn-submit').prop('disabled', false);
			$('#btn-submit').show();
			$('#loading').hide();
		}
	});
});