<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
</head>
<body>
	계좌목록
	<div id="list">
		<div class="acc" data-usenum="12323232">
			<span>대구은행</span><span>11112222***</span>
		</div>
	</div>
	<div id="result"></div>
	
	<script>
	$(function(){
		$.ajax("account")
	}).done(function(result){
		for(bank of result.res_list) {
			$("<div>").addClass("acc")
					  .data("usenm", bank.fintec___)
					  .append($("<span>").html(bank.bank_name))
					  .append($("<span>").html(bank.bank_acc___))
					  .append($("<input>").val(bank.별칭))
					  .append($("<button>").html("거래내역").addClass("btnTrans"))
					  .appendTo("#list")
		}
	})
	//잔액조회
	$("#list").on("click", "span", function(){
		var num = $(this).closest(".acc").data("usenum");
		$.ajax({
			url:"balance",
			data: {fintechUseNum : num},
		}).done(function(data){
			$("#result").html(data); 
		})
	})
	</script>
</body>
</html>