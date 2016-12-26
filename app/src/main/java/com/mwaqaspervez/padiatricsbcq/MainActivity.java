package com.mwaqaspervez.padiatricsbcq;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3940256099942544~3347511713");
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        if (ApplicationClass.getInstance().getCurrentUser() != null)
            ((TextView) findViewById(R.id.register)).setText("" + "Logout" + "");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        this.finish();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.fcps_i:
                startActivity(new Intent(this, FCPSIMain.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                break;

            case R.id.fcps_ii:
                startActivity(new Intent(this, FCPSIIMain.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                break;


            case R.id.mbbs:
                startActivity(new Intent(this, MBBSMain.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                break;

            case R.id.free_tour:
                startActivity(new Intent(this, FreeTourActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                break;


            case R.id.register:
                if (ApplicationClass.getInstance().getCurrentUser() != null) {
                    ApplicationClass.getInstance().getAuth().signOut();
                    finish();
                    startActivity(getIntent());
                } else {
                    startActivity(new Intent(this, LoginActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                }
                break;
            case R.id.books:
                String packageName = "";
                Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
                if (intent == null) {
                    // Bring user to the market or let them choose an app?
                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("market://details?id=" + packageName));
                }
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onResume() {

        super.onResume();
        if (ApplicationClass.getInstance().getCurrentUser() != null)
            ((TextView) findViewById(R.id.register)).setText("" + "Logout" + "");
    }
}
