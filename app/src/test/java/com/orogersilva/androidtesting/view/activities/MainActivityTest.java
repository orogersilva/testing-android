package com.orogersilva.androidtesting.view.activities;

import android.content.Intent;
import android.test.suitebuilder.annotation.SmallTest;

import com.orogersilva.androidtesting.BuildConfig;
import com.orogersilva.androidtesting.R;

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

    // region TEST METHODS

    @Test
    public void clickingAddButton_shouldStartFormActivity() {

        // ARRANGE
        MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);

        Intent expectedIntent = new Intent(mainActivity, FormActivity.class);

        // ACT
        mainActivity.findViewById(R.id.add_button).performClick();

        ShadowActivity shadowMainActivity = Shadows.shadowOf(mainActivity);
        Intent actualIntent = shadowMainActivity.getNextStartedActivity();

        // ASSERT
        assertTrue(actualIntent.filterEquals(expectedIntent));
    }

    // endregion
}
