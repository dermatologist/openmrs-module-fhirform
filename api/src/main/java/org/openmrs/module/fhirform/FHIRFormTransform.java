package org.openmrs.module.fhirform;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import org.hl7.fhir.dstu3.model.Questionnaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by beapen on 05/12/2017.
 */
@Component
public class FHIRFormTransform {

    @Autowired
    public FHIRHttpClient fhirHttpClient;
    FhirContext ctxDstu3 = FhirContext.forDstu3();
    private JSONFormItem jsonFormItem;
    private JSONFormSet jsonFormSet;
    private JSONForm jsonForm;
    private String formID;
    private String version;

    public Questionnaire getQuestionnaire() {
        if (fhirHttpClient == null)
            fhirHttpClient = new FHIRHttpClient();
        this.formID = "144829";
        this.version = "10";
        Object o = fhirHttpClient.getFHIRForm("", this.formID, this.version);
        IParser parser = ctxDstu3.newJsonParser();
        return parser.parseResource(Questionnaire.class, o.toString());

    }

    public String getJsonForm() {
        if (fhirHttpClient == null)
            fhirHttpClient = new FHIRHttpClient();
        this.formID = "144829";
        this.version = "10";
        Object o = fhirHttpClient.getFHIRForm("", this.formID, this.version);
        IParser parser = ctxDstu3.newJsonParser();
        Questionnaire q = parser.parseResource(Questionnaire.class, o.toString());
        for (Questionnaire.QuestionnaireItemComponent i : q.getItem()) {
            jsonFormItem.set_title(i.getText());
            jsonFormItem.set_type(i.getType().toString());
            jsonFormSet.add_item(jsonFormItem);
        }

        jsonForm.add_item(jsonFormSet);
        return jsonForm.getForm();
    }
}
