package edu.gatech.tcf.ratsighting_app.Controller;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import edu.gatech.tcf.ratsighting_app.Model.RatSighting;
import edu.gatech.tcf.ratsighting_app.Model.SightingListContainer;
import edu.gatech.tcf.ratsighting_app.R;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Created by adithya on 11/20/17.
 */
//Adithya Mukund, for onSubmitPressMethod of AddNewRatSightingReport.java
public class AddNewRatSightingReportTest {
    @Rule
    public ActivityTestRule<AddNewRatSightingReport> rule = new ActivityTestRule<AddNewRatSightingReport>(AddNewRatSightingReport.class);


    private String address = "120 North Ave";
    private String city = "Atlanta";
    private String zipCode = "30318";
    private String coordinates = "34, 28";
    private String date = "11/12/17";
    private RatSighting sighting;

    @Before
    public void setUp() throws Exception {
        sighting = new RatSighting();
    }

    @Test
    public void testOnSubmitPressedNoEmptyFields() {
        int oldContainerSize = SightingListContainer.list.size();
        Espresso.onView(withId(R.id.newReport_address)).perform(typeText(address));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.newReport_city)).perform(typeText(city));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.newReport_ZipCode)).perform(typeText(zipCode));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.newReport_Coordinates)).perform(typeText(coordinates));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.newReport_Date)).perform(typeText(date));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.newReport_SubmitButton)).perform(click());



        assertEquals(SightingListContainer.list.size(), oldContainerSize + 1);
    }

    @Test
    public void testOnSubmitPressedAtLeastOneEmptyField() {
        int oldContainerSize = SightingListContainer.list.size();
        Espresso.onView(withId(R.id.newReport_address)).perform(typeText(address));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.newReport_SubmitButton)).perform(click());
        assertEquals(oldContainerSize, SightingListContainer.list.size());
    }

    @Test
    public void testOnSubmitPressedEmptyContainer() {
        SightingListContainer.list = new ArrayList<>(); //ensures empty container
        assertTrue(sighting.getKey() < 50000000 && sighting.getKey() >= 0);
    }

    @Test

    public void testOnSubmitPressedNotEmptyContainer() {
        Espresso.onView(withId(R.id.newReport_address)).perform(typeText(address));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.newReport_city)).perform(typeText(city));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.newReport_ZipCode)).perform(typeText(zipCode));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.newReport_Coordinates)).perform(typeText(coordinates));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.newReport_Date)).perform(typeText(date));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.newReport_SubmitButton)).perform(click());

        if (SightingListContainer.list.size() > 1) {
            int part1 = SightingListContainer.list.get(SightingListContainer.list.size() - 2).getKey() + 6;
            int part2 = SightingListContainer.list.get(SightingListContainer.list.size() - 1).getKey();
            assertEquals(part1, part2);
        } else {
            assertTrue(true);
        }
    }
}
