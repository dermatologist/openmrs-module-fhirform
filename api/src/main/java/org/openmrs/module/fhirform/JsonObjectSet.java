package org.openmrs.module.fhirform;

import com.google.gson.Gson;

import java.util.ArrayList;

public class JsonObjectSet {
    private String __type = "object";
    private String __title = "";
    private ArrayList<JsonObjectProperty> __properties = new ArrayList<JsonObjectProperty>();

    public String get__type() {
        return __type;
    }

    public void set__type(String __type) {
        this.__type = __type;
    }

    public String get__title() {
        return __title;
    }

    public void set__title(String __title) {
        this.__title = __title;
    }

    public ArrayList<JsonObjectProperty> get__properties() {
        return __properties;
    }

    public void set__properties(ArrayList<JsonObjectProperty> __properties) {
        this.__properties = __properties;
    }

    public void add_item(JsonObjectProperty jsonObjectProperty) {
        this.__properties.add(jsonObjectProperty);
    }

    public String getForm() {
        Gson gson = new Gson();
        return gson.toJson(this).replace("__", "");
    }
}
