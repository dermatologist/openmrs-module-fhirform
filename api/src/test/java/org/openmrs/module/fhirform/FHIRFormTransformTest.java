package org.openmrs.module.fhirform;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.test.BaseModuleContextSensitiveTest;
import org.springframework.beans.factory.annotation.Autowired;

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
		String q = fhirFormTransform.getJsonForm("https://fhirtest.uhn.ca/baseDstu3/Questionnaire/", "SMART-PROMs-QUE2",
		    "154");
		System.out.print(q);
	}
}
