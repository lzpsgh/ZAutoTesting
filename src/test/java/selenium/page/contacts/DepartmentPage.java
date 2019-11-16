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
    public List<String> getAllDepartment(String depType){
        System.out.println("getAll");
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
        System.out.println("add");
        findClick(ByObj.addBtn);
        findClick(ByObj.addDepart);
        findSendKeys(ByObj.departNameInput,depNew);
        findClick(ByObj.chooseParentDepartBtn);
        findElement(By.xpath("(//a[text()='"+depTotal+"'])[2]")).click();
        findClick(ByObj.submit);
        return this;
    }
    public DepartmentPage addSubDepartment(){
        System.out.println("addsub");
        return this;
    }
    public DepartmentPage delDepartment(String dep){
        System.out.println("del");
        return this;
    }
    public DepartmentPage delSubDepartment(){
        System.out.println("delsub");
        return this;
    }
    public DepartmentPage moveupDepartment(){
        System.out.println("moveup");
        return this;
    }
    public DepartmentPage renameDepartment(){
        System.out.println("rename");
        return this;
    }


}
