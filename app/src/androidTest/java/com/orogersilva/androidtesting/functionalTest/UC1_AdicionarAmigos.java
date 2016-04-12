package com.orogersilva.androidtesting.functionalTest;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import android.test.suitebuilder.annotation.LargeTest;

import com.orogersilva.androidtesting.R;
import com.orogersilva.androidtesting.view.activities.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import static com.orogersilva.androidtesting.functionalTest.customviewassertions.RecyclerViewAssertions.*;

/**
 * Created by orogersilva on 4/9/2016.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class UC1_AdicionarAmigos {

    // region FIELDS

    @Rule
    public ActivityTestRule<MainActivity> mMainActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    // endregion

    // region SETUP

    @Before
    public void setup() {
    }

    // endregion

    // region TEST METHODS

    /**
     * CENÁRIO: Inserção de amigo em lista vazia
     * DADO que não existem amigos na lista ainda
     * QUANDO eu adiciono um novo amigo
     * ENTÃO esse amigo é registrado na lista.
     */
    @Test
    public void dadoQueNaoExistemAmigosNaListaAinda_quandoEuAdicionoUmNovoAmigo_entaoEsseAmigoEhRegistradoNaLista() {

        // ARRANGE
        final String FRIEND_NAME = "Roger";
        final String FRIEND_AGE = "28";
        final String FRIEND_CITY = "Alvorada";

        final int EXPECTED_FRIENDS_COUNT = 1;

        // ACT
        onView(withId(R.id.add_user_button)).perform(click());

        onView(withId(R.id.user_name_editText)).perform(typeText(FRIEND_NAME));
        onView(withId(R.id.user_age_editText)).perform(typeText(FRIEND_AGE));
        onView(withId(R.id.user_city_editText)).perform(typeText(FRIEND_CITY), closeSoftKeyboard());

        onView(withId(R.id.save_user_button)).perform(click());

        // ASSERT
        onView(withId(R.id.friends_recyclerView)).check(hasItemsCount(EXPECTED_FRIENDS_COUNT));
    }

    // endregion

    // region TEARDOWN

    @After
    public void teardown() {
    }

    // endregion
}
