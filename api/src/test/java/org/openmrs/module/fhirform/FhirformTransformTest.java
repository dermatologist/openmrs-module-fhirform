package org.openmrs.module.fhirform;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.test.BaseModuleContextSensitiveTest;
import org.springframework.beans.factory.annotation.Autowired;

public class FhirformTransformTest extends BaseModuleContextSensitiveTest {

    //    @InjectMocks
    //    FhirformServiceImpl basicModuleService;
    //
    //    @Mock
    //    FhirformDao dao;
    //
    //    @Mock
    //    UserService userService;

    @Autowired
    FhirformTransform fhirFormTransform;


    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getJsonForm() {
        String q = fhirFormTransform.getJsonForm("https://fhirtest.uhn.ca/baseDstu3/Questionnaire/", "SMART-PROMs-QUE2",
                "154");
        System.out.print(q);

    }

    @Test
    public void getFormID() {
    }

    @Test
    public void setFormID() {
    }

    @Test
    public void getVersion() {
    }

    @Test
    public void setVersion() {
    }

    @Test
    public void getQuestionnaireUrl() {
    }

    @Test
    public void setQuestionnaireUrl() {
    }
}