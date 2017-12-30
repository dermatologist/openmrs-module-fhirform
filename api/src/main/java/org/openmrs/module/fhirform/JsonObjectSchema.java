package org.openmrs.module.fhirform;

import com.google.gson.Gson;

import java.util.ArrayList;

public class JsonObjectSchema {

    private String __type = "array";

    private ArrayList<JsonObjectItem> __items;

    public String get__type() {
        return __type;
    }

    public void set__type(String __type) {
        this.__type = __type;
    }

    public ArrayList<JsonObjectItem> get__items() {
        return __items;
    }

    public void set__items(ArrayList<JsonObjectItem> __items) {
        this.__items = __items;
    }

    public void add_item(JsonObjectItem jsonObjectItem) {
        this.__items.add(jsonObjectItem);
    }

    public String getForm() {
        Gson gson = new Gson();
        return gson.toJson(this).replace("__", "");
    }

}
