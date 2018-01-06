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
    JsonObjectSet jsonObjectSet = new JsonObjectSet();
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
            if (i.getType() == Questionnaire.QuestionnaireItemType.GROUP) {
                jsonObjectFromFhir.add_schema(i.getLinkId().toString(), process_group(i));
            } else {
                jsonObjectProperty = process_property(i);
                jsonObjectSet.add_item(jsonObjectProperty);
            }
        }
        jsonObjectSet.set__title(" ");//TODO: To change
        String generatedString = RandomStringUtils.randomAlphanumeric(10);
        jsonObjectFromFhir.add_schema(generatedString, jsonObjectSet);

        return jsonObjectFromFhir.getForm();

    }

    public JsonObjectProperty process_property(Questionnaire.QuestionnaireItemComponent i) {
        JsonObjectProperty jp = new JsonObjectProperty();
        if (i.getType() == Questionnaire.QuestionnaireItemType.CHOICE) {

            jp.set__linkId(i.getLinkId());
            jp.set__title(i.getText());
            jp.set__type("string");
            jp.add_item(i.getOptionsTarget().toString());

        } else if (i.getType() == Questionnaire.QuestionnaireItemType.BOOLEAN) {

            jp.set__linkId(i.getLinkId());
            jp.set__title(i.getText());
            jp.set__type("boolean");

        } else if (i.getType() == Questionnaire.QuestionnaireItemType.INTEGER) {

            jp.set__linkId(i.getLinkId());
            jp.set__title(i.getText());
            jp.set__type("integer");

        } else {

            jp.set__linkId(i.getLinkId());
            jp.set__title(i.getText());
            jp.set__type("string");
        }
        return jp;
    }

    public JsonObjectSet process_group(Questionnaire.QuestionnaireItemComponent i) {
        JsonObjectSet set = new JsonObjectSet();

        set.set__type("object");
        set.set__title(i.getText());

        for (Questionnaire.QuestionnaireItemComponent i2 : i.getItem()) {

            set.add_item(process_property(i2));
        }
        return set;
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
