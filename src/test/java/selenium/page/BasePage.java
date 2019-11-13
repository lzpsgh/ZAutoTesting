package selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    public static WebDriver driver;

    public WebElement findElement(By by){
        waitClickable(by);
        return driver.findElement(by);
    }
    public WebElement findElement(By by,int timeout){
        if(timeout>0){
            waitClickable(by,timeout);
        }
        return driver.findElement(by);
    }

    public WebElement findVisibleElement(By by){
        waitVisible(by);
        return driver.findElement(by);
    }

    public void waitClickable(By by,int timeout){
        new WebDriverWait(driver,timeout).until(ExpectedConditions.elementToBeClickable(by));
    }

    public void waitClickable(By by){
        new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOfElementLocated(by));
        new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(by));
    }

    public void waitVisible(By by){
        new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void quit(){
        driver.quit();
    }
}
