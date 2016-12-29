package com.mwaqaspervez.padiatricsbcq;

import android.app.Application;

import com.google.android.gms.ads.MobileAds;
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
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3750819656943471~1811440948");
        super.onCreate();

    }

    public FirebaseUser getCurrentUser() {
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    public FirebaseAuth getAuth() {
        return mAuth;
    }


}
