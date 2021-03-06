<!-- created with bulma form builder
https://jesobreira.github.io/Bulma-Form-Builder/
 -->

<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>

<fieldset>

	<!-- Text input-->
	<div class="field">
		<label
			class="label"
			for="jobTitle">Job Title</label>
		<div class="control">
			<input
				id="jobTitle"
				name="jobTitle"
				type="text"
				placeholder="<c:out value="${empty toEdit ? 'Job Title' : ''}" />"
				value="<c:out value='${empty toEdit ? "" : toEdit.title }' />"
				class="input "
				required>
		</div>
	</div>

	<!-- Select Basic -->
	<div class="field">
		<label
			class="label"
			for="jobEmployer">Employer</label>
		<div class="control">
			<div class="select">
				<select
					id="jobEmployer"
					name="jobEmployer"
					class="">
				<option disabled selected="selected">Select Employer</option>
					<jsp:useBean
						id="dao"
						class="controller.EmployerDAO" />

					<c:forEach
						var="employer"
						items="${dao.showAllEmployers()}">
						<option value="${employer.id }"
						<c:if test="${employer.getName() == toEdit.employer.name }">
							<c:out value='selected="selected'/>
						</c:if>
						>
							${employer.name }
						</option>
					</c:forEach>
				</select>
			</div>
		</div>
	</div>

	<!-- Textarea -->
	<div class="field">
		<label
			class="label"
			for="jobDescription">Job Description</label>
		<div class="control">
			<textarea
				class="textarea"
				id="jobDescription"
				placeholder="<c:out value="${empty toEdit ? 'Job Description' : ''}" />"
				name="jobDescription"><c:out value="${empty toEdit ? '' : toEdit.jobDescription }" /></textarea>
		</div>
	</div>
	<!-- Button -->
	<div class="field">
		<label
			class="label"
			for="singlebutton-0"></label>
		<div class="control">
			<button
				id="singlebutton-0"
				name="singlebutton-0"
				class="button is-inverted">Submit</button>
		</div>
	</div>
</fieldset>
