package com.mwaqaspervez.padiatricsbcq;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

public class FCPSIIMain extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_fcps_ii);
        if(getSupportActionBar() !=null){
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

            case R.id.fcps_ii_imnci:
                startActivity(new Intent(this, BCQMain.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                break;
            case R.id.fcps_ii_growth:
                startActivity(new Intent(this, BCQMain.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                break;
            case R.id.fcps_ii_infections:
                startActivity(new Intent(this, BCQMain.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                break;

            case R.id.fcps_ii_hematology:
                startActivity(new Intent(this, BCQMain.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                break;
            case R.id.fcps_ii_gastroenterology:
                startActivity(new Intent(this, BCQMain.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                break;
            case R.id.fcps_ii_pulmonology:
                startActivity(new Intent(this, BCQMain.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                break;
            case R.id.fcps_ii_nephrology:
                startActivity(new Intent(this, BCQMain.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                break;
            case R.id.fcps_ii_cardiology:
                startActivity(new Intent(this, BCQMain.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                break;
            case R.id.fcps_ii_dermatology:
                startActivity(new Intent(this, BCQMain.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                break;

            case R.id.fcps_ii_rheumatology:
                startActivity(new Intent(this, BCQMain.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                break;
            case R.id.fcps_ii_neurology:
                startActivity(new Intent(this, BCQMain.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                break;
            case R.id.fcps_ii_neonatology:
                startActivity(new Intent(this, BCQMain.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                break;
            case R.id.fcps_ii_orthopedics:
                startActivity(new Intent(this, BCQMain.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                break;
            case R.id.fcps_ii_endocrinology:
                startActivity(new Intent(this, BCQMain.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                break;
        }
    }
}
