package com.mwaqaspervez.padiatricsbcq;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.mwaqaspervez.padiatricsbcq.inAppBilling.IabHelper;
import com.mwaqaspervez.padiatricsbcq.inAppBilling.IabResult;
import com.mwaqaspervez.padiatricsbcq.inAppBilling.Purchase;

public class BuyNowActivity extends AppCompatActivity {

    public static final String TAG = "InAppBilling:";
    private static final String SKU_GAS = "android.test.purchased";
    IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener
            = new IabHelper.OnIabPurchaseFinishedListener() {
        public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
            if (result.isFailure())
                Log.d(TAG, "Error purchasing: " + result);
            else if (purchase.getSku().equals(SKU_GAS))
                Log.i("InAppPurchasing", "Item Bought");
        }
    };

    private EditText name, password, phone, email;
    private ProgressDialog dialog;
    private IabHelper mHelper;
    private AdView mAdView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_buy_now);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();

        mAdView.loadAd(adRequest);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        init();
    }

    private void init() {

        name = (EditText) findViewById(R.id.buy_name);
        password = (EditText) findViewById(R.id.buy_password);
        phone = (EditText) findViewById(R.id.buy_phone);
        email = (EditText) findViewById(R.id.buy_email);

        findViewById(R.id.buy_now).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateFields())
                    makeBillingRequest();
                else
                    Toast.makeText(BuyNowActivity.this, "Please Enter The Required Fields", Toast.LENGTH_LONG)
                            .show();
            }
        });
    }

    private boolean validateFields() {

        return !(name.getText().toString().isEmpty() ||
                password.getText().toString().isEmpty() ||
                email.getText().toString().isEmpty() ||
                phone.getText().toString().isEmpty());

    }

    private void makeBillingRequest() {

        String base64EncodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtaYQuaNVTmtLjzC4K2D3JnU7Xv0AuqBu1i8cm6+3mDIqKMqPXMUyjyykyYkMjktROKYUdLRnHq2/aexC3AgVa0yr3XyzJ72Wg11YFWW+Bn8Z1QaP18miAvqkupohs0DJeJa3BRS/kUCqxH8SFkyKwKiwLHSwQvxyqBI516ivoVb/NnqzQplXS6uZVsno36yvW6YhjkbP1Ld71WvPK9DG5qkyV0fSsabk6LtLM7mtZLgPJ0QOtFOqEm+qVCaHqO/m188jcutIDphmZFcsJwosswXH7l3oh3JCeLqrXx4cY5WlkEZmv+BJ2UyD/nBRNTNPHEujK/fb2tT5FUINwDR6vQIDAQAB";

        // compute your public key and store it in base64EncodedPublicKey
        mHelper = new IabHelper(this, base64EncodedPublicKey);
        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            public void onIabSetupFinished(IabResult result) {
                if (!result.isSuccess())
                    Log.d(TAG, "Problem setting up In-app Billing: " + result);
                else {

                    try {
                        mHelper.launchPurchaseFlow(BuyNowActivity.this, SKU_GAS, 10001,
                                mPurchaseFinishedListener, "bGoa+V7g/yqDXvKRqq+JTFn4uQZbPiQJo4pf9RzJ");
                    } catch (IabHelper.IabAsyncInProgressException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


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

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mAdView != null)
            mAdView.destroy();


        if (mHelper != null) try {
            mHelper.dispose();
        } catch (IabHelper.IabAsyncInProgressException e) {
            e.printStackTrace();
        }
        mHelper = null;
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
        if (ApplicationClass.getInstance().getCurrentUser() != null)
            ((TextView) findViewById(R.id.register)).setText("" + "Logout" + "");
    }

}
