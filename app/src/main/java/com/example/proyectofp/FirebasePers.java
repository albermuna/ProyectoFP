package com.example.proyectofp;

import com.google.firebase.database.FirebaseDatabase;

public class FirebasePers extends android.app.Application{
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
