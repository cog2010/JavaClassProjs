<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Meeting Room Scheduler</title>

<jsp:useBean id="today" class="java.util.Date" scope="page" />

<c:set var="date" scope="request" value="${meetingDate}" />
<c:set var="list" scope="request" value="${meetingList}" />

</head>
<body>
	<h1>Meetings Scheduled for:</h1>
	<c:if test="${meetingDate == null}">
		<h2><f:formatDate value="${today}" pattern="MM-dd-yyyy" /></h2>
	</c:if>
	<h2>${date}</h2>
	<table class="table table-hover" id = "meeting-table">
		<thead>
			<tr>
				<th>Meeting Name</th>
				<th>Meeting Time</th>
				<th>Room Number</th>
				<th>Number of Occupants</th>
				<th>Conference Call Number</th>
				<th>Delete?</th>
			</tr>
		</thead>
		<tbody>
			${list}
		</tbody>
	</table>
</body>
</html>