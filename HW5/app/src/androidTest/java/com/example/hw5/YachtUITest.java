package com.example.hw5;

import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class YachtUITest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testYachtDeterminePrice() {
        // 3 Years Old, 3 Cabins and 2 Heads
        onView(withId(R.id.text_age))
                .perform(typeText("3"));

        onView(withId(R.id.num_cabins))
                .perform(click());
        onData(allOf(is(instanceOf(String.class)), is("3")))
                .perform(click());

        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.yacht_price)).check(matches(withText("$106250.00")));


        onView(withId(R.id.text_age)).perform(clearText());
        // -1 Years Old, 4 Cabins and 2 Heads
        onView(withId(R.id.text_age))
                .perform(typeText("-1"));

        onView(withId(R.id.num_cabins))
                .perform(click());
        onData(allOf(is(instanceOf(String.class)), is("4")))
                .perform(click());

        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.yacht_price)).check(matches(withText("$180000.00")));


        onView(withId(R.id.text_age)).perform(clearText());
        // 20 Years Old, 0 Cabins and 2 Heads
        onView(withId(R.id.text_age))
                .perform(typeText("20"));

        onView(withId(R.id.num_cabins))
                .perform(click());
        onData(allOf(is(instanceOf(String.class)), is("0")))
                .perform(click());

        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.yacht_price)).check(matches(withText("$1250.00")));
    }
}
