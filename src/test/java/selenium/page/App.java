package selenium.page;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import selenium.page.contacts.ContactPage;
import selenium.page.managetools.ManageToolsPage;
import selenium.page.managetools.MaterialLibPage;

import java.util.concurrent.TimeUnit;

public class App extends BasePage{


    public App loginWithCookie(){
        String url = "https://work.weixin.qq.com";

        driver = new ChromeDriver();
        ChromeOptions chromeOptions = new ChromeOptions();
        //Page Load Strategy: normal,eager,none
        chromeOptions.setCapability("pageLoadStrategy","none");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        js = (JavascriptExecutor) driver;
//        vars = new HashMap<String, Object>();
        driver.get(url);
//        driver.manage().window().fullscreen();
        driver.findElement(By.linkText("企业登录")).click();

        System.out.println(driver.manage().getCookies());

//        driver.manage().addCookie(new Cookie("wwrtx.refid","18316058762159986"));
        driver.manage().addCookie(new Cookie("wwrtx.sid","qMSD2gzQ7DG7eUrHLdy5kozrqG4iqmLnnP-WsindnL6AjYqAKMcJPgLIwr31rCRb"));

        driver.navigate().refresh();
        return this;
    }

//    public IndexPage toIndexPage(){
//        findElement(By.linkText("首页")).click();
//        return new IndexPage();
//    }
    public ContactPage toContact(){
        findElement(By.linkText("通讯录")).click();
        return new ContactPage();
    }
//    public AppsPage toAppsPage(){
//        findElement(By.linkText("应用管理")).click();
//        return new AppsPage();
//    }
//    public CustomerPage toCustomerPage(){
//        findElement(By.linkText("客户联系")).click();
//        return new CustomerPage();
//    }
    public ManageToolsPage toManageToolsPage(){
        findElement(By.linkText("管理工具")).click();
        return new ManageToolsPage();
    }
//    public ProfilePage toProfilePage(){
//        findElement(By.linkText("我的企业")).click();
//        return new ProfilePage();
//    }

}
