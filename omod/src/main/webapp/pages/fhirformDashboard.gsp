<%
    ui.decorateWith("appui", "standardEmrPage")
    ui.includeJavascript("fhirform", "jquery.form.js")

    ui.includeJavascript("uicommons", "angular.js")
    ui.includeJavascript("uicommons", "ngDialog/ngDialog.js")
    ui.includeCss("uicommons", "ngDialog/ngDialog.min.css")

    ui.includeCss("fhirform", "fhirform.css")

%>
<script type="text/javascript">
    var breadcrumbs = [
        {icon: "icon-home", link: '/' + OPENMRS_CONTEXT_PATH + '/index.htm'},
        {label: "${ ui.escapeJs(ui.message("fhirform.fhirformDashboard.title")) }"}
    ]
</script>



<!-- Section 1 -->
<h2>List of General (Patient Independent) Definitions:</h2>
<table class="fhirformTable">
    <thead>
    <tr>
        <th>ID</th>
        <th>Form Image (Comments)</th>
        <th>Created On</th>
        <th>Type</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>

    <% fhirformdefs.each { %>
    <tr<% if (it.status != FHIRFORM_CONSTANTS.ACTIVE) { %> class="inactive" <% } %>>
        <td>${it.id}</td>
        <td>${it.questionnaireUrl}/${it.questionnaire_id}/${it.version}</td>
        <td>${it.created_on}</td>
        <td>${it.formtype}</td>
        <% if (it.status == FHIRFORM_CONSTANTS.ACTIVE) { %>
        <td>
            <a href="${ui.pageLink("fhirform", "fhirform", [fhirformDefId: it.id])}">
                <i class="icon-file-alt edit-action" title="Create FhirForm"></i>
            </a>
            <a href="${ui.pageLink("fhirform", "fhirformListForDef", [fhirformDefId: it.id])}">
                <i class="icon-eye-open view-action" title="View FhirForms"></i>
            </a>
            <a href="${ui.actionLink("fhirform", "fhirutils", "toggleDef", [fhirformDefId: it.id])}">
                <i class="icon-remove delete-action" title="Delete Definition"></i>
            </a>
            <% } else { %>
        <td>
            <a href="${ui.actionLink("fhirform", "fhirutils", "toggleDef", [fhirformDefId: it.id])}">
                <i class="icon-undo delete-action" title="UnDelete"></i>
            </a>
            <a href="${ui.actionLink("fhirform", "fhirutils", "purgeDef", [fhirformDefId: it.id])}">
                <i class="icon-remove delete-action" title="Purge"></i>
            </a>
            <% } %></td>
    </tr>
    <% } %>
    </tbody>
</table>



<!-- Section 2 -->
<hr>

<h2>Create Definition:</h2>

<form id="FhirformCreate" method="post" action="${ui.pageLink("fhirform", "fhirformDashboard")}">
    <label for="formtype">Select Form Type. General forms are patient independent.</label>
    <select name="formtype" id="formtype">
        <option value="${FHIRFORM_CONSTANTS.GENERALFORM}">General</option>
        <option value="${FHIRFORM_CONSTANTS.PATIENTFORM}">Patient Specific</option>
    </select>

    <br>
    <label for="questionnaireUrl">Form Url</label>
    <input name="questionnaireUrl" id="questionnaireUrl" type="text"/>
    <br>
    <label for="questionnaire_id">Form ID</label>
    <input name="questionnaire_id" id="questionnaire_id" type="text"/>
    <br>
    <label for="version">Form Version</label>
    <input name="version" id="version" type="text"/>
    <br>
    <label for="submissionUrl">Submission Url</label>
    <input name="submissionUrl" id="submissionUrl" type="text"/>

    <label for="fhirform-comments">Comments</label>
    <textarea name="comments" id="fhirform-comments"></textarea><br>
    <button type="submit" id="fhirform-create">Create FhirForm</button>
</form>

<!-- Section 4 -->

<div id="confirmDeletePopup" class="dialog" style="display: none">
    <div class="dialog-header">
        <i class="icon-remove"></i>

        <h3>${ui.message("fhirform.delete.confirm")}</h3>
    </div>

    <div class="dialog-content">
        <p class="dialog-instructions">${ui.message("fhirform.delete.explanation")}</p>

        <button class="confirm">${ui.message("referenceapplication.okay")}</button>
        <button class="cancel">${ui.message("fhirform.delete.cancel")}</button>
    </div>
</div>