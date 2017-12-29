package org.openmrs.module.fhirform;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.test.BaseModuleContextSensitiveTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;

/**
 * Created by beapen on 05/12/2017.
 */
public class FHIRHttpClientTest extends BaseModuleContextSensitiveTest {

    @Autowired
    FHIRHttpClient fhirHttpClient;

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getFHIRForm() {
        Object o = fhirHttpClient.getFhirform("", "144829", "10");
        assertNotNull(o);
        System.out.print(o);
    }

}
