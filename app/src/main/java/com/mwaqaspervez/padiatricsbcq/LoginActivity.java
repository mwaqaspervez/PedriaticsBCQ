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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;


public class LoginActivity extends AppCompatActivity {

    private EditText email, password;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        email = (EditText) findViewById(R.id.login_email);
        password = (EditText) findViewById(R.id.login_password);

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
                dialog = ProgressDialog.show(LoginActivity.this, "", "Please Wait...",
                        true);

                ApplicationClass.getInstance().getAuth()
                        .signInWithEmailAndPassword(email.getText().toString(),
                                password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                dialog.dismiss();
                                LoginActivity.this.finish();
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

                                String value = edittext.getText().toString();
                            }
                        }).show();

                break;
        }
    }
}
