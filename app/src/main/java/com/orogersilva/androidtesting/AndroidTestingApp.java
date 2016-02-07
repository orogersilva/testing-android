package com.orogersilva.androidtesting;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by orogersilva on 2/7/2016.
 */
public class AndroidTestingApp extends Application {

    // region APPLICATION LIFECYCLE METHODS

    @Override
    public void onCreate() {

        super.onCreate();

        // Initializing Firebase...
        Firebase.setAndroidContext(this);
    }

    // endregion
}
