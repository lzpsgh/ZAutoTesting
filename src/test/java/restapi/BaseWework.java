package restapi;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class BaseWework {
    private static BaseWework instance;
    public String token;

    public static BaseWework getInstance() {
        if (instance == null) {
            System.out.println("instance is null\n");
            instance = new BaseWework();
        }
        return instance;
    }

    public String getToken() {
        if (token == null) {
            System.out.println("token is null\n");
            token = given()
                    .param("corpid", "ww2790085f4cf755b6")
                    .param("corpsecret", "pGmsUatoSx9RUy7vCk9g8DCh1zNuIWtC_nhk4nGtP_w")
                    .when()
                    .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                    .then().log().all().body("errcode", equalTo(0))
                    .extract().response().path("access_token");
        }
        System.out.println("access_token=" + token+"\n");
        return token;
    }
}
