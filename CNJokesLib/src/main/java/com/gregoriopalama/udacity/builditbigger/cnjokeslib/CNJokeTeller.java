package com.gregoriopalama.udacity.builditbigger.cnjokeslib;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import org.json.JSONObject;

/**
 * A class that retrieves a random joke from ICNDB
 *
 * @author Gregorio Palamà
 */
public class CNJokeTeller {
    private static final String ICNDB_API_URL = "http://api.icndb.com/jokes/random/";
    private static final String DEFAULT_JOKE = "Chuck Norris knows what you did last summer.";

    public static String tellJoke() {
        try {
            Client client = Client.create();
            WebResource webResource = client.resource(ICNDB_API_URL);

            ClientResponse response = webResource
                    .get(ClientResponse.class);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            String result = response.getEntity(String.class);
            JSONObject json = new JSONObject(result);

            return json.getJSONObject("value").getString("joke");
        } catch (Exception e) {
            return DEFAULT_JOKE;
        }
    }
}
