package org.openmrs.module.fhirform;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import org.apache.commons.lang.RandomStringUtils;
import org.hl7.fhir.dstu3.model.Questionnaire;

/**
 * Created by beapen on 05/12/2017.
 */
// Cannot use @component annotation as openmrs freezes on installation
// TODO: Find root cause and rectify this
public class FhirformTransform {

    public FHIRHttpClient fhirHttpClient = new FHIRHttpClient();

    FhirContext ctxDstu3 = FhirContext.forDstu3();

    // JsonObjects
    JsonObjectProperty jsonObjectProperty;
    JsonObjectItem jsonObjectItem = new JsonObjectItem();
    JsonObjectForm jsonObjectForm = new JsonObjectForm();
    JsonObjectFunction jsonObjectFunction = new JsonObjectFunction();
    JsonObjectFromFhir jsonObjectFromFhir = new JsonObjectFromFhir();

    private String formID;

    private String version;

    private String questionnaireUrl = FhirformConstants.GLOBALPROPERTY_FHIRFORM_DEFAULT_BASEURL;

    public String getJsonForm(String url, String formID, String version) {
        if (!url.isEmpty())
            this.questionnaireUrl = url;
        if (!formID.isEmpty())
            this.formID = formID;
        if (!version.isEmpty())
            this.version = version;
        Object o = fhirHttpClient.getFhirform(this.questionnaireUrl, this.formID, this.version);
        IParser parser = ctxDstu3.newJsonParser();
        Questionnaire q = parser.parseResource(Questionnaire.class, o.toString());
        for (Questionnaire.QuestionnaireItemComponent i : q.getItem()) {
            jsonObjectProperty = new JsonObjectProperty();

            // TODO: Group to array / fieldset
            String _type = i.getType().toString().toLowerCase();
            if (_type.contains("group")) {
                _type = "object";
            }

            jsonObjectProperty.set__linkId(i.getLinkId());
            jsonObjectProperty.set__title(i.getText());
            jsonObjectProperty.set__type(_type);
            jsonObjectItem.add_item(jsonObjectProperty);
        }
        jsonObjectItem.set__title("Questionnaire");//TODO: To change
        String generatedString = RandomStringUtils.randomAlphanumeric(10);
        jsonObjectFromFhir.add_schema(generatedString, jsonObjectItem);

        return jsonObjectFromFhir.getForm();

    }

    public String getFormID() {
        return formID;
    }

    public void setFormID(String formID) {
        this.formID = formID;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getQuestionnaireUrl() {
        return questionnaireUrl;
    }

    public void setQuestionnaireUrl(String questionnaireUrl) {
        this.questionnaireUrl = questionnaireUrl;
    }
}
