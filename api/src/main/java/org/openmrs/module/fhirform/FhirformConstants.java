package org.openmrs.module.fhirform;

import org.openmrs.ui.framework.SimpleObject;

/**
 * Created by beapen on 09/11/2017.
 */
public class FhirformConstants {

    public final static String GENERALFORM = "1";

    public final static String PATIENTFORM = "2";

    public final static String PERSONALFORM = "3";

    public final static String ACTIVE = "1";

    public final static String DELETED = "0";

    // Patient associated images for annotation. Not to be listed.
    public final static String SUCCESS = "success";

    public final static String ERROR = "error";

    public final static String GLOBALPROPERTY_NOT_FOUND = "Not Found";

    public final static String GLOBALPROPERTY_FHIRFORM_BASEURL = "fhirform.baseurl";

    public final static String GLOBALPROPERTY_FHIRFORM_DEFAULT_BASEURL = "https://fhirtest.uhn.ca/baseDstu3/Questionnaire/";

    public final static String GLOBALPROPERTY_FHIRFORM_USERNAME = "fhirform.username";

    public final static String GLOBALPROPERTY_FHIRFORM_DEFAULT_USERNAME = "beapen";

    public final static String GLOBALPROPERTY_FHIRFORM_PASSWORD = "fhirform.password";

    public final static String GLOBALPROPERTY_FHIRFORM_DEFAULT_PASSWORD = "fhirform123";

    public final static int GLOBALPROPERTY_FHIRFORM_SUCCESS = 200;

    public static final SimpleObject NUFORM_CONSTANTS() {
        SimpleObject o = new SimpleObject();
        o.put("GENERALFORM", "1");
        o.put("PATIENTFORM", "2");
        o.put("PERSONALFORM", "3");
        o.put("ACTIVE", "1");
        o.put("DELETED", "0");
        o.put("SUCCESS", "success");
        o.put("ERROR", "error");
        return o;

    }

}
