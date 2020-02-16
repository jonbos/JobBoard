<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>

<section>
	<div class="container">
		<div class="columns">
			<div class="column is-three-fifths">
				<h1 class="title">${toView.getName() }</h1>
				<h1 class="subtitle">${toView.getLocation() }</h1>
				<div class="box">
					<h3 class="is-size-4">Description</h3>

					${toView.getDescription()}
				</div>
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
							class="controller.JobDAO" />

						<c:forEach
							var="job"
							items="${dao.searchForJobByEmployerId(toView.getId())}">
							<tr>
								<td><a href="viewDetailsServlet?type=job&id=${job.getId() }"><strong>${job.getTitle()}</strong></a></td>
								<td>${job.getCreated()}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<form
			method="post"
			action="navigationServlet?type=employer&id=${toView.getId()}">
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
</section>
