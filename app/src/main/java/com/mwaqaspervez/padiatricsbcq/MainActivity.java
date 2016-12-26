package com.mwaqaspervez.padiatricsbcq;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                startActivity(new Intent(this, LoginActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                break;


            case R.id.books:
                break;
        }
    }
}
