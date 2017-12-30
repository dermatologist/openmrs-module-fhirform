package org.openmrs.module.fhirform;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import org.hl7.fhir.dstu3.model.Questionnaire;

/**
 * Created by beapen on 05/12/2017.
 */
// Cannot use @component annotation as openmrs freezes on installation
// TODO: Find root cause and rectify this
public class FhirformTransform {

    public FHIRHttpClient fhirHttpClient = new FHIRHttpClient();

    FhirContext ctxDstu3 = FhirContext.forDstu3();

    private FhirQuestionnaireItem fhirQuestionnaireItem;

    private FhirQuestionnaireSet fhirQuestionnaireSet = new FhirQuestionnaireSet();

    private FhirQuestionnaire fhirQuestionnaire = new FhirQuestionnaire();

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
            fhirQuestionnaireItem = new FhirQuestionnaireItem();
            fhirQuestionnaireItem.set_title(i.getText());
            fhirQuestionnaireItem.set_type(i.getType().toString().toLowerCase());
            fhirQuestionnaireSet.add_item(fhirQuestionnaireItem);
        }

        fhirQuestionnaire.add_item(fhirQuestionnaireSet);
        return fhirQuestionnaire.getForm();
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
