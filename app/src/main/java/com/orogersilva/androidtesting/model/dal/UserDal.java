package com.orogersilva.androidtesting.model.dal;

import android.content.Context;
import android.support.annotation.VisibleForTesting;

import com.orogersilva.androidtesting.utils.StringUtils;
import com.orogersilva.androidtesting.vo.User;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.exceptions.RealmPrimaryKeyConstraintException;

/**
 * Created by azevedor on 10/01/2016.
 */
public class UserDal {

    // region FIELDS

    private Realm mRealm;
    private Context mContext;

    public static final int SUCCESS_OPERATION = 0;
    public static final int FAIL_OPERATION = 1;

    // endregion

    // region CONSTRUCTORS

    public UserDal(Context context) {

        mContext = context;
        mRealm = Realm.getInstance(mContext);
    }

    // endregion

    // region CRUD

    public int createUser(User user) {

        mRealm.beginTransaction();

        try {

            mRealm.copyToRealm(user);

        } catch (IllegalArgumentException | RealmPrimaryKeyConstraintException e) {

            return FAIL_OPERATION;

        } finally {

            mRealm.commitTransaction();
        }

        return SUCCESS_OPERATION;
    }

    public User retrieveUser(String name) {

        User user = null;

        if (!StringUtils.isNullOrEmpty(name)) {

            user = mRealm.where(User.class).equalTo("name", name).findFirst();
        }

        return user;
    }

    public void updateUser(User user) throws IllegalArgumentException {

        mRealm.beginTransaction();

        try {

            mRealm.copyToRealmOrUpdate(user);

        } catch (IllegalArgumentException e) {

            throw e;

        } finally {

            mRealm.commitTransaction();
        }
    }

    public void deleteUser(String name) {

        mRealm.beginTransaction();

        try {

            User user = mRealm.where(User.class).equalTo("name", name).findFirst();
            user.removeFromRealm();

        } catch (IllegalArgumentException | NullPointerException e) {

            throw e;

        } finally {

            mRealm.commitTransaction();
        }
    }

    // endregion

    // region OTHER METHODS

    public void clearDatabase() {

        mRealm.beginTransaction();
        mRealm.where(User.class).findAll().clear();
        mRealm.commitTransaction();
    }

    // endregion
}
