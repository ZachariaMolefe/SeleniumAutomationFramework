package org.webstar.Base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.webstar.Factory.BrowserConfig.BrowserSetup;
import org.webstar.Pages.Cart;
import org.webstar.Pages.LandingPage;
import org.webstar.Utilities.ReadProperties;

import java.util.Properties;

public class BaseTest {

    BrowserSetup setup;
    protected WebDriver driver;
    protected LandingPage landingPage;
    protected Cart cart;
    protected Properties prop;

    @Parameters({"url","browser"})
    @BeforeTest
    protected void setupTest(String url,@Optional String browser) {
        try {
            prop = new ReadProperties().init_Prop();

            if(browser !=null)
                prop.setProperty("browser",browser);

            setup = new BrowserSetup();
            setup.setupBrowser(prop.getProperty("browser"));
            driver = setup.getDriver();
            driver.get("https://www.takealot.com/");
            driver.manage().window().maximize();

            //Page objects
            landingPage = new LandingPage(driver);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @AfterTest
    protected void tearDown() {
        try{
            driver.quit();
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    public WebDriver getDriver(){
        return driver;
    }
}
