package restapi.api;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import restapi.base.BaseWework;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
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
//                .postBodyMap("errcode",equalTo(0));
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
//                .postBodyMap("errcode",equalTo(0));
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
//                .postBodyMap("errcode",equalTo(0));
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
//                .postBodyMap("errcode",equalTo(0));
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
//                .postBodyMap("errcode",equalTo(0));
    }

    public Response clone(String userid, HashMap<String, Object> data) {
        data.put("userid", userid);
        String body=template("user.json", data);

        return given()
                .queryParam("access_token", BaseWework.getInstance().getToken())
                .contentType(ContentType.JSON)
                .body(body)
                .when().log().all().post("https://qyapi.weixin.qq.com/cgi-bin/user/create")
                .then().log().all().extract().response();
    }

    public String template(String templatePath, HashMap<String, Object> data){
        Writer writer = new StringWriter();
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile(this.getClass().getResource(templatePath).getPath());
        mustache.execute(writer, data);
        try {
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writer.toString();
    }
}















