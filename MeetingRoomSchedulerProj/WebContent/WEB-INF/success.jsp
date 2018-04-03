<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/style.css">
<title>Meeting Room Scheduler</title>
</head>
<body>
<%@include file="/WEB-INF/header.jsp"%>
	<h3>
		Your meeting has been created successfully!
	</h3>
			<%@include file="/WEB-INF/form.jsp" %>
			<hr>
			<%@include file="/WEB-INF/list.jsp"%>
			<%@include file="/WEB-INF/footer.jsp"%>
</body>
</html>