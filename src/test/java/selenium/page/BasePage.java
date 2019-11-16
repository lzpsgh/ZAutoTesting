package selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage {
    public static WebDriver driver;

    public List<WebElement> findElements(By by){
        return driver.findElements(by);
    }

    public void findClick(By by){
        findElement(by).click();
    }
    public void findClick(By by,int timeout){
        findElement(by,timeout).click();
    }

    public void findSendKeys(By by,String str){
        findElement(by).sendKeys(str);
    }
    public void findSendKeys(By by,int timeout,String str){
        findElement(by,timeout).sendKeys(str);
    }
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
        //presenceOfElementLocated是指元素出现在DOM内，此时未必可见
        new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOfElementLocated(by));
        new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(by));
    }

    public void waitVisible(By by){
        new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    public void waitSleep(int second){
        try {
            Thread.sleep(second*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void refresh(){
        driver.navigate().refresh();
    }
    public void quit(){
        driver.quit();
    }
}
