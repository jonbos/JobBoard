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
<title>Job Board | Job Details</title>
<link
	rel="stylesheet"
	href="base.css" />
<link
	rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bulma@0.8.0/css/bulma.min.css">

</head>
<body class="">
	<jsp:include page="header.html" />
	<section>
		<div class="container hero-box">
			<div class="column">
				<div class="box has-background-grey-lighter">
					<h1 class="title">${job.getTitle() }</h1>
					<h1 class="subtitle">
						<a
							href="viewEmployerDetailsServlet?id=${job.getEmployer().getId() }">${job.getEmployer().getName() }</a>
					</h1>
					<label class="label">Description</label>
					<div class="box is-bordered">${job.getJobDescription()}</div>
					<form
						method="post"
						action="navigationServlet?type=job&id=${job.getId()}">
						<div class="field is-grouped">
							<div class="control">
								<button
									name="action"
									value="edit"
									class="button is-link">Edit</button>
							</div>
							<div class="control">
								<button
									name=action
									value="delete"
									class="button is-link is-danger">Delete</button>
							</div>
						</div>

					</form>

				</div>
			</div>
		</div>
	</section>
</body>
</html>