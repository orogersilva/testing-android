package com.orogersilva.androidtesting.model.bll;

import android.test.suitebuilder.annotation.SmallTest;

import com.orogersilva.androidtesting.model.dal.UserDal;
import com.orogersilva.androidtesting.vo.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by azevedor on 10/01/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserBllTest {

    // region FIELDS

    @Mock
    UserDal mUserDalMock;

    // endregion

    // region TEST METHODS

    @Test(expected = NullPointerException.class)
    @SmallTest
    public void addUser_whenUserIsNull_throwsNullPointerException() {

        // ARRANGE

        User nullUser = null;
        UserBll userBll = new UserBll(mUserDalMock);

        // ACT
        userBll.addUser(nullUser);
    }

    // endregion
}
