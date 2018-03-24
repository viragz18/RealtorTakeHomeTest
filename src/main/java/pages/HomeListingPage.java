package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class HomeListingPage {
    WebDriver driver;
    WebDriverWait wait2;
    WebElement homeCount;
    By ad = By.id("div-gpt-ad-MRECT1");
    By idCount = By.id("search-result-count");
    By classCount = By.className("search-result-count");
    By popupIframe = By.xpath("//*[@id=\"200_223_html_floating_0.if\"]");
    By closeFrame = By.xpath("//*[@id=\"close\"]");
    By secondItem = By.id("2");
    By saveSearchBtn = By.xpath(".//*[@id='facet-followbtn']");
    By priceTag = By.xpath("//*[@id=\"aj-qv-sec-property-header\"]/div/div[2]/div[1]/div/span");
    By viewDetails = By.xpath("//*[@id=\"aj-qv-header\"]/a[5]/span");
    WebElement price;

    public HomeListingPage(WebDriver driver) {
        this.driver = driver;
        wait2 = new WebDriverWait(driver, 20);
    }


    public int getHomeCount() {
        boolean present;
        try {
            driver.findElement(ad);
            present = true;
        } catch (NoSuchElementException e) {
            present = false;
        }

        if (present == false) {
            homeCount = driver.findElement(idCount);
        }else{
            homeCount = driver.findElement(classCount);
        }
        String countText = homeCount.getText().replaceAll("\\D+", "");

        System.out.println(countText);
        int count = Integer.valueOf(countText);
        return count;
    }

    public void waitForPageToLoad() {
        wait2.until(ExpectedConditions.visibilityOfElementLocated(saveSearchBtn));
    }

    public String checkPriceOnList() {
        closePopUp();
        boolean present;
        try {
            driver.findElement(ad);
            present = true;
        } catch (NoSuchElementException e) {
            present = false;
        }
        if(present==true){
            price = driver.findElement(By.xpath("//*[@id=\"2\"]/div[2]/div[2]/div/span"));
           String pric = price.getText();
            wait2.until(ExpectedConditions.visibilityOfElementLocated(secondItem));
            WebElement list = driver.findElement(secondItem);
            list.click();
            return pric;
        }
        else{
            wait2.until(ExpectedConditions.visibilityOfElementLocated(secondItem));
            WebElement list = driver.findElement(secondItem);
            list.click();
            driver.switchTo().activeElement();
            wait2.until(ExpectedConditions.presenceOfElementLocated(By.id("ldp-cta")));
            price=driver.findElement(priceTag);
            String pric = price.getText();
            System.out.println(pric);
            WebElement details = driver.findElement(viewDetails);
            details.click();
            ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
            driver.switchTo().window(tabs2.get(1));
            return pric;
        }

    }

    private void closePopUp() {
        boolean pop;
        try
        {
            driver.findElement(popupIframe);
            pop= true;
        }   // try
        catch (NoSuchElementException Ex)
        {
            pop= false;
        }
        if(pop==true) {
            //driver.switchTo().activeElement();
            driver.switchTo().frame(driver.findElement(By.xpath("//*[@id=\"200_223_html_floating_0.if\"]")));
            driver.findElement(closeFrame).click();
        }
    }
}