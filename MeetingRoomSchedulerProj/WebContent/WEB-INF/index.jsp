<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/style.css">
<title>Meeting Room Scheduler</title>
</head>
<body>
	<%@include file="/WEB-INF/header.jsp"%>
	<div class="container-fluid">
		<%@include file="/WEB-INF/form.jsp"%>
		<hr>
		<%@include file="/WEB-INF/list.jsp"%>
	</div>
	<%@include file="/WEB-INF/footer.jsp"%>

</body>
</html>