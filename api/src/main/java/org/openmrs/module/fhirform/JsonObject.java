package org.openmrs.module.fhirform;

import com.google.gson.Gson;

public class JsonObject {

    private JsonObjectSchema __schema;

    private JsonObjectForm __form;

    private JsonObjectFunction __onSubmitValid;

    public JsonObjectSchema get__schema() {
        return __schema;
    }

    public void set__schema(JsonObjectSchema __schema) {
        this.__schema = __schema;
    }

    public JsonObjectForm get__form() {
        return __form;
    }

    public void set__form(JsonObjectForm __form) {
        this.__form = __form;
    }

    public JsonObjectFunction get__onSubmitValid() {
        return __onSubmitValid;
    }

    public void set__onSubmitValid(JsonObjectFunction __onSubmitValid) {
        this.__onSubmitValid = __onSubmitValid;
    }

    public String getForm() {
        Gson gson = new Gson();
        return gson.toJson(this).replace("__", "");
    }
}
