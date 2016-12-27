package com.mwaqaspervez.padiatricsbcq;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

public class MBBSMain extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_mbbs);

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

            case R.id.mbb_imnci:
                intent.putExtra("subject", "mbb_imnci");
                break;

            case R.id.mbb_growth:
                intent.putExtra("subject", "mbb_growth");
                break;
            case R.id.mbb_neonatology:
                intent.putExtra("subject", "mbb_neonatology");
                break;
            case R.id.mbb_hematology:
                intent.putExtra("subject", "mbb_hematology");
                break;
            case R.id.mbb_cardiology:
                intent.putExtra("subject", "mbb_cardiology");
                break;

            case R.id.mbb_pulmonology:
                intent.putExtra("subject", "mbb_pulmonology");
                break;
            case R.id.mbb_nephrology:
                intent.putExtra("subject", "mbb_nephrology");
                break;
            case R.id.mbb_endocrinology:
                intent.putExtra("subject", "mbb_endocrinology");
                break;
            case R.id.mbb_dermatology:
                intent.putExtra("subject", "mbb_dermatology");
                break;
        }

        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
    }
}
