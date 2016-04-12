package com.orogersilva.androidtesting.functionalTest.customviewassertions;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import static org.junit.Assert.*;

/**
 * Created by orogersilva on 4/9/2016.
 */
public class RecyclerViewAssertions {

    public static ViewAssertion hasItemsCount(final int count) {

        return new ViewAssertion() {

            @Override public void check(View view, NoMatchingViewException e) {

                if (!(view instanceof RecyclerView)) {
                    throw e;
                }

                RecyclerView rv = (RecyclerView) view;

                assertEquals(count, rv.getAdapter().getItemCount());
            }
        };
    }
}
