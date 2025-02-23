package pages;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileRead {

    private Properties properties;

    // Path to the configuration properties file
    private final String propertyFilePath = "D:\\Automation Testing\\PageObjectModel\\src\\test\\resources\\config.properties";

    // Constructor to read the configuration properties file
    public ConfigFileRead() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        }
    }

    // Get the URL from the properties file
    public String getURL()
    {
        String url = properties.getProperty("URL");
        if (url != null) return url;
        else throw new RuntimeException("url path not specified in the Configuration.properties file.");
    }

    // Get the implicitly wait time from the properties file
    public long getImplicitlyWait()
    {
        String implicitlyWait = properties.getProperty("IMPLICITWAIT");
        if (implicitlyWait != null) return Long.parseLong(implicitlyWait);
        else throw new RuntimeException("implicitlyWait not specified in the Configuration.properties file.");
    }

    // Get the expected result for login from the properties file
    public String getExpSAResultForLogin()
        {
            String getExpSAResultForLogin = properties.getProperty("EXPECTEDSALOGIN");
            if ((getExpSAResultForLogin != null)) return getExpSAResultForLogin;
            else throw new RuntimeException("exp result for login not specified in the Configuration.properties file.");
        }

    public String getExpectedAddSA()
    {
        String getExpectedAddSA = properties.getProperty("EXPECTEDADDSA");
        if ((getExpectedAddSA != null)) return getExpectedAddSA;
        else throw new RuntimeException("exp result for add user not specified in the Configuration.properties file.");
    }

    public String getExpectedLogoutSA()
    {
        String getExpectedLogoutSA = properties.getProperty("EXCPECTEDLOGOUTSA");
        if ((getExpectedLogoutSA != null)) return getExpectedLogoutSA;
        else throw new RuntimeException("exp result for super admin logout not specified in the Configuration.properties file.");
    }

    public String getExpCALogin()
    {
        String getExpSAResultForLogin = properties.getProperty("EXPCALOGIN");
        if ((getExpSAResultForLogin != null)) return getExpSAResultForLogin;
        else throw new RuntimeException("exp result for login not specified in the Configuration.properties file.");
    }

    public String getExpAddCA()
    {
        String getExpectedAddSA = properties.getProperty("EXPADDCA");
        if ((getExpectedAddSA != null)) return getExpectedAddSA;
        else throw new RuntimeException("exp result for add user not specified in the Configuration.properties file.");
    }

    public String getExpDeleteCA()
    {
        String getExpectedLogoutSA = properties.getProperty("EXPECTEDDELETECA");
        if ((getExpectedLogoutSA != null)) return getExpectedLogoutSA;
        else throw new RuntimeException("exp result for super admin logout not specified in the Configuration.properties file.");
    }

    public String getExpectedLogoutCA()
    {
        String getExpectedLogoutSA = properties.getProperty("EXPLOGOUTCA");
        if ((getExpectedLogoutSA != null)) return getExpectedLogoutSA;
        else throw new RuntimeException("exp result for super admin logout not specified in the Configuration.properties file.");
    }

    public String getExpIM10SALogin()
    {
        String getExpSAResultForLogin = properties.getProperty("EXPIM10SALOGIN");
        if ((getExpSAResultForLogin != null)) return getExpSAResultForLogin;
        else throw new RuntimeException("exp result for login not specified in the Configuration.properties file.");
    }

    public String getExpAddIM10SA()
    {
        String getExpectedAddSA = properties.getProperty("EXPADDIM10SA");
        if ((getExpectedAddSA != null)) return getExpectedAddSA;
        else throw new RuntimeException("exp result for add user not specified in the Configuration.properties file.");
    }

    public String getExpDeleteIM10SA()
    {
        String getExpectedLogoutSA = properties.getProperty("EXPDELETEIM10SA");
        if ((getExpectedLogoutSA != null)) return getExpectedLogoutSA;
        else throw new RuntimeException("exp result for super admin logout not specified in the Configuration.properties file.");
    }

    public String getExpectedLogoutIM10SA()
    {
        String getExpectedLogoutSA = properties.getProperty("EXPLOGOUTIM10SA");
        if ((getExpectedLogoutSA != null)) return getExpectedLogoutSA;
        else throw new RuntimeException("exp result for super admin logout not specified in the Configuration.properties file.");
    }

    public String getExpEALogin()
    {
        String getExpSAResultForLogin = properties.getProperty("EXPEALOGIN");
        if ((getExpSAResultForLogin != null)) return getExpSAResultForLogin;
        else throw new RuntimeException("exp result for login not specified in the Configuration.properties file.");
    }

    public String getExpAddEA()
    {
        String getExpectedAddSA = properties.getProperty("EXPADDEA");
        if ((getExpectedAddSA != null)) return getExpectedAddSA;
        else throw new RuntimeException("exp result for add user not specified in the Configuration.properties file.");
    }

    public String getExpDeleteEA()
    {
        String getExpectedLogoutSA = properties.getProperty("EXPDELETEEA");
        if ((getExpectedLogoutSA != null)) return getExpectedLogoutSA;
        else throw new RuntimeException("exp result for super admin logout not specified in the Configuration.properties file.");
    }

    public String getExpectedLogoutEA()
    {
        String getExpectedLogoutSA = properties.getProperty("EXPLOGOUTEA");
        if ((getExpectedLogoutSA != null)) return getExpectedLogoutSA;
        else throw new RuntimeException("exp result for super admin logout not specified in the Configuration.properties file.");
    }

    public String getFirstNameCA()
    {
        String getExpectedLogoutSA = properties.getProperty("CAFIRSTNAME");
        if ((getExpectedLogoutSA != null)) return getExpectedLogoutSA;
        else throw new RuntimeException("exp result for super admin logout not specified in the Configuration.properties file.");
    }

    public String getFirstNameIM10SA()
    {
        String getExpectedLogoutSA = properties.getProperty("IMSAFIRSTNAME");
        if ((getExpectedLogoutSA != null)) return getExpectedLogoutSA;
        else throw new RuntimeException("exp result for super admin logout not specified in the Configuration.properties file.");
    }

    public String getFirstNameEMA()
    {
        String getExpectedLogoutSA = properties.getProperty("EMAFIRSTNAME");
        if ((getExpectedLogoutSA != null)) return getExpectedLogoutSA;
        else throw new RuntimeException("exp result for super admin logout not specified in the Configuration.properties file.");
    }

    public String getLastNameCA()
    {
        String getExpectedLogoutSA = properties.getProperty("CALASTNAME");
        if ((getExpectedLogoutSA != null)) return getExpectedLogoutSA;
        else throw new RuntimeException("exp result for super admin logout not specified in the Configuration.properties file.");
    }

    public String getLastNameIM10SA()
    {
        String getExpectedLogoutSA = properties.getProperty("IMSALASTNAME");
        if ((getExpectedLogoutSA != null)) return getExpectedLogoutSA;
        else throw new RuntimeException("exp result for super admin logout not specified in the Configuration.properties file.");
    }

    public String getLastNameEMA()
    {
        String getExpectedLogoutSA = properties.getProperty("EMALASTNAME");
        if ((getExpectedLogoutSA != null)) return getExpectedLogoutSA;
        else throw new RuntimeException("exp result for super admin logout not specified in the Configuration.properties file.");
    }

    public String getAdminRoleCA()
    {
        String getExpectedLogoutSA = properties.getProperty("CAADMINROLE");
        if ((getExpectedLogoutSA != null)) return getExpectedLogoutSA;
        else throw new RuntimeException("exp result for super admin logout not specified in the Configuration.properties file.");
    }

    public String getAdminRoleIM10SA()
    {
        String getExpectedLogoutSA = properties.getProperty("IM10SAADMINROLE");
        if ((getExpectedLogoutSA != null)) return getExpectedLogoutSA;
        else throw new RuntimeException("exp result for super admin logout not specified in the Configuration.properties file.");
    }

    public String getAdminRoleEMA()
    {
        String getExpectedLogoutSA = properties.getProperty("EMAADMINROLE");
        if ((getExpectedLogoutSA != null)) return getExpectedLogoutSA;
        else throw new RuntimeException("exp result for super admin logout not specified in the Configuration.properties file.");
    }

    public String getUserPlayerMap()
    {
        String getExpectedLogoutSA = properties.getProperty("USERPLAYERMAP");
        if ((getExpectedLogoutSA != null)) return getExpectedLogoutSA;
        else throw new RuntimeException("exp result for super admin logout not specified in the Configuration.properties file.");
    }

    public String getExpSPALogin()
    {
        String getExpSAResultForLogin = properties.getProperty("EXPSPALOGIN");
        if ((getExpSAResultForLogin != null)) return getExpSAResultForLogin;
        else throw new RuntimeException("exp result for login not specified in the Configuration.properties file.");
    }

    public String getExpSPAResultForLogin()
    {
        String getExpSAResultForLogin = properties.getProperty("EXPECTEDSPALOGIN");
        if ((getExpSAResultForLogin != null)) return getExpSAResultForLogin;
        else throw new RuntimeException("exp result for login not specified in the Configuration.properties file.");
    }

    public String getExpEMAResultForLogin()
    {
        String getExpSAResultForLogin = properties.getProperty("EXPECTEDEMALOGIN");
        if ((getExpSAResultForLogin != null)) return getExpSAResultForLogin;
        else throw new RuntimeException("exp result for login not specified in the Configuration.properties file.");
    }

}
