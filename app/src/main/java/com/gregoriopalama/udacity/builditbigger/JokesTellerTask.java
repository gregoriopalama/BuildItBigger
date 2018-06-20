package com.gregoriopalama.udacity.builditbigger;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.gregoriopalama.udacity.builditbigger.backend.cnJokesApi.CnJokesApi;

import java.io.IOException;


/**
 * A task to retrieve the jokes
 *
 * @author Gregorio Palamà
 */

public class JokesTellerTask extends AsyncTask<Void, Void, String> {
    private static CnJokesApi cnJokesApiService = null;
    private static final String ENDPOINT_URL = "http://10.0.2.2:8080/_ah/api/";

    private JokesTellerCallback callback;

    public JokesTellerTask(JokesTellerCallback callback) {
        this.callback = callback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        callback.startLoadingJoke();
    }

    @Override
    protected String doInBackground(Void... voids) {
        if(cnJokesApiService == null) {
            CnJokesApi.Builder builder = new CnJokesApi.Builder(AndroidHttp.newCompatibleTransport(),
                new AndroidJsonFactory(), null)
                .setRootUrl(ENDPOINT_URL)
                .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                    @Override
                    public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest)
                            throws IOException {
                        abstractGoogleClientRequest.setDisableGZipContent(true);
                    }
                });

            cnJokesApiService = builder.build();
        }

        try {
            return cnJokesApiService.tellAJoke().execute().getJoke();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String joke) {
        super.onPostExecute(joke);
        callback.showJoke(joke);
    }
}
