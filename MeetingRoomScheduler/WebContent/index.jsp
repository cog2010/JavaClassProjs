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

		<form method=post action="schedule-update">
			<div class="row">
				<div class="container col form-group">
					<label for="meeting-name">Meeting Name*</label> <input
						name="meeting-name" class="form-control" id="meetingName" required>
				</div>
				<div class="container col form-group">
					<label for="room-number">Room Number*</label> 
					<select name="room-number" class="form-control" id="roomNUmber" required>
						<option value="" disabled selected="selected">Room Number</option>
						<option value="101">101</option>
						<option value="102">102</option>
						<option value="103">103</option>
						<option value="104">104</option>
						<option value="105">105</option>
					</select>
				</div>
				<div class="container col form-group">
					<label for="occupant-count">Occupants*</label> <input
						type="number" min="1" max="20" name="occupant-count" class="form-control" id="occupantCount"
						required>
				</div>
				<div class="container col form-group">
					<label for="meeting-date">Meeting Date*</label> <input
						TYPE="date" name="meeting-date" class="form-control" id="meetingDate" required>
				</div>
				<div class="container col form-group">
					<label for="meeting-time">Meeting Time*</label> 
					<select name="meeting-time" class="form-control" id="meetingTime" required>
						<option value="" disabled selected="selected">Meeting Time</option>
						<option value="7">7:00am-8:00am</option>
						<option value="8">8:00am-9:00am</option>
						<option value="9">9:00am-10:00am</option>
						<option value="10">10:00am-11:00am</option>
						<option value="11">11:00am-12:00pm</option>
						<option value="12">12:00pm-1:00pm</option>
						<option value="13">1:00pm-2:00pm</option>
						<option value="14">2:00pm-3:00pm</option>
						<option value="15">3:00pm-4:00pm</option>
						<option value="16">4:00pm-5:00pm</option>
						<option value="17">5:00pm-6:00pm</option>
					</select>
				</div>
				<div class="container col form-group">
					<label for="conference-call-number">Conference Call</label> <input name="conference-call-number"
						class="form-control" id="conferenceCallNumber">
				</div>
				<div class="container col form-group">
					<label for="submit-button"></label>
					<button class="btn btn-primary col" name="submit-button"
						type="submit">Submit</button>
				</div>
			</div>
		</form>
		<hr>
		<%@include file="/WEB-INF/list.jsp"%>
	</div>
	<%@include file="/WEB-INF/footer.jsp"%>
	
</body>
</html>