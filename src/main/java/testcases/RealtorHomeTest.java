package testcases;


import org.apache.commons.lang3.builder.ToStringExclude;
import org.apache.xpath.operations.Bool;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomeListingPage;
import pages.RealtorHomePage;
import pages.ResultPage;

public class RealtorHomeTest {

    WebDriver driver;
    WebDriverWait wait2;

    RealtorHomePage homePage;

    HomeListingPage listingPage;

    ResultPage resultPage;



    @BeforeTest

    // Passing Browser parameter from TestNG xml
    @Parameters("browser")
    public void beforeTest(String browser) {

        // If the browser is Firefox, then do this

        if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", ".\\src\\test\\java\\resources\\geckodriver-v0.18.0-win64\\geckodriver.exe");
            DesiredCapabilities capabilities = DesiredCapabilities.firefox();
            capabilities.setCapability("marionette", true);
            driver = new FirefoxDriver(capabilities);

            // If browser is IE, then do this

        } else if (browser.equalsIgnoreCase("chrome")) {

            System.setProperty("webdriver.chrome.driver",".\\src\\test\\java\\resources\\chromedriver_win32\\chromedriver.exe");

            driver = new ChromeDriver();

        }
    }

    @Test(priority = 0)
    public void verifyHomePage(){
        driver.get("https://www.realtor.com/");
        driver.manage().window().maximize();
        wait2 = new WebDriverWait(driver, 20);
        homePage = new RealtorHomePage(driver);
        String actualTitle = homePage.getTitle();
        String expectedTitle = "Find Real Estate, Homes for Sale, Apartments & Houses for Rent - realtor.com®";
        Assert.assertEquals(actualTitle,expectedTitle);
    }

    @Test(priority = 1)
    public void navigationToListing(){
        homePage = new RealtorHomePage(driver);
        homePage.enterQuery("Morgantown, WV");
        homePage.searchQuery();
        String expectedListTitle = "Morgantown, WV Real Estate - Morgantown Homes for Sale - realtor.com®";
        listingPage = new HomeListingPage(driver);
        listingPage.waitForPageToLoad();
        String actualTitle=driver.getTitle();
         Assert.assertEquals(actualTitle,expectedListTitle);
    }

    @Test(priority = 2)
    public void checkHomeCount(){
        Boolean  flag = false;
        listingPage = new HomeListingPage(driver);
       int count= listingPage.getHomeCount();
       if(count>0)
           flag=true;
       else flag=false;
        Assert.assertTrue(flag);
    }
    @Test(priority = 3)
    public void checkPrice(){
        Boolean  flag = false;
        listingPage = new HomeListingPage(driver);
        resultPage = new ResultPage(driver);
        String count1= listingPage.checkPriceOnList();
        String count2= resultPage.checkPriceOnView();
        if(count1.equals(count2))
            flag=true;
        else flag=false;
        Assert.assertTrue(flag);
    }

    @AfterTest
    public void closeDriver(){
        driver.quit();
    }
}


