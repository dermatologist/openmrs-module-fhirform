package org.openmrs.module.fhirform;

import org.openmrs.ui.framework.SimpleObject;

/**
 * Created by beapen on 09/11/2017.
 */
public class FHIRFormConstants {
    public final static String GENERALFORM = "1";
    public final static String PATIENTFORM = "2";
    public final static String PERSONALFORM = "3";
    public final static String ACTIVE = "1";
    public final static String DELETED = "0";
    // Patient associated images for annotation. Not to be listed.
    public final static String SUCCESS = "success";
    public final static String ERROR = "error";


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