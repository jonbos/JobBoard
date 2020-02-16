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
			for="empName">Employer Name</label>
		<div class="control">
			<input
				class="input"
				id="empName"
				name="empName"
				type="text"
				placeholder="<c:out value="${empty toEdit ? 'Name' : ''}" />"
				value="<c:out value='${empty toEdit ? "" : toEdit.getName() }' />"
				required>
		</div>
	</div>

	<!-- Text input-->

	<div class="field">
		<label
			class="label"
			for="empLocation">Employer Location</label>
		<div class="control">
			<input
				id="empLocation"
				name="empLocation"
				type="text"
				placeholder="<c:out value="${empty toEdit ? 'Los Angeles, CA' : ''}" />"
				value="<c:out value="${empty toEdit ? '' : toEdit.getLocation() }" />"
				class="input "
				required>
		</div>
	</div>

	<!-- Textarea -->

	<div class="field">
		<label
			class="label"
			for="empDescription">Employer Information</label>
		<div class="control">
			<textarea
				class="textarea"
				id="empDescription"
				name="empDescription"
				wrap: "soft"
				placeholder="<c:out value="${empty toEdit ? 'Brief Employee Description' : ''}" />"><c:out value="${empty toEdit ? '' : toEdit.getDescription() }" /></textarea>
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
				class="button is-dark">Submit</button>
		</div>
	</div>

</fieldset>
