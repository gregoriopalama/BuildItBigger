package com.gregoriopalama.udacity.builditbigger;

import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * The large test that verify if the task is correctly retrieving the jokes
 *
 * @author Gregorio Palamà
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class JokesTellerTaskTest {
    public static final String TAG = "JokesTellerTaskTest";
    @Test
    public void testTask() throws Exception {
        final Object syncObject = new Object();
        JokesTellerTask task = new JokesTellerTask(new JokesTellerCallback() {
            @Override
            public void startLoadingJoke() {

            }

            @Override
            public void showJoke(String joke) {
                Log.d(TAG, "The read joke is: "+joke);
                Assert.assertNotNull(joke);
                Assert.assertTrue(joke.length() > 0);

                synchronized (syncObject){
                    syncObject.notify();
                }
            }
        });

        task.execute();

        synchronized (syncObject){
            syncObject.wait();
        }
    }
}
