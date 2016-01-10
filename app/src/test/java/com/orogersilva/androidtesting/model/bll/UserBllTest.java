package com.orogersilva.androidtesting.model.bll;

import android.test.suitebuilder.annotation.SmallTest;

import com.orogersilva.androidtesting.model.dal.UserDal;
import com.orogersilva.androidtesting.vo.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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

    @Test
    @SmallTest
    public void addUser_whenUserIsNotNull_verifyCallToDal() {

        // ARRANGE

        final String USER_NAME = "Roger";
        final String USER_AGE = "27";
        final String USER_CITY = "Alvorada";

        User validUser = new User(USER_NAME, USER_AGE, USER_CITY);

        UserBll userBll = new UserBll(mUserDalMock);

        // ACT
        userBll.addUser(validUser);

        // ASSERT
        verify(mUserDalMock, times(1)).createUser(validUser);
    }

    @Test
    @SmallTest
    public void getUser_whenUserNameIsNull_returnsNull() {

        // ARRANGE

        final String NULL_USER_NAME = null;

        when(mUserDalMock.retrieveUser(NULL_USER_NAME)).thenReturn(null);

        UserBll userBll = new UserBll(mUserDalMock);

        // ACT
        User gottenNullUser = userBll.getUser(NULL_USER_NAME);

        // ASSERT
        assertNull(gottenNullUser);
    }

    @Test
    @SmallTest
    public void getUser_whenUserNameIsEmpty_returnsNull() {

        // ARRANGE

        final String EMPTY_USER_NAME = "";

        when(mUserDalMock.retrieveUser(EMPTY_USER_NAME)).thenReturn(null);

        UserBll userBll = new UserBll(mUserDalMock);

        // ACT
        User gottenNullUser = userBll.getUser(EMPTY_USER_NAME);

        // ASSERT
        assertNull(gottenNullUser);
    }

    @Test
    @SmallTest
    public void getUser_whenUserNameIsValid_returnsUser() {

        // ARRANGE

        final String FAILED_TEST_MESSAGE = "Usuário recuperado deve ser igual ao usuário esperado.";

        final String VALID_USER_NAME = "Roger";
        final String USER_AGE = "27";
        final String USER_CITY = "Alvorada";

        User expectedUser = new User(VALID_USER_NAME, USER_AGE, USER_CITY);

        when(mUserDalMock.retrieveUser(VALID_USER_NAME)).thenReturn(expectedUser);

        UserBll userBll = new UserBll(mUserDalMock);

        // ACT
        User gottenValidUser = userBll.getUser(VALID_USER_NAME);

        // ASSERT
        assertEquals(FAILED_TEST_MESSAGE, expectedUser, gottenValidUser);
    }

    // endregion
}
