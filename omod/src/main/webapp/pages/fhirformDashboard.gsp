<%
    ui.decorateWith("appui", "standardEmrPage")
    ui.includeJavascript("uicommons", "angular.js")
    ui.includeJavascript("uicommons", "ngDialog/ngDialog.js")
    ui.includeCss("uicommons", "ngDialog/ngDialog.min.css")


%>
<script type="text/javascript">
    var breadcrumbs = [
        {icon: "icon-home", link: '/' + OPENMRS_CONTEXT_PATH + '/index.htm'},
        {label: "${ ui.escapeJs(ui.message("fhirform.fhirformDashboard.title")) }"}
    ]
</script>

<script>
    var jq = jQuery;


    jq(document).ready(function () {

        var confirmDeleteController = emr.setupConfirmationDialog({
            selector: '#confirmDeletePopup',
            actions: {
                cancel: function () {
                    confirmDeleteController.close();
                },
                confirm: function () {
                    confirmDeleteController.close();
                    jq.post("${ ui.actionLink("nuform","nuformUtils","deleteImage")}", {
                            returnFormat: 'json',
                            type: "data",
                            image: (filesList[image_pointer]).trim()
                        },
                        function (data) {
                            if (data.indexOf("${NUFORM_CONSTANTS.SUCCESS}") >= 0) {
                                jq().toastmessage('showSuccessToast', "Image Deleted.");
                                location.reload();
                            } else {
                                jq().toastmessage('showErrorToast', "Error. Try again after page refresh");
                            }
                        })
                        .error(function () {
                            jq().toastmessage('showErrorToast', "Error. Try again after page refresh");
                        });
                }
            }
        });


        jq("#but_delete").click(function (e) {
            confirmDeleteController.show();
        });

    });
</script>

<!-- Section 1 -->
<h2>List of General (Patient Independent) Definitions:</h2>
<table class="nuformTable">
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

    <% nuformdefs.each { %>
    <tr<% if (it.status != NUFORM_CONSTANTS.ACTIVE) { %> class="inactive" <% } %>>
        <td>${it.id}</td>
        <td>${it.backgroundImage} (${it.comments})</td>
        <td>${it.created_on}</td>
        <td>${it.formtype}</td>
        <% if (it.status == NUFORM_CONSTANTS.ACTIVE) { %>
        <td>
            <a href="${ui.pageLink("nuform", "nuform", [nuformDefId: it.id])}">
                <i class="icon-file-alt edit-action" title="Create NuForm"></i>
            </a>
            <a href="${ui.pageLink("nuform", "nuformListForDef", [nuformDefId: it.id])}">
                <i class="icon-eye-open view-action" title="View NuForms"></i>
            </a>
            <a href="${ui.actionLink("nuform", "nuformUtils", "toggleDef", [nuformDefId: it.id])}">
                <i class="icon-remove delete-action" title="Delete Definition"></i>
            </a>
            <% } else { %>
        <td>
            <a href="${ui.actionLink("nuform", "nuformUtils", "toggleDef", [nuformDefId: it.id])}">
                <i class="icon-undo delete-action" title="UnDelete"></i>
            </a>
            <a href="${ui.actionLink("nuform", "nuformUtils", "purgeDef", [nuformDefId: it.id])}">
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

<form id="NuformCreate" method="post" action="${ui.pageLink("nuform", "nuformDashboard")}">
    <label for="formtype">Select Form Type. General forms are patient independent.</label>
    <select name="formtype" id="formtype">
        <option value="${NUFORM_CONSTANTS.GENERALFORM}">General</option>
        <option value="${NUFORM_CONSTANTS.PATIENTFORM}">Patient Specific</option>
    </select>

    <label for="backgroundImage">Form Image: (Use complete URL for online images.)
    </label>
    <input name="backgroundImage" id="backgroundImage" type="text"/>
    <label for="nuform-comments">Comments</label>
    <textarea name="comment" id="nuform-comments"></textarea><br>
    <button type="submit" id="nuform-create">Create NuForm</button>
</form>

<!-- Section 4 -->

<div id="confirmDeletePopup" class="dialog" style="display: none">
    <div class="dialog-header">
        <i class="icon-remove"></i>

        <h3>${ui.message("nuform.delete.confirm")}</h3>
    </div>

    <div class="dialog-content">
        <p class="dialog-instructions">${ui.message("nuform.delete.explanation")}</p>

        <button class="confirm">${ui.message("referenceapplication.okay")}</button>
        <button class="cancel">${ui.message("nuform.delete.cancel")}</button>
    </div>
</div>