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
<link
	rel="stylesheet"
	href="base.css"
/>
<link
	rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bulma@0.8.0/css/bulma.min.css"
>

<meta charset="UTF-8">
<title>Employer Details</title>
</head>
<body>
	<jsp:include page="header.html" />
	<section>
		<div class="container">
			<div class="box">
				<div class="columns">
					<div class="column is-three-fifths">
						<h1 class="title">${employer.getName() }</h1>
						<h1 class="subtitle">${employer.getLocation() }</h1>
						<div class="box">
						<h3 class="is-size-4">Description</h3>
						
						${employer.getDescription()}</div>
					</div>
					<div class="column">
						<label class="subtitle"> Current Listings </label>

						<table class="table is-striped is-hoverable">
							<thead>
								<tr>
									<th>Job Title</th>
									<th>Date Posted</th>

								</tr>
							</thead>
							<tbody>
								<jsp:useBean
									id="dao"
									class="controller.JobHelper"
								/>

								<c:forEach
									var="job"
									items="${dao.searchForJobByEmployerId(employer.getId())}"
								>
									<tr>
										<td><a href="viewJobDetailsServlet?id=${job.getId() }"><strong>${job.getTitle()}</strong></a></td>
										<td>${job.getCreated()}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>

			</div>
		</div>
	</section>
</body>
</html>