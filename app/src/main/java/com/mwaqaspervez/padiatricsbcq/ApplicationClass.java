package com.mwaqaspervez.padiatricsbcq;

import android.app.Application;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;


public class ApplicationClass extends Application {

    private static ApplicationClass instance = null;
    private static FirebaseAuth mAuth;

    public static ApplicationClass getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        instance = this;
        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        super.onCreate();

    }

    public FirebaseUser getCurrentUser() {
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    public FirebaseAuth getAuth() {
        return mAuth;
    }
}
