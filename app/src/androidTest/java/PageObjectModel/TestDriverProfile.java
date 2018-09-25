package PageObjectModel;

import android.content.Intent;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.intent.matcher.IntentMatchers;

import com.mytaxi.android_demo.R;

import java.util.concurrent.TimeUnit;

import static Helpers.HelperClass.AssertForToolbarTitle;
import static Helpers.HelperClass.waitId;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

public class TestDriverProfile {
    //Actions
    public void WaitForDriverProfilePage(){
        onView(isRoot()).perform(waitId(R.id.textViewDriverName, TimeUnit.SECONDS.toSeconds(5)));
    }
    public void ClickOnCallBtn() throws InterruptedException {
        onView(withId(R.id.fab)).perform(click());
    }

    //Assertions
    public void AssertToolBarTitle(){
        AssertForToolbarTitle(R.string.title_activity_driver_profile);
    }
    public void AssertDriverName(String DriverName){
        onView(withText(DriverName)).check(matches(isDisplayed()));
    }
    public void AssertDriverInfoDisplayed() {
        onView(withId(R.id.imageViewDriverAvatar)).check(matches(isDisplayed()));
        onView(withId(R.id.textViewDriverDate)).check(matches(isDisplayed()));
        onView(withId(R.id.textViewDriverLocation)).check(matches(isDisplayed()));
    }

}
