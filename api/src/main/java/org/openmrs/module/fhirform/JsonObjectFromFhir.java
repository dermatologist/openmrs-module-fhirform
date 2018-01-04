package org.openmrs.module.fhirform;

import com.bazaarvoice.jolt.Chainr;
import com.bazaarvoice.jolt.JsonUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.openmrs.module.fhirform.api.impl.CollectionAdapter;
import org.openmrs.ui.framework.SimpleObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JsonObjectFromFhir {

    //JsonObjectItems
    private SimpleObject __schema = new SimpleObject();

    //Previously submitted form values
    private SimpleObject __values = new SimpleObject();

    private ArrayList<JsonObjectForm> __form = new ArrayList<JsonObjectForm>();

    private JsonObjectFunction __onSubmitValid = new JsonObjectFunction();



    public ArrayList<JsonObjectForm> get__form() {
        return __form;
    }

    public void set__form(ArrayList<JsonObjectForm> __form) {
        this.__form = __form;
    }

    public void add_form(JsonObjectForm jsonObjectForm) {
        this.__form.add(jsonObjectForm);
    }

    public void add_schema(String key, JsonObjectItem jsonObjectItem) {
        this.__schema.put(key, jsonObjectItem);
    }

    //Previously submitted form values
    public void add_value(String key, String value) {
        this.__values.put(key, value);
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
        String jsonFhirForm = gson.toJson(this).replace("__", "");

        String transformInput = "{" + jsonFhirForm.substring(jsonFhirForm.indexOf("\"properties\":["), jsonFhirForm.indexOf("}]")) + "}]}";

        String spec = "[\n" +
                "  {\n" +
                "    \"operation\": \"shift\",\n" +
                "    \"spec\": {\n" +
                "      \"properties\": {\n" +
                "        \"*\": {\n" +
                "          \"linkId\": {\n" +
                "            \"*\": { // match any value of keyName\n" +
                "              \"@2\": { // go back up the tree\n" +
                "                // now match value and metadata, but we can now\n" +
                "                // \"reference\" teh value of keyName as \"&2\"\n" +
                "                \"title\": \"&2.title\",\n" +
                "                \"type\": \"&2.type\",\n" +
                "                \"required\": \"&2.required\",\n" +
                "                \"default\": \"&2.default\",\n" +
                "                \"enum\": \"&2.enum\"\n" +
                "              }\n" +
                "            }\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "]";

        List chainrSpecJSON = JsonUtils.jsonToList(spec);
        Chainr chainr = Chainr.fromSpec(chainrSpecJSON);

        Object inputJSON = JsonUtils.jsonToObject(transformInput);

        Object transformedOutput = chainr.transform(inputJSON);

        String toReplace = jsonFhirForm.substring(jsonFhirForm.indexOf("\"properties\":["), jsonFhirForm.indexOf("}]"));

        String replacement = "  \"properties\":" + JsonUtils.toJsonString(transformedOutput);


        //String toReturn = jsonFhirForm.replace(toReplace + "}]", replacement).replace(",\"form\":{},\"onSubmitValid\":{}", "");
        String toReturn = jsonFhirForm.replace(toReplace + "}]", replacement)
                .replace(",\"onSubmitValid\":{}", ",\"onSubmitValid\":{function(values){fhirFormSubmit(values);}}");

        return toReturn;
    }
}
