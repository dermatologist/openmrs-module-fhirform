package org.openmrs.module.fhirform.fragment.controller;

import org.openmrs.User;
import org.openmrs.api.PatientService;
import org.openmrs.api.context.Context;
import org.openmrs.module.fhirform.Fhirform;
import org.openmrs.module.fhirform.FhirformConstants;
import org.openmrs.module.fhirform.FhirformDef;
import org.openmrs.module.fhirform.api.FhirformService;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.fragment.FragmentConfiguration;
import org.openmrs.ui.framework.fragment.FragmentModel;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Calendar;

public class FhirutilsFragmentController {

    public void controller(FragmentConfiguration config,
                           @SpringBean("patientService") PatientService patientService,
                           FragmentModel model) {

    }


    public void toggleDef(@RequestParam(value = "fhirformDefId", required = true) int fhirformDefId,
                          Errors errors,
                          UiUtils ui) {

        User user = Context.getAuthenticatedUser();
        Calendar cal = Calendar.getInstance();
        FhirformService fhirformService = Context.getService(FhirformService.class);
        FhirformDef fhirformDef = fhirformService.getFhirformDefById(fhirformDefId);
        if (fhirformDef.getStatus().equals(FhirformConstants.ACTIVE)) {
            fhirformDef.setStatus(FhirformConstants.DELETED);
            fhirformDef.setDeleted_by(user.toString());
            fhirformDef.setDeleted_on(cal.getTime());
        } else
            fhirformDef.setStatus(FhirformConstants.ACTIVE);

        FhirformDef saved = fhirformService.saveFhirformDef(fhirformDef);
    }

    public void toggleFhirform(@RequestParam(value = "fhirformId", required = true) int fhirformId,
                               Errors errors,
                               UiUtils ui) {

        User user = Context.getAuthenticatedUser();
        Calendar cal = Calendar.getInstance();
        FhirformService fhirformService = Context.getService(FhirformService.class);
        Fhirform fhirform = fhirformService.getFhirformById(fhirformId);
        if (fhirform.getStatus().equals(FhirformConstants.ACTIVE)) {
            fhirform.setStatus(FhirformConstants.DELETED);
            fhirform.setDeleted_by(user.toString());
            fhirform.setDeleted_on(cal.getTime());
        } else
            fhirform.setStatus(FhirformConstants.ACTIVE);

        Fhirform saved = fhirformService.saveFhirform(fhirform);
    }


    public void purgeDef(@RequestParam(value = "fhirformDefId", required = true) int fhirformDefId,
                         Errors errors,
                         UiUtils ui) {
        FhirformService fhirformService = Context.getService(FhirformService.class);
        FhirformDef fhirformDef = fhirformService.getFhirformDefById(fhirformDefId);
        fhirformService.purgeFhirformDef(fhirformDef);
    }

    public void purgeFhirform(@RequestParam(value = "fhirformId", required = true) int fhirformId,
                              Errors errors,
                              UiUtils ui) {
        FhirformService fhirformService = Context.getService(FhirformService.class);
        Fhirform fhirform = fhirformService.getFhirformById(fhirformId);
        fhirformService.purgeFhirform(fhirform);
    }
    
    
    /*

    public Object deleteFormDef(@RequestParam("FhirformDef") String image) {

        SimpleObject output;

        FhirformService fhirformService = Context.getService(FhirformService.class);
        FhirformDef fhirformDef = fhirformService.getFhirformDefById(fhirformDefId);
        fhirformService.purgeFhirformDef(fhirformDef);


        if (toDelete.delete()) {
            output = SimpleObject.create("message", FhirformConstants.SUCCESS);
        } else {
            output = SimpleObject.create("message", FhirformConstants.ERROR);
        }
        return output;
    }
    
    */
    
}
