package org.openmrs.module.fhirform;

import com.google.gson.Gson;

import java.util.ArrayList;

public class JsonObjectProperty {

    private String __title;

    private String __type;

    private ArrayList<String> __enum;

    private String __default;

    private boolean __required = false;

    public String get__title() {
        return __title;
    }

    public void set__title(String __title) {
        this.__title = __title;
    }

    public String get__type() {
        return __type;
    }

    public void set__type(String __type) {
        this.__type = __type;
    }

    public ArrayList<String> get__enum() {
        return __enum;
    }

    public void set__enum(ArrayList<String> __enum) {
        this.__enum = __enum;
    }

    public String get__default() {
        return __default;
    }

    public void set__default(String __default) {
        this.__default = __default;
    }

    public boolean is__required() {
        return __required;
    }

    public void set__required(boolean __required) {
        this.__required = __required;
    }

    public void add_item(String item) {
        this.__enum.add(item);
    }

    public String getForm() {
        Gson gson = new Gson();
        return gson.toJson(this).replace("__", "");
    }
}
