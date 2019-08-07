
$(".answer-write input[type=submit]").click(addAnswer);

function addAnswer(e) {
  e.preventDefault();
  var queryString = $(".answer-write").serialize();
  
  var url = $(".answer-write").attr("action");
  
  $.ajax({
	type : 'post',
	url : url,
	data : queryString,
	dataType : 'json',
	error : onError,
	success : onSuccess
  });
  
}

function onError(){
	
}

function onSuccess(data, status) {
	console.log(data)
	var anserTemplate = $("#answerTemplate").html();
	var template = anserTemplate.format(data.writer.userId, data.formattedCreateDate, data.contents, data.question.id,data.id);
	$(".qna-comment-slipp-articles").prepend(template);
	$(".answer-write textarea").val("");
	
	$("body a.link-delete-article").click(deleteAnswer);
}

$("body a.link-delete-article").click(deleteAnswer);

function deleteAnswer(e) {
	e.preventDefault();
	var thisBtn = $(this);
	var url = thisBtn.attr("href");
	console.log(url)
	$.ajax({
		type: 'delete',
		url : url,
		dataType : 'json',
		error : function(xhr, status){
			console.log("error");
		},
		success : function(data, status){
			console.log(data);
			if(data.valid){
				thisBtn.closest("article").remove();
			}
		}
	})
}


String.prototype.format = function() {
  var args = arguments;
  return this.replace(/{(\d+)}/g, function(match, number) {
    return typeof args[number] != 'undefined'
        ? args[number]
        : match
        ;
  });
};