// This sample uses the Apache HTTP client library(org.apache.httpcomponents:httpclient:4.2.4)
// and the org.json library (org.json:json:20170516).

import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class Main
{
    // **********************************************
    // *** Update or verify the following values. ***
    // **********************************************

    // Replace the subscriptionKey string value with your valid subscription key.
    public static final String subscriptionKey = "ec21e11600704233a7cdb295a37249b6";

    // Replace or verify the region.
    //
    // You must use the same region in your REST API call as you used to obtain your subscription keys.
    // For example, if you obtained your subscription keys from the westus region, replace
    // "westcentralus" in the URI below with "westus".
    //
    // NOTE: Free trial subscription keys are generated in the westcentralus region, so if you are using
    // a free trial subscription key, you should not need to change this region.
    public static final String uriBase = "https://southeastasia.api.cognitive.microsoft.com/face/v1.0/detect";


    public static void main(String[] args)
    {
        HttpClient httpclient = new DefaultHttpClient();

        try
        {
            URIBuilder builder = new URIBuilder(uriBase);

            // Request parameters. All of them are optional.
            builder.setParameter("returnFaceId", "true");
            builder.setParameter("returnFaceLandmarks", "false");
            builder.setParameter("returnFaceAttributes", "age,gender,emotion");

            // Prepare the URI for the REST API call.
            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);

            // Request headers.
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);

            // Request body.
            StringEntity reqEntity = new StringEntity("{\"url\":\"data:image/jpeg;base64,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\"}");
            request.setEntity(reqEntity);

            // Execute the REST API call and get the response entity.
            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null)
            {
                // Format and display the JSON response.
                System.out.println("REST Response:\n");

                String jsonString = EntityUtils.toString(entity).trim();
                if (jsonString.charAt(0) == '[') {
                    JSONArray jsonArray = new JSONArray(jsonString);
                    System.out.println(jsonArray.toString(2));
                }
                else if (jsonString.charAt(0) == '{') {
                    JSONObject jsonObject = new JSONObject(jsonString);
                    System.out.println(jsonObject.toString(2));
                } else {
                    System.out.println(jsonString);
                }
            }
        }
        catch (Exception e)
        {
            // Display error message.
            System.out.println(e.getMessage());
        }
    }
}