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
        {label: "${ ui.escapeJs(ui.message("fhirform.fhirformList.title")) }"}
    ]
</script>


<h2>List of NuForms</h2>
<table class="fhirformTable">
    <thead>
    <tr>
        <th>ID</th>
        <th>Last Edited On</th>
        <th>Created On</th>
        <th>Patient ID</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>

    <% fhirforms.each {
        if (!it.patient) {
            patientId = null
        } else {
            patientId = it.patient.id
        }
    %>
    <tr<% if (it.status != FHIRFORM_CONSTANTS.ACTIVE) { %> class="inactive" <% } %>>
        <td>${it.id}</td>
        <td>${it.last_edited_on}</td>
        <td>${it.created_on}</td>
        <td>${patientId}</td>
        <% if (it.status == FHIRFORM_CONSTANTS.ACTIVE) { %>
        <td>
            <a href="${
                    ui.pageLink("fhirform", "fhirform", [patientId: patientId, fhirformId: it.id, fhirformDefId: fhirformDefId])}">
                <i class="icon-pencil edit-action" title="Edit"></i>
            </a>
            <a href="${ui.actionLink("fhirform", "fhirformUtils", "toggleFhirform", [fhirformId: it.id])}">
                <i class="icon-remove delete-action" title="Delete"></i>
            </a>

            <% } else { %>
        <td>
            <a href="${ui.actionLink("fhirform", "fhirformUtils", "toggleFhirform", [fhirformId: it.id])}">
                <i class="icon-undo delete-action" title="UnDelete"></i>
            </a>
            <a href="${ui.actionLink("fhirform", "fhirformUtils", "purgeFhirform", [fhirformId: it.id])}">
                <i class="icon-remove delete-action" title="Purge"></i>
            </a>
            <% } %>
        </td>
    </tr>
    <% } %>
    </tbody>
</table>