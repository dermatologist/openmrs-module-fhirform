package org.openmrs.module.fhirform;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.test.BaseModuleContextSensitiveTest;

public class FhirformTransformTest extends BaseModuleContextSensitiveTest {

    //    @InjectMocks
    //    FhirformServiceImpl basicModuleService;
    //
    //    @Mock
    //    FhirformDao dao;
    //
    //    @Mock
    //    UserService userService;

    FhirformTransform fhirFormTransform;


    @Before
    public void setUp() {
        fhirFormTransform = new FhirformTransform();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getJsonForm() {
        String q = fhirFormTransform.getJsonForm("", "144829", "10");
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