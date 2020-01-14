package restapi;

import io.restassured.response.Response;

import java.util.HashMap;

//A http request
public class ApiObjectModel {
//    public HashMap<String,Object> query;
//    public HashMap<String,Object> header;
//    public HashMap<String,Object> postBodyMap;
//    public String postBodyStr
    public HashMap<String,ApiObjectMethodModel> methods = new HashMap<>();

    public ApiObjectMethodModel getMethod(String method){
        return methods.get(method);
    }

    public Response run(String method){
        return getMethod(method).run();
    }

    public Response run(String method, HashMap<String, Object> params) {
        return getMethod(method).run(params);
    }
}
