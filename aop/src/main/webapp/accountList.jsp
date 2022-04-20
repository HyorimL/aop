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
	<h3>계좌리스트</h3>
	
		<div id="list">
			
		</div>
		
		<div id="result">
			
		</div>
	
<script>
	
var url = "/prj/accountList";

$.ajax(url)
.done(function(datas){
	console.log(datas)
	for(d of datas) {
		const accDiv = $("<div>").data("usenum", d.fintech_use_num);
		const p1 = $("<p>").html("은행명: " +  d.bank_name);
		const p2 = $("<p>").html("계좌이름: " + d.account_alias);
		const p3 = $("<p>").html("계좌번호: " + d.account_num_masked);
		const p4 = $("<p>").html("예금주: " + d.account_holder_name);
		const balBtn = $("<button>").html("잔액조회").addClass("balView");
		const trBtn = $("<button>").html("거래내역조회").addClass("btnTrans");
		
		$(accDiv).append(p1, p2, p3, p4, balBtn, trBtn).appendTo($('#list'));
	
	}
})

$("#list").on("click", ".balView", function() {
	var num = $(this).closest("div").data("usenum");
	let a = $(this).parent();
	
	$.ajax({		
		url : "/prj/balance",
		data : {fintechUseNum : num}
	}).done(function(datas) {
		$(a).append( $("<div>").html(datas + "원") );
	})	
})

$("#list").on("click", ".btnTrans", function(){
	
	$("#result").empty();
	
	var num = $(this).closest("div").data("usenum");
	
	$.ajax({
		url : "/prj/transList",
		data : {fintechUseNum : num}
		
	}).done(function(data){
		console.log(data)
	
		$("#result").append($("<p>").html("=====" + data.bank_name + " 거래내역====="));
		
		for(d of data.res_list) {
			const p = $("<p>")
			const s1 = $("<span>").html(d.inout_type);
			const s2 = $("<span>").html(d.tran_amt + "원");
			const s3 = $("<span>").html(d.print_content);
			const s4 = $("<span>").html(d.after_balance_amt);
			const s5 = $("<span>").html(d.tran_date);
			$(p).append(s1, s2, s3, s4, s5).appendTo("#result")
		}	
	
	})
}) 

</script>
</body>
</html>