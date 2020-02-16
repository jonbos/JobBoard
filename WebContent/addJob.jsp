<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Job Board | Add Job</title>
<link
	rel="stylesheet"
	href="base.css" />
<link
	rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bulma@0.8.0/css/bulma.min.css">

</head>
<body>
	<jsp:include page="header.html" />
	<div class="container is-three-fifths is-offset-one-fifth">
		<div class="box">
			<h1 class="title">Add Job</h1>
			<form
				method="post"
				action="addJobServlet"
				id="jobForm">
				<input
					type="text"
					name="jobTitle"
					placeholder="Job Title"> <select
					id="empId"
					name="empId">
					<jsp:useBean
						id="dao"
						class="controller.EmployerDAO" />
					<c:forEach
						var="employer"
						items="${dao.showAllEmployers()}">
						<option value="${employer.getId()}">${employer.getName()}</option>

					</c:forEach>
				</select> <input
					type="submit"
					value="submit">
			</form>
			<textarea
				rows="30"
				name="jobDesc"
				cols="100"
				form="jobForm"
				placeholder="Enter job description"></textarea>
		</div>
	</div>
</body>
</html>
