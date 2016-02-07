package com.orogersilva.androidtesting.model.bll;

import android.test.suitebuilder.annotation.SmallTest;

import com.orogersilva.androidtesting.model.dal.UserDal;
import com.orogersilva.androidtesting.utils.exception.InvalidStringException;
import com.orogersilva.androidtesting.vo.User;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by azevedor on 10/01/2016.
 */
@SmallTest
@RunWith(MockitoJUnitRunner.class)
public class UserBllTest {

    // region FIELDS

    @Mock
    UserDal mUserDalMock;
    UserBll mUserBll;

    // endregion

    // region SETUP METHODS

    @Before
    public void setup() {

        mUserBll = new UserBll(mUserDalMock);
    }

    // endregion

    // region TEST METHODS

    @Test(expected = NullPointerException.class)
    public void addUser_whenUserIsNull_throwsNullPointerException() {

        // ARRANGE
        User nullUser = null;

        // ACT
        mUserBll.addUser(nullUser);
    }

    @Test
    public void addUser_whenUserIsNotNull_verifyCallToDal() {

        // ARRANGE
        final String USER_NAME = "Roger";
        final String USER_AGE = "27";
        final String USER_CITY = "Alvorada";

        User validUser = new User(USER_NAME, USER_AGE, USER_CITY);

        // ACT
        mUserBll.addUser(validUser);

        // ASSERT
        verify(mUserDalMock, times(1)).createUser(validUser);
    }

    @Test
    public void getUser_whenUserNameIsNull_returnsNull() {

        // ARRANGE
        final String NULL_USER_NAME = null;

        // ACT
        User gottenNullUser = mUserBll.getUser(NULL_USER_NAME);

        // ASSERT
        assertNull(gottenNullUser);
    }

    @Test
    public void getUser_whenUserNameIsEmpty_returnsNull() {

        // ARRANGE
        final String EMPTY_USER_NAME = "";

        // ACT
        User gottenNullUser = mUserBll.getUser(EMPTY_USER_NAME);

        // ASSERT
        assertNull(gottenNullUser);
    }

    @Test
    public void getUser_whenUserNameIsValid_returnsUser() {

        // ARRANGE
        final String FAILED_TEST_MESSAGE = "Usuário recuperado deve ser igual ao usuário esperado.";

        final String VALID_USER_NAME = "Roger";
        final String USER_AGE = "27";
        final String USER_CITY = "Alvorada";

        User expectedUser = new User(VALID_USER_NAME, USER_AGE, USER_CITY);

        when(mUserDalMock.retrieveUser(VALID_USER_NAME)).thenReturn(expectedUser);

        // ACT
        User gottenValidUser = mUserBll.getUser(VALID_USER_NAME);

        // ASSERT
        assertEquals(FAILED_TEST_MESSAGE, expectedUser, gottenValidUser);
    }

    @Test(expected = NullPointerException.class)
    public void updateUser_whenUserIsNull_throwsNullPointerException() {

        // ARRANGE
        User nullUser = null;

        // ACT
        mUserBll.updateUser(nullUser);
    }

    @Test
    public void updateUser_whenUserIsNotNull_updateIsSuccessful() {

        // ARRANGE
        final String USER_NAME = "Roger";
        final String USER_AGE = "27";
        final String USER_CITY = "Alvorada";

        User validUser = new User(USER_NAME, USER_AGE, USER_CITY);

        // ACT
        mUserBll.updateUser(validUser);

        // ASSERT
        verify(mUserDalMock, times(1)).updateUser(validUser);
    }

    @Test(expected = InvalidStringException.class)
    public void removeUser_whenUserNameIsNull_throwsInvalidStringException() throws InvalidStringException {

        // ARRANGE
        final String NULL_USER_NAME = null;

        // ACT
        mUserBll.removeUser(NULL_USER_NAME);
    }

    @Test(expected = InvalidStringException.class)
    public void removeUser_whenUserNameIsEmpty_throwsInvalidStringException() throws InvalidStringException {

        // ARRANGE
        final String EMPTY_USER_NAME = "";

        // ACT
        mUserBll.removeUser(EMPTY_USER_NAME);
    }

    @Test
    public void removeUser_whenUserNameIsValid_verifyCallToDal() throws InvalidStringException {

        // ARRANGE
        final String VALID_USER_NAME = "Roger";

        // ACT
        mUserBll.removeUser(VALID_USER_NAME);

        // ASSERT
        verify(mUserDalMock, times(1)).deleteUser(VALID_USER_NAME);
    }

    // endregion

    // region TEARDOWN METHODS

    @After
    public void teardown() {

        mUserBll = null;
    }

    // endregion
}
