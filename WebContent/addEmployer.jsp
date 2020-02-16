<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Job Board | Add Employer</title>
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
			<h1 class="title">Add Employer</h1>
			<form
				method="post"
				action="addEmployerServlet"
				id="empForm">
				<input
					type="text"
					name="empName"
					placeholder="Employer Name"
					required="true"> <input
					type="text"
					name="empLocation"
					placeholder="Employer Location"
					required="true"> 
					
					<input
					type="submit"
					value="submit">
			</form>
			<textarea
				rows="30"
				name="empDescription"
				cols="100"
				form="empForm"
				placeholder="Enter employer description"></textarea>

		</div>
	</div>

</body>
</html>