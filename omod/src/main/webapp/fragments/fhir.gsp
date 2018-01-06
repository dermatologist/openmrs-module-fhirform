<%
    ui.includeCss("fhirform", "fhirform.css")
%>
<script>
    var jq = jQuery;
    jq(document).ready(function () {
        jq("#tabs").tabs();


    });
</script>

<!-- Title begins here -->
<div id="fhirform-main" class="info-section fhirform">
    <div class="info-header">
        <i class="icon-pencil"></i>

        <h3>FHIRFORM</h3>
    </div>


    <div id="tabs" class="ui-tabs">
        <ul class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all" role="tablist">
            <li class="ui-state-default ui-corner-top ui-tabs-active ui-state-active">
                <a href="#fhirform-tab" class="ui-tabs-anchor">
                    Fhirforms
                </a>
            </li>
            <li class="ui-state-default ui-corner-top">
                <a href="#create-tab" class="ui-tabs-anchor">
                    Create
                </a>
            </li>
            <li class="ui-state-default ui-corner-top">
                <a href="#help-about-tab" class="ui-tabs-anchor">
                    Help/About
                </a>
            </li>
        </ul>

        <!-- Title Ends here -->
        <small>
            <div id="fhirform-tab" class="ui-tabs-panel ui-widget-content ui-corner-bottom">
                <div>
                    <table class="fhirformTable">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Edited</th>
                            <th>Created</th>
                            <th>Actions</th>
                        </tr>
                        </thead>
                        <tbody>

                        <% fhirforms.each { %>
                        <tr<% if (it.status != FHIRFORM_CONSTANTS.ACTIVE) { %> class="inactive" <% } %>>
                            <td>${it.id}</td>
                            <td>${it.last_edited_on}</td>
                            <td>${it.created_on}</td>
                            <% if (it.status == FHIRFORM_CONSTANTS.ACTIVE) { %>
                            <td>
                                <a href="${
                                        ui.pageLink("fhirform", "fhirform", [patientId: patient.id, fhirformId: it.id, fhirformDefId: it.fhirformDef])}">
                                    <i class="icon-pencil edit-action" title="Edit"></i>
                                </a>
                                <a href="${
                                        ui.actionLink("fhirform", "fhirformUtils", "toggleFhirform", [fhirformId: it.id])}">
                                    <i class="icon-remove delete-action" title="Delete"></i>
                                </a>

                                <% } else { %>
                            <td>
                                <a href="${
                                        ui.actionLink("fhirform", "fhirformUtils", "toggleFhirform", [fhirformId: it.id])}">
                                    <i class="icon-undo delete-action" title="UnDelete"></i>
                                </a>
                                <a href="${
                                        ui.actionLink("fhirform", "fhirformUtils", "purgeFhirform", [fhirformId: it.id])}">
                                    <i class="icon-remove delete-action" title="Purge"></i>
                                </a>
                                <% } %>
                            </td>
                        </tr>
                        <% } %>
                        </tbody>
                    </table>

                </div>
            </div>


            <div id="create-tab" class="ui-tabs-panel ui-widget-content ui-corner-bottom">
                <table class="fhirformTable">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Form</th>
                        <th>Created</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>

                    <% fhirformdefs.each { %>
                    <tr<% if (it.status != FHIRFORM_CONSTANTS.ACTIVE) { %> class="inactive" <% } %>>
                        <td>${it.id}</td>
                        <td>${it.questionnaire_id}/${it.version}</td>
                        <td>${it.created_on}</td>
                        <% if (it.status == FHIRFORM_CONSTANTS.ACTIVE) { %>
                        <td>
                            <a href="${
                                    ui.pageLink("fhirform", "fhirform", [patientId: patient.id, fhirformDefId: it.id])}">
                                <i class="icon-file-alt edit-action" title="Create"></i>
                            </a>
                            <a href="${ui.pageLink("fhirform", "fhirformListForDef", [fhirformDefId: it.id])}">
                                <i class="icon-eye-open view-action" title="View"></i>
                            </a>
                            <a href="${
                                    ui.actionLink("fhirform", "fhirformUtils", "toggleDef", [fhirformDefId: it.id])}">
                                <i class="icon-remove delete-action" title="Delete"></i>
                            </a>
                            <% } else { %>
                        <td>
                            <a href="${
                                    ui.actionLink("fhirform", "fhirformUtils", "toggleDef", [fhirformDefId: it.id])}">
                                <i class="icon-undo delete-action" title="UnDelete"></i>
                            </a>
                            <a href="${ui.actionLink("fhirform", "fhirformUtils", "purgeDef", [fhirformDefId: it.id])}">
                                <i class="icon-remove delete-action" title="Purge"></i>
                            </a>

                            <% } %></td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>

            <div id="help-about-tab" class="ui-tabs-panel ui-widget-content ui-corner-bottom">

                HELP/ABOUT

            </div>
        </small>
    </div> <!--tabs-->
</div><!--fhirform-main-->