package com.gregoriopalama.udacity.builditbigger;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gregoriopalama.udacity.builditbigger.jokedetail.JokeDetailActivity;
import com.gregoriopalama.udacity.builditbigger.databinding.FragmentMainBinding;


/**
 * The fragment that contains the button to retrieve the joke.
 * In the paid flavor, there is no ad.
 *
 * @author Gregorio Palamà
 */
public class MainActivityFragment extends Fragment implements JokesTellerCallback {
    private static final int REQUEST_CODE = 100;
    private FragmentMainBinding binding;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);

        binding.tellJoke.setOnClickListener(v -> {
            JokesTellerTask task = new JokesTellerTask(this);
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        });

        return binding.getRoot();
    }

    @Override
    public void startLoadingJoke() {
        binding.instructionsTextView.setVisibility(View.GONE);
        binding.tellJoke.setVisibility(View.GONE);
        binding.progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void showJoke(final String joke) {
        showJokeDetail(joke);
    }

    private void hideProgress() {
        binding.instructionsTextView.setVisibility(View.VISIBLE);
        binding.tellJoke.setVisibility(View.VISIBLE);
        binding.progress.setVisibility(View.GONE);
    }

    private void showJokeDetail(String joke) {
        Intent intent = new Intent(getContext(), JokeDetailActivity.class);
        intent.putExtra(JokeDetailActivity.JOKE_EXTRA_NAME, joke);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            hideProgress();
        }
    }
}
