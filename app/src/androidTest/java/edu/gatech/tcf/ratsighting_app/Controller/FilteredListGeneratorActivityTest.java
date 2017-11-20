package edu.gatech.tcf.ratsighting_app.Controller;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;

import edu.gatech.tcf.ratsighting_app.R;

import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Created by raghavbhat on 11/19/17.
 */
public class FilteredListGeneratorActivityTest {

    @Rule
    public ActivityTestRule<FilteredListGeneratorActivity> mActivityTestRule = new ActivityTestRule<>(FilteredListGeneratorActivity.class);

    @Before
    public void setUp() throws Exception {
    }

    private String ten = "10";
    private int tenInt = 10;
    private String twoThousand = "2000";
    private int twoThousandInt = 2000;
    private String twoThousandTen = "2010";
    private int twoThousandTenInt = 2010;
    @Test
    public void testStandardDates() {
        Espresso.onView(withId(R.id.startDay)).perform(typeText(ten));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.startMonth)).perform(typeText(ten));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.startYear)).perform(typeText(twoThousand));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.endDay)).perform(typeText(ten));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.endMonth)).perform(typeText(ten));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.endYear)).perform(typeText(twoThousandTen));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.graphLauncher)).perform(click());
        Calendar startDate = new GregorianCalendar(twoThousandInt, tenInt, tenInt, 0, 0, 0);
        Calendar endDate = new GregorianCalendar(twoThousandTenInt, tenInt, tenInt, 0, 0, 0);
        assertEquals(startDate, FilteredListGeneratorActivity.startDateParent);
        assertEquals(endDate, FilteredListGeneratorActivity.endDateParent);
    }

    private String hi = "hii";
    @Test
    public void testNotInts() {
        Espresso.onView(withId(R.id.startDay)).perform(typeText(hi));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.startMonth)).perform(typeText(hi));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.startYear)).perform(typeText(hi));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.endDay)).perform(typeText(hi));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.endMonth)).perform(typeText(hi));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.endYear)).perform(typeText(hi));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.graphLauncher)).perform(click());
        Calendar startDate = new GregorianCalendar(0, 0, 0, 0, 0, 0);
        Calendar endDate = new GregorianCalendar(0, 0, 0, 0, 0, 0);
        assertEquals(startDate, FilteredListGeneratorActivity.startDateParent);
        assertEquals(endDate, FilteredListGeneratorActivity.endDateParent);
    }

    private String negTen = "-10";
    private String negTwoThousand = "2000";
    @Test
    public void testNegativeInts() {
        Espresso.onView(withId(R.id.startDay)).perform(typeText(negTen));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.startMonth)).perform(typeText(negTen));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.startYear)).perform(typeText(negTwoThousand));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.endDay)).perform(typeText(ten));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.endMonth)).perform(typeText(ten));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.endYear)).perform(typeText(twoThousandTen));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.graphLauncher)).perform(click());
        Calendar startDate = new GregorianCalendar(0, 0, 0, 0, 0, 0);
        Calendar endDate = new GregorianCalendar(twoThousandTenInt, tenInt, tenInt, 0, 0, 0);
        assertEquals(startDate, FilteredListGeneratorActivity.startDateParent);
        assertEquals(endDate, FilteredListGeneratorActivity.endDateParent);
    }

    private int max = Integer.MAX_VALUE;
    private String maxInt = ((Integer) max).toString() + "0";
    @Test
    public void testIntegerMax() {
        Espresso.onView(withId(R.id.startDay)).perform(typeText(maxInt));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.startMonth)).perform(typeText(maxInt));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.startYear)).perform(typeText(maxInt));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.endDay)).perform(typeText(maxInt));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.endMonth)).perform(typeText(maxInt));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.endYear)).perform(typeText(maxInt));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.graphLauncher)).perform(click());
        Calendar startDate = new GregorianCalendar(max, max, max, 0, 0, 0);
        Calendar endDate = new GregorianCalendar(max, max, max, 0, 0, 0);
        assertEquals(startDate, FilteredListGeneratorActivity.startDateParent);
        assertEquals(endDate, FilteredListGeneratorActivity.endDateParent);
    }


    @After
    public void tearDown() throws Exception {
    }

}