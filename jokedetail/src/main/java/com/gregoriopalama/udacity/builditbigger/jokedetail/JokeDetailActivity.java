package com.gregoriopalama.udacity.builditbigger.jokedetail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.gregoriopalama.udacity.builditbigger.jokedetail.databinding.ActivityJokeDetailBinding;

/**
 * An activity to show the joke in detail
 *
 * @author Gregorio Palamà
 */
public class JokeDetailActivity extends AppCompatActivity {
    public static final String JOKE_EXTRA_NAME = "com.gregoriopalama.udacity.builditbigger.jokedetail.JOKE";
    ActivityJokeDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_joke_detail);

        if (!getIntent().hasExtra(JOKE_EXTRA_NAME))
            finish();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.setJoke(getIntent().getStringExtra(JOKE_EXTRA_NAME));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
