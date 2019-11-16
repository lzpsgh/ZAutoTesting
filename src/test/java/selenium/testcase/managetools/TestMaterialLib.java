package selenium.testcase.managetools;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import selenium.page.App;
import selenium.page.managetools.MaterialLibPage;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestMaterialLib {
    private static App app;
    private static MaterialLibPage materialLibPage;

    @BeforeClass
    public static void loginFirst(){
        app = new App().loginWithCookie();//TODO 这里应该封装出通用的登录接口
        materialLibPage = app.toManageToolsPage().toMediaLibrary();
    }

    @Test
    public void testAddMedia(){
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
