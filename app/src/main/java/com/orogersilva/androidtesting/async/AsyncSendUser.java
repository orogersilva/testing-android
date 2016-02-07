package com.orogersilva.androidtesting.async;

import android.content.Context;
import android.os.AsyncTask;

import com.firebase.client.Firebase;
import com.orogersilva.androidtesting.net.UserNet;
import com.orogersilva.androidtesting.vo.User;

import java.util.List;

/**
 * Created by orogersilva on 2/7/2016.
 */
public class AsyncSendUser extends AsyncTask<Void, Void, Void> {

    // region FIELDS

    private List<User> mUsers;
    private SendUserCallback mCallback;

    // endregion

    // region CALLBACKS

    public interface SendUserCallback {
        void onFinish();
    }

    // endregion

    // region CONSTRUCTORS

    public AsyncSendUser(List<User> users, SendUserCallback callback) {
        mUsers = users;
        mCallback = callback;
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    protected Void doInBackground(Void... voids) {

        UserNet userNet = new UserNet(new Firebase("https://android-testing.firebaseio.com/users"));

        userNet.sendUsers(mUsers);

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {

        super.onPostExecute(result);

        mCallback.onFinish();
    }

    // endregion
}
