package com.orogersilva.androidtesting.net;

import com.firebase.client.Firebase;
import com.orogersilva.androidtesting.vo.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by orogersilva on 2/7/2016.
 */
public class UserNet {

    // region FIELDS

    private Firebase mFirebaseUser;

    // endregion

    // region CONSTRUCTORS

    public UserNet(Firebase firebase) {

        mFirebaseUser = firebase;
    }

    // endregion

    public List<User> getUsers() {

        return null;
    }

    public void sendUsers(List<User> users) {
    }
}
