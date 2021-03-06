package restapi;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Base64;
import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static io.restassured.config.JsonConfig.jsonConfig;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.hamcrest.Matchers.*;


public class Sample {

    static String urlBeal = "http://127.0.0.1:80/beal.json";
    static String urlBase64 = "http://localhost:8002/base64.json";

    @Ignore
    public void testJson(){
        given()
                .when()
                .get("http://127.0.0.1:80/lzp.json")
                .then()
                .body("lotto.lottoId",equalTo(5))
                .body("lotto.winners.winnerId",hasItems(23,54))
        ;
    }

    //id=5
    @Test
    public void test1(){
        get(urlBeal).then().assertThat().body("id",equalTo("390"));
    }

    //data.leagueId=35
    @Test
    public void test2(){
        get(urlBeal).then().assertThat().body("data.leagueId",equalTo(35));
    }

    //data.float=33.3f
    @Test
    public void test3(){
        get(urlBeal).then().assertThat().body("data.float",equalTo(33.3f));
    }

    //data.homeTeam="Norway"
    @Test
    public void test4(){
        get(urlBeal).then().assertThat().body("data.homeTeam",equalTo("Norway"));
    }

    //data.homeTeam hasItem
    @Test
    public void test5(){
        get(urlBeal).then().assertThat().body("odds.findAll{ it.price == \"5.25\"}.name",hasItem("X"));
    }

    //data.odds count=3
    @Test
    public void test6(){
        get(urlBeal).then().assertThat().body("odds.size()", equalTo(2));
    }

    //data.odds[1].price = "5.25"
    @Test
    public void test7(){
        get(urlBeal).then().assertThat().body("odds[1].price", equalTo("5.25"));
    }



    @Test
    public void testGetHtml(){
        useRelaxedHTTPSValidation();//x信任证书
        given().log().all().proxy("127.0.0.1",7778).get("http://www.baidu.com")
                .then().log().all().statusCode(200);

    }

    @Test
    public void testMp3(){
        given().get("http://www.bing.com/search?q=mp3").then().log().all().statusCode(200);
    }

    @Test
    public void testStandardMp3(){
        given()
//            .config(RestAssured.config().jsonConfig(jsonConfig().numberReturnType(BIG_DECIMAL))).
                .queryParam("q","mp3")
//            .contentType()
                .header("Cookie","aaa")
                .when()
                .get("http://www.bing.com/search")
                .then()
//            .postBodyMap("price", is(new BigDecimal(12.12))
                .log().all()
                .statusCode(200)
                .body("root.parent",hasItem("sss"))
                .body("root.parent.child[1]",hasItem("asdf"))
        ;
    }

    @Test
    public void testBase64(){
        given().filter(new RespBase64Filter())
                .get("http://101.132.159.87:8080/user.json")
                .prettyPeek()
                .then()
                .statusCode(200)
                .body("errcode",equalTo(0))
                .body("errmsg",equalTo("ok"))
        ;
    }

    @Ignore
    public void testBase64Tmp(){
        given().filter( (req,res,ctx)-> {
            Response resOrigin = ctx.next(req,res);
            ResponseBuilder responseBuilder = new ResponseBuilder().clone(resOrigin);
            String tmpDecodeContent = new String(
                    Base64.getDecoder().decode(
                            resOrigin.body().asString().trim()
                    )
            );
            responseBuilder.setBody(tmpDecodeContent);
            Response resNew = responseBuilder.build();
            return resNew;
        })
//                .get(urlBase64)
                .get("http://101.132.159.87:8080/user.json")
                .prettyPeek()
                .then()
                .statusCode(200)
                .body("errcode",equalTo(0))
                .body("errmsg",equalTo("ok"))
        ;
    }

    public String cloneFromTemplate(String templatePath, HashMap<String, Object> data){
        Writer writer = new StringWriter();
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile(this.getClass().getResource(templatePath).getPath());//mf.compile(templatePath);
        mustache.execute(writer, data);
        try {
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writer.toString();
    }

//    public String matchSchema(){
//        get(urlBeal)
//                .then().assertThat()
//                .postBodyMap(matchesJsonSchemaInClasspath("restapi/schema_user_get.json"));
//    }

    //关闭https校验
//    RestAssured.useRelaxedHTTPSValidation();

}














