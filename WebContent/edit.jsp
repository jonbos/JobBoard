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
<title>EDIT</title>
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
			<c:choose>
				<c:when test="${param.type == 'employer'}">
					<h1 class="title">
						Editing
						<c:out value="${toEdit.getName() }"></c:out>
					</h1>
					<form
						method="post"
						action="editServlet?type=employer"
						class="form-horizontal">
						<jsp:include page="employerForm.jsp" />
						<input
							type="hidden"
							name="id"
							value="${toEdit.getId() }" />
					</form>
				</c:when>
				<c:when test="${param.type == 'job'}">
					<form
						method="post"
						action="editServlet?type=job"
						class="form-horizontal">
						<jsp:include page="jobForm.jsp" />
						<input
							type="hidden"
							name="id"
							value="${toEdit.getId() }" />
					</form>
				</c:when>
			</c:choose>
		</div>
	</div>
</body>
</html>