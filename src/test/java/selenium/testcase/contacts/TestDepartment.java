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
        //TODO 如果每个case执行钱都提前用assert会污染断言成功率，前期数据清理环境准备统一共用一个入口函数
//        assertThat(departmentPage.getAllDepartmentName("Depart"), not(hasItem(TextObj.DEP_NAME_NEW)));
        departmentPage.addDepartment(TextObj.DEP_NAME_NEW, TextObj.DEP_NAME_TOTAL);
        assertThat(departmentPage.getAllDepartmentName("Depart"), hasItem(TextObj.DEP_NAME_NEW));
        departmentPage.delDepartment(TextObj.DEP_NAME_NEW);//测试数据清理
    }


    @Test
    public void testAddSubDepartment(){
        //TODO  待调试
        departmentPage.addDepartment(TextObj.DEP_NAME_CHILD,TextObj.DEP_NAME_NEW);
        assertThat(departmentPage.getAllDepartmentName("SubDepart"), hasItem(TextObj.DEP_NAME_CHILD));
        departmentPage.delDepartment(TextObj.DEP_NAME_CHILD);
    }
    @Test
    public void testDelDepartment(){
        //TODO  待调试
        departmentPage.addDepartment(TextObj.DEP_NAME_DELETE,TextObj.DEP_NAME_TOTAL)
                .delDepartment("TextObj.DEP_NAME_DELETE");
        assertThat(departmentPage.getAllDepartmentName("Depart"),not(hasItem(TextObj.DEP_NAME_DELETE)));
    }
    @Test
    public void testDelSubDepartment(){
        //TODO  待调试
        departmentPage.addDepartment(TextObj.DEP_NAME_DELETE,TextObj.DEP_NAME_NEW)
                .delDepartment("TextObj.DEP_NAME_DELETE");
        assertThat(departmentPage.getAllDepartmentName("SubDepart"),not(hasItem(TextObj.DEP_NAME_DELETE)));
    }
    @Test
    public void testMoveupDepartment(){
        //TODO  待调试
        departmentPage.addDepartment(TextObj.DEP_NAME_UP,TextObj.DEP_NAME_TOTAL)
                .moveupDepartment(TextObj.DEP_NAME_UP);
        List<String> allDepartments = departmentPage.getAllDepartmentName("Depart");
        assertThat(allDepartments.get(allDepartments.size()-1), not(TextObj.DEP_NAME_UP));
    }
    @Test
    public void testRenameDepartment(){
        //TODO  待调试
        departmentPage.addDepartment(TextObj.DEP_NAME_NEW,TextObj.DEP_NAME_TOTAL)
                .renameDepartment(TextObj.DEP_NAME_NEW,TextObj.DEP_NAME_MOD);
        assertThat(departmentPage.getAllDepartmentName("Depart"), hasItem(TextObj.DEP_NAME_MOD));
        departmentPage.delDepartment(TextObj.DEP_NAME_MOD);
    }

    @AfterClass
    public static void quit(){
        departmentPage = null;
        app.quit();
    }
}
