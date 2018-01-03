package org.openmrs.module.fhirform.page.controller;

import org.openmrs.User;
import org.openmrs.api.context.Context;
import org.openmrs.module.fhirform.FhirformConstants;
import org.openmrs.module.fhirform.FhirformDef;
import org.openmrs.module.fhirform.api.FhirformService;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.expression.EvaluationException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Calendar;

public class FhirformDashboardPageController {

    public void controller(PageModel model) throws EvaluationException {
        FhirformService fhirFormService = Context.getService(FhirformService.class);

        model.addAttribute("FHIRFORM_CONSTANTS", FhirformConstants.FHIRFORM_CONSTANTS());
        model.addAttribute("fhirformdefs", fhirFormService.getAllDef(FhirformConstants.GENERALFORM));
    }

    public String post(@RequestParam("formtype") String formtype,
                       @RequestParam(required = false, value = "questionnaireUrl", defaultValue = "") String questionnaireUrl,
                       @RequestParam(required = false, value = "questionnaire_id", defaultValue = "") String questionnaire_id,
                       @RequestParam(required = false, value = "version", defaultValue = "") String version,
                       @RequestParam(required = false, value = "submissionUrl", defaultValue = "") String submissionUrl,
                       @RequestParam(required = false, value = "comments", defaultValue = "") String comment, Errors errors, UiUtils ui) {

        FhirformDef fhirformDef = new FhirformDef();
        User user = Context.getAuthenticatedUser();
        Calendar cal = Calendar.getInstance();
        fhirformDef.setCreated_by(user.toString());
        fhirformDef.setCreated_on(cal.getTime());
        fhirformDef.setFormtype(formtype);
        fhirformDef.setVersion(version);
        fhirformDef.setQuestionnaireUrl(questionnaireUrl);
        fhirformDef.setQuestionnaire_id(questionnaire_id);
        fhirformDef.setSubmissionUrl(submissionUrl);
        fhirformDef.setStatus(FhirformConstants.ACTIVE);
        fhirformDef.setComments(comment);
        FhirformService fhirformService = Context.getService(FhirformService.class);
        FhirformDef saved = fhirformService.saveFhirformDef(fhirformDef);
        SimpleObject redirectParams = new SimpleObject();
        if (saved.getId() != null)
            redirectParams.put("savedId", saved.getId());
        else
            redirectParams.put("savedId", 0);
        return "redirect:" + ui.pageLink("fhirform", "fhirformDashboard", redirectParams);
    }
}
