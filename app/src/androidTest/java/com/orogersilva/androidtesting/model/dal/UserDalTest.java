package com.orogersilva.androidtesting.model.dal;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.MediumTest;

import com.orogersilva.androidtesting.vo.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by azevedor on 10/01/2016.
 */
@MediumTest
@RunWith(AndroidJUnit4.class)
public class UserDalTest {

    // region FIELDS

    UserDal mUserDal;

    // endregion

    // region SETUP METHOD

    @Before
    public void setup() {

        mUserDal = new UserDal(InstrumentationRegistry.getTargetContext().getApplicationContext());
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

    // region CLEAN UP METHOD

    @After
    public void teardown() {

        mUserDal.clearDatabase();

        mUserDal = null;
    }

    // endregion
}
