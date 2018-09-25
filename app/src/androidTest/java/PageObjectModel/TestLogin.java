package PageObjectModel;

import android.support.test.espresso.matcher.ViewMatchers;

import com.mytaxi.android_demo.R;

import Helpers.HelperClass;

import static Helpers.HelperClass.AssertForToolbarTitle;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class TestLogin {
    //Actions
    public void Login (String userName,String password) {
        onView(withId(R.id.edt_username)).perform(typeText(userName));
        onView(withId(R.id.edt_password)).perform(typeText(password),closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());
    }

    //Assertions
    public void AssertToolBarTitle(){
        AssertForToolbarTitle(R.string.title_activity_authentication);
    }
    public void AssertLoginElementsDisplayed(){
        onView(ViewMatchers.withId(R.id.edt_username)).check(matches(isDisplayed()));
        onView(withId(R.id.edt_password)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_login)).check(matches(isDisplayed()));
    }
    public void AssertInvalidLogin() {
        onView(withText(R.string.message_login_fail)).check(matches(isDisplayed()));
    }
}
