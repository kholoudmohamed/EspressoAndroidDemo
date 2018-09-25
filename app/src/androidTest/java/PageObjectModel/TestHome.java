package PageObjectModel;

import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.NavigationViewActions;

import com.mytaxi.android_demo.R;
import com.mytaxi.android_demo.activities.MainActivity;

import static Helpers.HelperClass.AssertForToolbarTitle;
import static Helpers.HelperClass.waitId;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;

import java.util.concurrent.TimeUnit;

public class TestHome {

    // Actions
    public void OpenMenu(){
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
    }
    public void Logout(){
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_logout));

    }
    public void WaitForHomePage(){
        onView(isRoot()).perform(waitId(R.id.textSearch, TimeUnit.SECONDS.toSeconds(10)));
        onView(isRoot()).perform(waitId(R.id.fab, TimeUnit.SECONDS.toSeconds(5)));

    }
    public void SearchDrivers(String searchText) {
        onView(withId(R.id.textSearch)).perform(typeText(searchText));
    }
    public void ClickDriverByName(MainActivity mActivity,String driverName) throws InterruptedException {
        onView(withText(driverName))
                .inRoot(withDecorView(not(is(mActivity.getWindow().getDecorView()))))
                .perform(click());
    }

    // Assertions
    public void AssertToolBarTitle(){
        AssertForToolbarTitle(R.string.app_name);
    }
    public void AssertLoggedInUserName(String userName){
        onView(withText(userName)).check(matches(isDisplayed()));
    }
}
