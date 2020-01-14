package restapi.testcase;

import org.junit.Test;
import restapi.api.UserApi;
import static org.hamcrest.Matchers.*;

public class UserApiTest {

    @Test
    public void get(){
        UserApi userServiceApi = new UserApi();
        userServiceApi.getUser("story11578206671544").then().body("errcode",equalTo(0));
    }

    @Test
    public void delete(){
        UserApi userServiceApi = new UserApi();
        userServiceApi.deleteUser("1111").then().body("errcode",equalTo(0));
    }
}
