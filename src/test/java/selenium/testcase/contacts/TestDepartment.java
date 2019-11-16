package selenium.testcase.contacts;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import selenium.data.TextObj;
import selenium.page.App;
import selenium.page.contacts.DepartmentPage;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestDepartment {
    private static App app;
    private static DepartmentPage departmentPage;

    @BeforeClass
    public static void loginFirst(){
        app = new App().loginWithCookie();//TODO 这里应该封装出通用的登录接口
        departmentPage = app.toContact().toDepartmentPage();
    }

    @Before
    public void beforeEach(){
        System.out.println("beforeeach");
        app.refresh();
    }

    @Test
    public void testAddDepartment(){
        assertThat(departmentPage.getAllDepartment("Depart"), not(hasItem(TextObj.DEP_NAME_NEW)));
        departmentPage.addDepartment(TextObj.DEP_NAME_NEW, TextObj.DEP_NAME_TOTAL);
        assertThat(departmentPage.getAllDepartment("Depart"), (hasItem(TextObj.DEP_NAME_NEW)));
        departmentPage.delDepartment(TextObj.DEP_NAME_NEW);//测试数据清理
    }

    @Test
    public void testAddSubDepartment(){
        departmentPage.addSubDepartment();
    }
    @Test
    public void testDelDepartment(){
        departmentPage.delDepartment("");
    }
    @Test
    public void testDelSubDepartment(){
        departmentPage.delSubDepartment();
    }
    @Test
    public void testMoveupDepartment(){
        departmentPage.moveupDepartment();
    }
    @Test
    public void testRenameDepartment(){
        departmentPage.renameDepartment();
    }

    @AfterClass
    public static void quit(){
        departmentPage = null;
        app.quit();
    }
}
