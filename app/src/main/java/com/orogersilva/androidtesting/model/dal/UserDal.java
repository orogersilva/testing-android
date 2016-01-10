package com.orogersilva.androidtesting.model.dal;

import android.content.Context;

import com.orogersilva.androidtesting.vo.User;

import io.realm.Realm;

/**
 * Created by azevedor on 10/01/2016.
 */
public class UserDal {

    // region FIELDS

    private Realm mRealm;
    private Context mContext;

    // endregion

    // region CONSTRUCTORS

    public UserDal(Context context) {

        mContext = context;
        mRealm = Realm.getInstance(mContext);
    }

    // endregion

    // region CRUD

    public void createUser(User user) {

        mRealm.beginTransaction();
        mRealm.copyToRealm(user);
        mRealm.commitTransaction();
    }

    public User retrieveUser(String name) {

        User user = mRealm.where(User.class).equalTo("name", name).findFirst();

        return user;
    }

    // endregion
}
