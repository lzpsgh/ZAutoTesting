package restapi.testcase;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import restapi.api.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.params.provider.Arguments.arguments;

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
                .body("name",equalTo("李兆"));
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
        //删除前还没有做数据准备
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

    //以下未经过测试

    @ParameterizedTest
    @CsvFileSource(resources = "TestUser.csv")
    public void deleteByParams(String name, String userid) {
        String nameNew = name;
        if (userid.isEmpty()) {
            userid = "seveniruby_" + System.currentTimeMillis();
        }

        HashMap<String, Object> data = new HashMap<>();
        data.put("name", nameNew);
        data.put("department", new int[]{1});
        data.put("mobile", String.valueOf(System.currentTimeMillis()).substring(0, 11));

        User user = new User();
        user.createUser(data).then().body("errcode", equalTo(0));
        //deleteUser未实现
//        user.deleteUser(userid).then().body("errcode", equalTo(0));
        user.getUser(userid).then().body("errcode", not(equalTo(0)));
    }

    @ParameterizedTest
    @MethodSource("deleteByParamsFromYamlData")
    public void deleteByParamsFromYaml(String name, String userid, List<Integer> departs) {
        String nameNew = name;
        if (userid.isEmpty()) {
            userid = "seveniruby_" + System.currentTimeMillis();
        }
        if(departs==null){
            departs=Arrays.asList(1);
        }

        HashMap<String, Object> data = new HashMap<>();
        data.put("name", nameNew);
        data.put("department", departs);
        data.put("mobile", String.valueOf(System.currentTimeMillis()).substring(0, 11));

        User user = new User();
        user.createUser(data).then().body("errcode", equalTo(0));
        //deleteUser未实现
//        user.deleteUser(userid).then().body("errcode", equalTo(0));
        user.getUser(userid).then().body("errcode", not(equalTo(0)));
    }

    static Stream<Arguments> deleteByParamsFromYamlData() {

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory()); //rest_assured

        //生成一个代表List<HashMap>的类型，用于传递给readValue
        TypeReference<List<HashMap<String, Object>>> typeRef =
                new TypeReference<List<HashMap<String, Object>>>() {
                };
//        String fileName = Thread.currentThread().getStackTrace()[1].getMethodName();

        List<HashMap<String, Object>> data;
        try {
            data = mapper.readValue(
                    UserTest.class.getResourceAsStream("TestUser.yaml"),
                    typeRef);
            ArrayList<Arguments> results = new ArrayList<>();
            data.forEach(map -> {
                results.add(arguments(
                        map.get("name").toString(),
                        map.get("userid").toString(),
                        map.get("departs")
                ));
            });

            return results.stream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Stream.of();

    }

}
