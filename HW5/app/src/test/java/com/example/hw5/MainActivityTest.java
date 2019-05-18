package com.example.hw5;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowToast;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    @Test
    public void testInputNotEntered() {
        MainActivity activity = Robolectric.setupActivity(MainActivity.class);
        activity.findViewById(R.id.button).performClick();

        assertEquals("Input is required!", ShadowToast.getTextOfLatestToast());
    }
}