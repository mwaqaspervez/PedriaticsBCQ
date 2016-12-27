package com.mwaqaspervez.padiatricsbcq;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.FirebaseDatabase;

import java.security.SecureRandom;

public class GenerateActivationKey extends AppCompatActivity implements View.OnClickListener {


    private TextView copy, generate;
    private TextView key;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_generate_key);


        dialog = ProgressDialog.show(this, "", "Please Wait...",
                true);
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
                new AlertDialog.Builder(GenerateActivationKey.this)
                        .setMessage(e.getMessage())
                        .setCancelable(false)
                        .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                GenerateActivationKey.this.finish();
                            }
                        })
                        .create()
                        .show();
            }
        });
        copy = (TextView) findViewById(R.id.copy_key);
        generate = (TextView) findViewById(R.id.generate_key);
        key = (TextView) findViewById(R.id.save_key);
        copy.setOnClickListener(this);
        generate.setOnClickListener(this);

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


    void copyKey() {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("key", key.getText().toString());
        clipboard.setPrimaryClip(clip);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.copy_key:
                copyKey();
                Snackbar.make(findViewById(R.id.copy_key), "Key Copied !", Snackbar.LENGTH_LONG).show();
                break;

            case R.id.generate_key:
                dialog = ProgressDialog.show(this, "", "Please Wait...",
                        true);
                final String generatedKey = generateRandomKey(6);

                FirebaseDatabase.getInstance().getReference().child("activationKey")
                        .push().setValue(generatedKey.trim())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                key.setText(generatedKey.trim());
                                copy.setEnabled(true);
                                copy.setBackgroundResource(R.color.colorPrimary);
                                dialog.dismiss();
                                Snackbar.make(findViewById(R.id.copy_key), "Key Generated", Snackbar.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        new AlertDialog.Builder(GenerateActivationKey.this)
                                .setMessage(e.getMessage())
                                .setCancelable(true)
                                .create()
                                .show();
                        dialog.dismiss();
                    }
                });

                break;
        }
    }

    String generateRandomKey(int len) {
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }


    @Override
    protected void onDestroy() {
        ApplicationClass.getInstance().getAuth().signOut();
        super.onDestroy();

    }
}