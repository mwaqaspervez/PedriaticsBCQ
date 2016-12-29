package com.mwaqaspervez.padiatricsbcq;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class FCPSIMain extends AppCompatActivity {


    private AdView mAdView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_fcps_i);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();

        mAdView.loadAd(adRequest);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        this.finish();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, BCQMain.class);
        switch (view.getId()) {

            case R.id.fcps_i_anatomy:
                intent.putExtra("subject", "fcps_i_anatomy");
                break;


            case R.id.fcps_i_physiology:
                intent.putExtra("subject", "fcps_i_physiology");
                break;

            case R.id.fcps_i_pathology:
                intent.putExtra("subject", "fcps_i_pathology");
                break;
        }

        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
    }

    /**
     * Called when leaving the activity
     */
    @Override
    public void onPause() {
        if (mAdView != null)
            mAdView.pause();

        super.onPause();
    }

    /**
     * Called when returning to the activity
     */
    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null)
            mAdView.resume();
    }

    /**
     * Called before the activity is destroyed
     */
    @Override
    public void onDestroy() {
        if (mAdView != null)
            mAdView.destroy();

        super.onDestroy();
    }
}
