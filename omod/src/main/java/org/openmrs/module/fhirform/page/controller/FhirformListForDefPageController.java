package org.openmrs.module.fhirform.page.controller;

import org.openmrs.api.context.Context;
import org.openmrs.module.fhirform.Fhirform;
import org.openmrs.module.fhirform.FhirformConstants;
import org.openmrs.module.fhirform.FhirformDef;
import org.openmrs.module.fhirform.api.FhirformService;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class FhirformListForDefPageController {
    public void get(@RequestParam("fhirformDefId") int fhirformDefId,
                    PageModel model) {
        FhirformService fhirformService = Context.getService(FhirformService.class);
        FhirformDef fhirformDef = fhirformService.getFhirformDefById(fhirformDefId);
        List<Fhirform> fhirforms = fhirformService.getAllFhirformsByDef(fhirformDef);
        model.addAttribute("fhirforms", fhirforms);
        model.addAttribute("fhirformDefId", fhirformDefId);
        model.addAttribute("FHIRFORM_CONSTANTS", FhirformConstants.FHIRFORM_CONSTANTS());
    }
}
