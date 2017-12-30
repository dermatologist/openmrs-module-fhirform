package org.openmrs.module.fhirform;

import com.google.gson.Gson;

public class JsonObjectSchema {

    private JsonObjectProperty __message = new JsonObjectProperty();
    private JsonObjectItems __questions = new JsonObjectItems();

    public JsonObjectProperty get__message() {
        return __message;
    }

    public void set__message(JsonObjectProperty __message) {
        this.__message = __message;
    }

    public JsonObjectItems get__questions() {
        return __questions;
    }

    public void set__questions(JsonObjectItems __questions) {
        this.__questions = __questions;
    }

    public String getForm() {
        Gson gson = new Gson();
        return gson.toJson(this).replace("__", "");
    }

}
