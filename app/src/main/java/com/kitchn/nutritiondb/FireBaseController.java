package com.kitchn.nutritiondb;

import android.content.Context;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by benebsworth on 23/05/16.
 */
public class FireBaseController extends Search {

    public boolean initialised  = false;
    public FirebaseDatabase mDatabase;


    public void initialise(Context context) {
        if (!initialised) {
            mDatabase.getReferenceFromUrl(Config.FIREBASE_URL);
        }

    }
}
