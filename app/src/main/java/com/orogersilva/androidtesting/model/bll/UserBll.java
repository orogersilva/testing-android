package com.orogersilva.androidtesting.model.bll;

import com.orogersilva.androidtesting.model.dal.UserDal;
import com.orogersilva.androidtesting.utils.StringUtils;
import com.orogersilva.androidtesting.utils.exception.InvalidStringException;
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

        User user = null;

        try {

            if (StringUtils.isNullOrEmpty(name)) {
                throw new InvalidStringException();
            }

            user = mUserDal.retrieveUser(name);

        } catch (InvalidStringException e) {
        }

        return user;
    }

    public void updateUser(User user) {

        if (user == null) {
            throw new NullPointerException();
        }

        mUserDal.updateUser(user);
    }

    public void removeUser(String name) throws InvalidStringException {

        if (StringUtils.isNullOrEmpty(name)) {
            throw new InvalidStringException();
        }

        mUserDal.deleteUser(name);
    }

    // endregion
}
