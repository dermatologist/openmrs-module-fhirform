<%
    ui.decorateWith("appui", "standardEmrPage")
    ui.includeJavascript("uicommons", "underscore-min.js")

    ui.includeJavascript("uicommons", "angular.js")
    ui.includeJavascript("uicommons", "ngDialog/ngDialog.js")
    ui.includeCss("uicommons", "ngDialog/ngDialog.min.css")
    ui.includeJavascript("fhirform", "jquery.form.js")
    ui.includeJavascript("fhirform", "jsonform.js")

%>
<script type="text/javascript">
    var breadcrumbs = [
        {icon: "icon-home", link: '/' + OPENMRS_CONTEXT_PATH + '/index.htm'},
        {label: "${ ui.escapeJs(ui.message("fhirform.fhirformDrawboard.title")) }"}
    ]
</script>

<script>
    var formtype = "${formtype}";
    var jsonForm = "${jsonForm}";

    function saveFhirform() {

    }

    jQuery(document).ready(function () {


        jQuery('form').jsonForm(jsonForm);
    });
</script>

<form></form>