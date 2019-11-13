package selenium.testcase.managetools;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import selenium.page.App;
import selenium.page.MaterialLibPage;

public class TestMaterialLib {
    public static App app;
    public static MaterialLibPage materialLibPage;

    //TODO 这里应该封装出通用的登录接口
    @BeforeClass
    public static void loginFirst(){
        if (app == null){
            app = new App().loginWithCookie();
        }
        if (materialLibPage == null){
            materialLibPage = new MaterialLibPage();
        }
        app.toMediaLibrary();
    }

    @Test
    public void addMedia(){
        // 企业微信只支持gif jpeg png bmp格式
//        materialLibPage.uploadImg("/Users/lensaclrtn/Downloads/miss.gif");
        materialLibPage.uploadImg("D:/Temp/Ctrl+Esc.jpg");
    }

    @AfterClass
    public static void quit(){
        materialLibPage = null;
        app.quit();
    }

}
