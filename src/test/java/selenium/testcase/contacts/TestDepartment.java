package selenium.testcase.contacts;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import selenium.page.App;
import selenium.page.contacts.DepartmentPage;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestDepartment {
    public static App app;
    public static DepartmentPage departmentPage;

    @BeforeClass
    public static void loginFirst(){
        app = new App().loginWithCookie();//TODO 这里应该封装出通用的登录接口
        departmentPage = app.toContact().toDepartmentPage();
    }

    @Test
    public void testAddDepartment(){
        departmentPage.addDepartment();
    }
    @Test
    public void testAddSubDepartment(){
        departmentPage.addSubDepartment();
    }
    @Test
    public void testDelDepartment(){
        departmentPage.delDepartment();
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
