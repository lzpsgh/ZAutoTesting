package restapi.api;

import io.restassured.http.ContentType;
import restapi.base.BaseWework;
import io.restassured.response.Response;
import java.util.HashMap;

import static io.restassured.RestAssured.*;

public class Tag {


    public Response createTag(String tagName){
        HashMap<String, Object> map = new HashMap<>();
        map.put("tagname", tagName);
        return given()
                    .queryParam("access_token", BaseWework.getInstance().getToken())
                    .contentType(ContentType.JSON)
                    .body(map)
                .when()
//                    .log().all()
                    .post("/create")
                .then()
                    .extract().response();
    }

    public Response createTag(String tagName,int tagId){
        HashMap<String, Object> map = new HashMap<>();
        map.put("tagname",tagName);
        map.put("tagid", tagId);
        return given()
                    .queryParam("access_token",BaseWework.getInstance().getToken())
                    .contentType(ContentType.JSON)
                    .body(map)
                .when()
//                    .log().all()
                    .post("/create")
                .then()
                    .extract().response();
    }


    public Response updateTag(int tagId,String tagName){
        HashMap<String,Object> map = new HashMap<>();
        map.put("tagid",tagId);
        map.put("tagname", tagName);
        return given()
                    .queryParam("access_token",BaseWework.getInstance().getToken())
                    .contentType(ContentType.JSON)
                    .body(map)
                .when()
//                    .log().all()
                    .post("/update")
                .then()
                    .extract().response();
    }

    public Response deleteTag(int tagId){
        return given()
                    .queryParam("access_token",BaseWework.getInstance().getToken())
                    .formParam("tagid",tagId)
                .when()
//                    .log().all()
                    .get("/delete")
                .then()
                    .extract().response();
    }

    public Response getTagList(){
        HashMap taglist = new HashMap();
        return given()
                    .queryParam("access_token",BaseWework.getInstance().getToken())
                .when()
//                    .log().all()
                    .get("/list")
                .then()
                    .extract().response();
    }

}
