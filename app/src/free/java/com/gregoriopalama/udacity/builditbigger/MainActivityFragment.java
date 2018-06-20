package com.gregoriopalama.udacity.builditbigger;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.gregoriopalama.udacity.builditbigger.jokedetail.JokeDetailActivity;
import com.gregoriopalama.udacity.builditbigger.databinding.FragmentMainBinding;


/**
 * The fragment that contains the button to retrieve the joke and
 * an ad. The ad is visible only for the free flavor
 *
 * @author Gregorio Palamà
 */
public class MainActivityFragment extends Fragment implements JokesTellerCallback {
    private static final int REQUEST_CODE = 100;
    private FragmentMainBinding binding;
    private InterstitialAd interstitialAd;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        binding.adView.loadAd(adRequest);

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
        interstitialAd = new InterstitialAd(getContext());
        interstitialAd.setAdUnitId(getContext().getString(R.string.interstitial_ad_unit_id));
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                interstitialAd.show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);
                showJokeDetail(joke);
            }

            @Override
            public void onAdClosed() {
                showJokeDetail(joke);
            }
        });
        AdRequest request = new AdRequest
                .Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        interstitialAd.loadAd(request);
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
