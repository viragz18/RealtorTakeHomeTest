package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ResultPage {
    WebDriver driver;

    By priceOnView = By.xpath("//*[@id=\"ldp-pricewrap\"]/div/div/span");

    public ResultPage(WebDriver driver) {
        this.driver=driver;
    }

    public String checkPriceOnView() {
        String price = driver.findElement(priceOnView).getText();
        return price;
    }
}
