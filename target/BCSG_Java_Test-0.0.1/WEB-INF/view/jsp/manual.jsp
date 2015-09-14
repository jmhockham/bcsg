<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<meta charset="utf-8">
<title>File Upload</title>
</head>
<body>
	<p>Manually enter card details below.</p>
	<p>If the card long number exists, that cards details will be
		updated instead.</p>
	<form:form method="POST" action="createCard" modelAttribute="card">
		<table>
			<tr>
				<td>Bank Name:</td>
				<td><form:input path="bank" /></td>
				<td align="left"><form:errors path="bank" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Card Long Number:</td>
				<td><form:input path="code" /></td>
				<td align="left"><form:errors path="code" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Expiry Date:</td>
				<fmt:formatDate value="${card.expiry}" var="dateString"
					pattern="dd/MM/yyyy" />
				<td><form:input path="expiry" value="${dateString}" /></td>
				<td align="left"><form:errors path="expiry" cssClass="error" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Save Details" /></td>
			</tr>
		</table>
	</form:form>
	<br />
	<p>Select a CSV file to upload.</p>
	<p>The code assumes there are no headers, and dates are formatted
		dd/MM/yyyy</p>
	<form method="post" action="createCardsFromCsv"
		class="well form-vertical" enctype="multipart/form-data">
		<input type="file" name="file" />
		<button type="submit" class="btn">Upload</button>
	</form>
</body>
</html>