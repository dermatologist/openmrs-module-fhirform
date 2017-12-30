package org.openmrs.module.fhirform;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.openmrs.module.fhirform.api.impl.CollectionAdapter;

import java.util.Collection;

public class JsonObjectFromFhir {

    private JsonObjectSchema __schema = new JsonObjectSchema();

    private JsonObjectForm __form = new JsonObjectForm();

    private JsonObjectFunction __onSubmitValid = new JsonObjectFunction();

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
        Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(
                Collection.class, new CollectionAdapter()).create();
        return gson.toJson(this).replace("__", "");
    }
}
