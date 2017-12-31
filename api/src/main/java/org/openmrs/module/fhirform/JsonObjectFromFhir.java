package org.openmrs.module.fhirform;

import com.bazaarvoice.jolt.Chainr;
import com.bazaarvoice.jolt.JsonUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.openmrs.module.fhirform.api.impl.CollectionAdapter;

import java.util.Collection;
import java.util.List;

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


        String toReturn = jsonFhirForm.replace(toReplace + "}]", replacement).replace(",\"form\":{},\"onSubmitValid\":{}", "");
        return toReturn;
    }
}
