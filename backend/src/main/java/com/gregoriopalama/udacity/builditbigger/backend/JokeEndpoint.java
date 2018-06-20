package com.gregoriopalama.udacity.builditbigger.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.gregoriopalama.udacity.builditbigger.cnjokeslib.CNJokeTeller;

/**
 * The exposed Endpoint that produces jokes
 *
 * @author Gregorio Palamà
 */
@Api(
        name = "cnJokesApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.udacity.gregoriopalama.com",
                ownerName = "backend.builditbigger.udacity.gregoriopalama.com"
        )
)
public class JokeEndpoint {

    /**
     * The endpoint's method that produces the jokes
     */
    @ApiMethod(name = "tellAJoke")
    public JokeBean tellAJoke() {
        JokeBean response = new JokeBean();
        response.setJoke(CNJokeTeller.tellJoke());

        return response;
    }

}
