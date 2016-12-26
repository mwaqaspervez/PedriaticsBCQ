package com.mwaqaspervez.padiatricsbcq;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;

public class BuyNowActivity extends AppCompatActivity {

    private EditText name, password, phone, email;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_buy_now);
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

                dialog = ProgressDialog.show(BuyNowActivity.this, "", "Please Wait...",
                        true);

                FirebaseAuth.getInstance().
                        createUserWithEmailAndPassword(email.getText().toString(),
                                password.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {

                                FirebaseDatabase.getInstance().getReference()
                                        .child("users").push().
                                        setValue(new UserModel(name.getText().toString(),
                                                email.getText().toString(),
                                                phone.getText().toString(),
                                                password.getText().toString(),
                                                DateFormat.getDateTimeInstance().format(new Date())))

                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                dialog.cancel();
                                                Toast.makeText(BuyNowActivity.this, "Registration Done", Toast.LENGTH_SHORT).show();
                                            }

                                        }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        new AlertDialog.Builder(BuyNowActivity.this)
                                                .setMessage(e.getMessage())
                                                .setCancelable(true)
                                                .create()
                                                .show();
                                    }
                                });


                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        dialog.cancel();
                        new AlertDialog.Builder(BuyNowActivity.this)
                                .setMessage(e.getMessage())
                                .setCancelable(true)
                                .create()
                                .show();


                    }
                });

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
}
