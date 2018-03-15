<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>결제준비</title>
</head>
<body>
	<h1>결제준비</h1>
	<p>사용자에게 결제를 요청하기</p>
	<p>실제 사용 시 상품 수량을 제외한 나머지 부분은 서버에서 처리한다.</p>
	<p>테스트일 경우에는 상품 총액은 백만원 이상 입력하면 안됩니다.</p>
	
	<form action="<c:url value='/kakao-pay/ready.do'/>" method="post">
		<label>가맹점 주문번호: <input type="text" name="partner_order_id"></label><br>
		<label>가맹점 회원 ID: <input type="text" name="partner_user_id"></label><br>
		<label>상품명: <input type="text" name="item_name"></label><br>
		<label>상품 수량: <input type="number" name="quantity"></label><br>
		<label>상품 총액: <input type="number" name="total_amount"></label><br>
		<label>상품 비과세 금액: <input type="number" name="tax_free_amount"></label><br>
		<input type="submit" value="결제하기">
	</form>
</body>
</html>