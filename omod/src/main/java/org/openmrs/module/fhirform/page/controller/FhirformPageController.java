package org.openmrs.module.fhirform.page.controller;

import org.openmrs.Patient;
import org.openmrs.User;
import org.openmrs.api.PatientService;
import org.openmrs.api.context.Context;
import org.openmrs.module.fhirform.Fhirform;
import org.openmrs.module.fhirform.FhirformConstants;
import org.openmrs.module.fhirform.FhirformDef;
import org.openmrs.module.fhirform.api.FhirformService;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Calendar;

public class FhirformPageController {
    public void get(@RequestParam(required = false, value = "fhirformId", defaultValue = "0") int fhirformId,
                    @RequestParam(required = false, value = "fhirformDefId", defaultValue = "0") int fhirformDefId,
                    @RequestParam(required = false, value = "patientId", defaultValue = "0") int patientId,
                    @RequestParam(required = false, value = "questionnaireUrl", defaultValue = "") String questionnaireUrl,
                    @RequestParam(required = false, value = "questionnaire_id", defaultValue = "") String questionnaire_id,
                    @RequestParam(required = false, value = "version", defaultValue = "") String version,
                    @RequestParam(required = false, value = "lesionmap", defaultValue = "") String lesionmap,
                    PageModel model) {
        FhirformService fhirformService = Context.getService(FhirformService.class);
        String formtype = "";
        Fhirform fhirform = new Fhirform();
        FhirformDef fhirformDef = new FhirformDef();
        if (fhirformDefId > 0) {
            fhirformDef = fhirformService.getFhirformDefById(fhirformDefId);
            questionnaireUrl = fhirformDef.getQuestionnaireUrl();
            questionnaire_id = fhirformDef.getQuestionnaire_id();
            version = fhirformDef.getVersion();
            formtype = fhirformDef.getFormtype();
        }
        if (fhirformId > 0) {
            fhirform = fhirformService.getFhirformById(fhirformId);
            fhirformDef = fhirform.getFhirformDef();
            questionnaireUrl = fhirformDef.getQuestionnaireUrl();
            questionnaire_id = fhirformDef.getQuestionnaire_id();
            version = fhirformDef.getVersion();

        }

        String jsonForm = fhirformService.getJsonForm(fhirformDef);
        model.addAttribute("fhirformId", fhirformId);
        model.addAttribute("fhirformDefId", fhirformDefId);
        model.addAttribute("patientId", patientId);
        model.addAttribute("jsonForm", jsonForm);
        model.addAttribute("questionnaireUrl", questionnaireUrl);
        model.addAttribute("questionnaire_id", questionnaire_id);
        model.addAttribute("version", version);
        model.addAttribute("formtype", formtype);
        model.addAttribute("FHIRFORM_CONSTANTS", FhirformConstants.FHIRFORM_CONSTANTS());

    }

    public String post(@RequestParam(required = false, value = "fhirformId", defaultValue = "0") int fhirformId,
                       @RequestParam("fhirformDefId") int fhirformDefId,
                       @RequestParam(required = false, value = "patientId", defaultValue = "0") int patientId,
                       @RequestParam(required = false, value = "lesionmap", defaultValue = "") String lesionmap,
                       Errors errors,
                       UiUtils ui) {

        User user = Context.getAuthenticatedUser();
        Calendar cal = Calendar.getInstance();
        FhirformService fhirformService = Context.getService(FhirformService.class);
        PatientService patientService = Context.getPatientService();
        Patient patient;
        if (patientId > 0)
            patient = patientService.getPatient(patientId);
        else
            patient = null;
        FhirformDef fhirformDef = fhirformService.getFhirformDefById(fhirformDefId);
        Fhirform fhirform;
        if (fhirformId > 0) {
            fhirform = fhirformService.getFhirformById(fhirformId);
            fhirform.setLast_edited_by(user.toString());
            fhirform.setLast_edited_on(cal.getTime());
        } else {
            fhirform = new Fhirform();
            fhirform.setCreated_by(user.toString());
            fhirform.setCreated_on(cal.getTime());
        }
        fhirform.setFhirformDef(fhirformDef);

        fhirform.setPatient(patient);
        // fhirform.setLesionmap(lesionmap); // TODO:
        Fhirform saved = fhirformService.saveFhirform(fhirform);

        SimpleObject redirectParams = new SimpleObject();
        if (saved.getId() != null)
            redirectParams.put("savedId", saved.getId());
        else
            redirectParams.put("savedId", 0);
        if (patientId > 0) {
            // If a patient specific form return to patient dashboard
            redirectParams.put("patientId", patientId);
            redirectParams.put("app", "pih.app.clinicianDashboard");
            return "redirect:" + ui.pageLink("coreapps", "clinicianfacing/patient", redirectParams);
        }
        // Else return to Fhirform dashboard
        return "redirect:" + ui.pageLink("fhirform", "fhirformDashboard", redirectParams);
    }
}
