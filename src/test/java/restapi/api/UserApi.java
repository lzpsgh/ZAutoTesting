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

public class UserApi extends BaseApi {

    public Response getUser(String userId) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("userid", userId);
        setParams(params);
        return parseSteps();

    }

    public Response updateUser(String userId, HashMap<String, Object> data) {
        data.put("userid", userId);
        return given()
                .queryParam("access_token", BaseWework.getInstance().getToken())
                .body(data)
                .when()
                .log().all()
                .post("https://qyapi.weixin.qq.com/cgi-bin/user/update")
                .then().log().all().extract().response();
    }

    public Response deleteUser(String userId) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("userid", userId);
        setParams(params);
        return parseSteps();
    }

//    以下代码未改好
public Response create(String userid, HashMap<String, Object> data) {
    data.put("userid", userid);

    return given()
            .queryParam("access_token", BaseWework.getInstance().getToken())
            .body(data)
            .when().log().all().post("https://qyapi.weixin.qq.com/cgi-bin/user/create")
            .then().log().all().extract().response();
}

    public Response clone(String userid, HashMap<String, Object> data) {
        data.put("userid", userid);
        //todo: 使用模板技术

        String body=template("/service/user/api/user.json", data);

        return given()
                .queryParam("access_token", BaseWework.getInstance().getToken())
                .contentType(ContentType.JSON)
                .body(body)
                .when().log().all().post("https://qyapi.weixin.qq.com/cgi-bin/user/create")
                .then().log().all().extract().response();
    }

    public Response delete(String userid) {
        return given()
                .queryParam("access_token", BaseWework.getInstance().getToken())
                .queryParam("userid", userid)
                .when().log().all().get("https://qyapi.weixin.qq.com/cgi-bin/user/delete")
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