package selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class MediaLibrary extends BasePage {

    public MediaLibrary uploadImg(String imgPath) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //细节1 不用sleep，可以考虑检测右侧布局加载完成后再点击ww_icon_GrayPic

        findElement(By.cssSelector(".ww_icon_GrayPic")).click();
        findElement(By.cssSelector(".js_upload_file_selector")).click();
        findElement(By.cssSelector(".material_upload_input"),0).sendKeys(imgPath);
//        findElement(By.id("js_upload_input"), 0).sendKeys(imgPath);
        if(findVisibleElement(By.cssSelector(".material_pic_list_item")).isDisplayed()){
            System.out.println("图片已上传");

            //细节2 出现"图片正在上传"的弹窗后会导致无法点击"完成"按钮，但用例依然能通过？
            findElement(By.linkText("完成")).click();
            System.out.println("用例完成");
        }
        return this;
    }

}
