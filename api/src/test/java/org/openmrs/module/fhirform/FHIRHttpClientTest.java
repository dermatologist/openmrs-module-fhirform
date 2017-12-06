package org.openmrs.module.fhirform;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.openmrs.module.fhirform.api.impl.FHIRFormServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;

/**
 * Created by beapen on 05/12/2017.
 */
public class FHIRHttpClientTest {

    @Autowired
    FHIRHttpClient fhirHttpClient;

    @InjectMocks
    FHIRFormServiceImpl basicModuleService;


    //@Mock
    //AdministrationService administrationService;

    @Before
    public void setUp() throws Exception {
        fhirHttpClient = new FHIRHttpClient();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getFHIRForm() throws Exception {
        Object o = fhirHttpClient.getFHIRForm("", "144829", "10");
        assertNotNull(o);
        System.out.print(o);
    }

}