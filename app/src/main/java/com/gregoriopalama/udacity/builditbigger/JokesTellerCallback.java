package com.gregoriopalama.udacity.builditbigger;

/**
 * An interface to implement the callback for the task that retrieves the jokes
 *
 * @author Gregorio Palamà
 */

public interface JokesTellerCallback {
    public void startLoadingJoke();
    public void showJoke(String joke);
}
