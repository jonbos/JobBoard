<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bulma@0.8.0/css/bulma.min.css"
/>
<link
	rel="stylesheet"
	href="base.css"
/>
</head>
<body class="">
	<jsp:include page="header.html" />

	<jsp:useBean
		id="dao"
		class="controller.JobHelper"
	/>

	<div class="container hero-box">
		<section>
			<div class="box is-bordered is-three-fifths is-offset-one-fifth">
				<table class="table is-striped is-hoverable is-fullwidth">
					<thead>
						<tr>
							<th>Job Title</th>
							<th>Employer</th>
							<th>Date Posted</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach
							var="job"
							items="${dao.showAllJobs()}"
						>
							<tr>
								<td><a href="viewJobDetailsServlet?id=${job.getId() }"><strong>${job.getTitle()}</strong></a></td>
								<td><a
									href="viewEmployerDetailsServlet?id=${job.getEmployer().getId() }"
								>${job.getEmployer().getName()}</a></td>
								<td>${job.getCreated()}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</section>
	</div>
</body>
</html>