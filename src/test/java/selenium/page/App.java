package selenium.page;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class App extends BasePage{


    public App loginWithCookie(){
        String url = "https://work.weixin.qq.com";

        driver = new ChromeDriver();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        js = (JavascriptExecutor) driver;
//        vars = new HashMap<String, Object>();
        driver.get(url);
//        driver.manage().window().fullscreen();
        driver.findElement(By.linkText("企业登录")).click();

        System.out.println(driver.manage().getCookies());

        driver.manage().addCookie(new Cookie("wwrtx.refid","18316058762159986"));
        driver.manage().addCookie(new Cookie("wwrtx.sid","qMSD2gzQ7DG7eUrHLdy5kjvac9vc1pFA9h2XVG3c4v08a1fuRqZ-AISZxv5ykQEL"));

        driver.navigate().refresh();
        return this;

    }

    public ContactPage toContact(){
//        findElement(By.linkText("添加成员")).click();
        return new ContactPage();
    }

    public ContactPage toMemberAdd(){
        findElement(By.linkText("添加成员")).click();
        return new ContactPage();
    }

    public MediaLibrary toMediaLibrary(){
        findElement(By.linkText("管理工具")).click();
        findElement(By.cssSelector(".ww_icon_AppMaterialBig")).click();
        return new MediaLibrary();
    }
    public void quit(){

    }
}
