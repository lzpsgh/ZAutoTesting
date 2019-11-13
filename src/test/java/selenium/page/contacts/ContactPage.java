package selenium.page.contacts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import selenium.page.BasePage;

import java.util.List;

public class ContactPage extends BasePage {
    public ContactPage add(String username, String id, String phone){
        //最好先clear再sendkeys
        findElement(By.name("username")).sendKeys(username);
        findElement(By.name("acctid")).sendKeys(id);
        findElement(By.name("mobile")).sendKeys(phone);
        findElement(By.linkText("保存")).click();
        return this;
    }

    public ContactPage delete(String keyword){
        findElement(By.name("memberSearchInput")).clear();
        findElement(By.name("memberSearchInput")).sendKeys(keyword);
        //
        try {
            waitClickable(By.linkText("删除"));
        }catch (Exception exp){
            System.out.println("not found");
            return this;
        }

        findElement(By.linkText("删除")).click();
        findElement(By.linkText("确认")).click();
        findElement(By.id("clearMemberSearchInput")).click();

        return this;
    }

    public void deleteCurrentPage(){
        try {
            Thread.sleep(2000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        waitClickable(By.cssSelector(".ww_checkbox"));
        List<WebElement> elementList = driver.findElements(By.cssSelector(".ww_checkbox"));
        for (int i=1;i<elementList.size();i++){
            System.out.println(i);
            elementList.get(i).click();
            try {
                Thread.sleep(500);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    public void importFromFile(){
        findElement(By.partialLinkText("批量导入/导出")).click();
        findElement(By.linkText("文件导入")).click();
        findElement(By.id("js_upload_file_input"),0).sendKeys("/Users/");
        findElement(By.id("submit_csv")).click();
        findElement(By.linkText("完成")).click();
    }

    public void list(){
    }

    public DepartmentPage toDepartmentPage(){
        return new DepartmentPage();
    }

}
