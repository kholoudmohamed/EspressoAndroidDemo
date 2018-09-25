package Helpers;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class DataReader {

    private Properties pro;
    public DataReader()
    {
        try {
            File source = new File ("\\src\\androidTest\\java\\Helpers\\TestData.properties");
            FileInputStream input=new FileInputStream(source);
            pro=new Properties();
            pro.load(input);
        }catch(Exception exp) {
            System.out.println("Exception is: ---" + exp.getMessage());
        }
    }

    public String getUsername()throws Exception{
        return pro.getProperty("userName");
    }
    public String getPassword()throws Exception{
        return pro.getProperty("password");
    }
    public String getInvalidUsernamePassword()throws Exception{
        return pro.getProperty("invalidUserNamePassword");
    }
    public String getSearchText()throws Exception{
        return pro.getProperty("searchText");
    }
    public String getIntendedDriverName()throws Exception{
        return pro.getProperty("intendedDriverNameSelection");
    }


}
