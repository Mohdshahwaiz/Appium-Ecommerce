package base;

import manager.DriverManager;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    @BeforeSuite
    public void setUpConfiguration () {
    }

    @BeforeTest
    public void setupDriver () {
        DriverManager.driverManager ()
            .createDriver ();
    }

    @AfterTest
    public void tearDownDriver () {
        DriverManager.driverManager ()
            .quit ();
    }

    @AfterSuite
    public void tearDownConfig () {
    }
}
