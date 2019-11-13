package selenium.testcase;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import selenium.page.App;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;

public class TestWeWork {

    public static App app;
    private Map<String, Object> vars;
    JavascriptExecutor js;

    @BeforeClass
    public static void beforeClass() {
        System.out.println("BeforeClass");
        app = new App();
        app.loginWithCookie();
        String phone = "18899758127";
//        app.toContact().delete(phone);
    }


    //测试通过，在添加通讯录页面能对3个编辑框进行填充
//    @Test
//    public void add() {
//        String phone = "18899758127";
//        app.toMemberAdd().add(phone,phone,phone);
////        assertThat();
//    }

//    @Test
//    public void delete(){
//        String phone = "18899758128";
//        app.toMemberAdd().add(phone,phone,phone).delete(phone);
//    }

//    @Test
//    public void deleteCurrentPage(){
//        app.toContact().deleteCurrentPage();
//    }

//    @Test
//    public void importFromFile(){
//        app.toContact().importFromFile();
//    }

    @After
    public void After() {
        System.out.println("After");
    }

    @AfterClass
    public static void AfterClass() {
        System.out.println("AfterClass");
        app.quit();
    }


}
