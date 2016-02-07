package com.orogersilva.androidtesting.net;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
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

    // region GETTERS AND SETTERS

    public void sendUsers(List<User> users) {

        Map<String, Map<String, Object>> usersMap= new HashMap<String, Map<String, Object>>();

        for (User user : users) {

            Map<String, Object> userMap = new HashMap<String, Object>();

            userMap.put("name", user.getName());
            userMap.put("age", user.getAge());
            userMap.put("city", user.getCity());

            usersMap.put(user.getName(), userMap);

            mFirebaseUser.setValue(usersMap);
        }
    }

    // endregion
}
