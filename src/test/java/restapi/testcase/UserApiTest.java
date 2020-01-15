package restapi.testcase;

import org.junit.Test;
import restapi.api.UserApi;
import static org.hamcrest.Matchers.*;

public class UserApiTest {

    @Test
    public void get(){
        UserApi userApi = new UserApi();
        userApi.getUser("userid_1234").then().body("errcode",equalTo(0));
    }

    @Test
    public void delete(){
        UserApi userServiceApi = new UserApi();
        userServiceApi.deleteUser("userid_1234").then().body("errcode",equalTo(0));
    }
}
