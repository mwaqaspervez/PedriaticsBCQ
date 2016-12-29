package com.mwaqaspervez.padiatricsbcq;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LoginActivity extends AppCompatActivity {

    private EditText email, password;
    private ProgressDialog dialog;
    private AdView mAdView;


    private FirebaseAuth.AuthStateListener listener = new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            if (firebaseAuth.getCurrentUser() != null)
                LoginActivity.this.finish();
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();

        mAdView.loadAd(adRequest);

        email = (EditText) findViewById(R.id.login_email);
        password = (EditText) findViewById(R.id.login_password);


        ApplicationClass.getInstance().getAuth().addAuthStateListener(listener);
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

            case R.id.login_login:

                if (email.getText().toString().trim().toLowerCase().equals("getkey") &&
                        password.getText().toString().trim().toLowerCase().equals("getkey"))
                    startActivity(new Intent(this, GenerateActivationKey.class));
                else {

                    dialog = ProgressDialog.show(LoginActivity.this, "", "Please Wait...",
                            true);

                    ApplicationClass.getInstance().getAuth()
                            .signInWithEmailAndPassword(email.getText().toString(),
                                    password.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    dialog.dismiss();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            dialog.dismiss();
                            new AlertDialog.Builder(LoginActivity.this)
                                    .setMessage(e.getMessage())
                                    .setCancelable(true)
                                    .create()
                                    .show();
                        }
                    });
                }
                break;

            case R.id.login_buy:
                startActivity(new Intent(this, BuyNowActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                break;

            case R.id.login_code:

                final EditText edittext = new EditText(this);
                new AlertDialog.Builder(this)
                        .setMessage("Enter Your Activation Code")
                        .setTitle("Activation")
                        .setView(edittext)
                        .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                checkActivationCode(edittext.getText().toString());
                            }
                        }).show();
                break;
        }
    }

    private void checkActivationCode(final String value) {

        dialog = ProgressDialog.show(LoginActivity.this, "", "Please Wait...",
                true);

        final DialogInterface finalDialog = dialog;
        FirebaseDatabase.getInstance().getReference()
                .child("activationKey")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            if (snapshot.getValue().toString().trim().equals(value.trim()))
                                loginAccount();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        new AlertDialog.Builder(LoginActivity.this)
                                .setMessage(databaseError.getMessage())
                                .setCancelable(true)
                                .create()
                                .show();
                        finalDialog.dismiss();
                    }
                });
    }

    private void loginAccount() {

        ApplicationClass.getInstance().getAuth()
                .signInWithEmailAndPassword("waqas@waqas.com",
                        "1234567")
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        dialog.dismiss();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
                new AlertDialog.Builder(LoginActivity.this)
                        .setMessage(e.getMessage())
                        .setCancelable(false)
                        .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                LoginActivity.this.finish();
                            }
                        })
                        .create()
                        .show();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mAdView != null)
            mAdView.resume();
        ApplicationClass.getInstance().getAuth().addAuthStateListener(listener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mAdView != null)
            mAdView.pause();
        ApplicationClass.getInstance().getAuth().removeAuthStateListener(listener);
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
