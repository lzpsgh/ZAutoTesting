package selenium.page.contacts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import selenium.data.ByObj;
import selenium.page.BasePage;

import java.util.ArrayList;
import java.util.List;


public class DepartmentPage extends BasePage {
    /*
    获取所有部门信息
    */
    public List<String> getAllDepartmentName(String depType){
        List<String> list = new ArrayList<String>();
//        waitVisible(By.id("js_tips"));
        waitSleep(1);
        if (depType.equals("Depart")){
            //TODO 把所有By定位器统一到ByObj类中作为全局静态变量来调用
            List<WebElement> webElements = findElements(ByObj.ALL_DEPS);
            for (WebElement webElement : webElements) {
                list.add(webElement.getText());
            }
        }else if (depType.equals("SubDepart")){
            List<WebElement> webElements = findElements(ByObj.ALL_SUB_DEPS);
            for (WebElement webElement : webElements) {
                list.add(webElement.getText());
            }
        }
        return list;
    }

    public DepartmentPage addDepartment(String depNew,String depTotal){
        findClick(By.cssSelector(".member_colLeft_top_addBtn"));
        findClick(By.linkText("添加部门"));
        findSendKeys(By.cssSelector(".inputDlg_item>input"),depNew);
        findClick(By.cssSelector(".js_parent_party_name"));
        findElement(By.xpath("(//a[text()='"+depTotal+"'])[2]")).click();
        findClick(By.linkText("确定"));
        return this;
    }


    //TODO 待测试
    public DepartmentPage delDepartment(String departName){
        waitSleep(1);
        findElement(By.xpath("(//*[text()='"+departName+"'])[1]")).click(); //点击部门
        findElement(By.xpath("(//*[text()='"+departName+"'])/span")).click(); //点击选项
        waitSleep(1);
        findClick(By.linkText("删除"),10);
        findClick(By.linkText("确定"));
        return this;
    }
    public DepartmentPage moveupDepartment(String departName){
        waitSleep(1);
        findElement(By.xpath("(//*[text()='"+departName+"'])[1]")).click(); //点击部门
        findElement(By.xpath("(//*[text()='"+departName+"'])/span")).click(); //点击选项
        findClick(By.linkText("上移"));
        return this;
    }
    public DepartmentPage renameDepartment(String departName,String departNewName){
        waitSleep(1);
        findElement(By.xpath("(//*[text()='"+departName+"'])[1]")).click(); //点击部门
        findElement(By.xpath("(//*[text()='"+departName+"'])/span")).click(); //点击选项
        waitSleep(1);
        findElement(By.linkText("修改名称")).click();
        findSendKeys(By.cssSelector(".js_rename_input"),departNewName);
        findClick(By.linkText("保存"));
        return this;
    }


}
