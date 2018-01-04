package org.openmrs.module.fhirform;

import com.google.gson.Gson;

import java.util.ArrayList;

public class JsonObjectForm {

    private String __key;

    private String __type;

    private String __title;

    private String __helpvalue;

    private ArrayList<JsonObjectItem> __items = new ArrayList<JsonObjectItem>();

    public String get__key() {
        return __key;
    }

    public void set__key(String __key) {
        this.__key = __key;
    }

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

    public String get__helpvalue() {
        return __helpvalue;
    }

    public void set__helpvalue(String __helpvalue) {
        this.__helpvalue = __helpvalue;
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
