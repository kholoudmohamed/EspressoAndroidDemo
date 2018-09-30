package Tests;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.mytaxi.android_demo.R;
import com.mytaxi.android_demo.activities.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static Helpers.HelperClass.ResetPreferences;
import static Helpers.HelperClass.tapAllowGpsBtn;

import Helpers.DataReader;
import PageObjectModel.TestDriverProfile;
import PageObjectModel.TestHome;
import PageObjectModel.TestLogin;


@RunWith(AndroidJUnit4.class)
public class DemoTests {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class,false,false);

    private MainActivity mActivity=null;
    private static DataReader data = new DataReader();


    @Before
    public void setUp() throws Exception
    {
        // Reset Application Preferences and Launch Activity
        ResetPreferences();
        mActivityRule.launchActivity(null);
        mActivity = mActivityRule.getActivity();

        // Allow GPS sharing
        tapAllowGpsBtn();
    }

    @Test
    // This test asserts that all login page elements are displayed.
    public void LoginPageUiDisplay() throws Exception {
        TestLogin testLogin = new TestLogin();

        // Assert Login Elements
        testLogin.AssertLoginElementsDisplayed();

        /// Assert for the toolbar title
        testLogin.AssertToolBarTitle();
    }

    @Test
    // This test verifies the valid login
    public void ValidLogin() throws Exception {
        TestLogin testLogin = new TestLogin();
        TestHome testHome = new TestHome();

        // Login
        testLogin.Login(data.getUsername(),data.getPassword());

        //Wait to make sure Home Page is displayed
        testHome.WaitForHomePage();

        // Assert for the toolbar title
        testHome.AssertToolBarTitle();

        // Logout
        testHome.OpenMenu();
        testHome.AssertLoggedInUserName(data.getUsername());
        testHome.Logout();

        // Assert for the toolbar title
        testLogin.AssertToolBarTitle();
    }

    @Test
    // This test verifies the invalid login
    public void InvalidLogin() throws Exception {
        TestLogin testLogin = new TestLogin();

        // Login
        testLogin.Login(data.getInvalidUsernamePassword(),data.getInvalidUsernamePassword());

        //Assert Invalid login message
        testLogin.AssertInvalidLogin();
    }

    @Test
    // This test verifies the Search
    public void Search() throws Exception {
        TestLogin testLogin = new TestLogin();
        TestHome testHome = new TestHome();
        TestDriverProfile testDriverProfile = new TestDriverProfile();

        // Login
        testLogin.Login(data.getUsername(),data.getPassword());

        //Wait to make sure Home Page is displayed
        testHome.WaitForHomePage();

        //Search drivers
        testHome.SearchDrivers(data.getSearchText());

        //Click on specific driver
        testHome.ClickDriverByName(mActivity,data.getIntendedDriverName());

        //Wait for Driver Profile screen to be loaded
        testDriverProfile.WaitForDriverProfilePage();

        //Assert Driver Profile Page
        testDriverProfile.AssertToolBarTitle();
        testDriverProfile.AssertDriverName(data.getIntendedDriverName());
        testDriverProfile.AssertDriverInfoDisplayed();

        //Click Call button
        testDriverProfile.ClickOnCallBtn();

    }
}
