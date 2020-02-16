<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>


<section>
	<div class="container hero-box">
		<div class="column">
			<h1 class="title">${toView.getTitle() }</h1>
			<h1 class="subtitle">
				<a
					href="viewDetailsServlet?type=employer&id=${toView.getEmployer().getId() }">${toView.getEmployer().getName() }</a>
			</h1>
			<div class="box is-bordered">
				<h2 class="subtitle">Description</h2>
				${toView.getJobDescription()}
			</div>
			<form
				method="post"
				action="navigationServlet?type=job&id=${toView.getId()}">
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
</section>
