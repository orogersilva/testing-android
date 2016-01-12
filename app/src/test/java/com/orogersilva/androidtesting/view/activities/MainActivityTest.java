package com.orogersilva.androidtesting.view.activities;

import android.content.Intent;
import android.test.suitebuilder.annotation.SmallTest;

import com.orogersilva.androidtesting.BuildConfig;
import com.orogersilva.androidtesting.R;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import static org.hamcrest.MatcherAssert.*;
import static org.junit.Assert.assertThat;
import static org.robolectric.Shadows.shadowOf;
import static org.junit.Assert.*;

/**
 * Created by azevedor on 11/01/2016.
 */
@SmallTest
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MainActivityTest {

    // region FIELDS

    private MainActivity mMainActivity;

    // endregion

    // region SETUP METHODS

    @Before
    public void setup() {

        mMainActivity = Robolectric.setupActivity(MainActivity.class);
    }

    // endregion

    // region TEST METHODS

    @Test
    public void clickingAddButton_shouldStartFormActivity() {

        // ARRANGE
        Intent expectedIntent = new Intent(mMainActivity, FormActivity.class);

        // ACT
        mMainActivity.findViewById(R.id.add_button).performClick();

        ShadowActivity shadowMainActivity = Shadows.shadowOf(mMainActivity);
        Intent actualIntent = shadowMainActivity.getNextStartedActivity();

        // ASSERT
        assertTrue(actualIntent.filterEquals(expectedIntent));
    }

    // endregion

    // region CLEAN UP METHODS

    @After
    public void tearDown() {

        mMainActivity = null;
    }

    // endregion
}
