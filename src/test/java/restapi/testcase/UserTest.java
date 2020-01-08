package restapi.testcase;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import restapi.api.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;

public class UserTest {

    User user = new User();
    String getUserId = "LiZhaoPeng"; //522和533 是霍格沃滋专用的部门id
    String updateUserId = "LiZhaoPeng";

    String createUserId = "userid_"+System.currentTimeMillis();
    String createUserName = "User_"+System.currentTimeMillis() ;
    String createMobile = String.valueOf(System.currentTimeMillis()).substring(0,11);
    int[] createUserDeps = new int[]{1};

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "https://qyapi.weixin.qq.com/cgi-bin";
        RestAssured.basePath = "/user";

    }

    @Test
    public void testCreateUser(){

        HashMap<String,Object> userData = new HashMap<>();
        userData.put("userid",createUserId);
        userData.put("name",createUserName);
        userData.put("mobile",createMobile);
        userData.put("department",createUserDeps);
        user.createUser(userData)
                .then()
                .body("errcode",equalTo(0))
                .body("errmsg",equalTo("created"));
    }

    @Test
    public void testGetUser(){
        user.getUser(getUserId)
            .then()
                .body("errcode",equalTo(0))
                .body("errmsg",equalTo("ok"))
                .body("name",equalTo("李兆鹏"));
    }

    @Test
    public void testUpdateUser(){
        HashMap<String,Object> userData = new HashMap<>();
//        userData.put("mobile","18899758128");
        userData.put("userid", updateUserId);
        userData.put("email","1017476777@qq.com");
        user.updateUser(userData)
            .then()
                .body("errcode",equalTo(0))
                .body("errmsg",equalTo("updated"));
    }

    @Test
    public void createFromTemplate(){
        HashMap<String,Object> userData = new HashMap<>();
        userData.put("userid",createUserId);
        userData.put("name",createUserName);
        userData.put("mobile",createMobile);
        userData.put("department",createUserDeps);

        user.createUser(userData)
                .then()
                .body("errcode",equalTo(0))
                .body("errmsg",equalTo("created"));
    }

    @Test
    public void testDeleteBatchUser(){
        String[] batchDeleteList = new String[] {"userid_1578126966675","asdf"};
        HashMap<String,Object> userData = new HashMap<>();
        userData.put("useridlist", batchDeleteList);
        user.deleteBatchUser(userData)
                .then()
                .body("errcode",equalTo(0))
                .body("errmsg",equalTo("deleted"));
    }

    @Test
    public void testGetDepUsers(){
        String depId = "1";
        int fetch_child_mode = 0; //1递归获取 0只获取本部门
        user.getDepUsers(depId,fetch_child_mode)
                .then()
                .body("errcode",equalTo(0))
                .body("errmsg",equalTo("ok"));
    }


}
