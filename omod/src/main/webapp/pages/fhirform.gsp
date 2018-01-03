<%
    ui.decorateWith("appui", "standardEmrPage")
    ui.includeJavascript("uicommons", "underscore-min.js")
    ui.includeJavascript("fhirform", "jquery.form.js")
    ui.includeJavascript("fhirform", "jsonform.js")

    ui.includeJavascript("uicommons", "angular.js")
    ui.includeJavascript("uicommons", "ngDialog/ngDialog.js")
    ui.includeCss("uicommons", "ngDialog/ngDialog.min.css")

    ui.includeCss("fhirform", "fhirform.css")

%>
<script>

    jQuery(document).ready(function () {
        jQuery('form').jsonForm(${jsonForm});
    });
</script>

<form></form>