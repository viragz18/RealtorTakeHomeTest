package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class RealtorHomePage {
    WebDriver driver;
   WebDriverWait wait2 ;
    //By titleText =By.("barone");

    By searchBox = By.name("q");

    By searchBtn =  By.cssSelector("span.input-group-btn:nth-child(3) > button:nth-child(2)");

    public RealtorHomePage(WebDriver driver) {
        this.driver = driver;
        wait2 =new WebDriverWait(driver, 20);
    }

    public String getTitle(){

        return driver.getTitle();
    }

    public void enterQuery(String query){
        WebDriverWait wait2 =new WebDriverWait(driver, 20);
        wait2.until(ExpectedConditions.presenceOfElementLocated(searchBox));
        driver.findElement(searchBox).sendKeys(query);
    }

    public void searchQuery(){
        driver.findElement(searchBtn).click();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

    }


}
