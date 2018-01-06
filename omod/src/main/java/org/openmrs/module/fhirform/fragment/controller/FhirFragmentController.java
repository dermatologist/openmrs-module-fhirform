package org.openmrs.module.fhirform.fragment.controller;

import org.apache.commons.beanutils.PropertyUtils;
import org.openmrs.Patient;
import org.openmrs.api.PatientService;
import org.openmrs.api.context.Context;
import org.openmrs.module.fhirform.Fhirform;
import org.openmrs.module.fhirform.FhirformConstants;
import org.openmrs.module.fhirform.FhirformDef;
import org.openmrs.module.fhirform.api.FhirformService;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.fragment.FragmentConfiguration;
import org.openmrs.ui.framework.fragment.FragmentModel;

import java.util.List;

public class FhirFragmentController {
    public void controller(FragmentConfiguration config,
                           @SpringBean("patientService") PatientService patientService,
                           FragmentModel model) throws Exception {
        // unfortunately in OpenMRS 2.1 the coreapps patient page only gives us a patientId for this extension point
        // (not a patient) but I assume we'll fix this to pass patient, so I'll code defensively
        Patient patient;
        config.require("patient|patientId");
        Object pt = config.getAttribute("patient");
        if (pt == null)
            patient = patientService.getPatient((Integer) config.getAttribute("patientId"));
        else
            patient = (Patient) (pt instanceof Patient ? pt : PropertyUtils.getProperty(pt, "patient"));
        FhirformService fhirformService = Context.getService(FhirformService.class);
        List<Fhirform> fhirforms = fhirformService.getAllFhirformsByPatient(patient);


        List<FhirformDef> fhirformDefs = fhirformService.getAllDef(FhirformConstants.PATIENTFORM);
        fhirformDefs.addAll(fhirformService.getAllDef(FhirformConstants.PERSONALFORM));
        model.addAttribute("FHIRFORM_CONSTANTS", FhirformConstants.FHIRFORM_CONSTANTS());
        model.addAttribute("fhirforms", fhirforms);
        model.addAttribute("patient", patient);
        model.addAttribute("fhirformdefs", fhirformDefs);
    }
}
