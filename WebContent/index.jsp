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
<title>Job Board | HOME</title>
<link
	rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bulma@0.8.0/css/bulma.min.css" />
<link
	rel="stylesheet"
	href="base.css" />
</head>
<body class="">
	<jsp:include page="header.html" />

	<jsp:useBean
		id="dao"
		class="controller.JobDAO" />

	<div class="container">
		<section class="hero">
			<div class="hero-body">
				<div class="container">
					<h1 class="title">Welcome to Job Search! You're viewing all
						listings</h1>
					<h2 class="subtitle">Click on a job listing or employer to
						view details.</h2>
				</div>
			</div>
		</section>

		<section>
			<div class="box is-bordered is-three-fifths is-offset-one-fifth">
				<table class="table is-striped is-hoverable is-fullwidth">
					<thead>
						<tr>
							<th>Job Title</th>
							<th>Employer</th>
							<th>Location</th>
							<th>Date Posted</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach
							var="job"
							items="${dao.getAll()}">
							<tr>
								<td><a
									href="viewDetailsServlet?type=job&id=${job.id }"><strong>${job.title}</strong></a></td>
								<td><a
									href="viewDetailsServlet?type=employer&id=${job.employer.id }">${job.employer.name}</a></td>
								<td>${job.employer.location}</td>

								<td>${job.created}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</section>
	</div>
</body>
</html>