package org.openmrs.module.fhirform;

/**
 * Created by beapen on 05/12/2017.
 */

import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openmrs.api.AdministrationService;
import org.openmrs.api.context.Context;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
// Cannot use @component annotation as openmrs freezes on installation
// TODO: Find root cause and rectify this
public class FHIRHttpClient {

    //private Log log = LogFactory.getLog(this.getClass());

    private AdministrationService administrationService;

    private String baseUrl;

    private String userName;

    private String password;

    private String protocol = "GET";

    private String urlString;

    public FHIRHttpClient() {
        try {
            administrationService = Context.getAdministrationService();
            baseUrl = administrationService.getGlobalProperty(FhirformConstants.GLOBALPROPERTY_FHIRFORM_BASEURL,
                    FhirformConstants.GLOBALPROPERTY_FHIRFORM_DEFAULT_BASEURL);
            userName = administrationService.getGlobalProperty(FhirformConstants.GLOBALPROPERTY_FHIRFORM_USERNAME,
                    FhirformConstants.GLOBALPROPERTY_FHIRFORM_DEFAULT_USERNAME);
            password = administrationService.getGlobalProperty(FhirformConstants.GLOBALPROPERTY_FHIRFORM_PASSWORD,
                    FhirformConstants.GLOBALPROPERTY_FHIRFORM_DEFAULT_PASSWORD);
        } catch (Exception e) {
            // For testing
            baseUrl = FhirformConstants.GLOBALPROPERTY_FHIRFORM_DEFAULT_BASEURL;
        }
    }

    public JSONObject getFhirform(String Url, String formID, String version) {
        if (Url == "")
            this.urlString = baseUrl + formID + "/_history/" + version;
        else
            this.urlString = Url + formID + "/_history/" + version;
        this.protocol = "GET";
        JSONObject jsonObject = new JSONObject();
        Object returnGet = get();
        if (returnGet instanceof JSONArray || returnGet == null) // If not found, returns an empty array
            return jsonObject;
        else
            return (JSONObject) returnGet; // If found returns an object.
    }

    public Object get() {
        Client client = ClientBuilder.newClient();
        ResteasyWebTarget target = (ResteasyWebTarget) client.target(this.urlString);

        // TODO: Change this for authentication
        //target.register(new BasicAuthentication(this.userName, this.password));

        Response response = target.request().get();
        //Read output in string format
        String return_value = response.readEntity(String.class);
        response.close();
        JSONParser parser = new JSONParser();
        Object responseObject = null;
        try {
            responseObject = parser.parse(return_value);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return responseObject;
    }

}
