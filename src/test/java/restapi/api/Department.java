package restapi.api;

import io.restassured.http.ContentType;
import restapi.BaseWework;
import io.restassured.response.Response;
import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class Department {

    public Response createDepartment(String depName, int parentDepartid, int depId){
        HashMap<String,Object> depData = new HashMap<>();
        depData.put("name",depName);
        depData.put("parentid",parentDepartid);
        depData.put("id",depId);

        return given()
                .contentType(ContentType.JSON)
                .queryParam("access_token",BaseWework.getInstance().getToken())
                .body(depData)
        .when()
                .post("/create")
        .then()
                .log().all()
                .extract().response();
//                .body("errcode",equalTo(0));
    }

    public Response deleteDepartment(int depid){
        return given()
                .queryParam("access_token",BaseWework.getInstance().getToken())
                .queryParam("id",depid)
        .when()
            .get("/delete")
        .then()
            .log().all()
            .extract().response();

    }

    public Response getDepartmentList(){
        return given()
                .queryParam("access_token",BaseWework.getInstance().getToken())
                .when()
                .get("/list")
                .then()
                .log().all()
                .extract().response();
//                .body("errcode",equalTo(0));
    }

    public Response getDepartmentList(int parentDepartid){

        return given()
                .queryParam("access_token",BaseWework.getInstance().getToken())
                .queryParam("id",parentDepartid)
        .when()
                .get("/list")
        .then()
//                .log().all()
                .extract().response();
//                .body("errcode",equalTo(0));
    }
}
