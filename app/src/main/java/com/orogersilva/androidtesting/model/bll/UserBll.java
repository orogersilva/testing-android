package com.orogersilva.androidtesting.model.bll;

import com.orogersilva.androidtesting.model.dal.UserDal;
import com.orogersilva.androidtesting.vo.User;

/**
 * Created by azevedor on 10/01/2016.
 */
public class UserBll {

    // region FIELDS

    private UserDal mUserDal;

    // endregion

    // region CONSTRUCTORS

    public UserBll(UserDal userDal) {
        mUserDal = userDal;
    }

    // endregion

    // region Business CRUD

    public void addUser(User user) {

        if (user == null) {
            throw new NullPointerException();
        }

        mUserDal.createUser(user);
    }

    public User getUser(String name) {

        User user = mUserDal.retrieveUser(name);

        return user;
    }

    // endregion
}
