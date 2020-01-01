package restapi.testcase;

import com.sun.xml.bind.v2.TODO;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import restapi.api.Tag;
import static org.hamcrest.Matchers.*;

public class TagTest {

    private Tag tag = new Tag();
    int tmpId;

    // TODO 用yaml管理测试数据
    String createTagName = "createTagName1";
    int createTagId = 101;

    String createTagNameWith = "createTagName2";
    int createTagIdWith = 102;

    String updateTagName = "updateTagName3";
    int updateTagId = 103;

    int deleteTagId = 104;

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "https://qyapi.weixin.qq.com/cgi-bin";
        RestAssured.basePath = "/tag";
    }

    @Test
    public void testCreateTagWithId(){
        tag.deleteTag(createTagIdWith).then().statusCode(200);
        tmpId = tag.createTag(createTagNameWith, createTagIdWith)
                .then()
                .body("errcode",equalTo(0))
                .body("errmsg",equalTo("created"))
                .extract().path("tagid");
        tag.getTagList()
                .then()
//                .log().all()
                .body("taglist.tagid",hasItem(createTagIdWith));
    }

    @Test
    public void testUpdateTag(){
        tag.createTag("updatehhhhh",updateTagId);
        tag.updateTag(updateTagId,updateTagName)
            .then()
                //.log().all()
                .body("errcode",equalTo(0))
                .body("errmsg",equalTo("updated"));
        tag.getTagList()
            .then()
//                .log().all()
                .body("taglist.tagname",hasItem(updateTagName));
    }

    @Test
    public void testDeleteTag(){
        tag.createTag("createhhhh",deleteTagId);
        tag.deleteTag(deleteTagId)
            .then()
//                .log().all()
                .body("errcode",equalTo(0))
                .body("errmsg",equalTo("deleted"));
        tag.getTagList()
            .then()
//                .log().all()
                .body("taglist.tagid",not(hasItem(deleteTagId)));
    }


}
