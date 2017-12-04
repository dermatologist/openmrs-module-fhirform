package org.openmrs.module.fhirform;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by beapen on 04/12/2017.
 */
public class JSONFormSet {

    public ArrayList<JSONFormItem> __items;
    public String __type = "object";
    public String __title = "Title";

    public ArrayList<JSONFormItem> get__items() {
        return __items;
    }

    public void add_item(JSONFormItem __item) {
        this.__items.add(__item);
    }

    public String getForm() {
        Gson gson = new Gson();
        return gson.toJson(this).replace("__", "");
    }


}
