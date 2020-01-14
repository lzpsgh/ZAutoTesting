package restapi.api;


import io.restassured.response.Response;
import restapi.base.BaseApi;
import restapi.base.BaseWework;

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
//        return given()
//                .queryParam("access_token", WeWork.getInstance().getToken())
//                .queryParam("userid",userId)
//                .when()
//                .log().all()
//                .get("https://qyapi.weixin.qq.com/cgi-bin/user/delete")
//                .then()
//                .log().all()
//                .extract().response();
    }

}