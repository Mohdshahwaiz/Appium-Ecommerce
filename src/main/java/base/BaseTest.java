package base;

import static manager.DriverSession.createSession;
import static manager.DriverSession.getSession;

import enums.PlatformType;
import manager.DriverManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class BaseTest {

    @BeforeSuite
    public void setUpConfiguration () {
    }

    @BeforeTest
    @Parameters ({ "platformType" })
    public void setupDriver (final PlatformType platformType) {
        createSession (platformType);
    }
//    @BeforeMethod
//    public void setDriver(final PlatformType platformType){
//
//    }

    @AfterTest
    public void tearDownDriver () {
        getSession ().getDriver ().quit ();
    }
//    @AfterMethod
//    public void tearDownDriverTest(){
//        getSession ().getDriver ().close ();
//
//    }

    @AfterSuite
    public void tearDownConfig () {
    }
}
