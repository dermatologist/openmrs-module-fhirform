<%
    ui.decorateWith("appui", "standardEmrPage")

    ui.includeJavascript("uicommons", "angular.js")
    ui.includeJavascript("uicommons", "ngDialog/ngDialog.js")
    ui.includeCss("uicommons", "ngDialog/ngDialog.min.css")

%>
<script type="text/javascript">
    var breadcrumbs = [
        {icon: "icon-home", link: '/' + OPENMRS_CONTEXT_PATH + '/index.htm'},
        {label: "${ ui.escapeJs(ui.message("fhirform.fhirformDrawboard.title")) }"}
    ]
</script>

<script>
    var formtype = "${formtype}";
    var backgroundImage = "${backgroundImage}";
    if (formtype === "${FHIRFORM_CONSTANTS.PERSONALFORM}") {
        imagePath = "../moduleServlet/dermimage/DermImageServlet?patId=${patientId}&image=${backgroundImage}"
    } else {
        imagePath = '../moduleServlet/fhirform/FhirformImageServlet?image=${backgroundImage}';
    }
    // If it is an online form, adjust to dimension.
    // 17-July-2016: The height and width are not currently supported in widget.
    if (backgroundImage.indexOf('://') > -1) {
        var img = new Image();
        img.onload = function () {
            height = img.height + 50;
            width = img.width;
        };
        imagePath = backgroundImage;
    }
    var FHIRFORM = {
        'image': imagePath,
        'width': width,
        'height': height,
        'fhirform_in': '',
        'fhirform_out': ''
    };
    // Ref Stackoverflow: 1224463
    var intervalID = setInterval(function () {

        if (FHIRFORM.fhirform_out.length > 500) {
            jQuery('#saveMessase').empty();
            jQuery('#saveMessase').append('<h4>Saved! Submit to transfer this to database.</h4>' + 'Buffer: ' + FHIRFORM.fhirform_out.length);

        } else {
            jQuery('#saveMessase').empty();
            jQuery('#saveMessase').append('<h4>Please save your work before submitting.</h4>' + 'Buffer: ' + FHIRFORM.fhirform_out.length);
        }

    }, 5000);

    function saveFhirform() {
        var imagemap = FHIRFORM.fhirform_out;
        imagemap = imagemap.replace(/\"/g, '!');
        jQuery("#lesionmap").val(imagemap);
        if (imagemap.length < 3)
            return confirm("Form not saved. Submit empty form?");
        console.log(imagemap);
        return true;
    }

    jQuery(document).ready(function () {
        response = jQuery("#lesionmap").val();
        response = response.replace(/!/g, '"');
        FHIRFORM.fhirform_in = response;
        console.log(response);
    });
</script>


<div id="saveMessase"></div>

<form onsubmit="return saveFhirform()" method="post" action="${ui.pageLink("fhirform", "fhirform")}" name="FormName"
      id="FormName">

    <input name="lesionmap" id="lesionmap" type="hidden" value="${lesionmap}">
    <input name="fhirformId" id="fhirformId" type="hidden" value="${fhirformId}">
    <input name="patientId" id="patientId" type="hidden" value="${patientId}">
    <input name="fhirformDefId" id="fhirformDefId" type="hidden" value="${fhirformDefId}">

    <input value="Submit" name="SubmitButton" id="SubmitButton" type="submit">

</form>