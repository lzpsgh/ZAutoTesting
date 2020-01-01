package restapi.testcase;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.*;
import restapi.api.Department;

import java.util.HashMap;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class DepartmentTest {

    private Department department = new Department();
    static int parentDepid = 1; //522和533 是霍格沃滋专用的部门id
    String createDepName = "TouHouProject";
    int createDepId = 10;
    String deleteDepName = "wtcf";
    int deleteDepId = 20;



    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "https://qyapi.weixin.qq.com/cgi-bin";
        RestAssured.basePath = "/department";
    }

    @Test
    public void testCreateDepartment(){
        //不能删除根部门；不能删除含有子部门、成员的部门
        department.deleteDepartment(createDepId);
        department.createDepartment(createDepName,parentDepid,createDepId)
                .then()
                .body("errcode",equalTo(0))
                .body("errmsg",equalTo("created"));
        department.getDepartmentList()
                .then()
                .body("department.id",hasItem(createDepId));
    }

    @Test
    public void testDeleteDepartment(){
        department.createDepartment(deleteDepName,parentDepid,deleteDepId);
        department.deleteDepartment(deleteDepId)
                .then()
                .body("errcode",equalTo(0))
                .body("errmsg",equalTo("deleted"));

    }


    @Test
    public void testGetDepartmentList(){
        department.getDepartmentList()
                .then()
                    .body("errcode",equalTo(0))
                    .body("errmsg",equalTo("ok"));
    }
}
