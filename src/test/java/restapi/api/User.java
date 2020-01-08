package restapi.api;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import restapi.BaseWework;

import java.io.*;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class User {

    //创建成员
    public Response createUser(HashMap<String, Object> userData) {
        return given()
                    .contentType(ContentType.JSON)
                    .queryParam("access_token", BaseWework.getInstance().getToken())
                    .body(userData)
                .when()
                    .post("/create")
                .then()
                    .log().all()
                    .extract().response();
//                .body("errcode",equalTo(0));
    }

    //读取成员信息
    public Response getUser(String userId){
        return given()
                .queryParam("access_token", BaseWework.getInstance().getToken())
                .queryParam("userid",userId)
                .when()
                .get("/get")
                .then()
//                    .log().all()
                .extract().response();
//                .body("errcode",equalTo(0));
    }

    //更新成员
    public Response updateUser(HashMap<String, Object> userData) {
        return given()
                .contentType(ContentType.JSON)
                .queryParam("access_token", BaseWework.getInstance().getToken())
                .body(userData)
                .when()
                .post("/update")
                .then()
                .log().all()
                .extract().response();
//                .body("errcode",equalTo(0));
    }

    //批量删除成员
    public Response deleteBatchUser(HashMap<String, Object> userData) {
        return given()
                    .log().all()
                    .contentType(ContentType.JSON)
                    .queryParam("access_token", BaseWework.getInstance().getToken())
                    .body(userData)
                .when()
                    .log().all()
                    .post("/batchdelete")
                .then()
                    .log().all()
                    .extract().response();
//                .body("errcode",equalTo(0));
    }

    //获取部门成员
    public Response getDepUsers(String depId, int fetch_child_mode) {
        return given()
                    .queryParam("access_token", BaseWework.getInstance().getToken())
                    .queryParam("department_id",depId)
                    .queryParam("fetch_child",fetch_child_mode)
                .when()
                    .get("/simplelist")
                .then()
                    .log().all()
                    .extract().response();
//                .body("errcode",equalTo(0));
    }

//    public Response cloneUser() {
//        userData.put("userid",userid);
//        String body = template(path,userData);
//
//        return given()
//                    .contentType(ContentType.JSON)
//                    .queryParam("access_token", BaseWework.getInstance().getToken())
//                    .body(body)
//                .when()
//                    .post("/create")
//                    .then()
//                    .log().all()
//                .extract().response();
////                .body("errcode",equalTo(0));
//    }

//    @Test
//    public String template() throws IOException {
//        HashMap<String ,Object> scopes = new HashMap<String,Object>();
//        scopes.put("name","Mustache");
//        Writer writer = new StringWriter();
//        MustacheFactory mf = new DefaultMustacheFactory();
//        Mustache mustache = mf.compile('src/main/resourdce根目录');
//        mustache.execute(writer,scopes);
//        writer.flush();
//        return writer.toString();
//    }
}















