
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>

<html lang="en">
<head>
<meta charset="utf-8">
<title>BCSG Java Test</title>

</head>
<body>
	<h2>BCSG Java Test</h2>
	<br />
	<table cellpadding="10" border="black">
		<c:forEach var="card" items="${cardList}">
			<tr>
				<td>${card.bank}</td>
				<td>${card.code}</td>
				<td><fmt:formatDate value="${card.expiry}" type="both"
						pattern="MMM-yyyy" /></td>
			</tr>
		</c:forEach>
	</table>
	<br/>
	<c:url value="/manual" var="manualEntry" />
	<a href="${manualEntry}">Click to here to create or update cards</a>

</body>
</html>
