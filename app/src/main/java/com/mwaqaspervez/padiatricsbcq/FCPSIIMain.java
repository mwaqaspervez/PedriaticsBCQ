package com.mwaqaspervez.padiatricsbcq;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class FCPSIIMain extends AppCompatActivity {

    private AdView mAdView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_fcps_ii);

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

            case R.id.fcps_ii_imnci:
                intent.putExtra("subject", "fcps_ii_imnci");
                break;
            case R.id.fcps_ii_growth:
                intent.putExtra("subject", "fcps_ii_growth");
                break;
            case R.id.fcps_ii_infections:
                intent.putExtra("subject", "fcps_ii_infections");
                break;

            case R.id.fcps_ii_hematology:
                intent.putExtra("subject", "fcps_ii_hematology");
                break;
            case R.id.fcps_ii_gastroenterology:
                intent.putExtra("subject", "fcps_ii_gastroenterology");
                break;
            case R.id.fcps_ii_pulmonology:
                intent.putExtra("subject", "fcps_ii_pulmonology");
                break;
            case R.id.fcps_ii_nephrology:
                intent.putExtra("subject", "fcps_ii_nephrology");
                break;
            case R.id.fcps_ii_cardiology:
                intent.putExtra("subject", "fcps_ii_cardiology");
                break;
            case R.id.fcps_ii_dermatology:
                intent.putExtra("subject", "fcps_ii_dermatology");
                break;

            case R.id.fcps_ii_rheumatology:
                intent.putExtra("subject", "fcps_ii_rheumatology");
                break;
            case R.id.fcps_ii_neurology:
                intent.putExtra("subject", "fcps_ii_neurology");
                break;
            case R.id.fcps_ii_neonatology:
                intent.putExtra("subject", "fcps_ii_neonatology");
                break;
            case R.id.fcps_ii_orthopedics:
                intent.putExtra("subject", "fcps_ii_orthopedics");
                break;
            case R.id.fcps_ii_endocrinology:
                intent.putExtra("subject", "fcps_ii_endocrinology");
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
