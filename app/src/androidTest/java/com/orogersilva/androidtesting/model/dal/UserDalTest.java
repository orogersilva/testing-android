package com.orogersilva.androidtesting.model.dal;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.MediumTest;

import com.orogersilva.androidtesting.vo.User;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.realm.Realm;

import static org.junit.Assert.*;

/**
 * Created by azevedor on 10/01/2016.
 */
@MediumTest
@RunWith(AndroidJUnit4.class)
public class UserDalTest {

    // region FIELDS

    private static Context mContext;
    private static UserDal mUserDal;

    // endregion

    // region SETUP METHODS

    @BeforeClass
    public static void setupClass() {

        mContext = InstrumentationRegistry.getTargetContext().getApplicationContext();
        mUserDal = new UserDal(mContext);
    }

    @Before
    public void setup() {
    }

    // endregion

    // region TEST METHODS

    @Test
    public void createUser_whenUserIsNull_returnsFailedOperationStatus() {

        // ARRANGE
        final String FAILED_TEST_MESSAGE = "Não deve ser possível a criação de um usuário nulo.";

        User nullUser = null;

        // ACT
        int operationResultStatus = mUserDal.createUser(nullUser);

        // ASSERT
        assertEquals(FAILED_TEST_MESSAGE, UserDal.FAIL_OPERATION, operationResultStatus);
    }

    // endregion

    @Test
    public void createUser_whenUserIsNotNull_returnsSuccessfulOperationStatus() {

        // ARRANGE
        final String FAILED_TEST_MESSAGE = "Criação do usuário deveria ter sido realizada.";

        final String USER_NAME = "Roger";
        final String USER_AGE = "27";
        final String USER_CITY = "Alvorada";

        User newUser = new User(USER_NAME, USER_AGE, USER_CITY);

        // ACT
        int operationResultStatus = mUserDal.createUser(newUser);

        // ASSERT
        assertEquals(FAILED_TEST_MESSAGE, UserDal.SUCCESS_OPERATION, operationResultStatus);
    }

    @Test
    public void createUser_whenCreatedSameUserTwice_returnsFailedOperationStatus() {

        // ARRANGE
        final String FIRST_FAILED_TEST_MESSAGE = "Criação de usuário deveria ter sido realizada.";
        final String SECOND_FAILED_TEST_MESSAGE = "Usuário não poderia ter sido recriado.";

        final String USER_NAME = "Roger";
        final String USER_AGE = "27";
        final String USER_CITY = "Alvorada";

        User newUser = new User(USER_NAME, USER_AGE, USER_CITY);

        // ACT
        int firstOperationResultStatus = mUserDal.createUser(newUser);
        int secondOperationResultStatus = mUserDal.createUser(newUser);

        assertEquals(FIRST_FAILED_TEST_MESSAGE, UserDal.SUCCESS_OPERATION, firstOperationResultStatus);
        assertEquals(SECOND_FAILED_TEST_MESSAGE, UserDal.FAIL_OPERATION, secondOperationResultStatus);
    }

    @Test
    public void retrieveUser_whenNameIsNull_returnsNull() {

        // ARRANGE
        final String NULL_NAME = null;

        // ACT
        User nullUser = mUserDal.retrieveUser(NULL_NAME);

        // ASSERT
        assertNull(nullUser);
    }

    @Test
    public void retrieveUser_whenNameIsEmpty_returnsNull() {

        // ARRANGE
        final String EMPTY_NAME = null;

        // ACT
        User nullUser = mUserDal.retrieveUser(EMPTY_NAME);

        // ASSERT
        assertNull(nullUser);
    }

    @Test
    public void retrieveUser_whenNameNotExists_returnsNull() {

        // ARRANGE

        final String NAME = "Roger";

        // ACT
        User retrievedUser = mUserDal.retrieveUser(NAME);

        // ASSERT
        assertNull(retrievedUser);
    }

    @Test
    public void retrieveUser_whenNameExists_returnsUser() {

        // ARRANGE

        final String USER_NAME = "Roger";
        final String USER_AGE = "27";
        final String USER_CITY = "Alvorada";

        User user = new User(USER_NAME, USER_AGE, USER_CITY);

        Realm realm = Realm.getInstance(mContext);

        realm.beginTransaction();
        realm.copyToRealm(user);
        realm.commitTransaction();

        // ACT
        User retrievedUser = mUserDal.retrieveUser(USER_NAME);

        // ASSERT
        assertEquals(user.getName(), retrievedUser.getName());
        assertEquals(user.getAge(), retrievedUser.getAge());
        assertEquals(user.getCity(), retrievedUser.getCity());
    }

    // region CLEAN UP METHODS

    @After
    public void teardown() {

        mUserDal.clearDatabase();
    }

    @AfterClass
    public static void teardownClass() {

        mUserDal = null;
    }

    // endregion
}
