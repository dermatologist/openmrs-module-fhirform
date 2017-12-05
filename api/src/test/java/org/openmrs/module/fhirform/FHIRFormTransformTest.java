package org.openmrs.module.fhirform;

import org.hl7.fhir.dstu3.model.Questionnaire;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.openmrs.api.UserService;
import org.openmrs.module.fhirform.api.dao.FHIRFormDao;
import org.openmrs.module.fhirform.api.impl.FHIRFormServiceImpl;

/**
 * Created by beapen on 05/12/2017.
 */
public class FHIRFormTransformTest {
    @InjectMocks
    FHIRFormServiceImpl basicModuleService;

    @Mock
    FHIRFormDao dao;

    @Mock
    UserService userService;

    //@Mock
    //FHIRFormTransform fhirFormTransform;

    FHIRFormTransform fhirFormTransform;

    @Before
    public void setup() {
        fhirFormTransform = new FHIRFormTransform();
    }


    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getQuestionnaire() throws Exception {
        Questionnaire q = fhirFormTransform.getQuestionnaire();
        System.out.print(q.getName());
    }

}