package Helpers;
import android.support.test.InstrumentationRegistry;
import com.mytaxi.android_demo.R;


public class DataReader {

    public String getData(int id)
    {
        return InstrumentationRegistry.getTargetContext()
                .getString(id);
    }
    public String getUsername()
    {
        return getData(R.string.userName);
    }
    public String getPassword()
    {
        return getData(R.string.password);
    }

    public String getInvalidUsernamePassword()
    {
        return getData(R.string.invalidUserNamePassword);
    }
    public String getSearchText()
    {
        return getData(R.string.searchText);
    }
    public String getIntendedDriverName()
    {
        return getData(R.string.intendedDriverNameSelection);
    }

}
