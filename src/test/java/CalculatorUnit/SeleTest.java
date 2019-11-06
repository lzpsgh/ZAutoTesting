package CalculatorUnit;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class SeleTest {
    //    public static void main(String[] args) {
//        WebDriver driver = new ChromeDriver();
//        driver.get("http://www.baidu.com");
//        String title = driver.getTitle();
//        System.out.printf(title);
//        driver.close();
//    }
    private WebDriver driver;
    private Map<String,Object> vars;
    private JavascriptExecutor js;

    @Before
    public void setUp(){
        driver = new ChromeDriver();
        js = (JavascriptExecutor)driver;
        vars = new HashMap<String, Object>();
    }

    @Test
    public void post_redmine_work() throws InterruptedException {
        driver.get("http://redmine.gizwits.com/login");
        driver.findElement(By.id("username")).sendKeys("zpli");
        driver.findElement(By.id("password")).sendKeys("zpli35vf");
        driver.findElement(By.id("login-submit")).submit();

//        我的工作台 "http://redmine.gizwits.com/my/page"
//        耗时  "http://redmine.gizwits.com/time_entries?user_id=me"
//        登记工时 "http://redmine.gizwits.com/time_entries/new"

//        driver.manage().deleteAllCookies();
//        Cookie c1 = new Cookie("_redmine_session", "SkxZV0ZVTXA0WTJrTXUxazA3RTQ0bytjQzQzMkh6cXdYd256a2VkZXlvbkYwSVpxcW4wTnVlWmZ4WGg2cUNKQnVPZjFlR2RNK0FZM05EeDdJVDB3NXd2TDR4dWlNYlZiTEozQW82VnI0NUZtdVlqNXltZ1Ixa1dhMmxnbnhrSEloOXRZcDBVWjhaYkdDbkp1TDNORjcrSVJBMUVqZms4eHRFUGxld2FpRE95ZjdVazMyQzJ1L3JBN255NjkrbVM0eno0MlhBOXU0UjBzMFU0U25xeTNsYkRVZmhuOGkrUTZJNmdGRHdRMTBHZmxLVmRQSTdkeDA5MkRpbnl0UmRkMWNiMDM1YTNwazI2Qjg5SU0wNUYwZ2trS29HREFjQXdrL0ZaZlpTR1dpVGYyVGMzOGQyRHZsY3BkVjR4SE1sQ05QVHlPSndJNGdXTzZ2WVQvbGszWjhUOFQzbWxNV0M5R1VlUlBpd0R6WUEyZTZ0MHR5QnJuQWh2cXRkNjN5VWZCTC9Cek93NTFIZ21EZEZ3dE5BbTVQQUQxczV3NkQ4WkNOS1V1c1RrcmVVWXJ2Y3E1cHkvaGw4T0FRMjRxYmRkWm94VFRqRVZhY1EvdDNDZHU3c1E5TzI5OWRaMHFmeXNxcnJmVEFndjcrK0x2cisvekFhL0l4SkFyL00vTllmM0hETFQ5Q1dZQUlxYTd0bzZBNGNhRU5MV2VkR00yMkJBMmJqY1ZKSldkcndwOUJ4NmFUZyt0anl4RzB6UXEvakgvaXdIeEllaU1ZREpFWmRWQTJtdUVGME9TcUgvWXA1TzZocHIrU1pHQ3dUVmJHajdISzJBMjhvK0RmSU1QTWtGclJQbFY3MGpUZ2p6a3BIbmNJb2dwSHc9PS0tYXZYRktEQlM3OGVlM0VwdXRsVHlNZz09");
//        driver.manage().addCookie(c1);
//        Cookie c2 = new Cookie("nice_idb01e0430-943a-11e6-8ad7-636675d9e71f", "a9de28f1-fabe-11e9-bad4-935ebcb55de6");
//        driver.manage().addCookie(c2);

//        Set<Cookie> coo = driver.manage().getCookies();
//        System.out.println(coo);

        driver.get("http://redmine.gizwits.com/time_entries/new");
//        driver.navigate().refresh();
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        System.out.printf(driver.getTitle());

        //隐式等待
//        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);

        //显式等待
//        new WebDriverWait(driver,5)
//                .until(ExpectedConditions.elementToBeClickable(By.name(("q"))));
//        driver.manage().window().setSize(new Dimension(1440,877));

//        driver.findElement(By.name("q")).click();
//        driver.findElement(By.name("q")).sendKeys("fefe");
//        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
//        driver.findElement(By.linkText("hhhhhh")).click();

        // 桌面端滚动条
//        new Actions(driver).contextClick().perform();
//        Thread.sleep(10000);

        //刷新页面
//        driver.navigate().refresh();

    }

    @Test
    public void testFrame(){
        driver.get("https://testerhome.com/topics/19664");
        driver.manage().window().maximize();
        driver.switchTo().frame(0);
        WebElement element = driver.findElement(By.partialLinkText("金数据"));
        element.click();

        driver.switchTo().window((String)driver.getWindowHandles().toArray()[1]);

        System.out.println(driver.getCurrentUrl());
    }

    @After
    public void tearDown() throws InterruptedException{
        Thread.sleep(10000);
        driver.quit();
    }
}