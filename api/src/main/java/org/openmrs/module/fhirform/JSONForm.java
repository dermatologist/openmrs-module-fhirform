package org.openmrs.module.fhirform;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by beapen on 04/12/2017.
 */
public class JSONForm {

    public ArrayList<JSONFormSet> __items;

    public void add_item(JSONFormSet __item) {
        this.__items.add(__item);
    }

    public String getForm() {
        Gson gson = new Gson();
        return gson.toJson(this).replace("__", "");
    }

}
