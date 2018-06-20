package com.gregoriopalama.udacity.builditbigger.backend;

/**
 * The Joke model that will be sent through the endpoint
 *
 * @author Gregorio Palamà
 */
public class JokeBean {

    private String joke;

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }
}