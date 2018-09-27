package Helpers;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.PerformException;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.espresso.util.HumanReadables;
import android.support.test.espresso.util.TreeIterables;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import java.io.File;
import java.util.concurrent.TimeoutException;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.Is.is;

public class HelperClass {
    // Following code is to allow the GPS sharing popup
    private static UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    public static void tapAllowGpsBtn() throws UiObjectNotFoundException {
        UiObject allowGpsBtn = device.findObject(new UiSelector()
                .text("ALLOW")
                .clickable(true));
        if (allowGpsBtn.exists() && allowGpsBtn.isEnabled()) {
            do {
                allowGpsBtn.click();
            } while (allowGpsBtn.exists());
        }
    }


    // Following code is for the toolbar title assertions
    public static ViewInteraction matchToolbarTitle(CharSequence title) {
        return onView(isAssignableFrom(Toolbar.class))
                .check(matches(withToolbarTitle(is(title))));
    }

    private static Matcher<Object> withToolbarTitle(final Matcher<CharSequence> textMatcher) {
        return new BoundedMatcher<Object, Toolbar>(Toolbar.class) {
            @Override public boolean matchesSafely(Toolbar toolbar) {
                return textMatcher.matches(toolbar.getTitle());
            }
            @Override public void describeTo(Description description) {
                description.appendText("with toolbar title: ");
                textMatcher.describeTo(description);
            }
        };
    }
    public static void AssertForToolbarTitle(int title){
        matchToolbarTitle(InstrumentationRegistry.getTargetContext()
                .getString(title));
    }

    // Following code is to wait for an element
    public static ViewAction waitId(final int viewId, final long millis) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "wait for a specific view with id <" + viewId + "> during " + millis + " millis.";
            }

            @Override
            public void perform(final UiController uiController, final View view) {
                uiController.loopMainThreadUntilIdle();
                final long startTime = System.currentTimeMillis();
                final long endTime = startTime + millis;
                final Matcher<View> viewMatcher = withId(viewId);

                do {
                    for (View child : TreeIterables.breadthFirstViewTraversal(view)) {
                        // found view with required ID
                        if (viewMatcher.matches(child)) {
                            return;
                        }
                    }

                    uiController.loopMainThreadForAtLeast(50);
                }
                while (System.currentTimeMillis() < endTime);

                // timeout happens
                throw new PerformException.Builder()
                        .withActionDescription(this.getDescription())
                        .withViewDescription(HumanReadables.describe(view))
                        .withCause(new TimeoutException())
                        .build();
            }
        };
    }

    //This method is to reset preferences and make sure user is logged out before performing a new test case
    public static void ResetPreferences() throws Exception{
        File root = InstrumentationRegistry.getTargetContext().getFilesDir().getParentFile();
        String[] sharedPreferencesFileNames = new File(root, "shared_prefs").list();
        try{
            for (String fileName : sharedPreferencesFileNames) {
                InstrumentationRegistry.getTargetContext().getSharedPreferences(fileName.replace(".xml", ""), Context.MODE_PRIVATE).edit().clear().commit();
            }
        }
        catch (NullPointerException exp){}

    }

}
