package org.openmrs.module.fhirform;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.openmrs.api.UserService;
import org.openmrs.module.fhirform.api.dao.FHIRFormDao;
import org.openmrs.module.fhirform.api.impl.FHIRFormServiceImpl;
import org.openmrs.test.BaseModuleContextSensitiveTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by beapen on 05/12/2017.
 */
public class FHIRFormTransformTest extends BaseModuleContextSensitiveTest {
//    @InjectMocks
//    FHIRFormServiceImpl basicModuleService;
//
//    @Mock
//    FHIRFormDao dao;
//
//    @Mock
//    UserService userService;



    @Autowired
    FHIRFormTransform fhirFormTransform;

    @Before
    public void setup() {
    }


    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getJsonForm() throws Exception {
        String q = fhirFormTransform.getJsonForm("", "144829", "10");
        System.out.print(q);
    }
}