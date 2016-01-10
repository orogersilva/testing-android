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
        int resultStatus = mUserDal.createUser(nullUser);

        // ASSERT
        assertEquals(FAILED_TEST_MESSAGE, UserDal.FAIL_OPERATION, resultStatus);
    }

    // endregion

    // region CLEAN UP METHOD

    @After
    public void teardown() {

        mUserDal.clearDatabase();

        mUserDal = null;
    }

    // endregion
}
