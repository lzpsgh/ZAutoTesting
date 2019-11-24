package selenium.data;

import org.openqa.selenium.By;

public final class ByObj {
    //TODO 转成大写
    public static final By addBtn = By.cssSelector(".member_colLeft_top_addBtn");
    public static final By addDepart = By.linkText("添加部门");
    public static final By departNameInput = By.cssSelector(".inputDlg_item>input");
    public static final By chooseParentDepartBtn = By.cssSelector(".js_parent_party_name");
    public static final By chooseParentDepart = By.xpath("(//*[text()='demo'])[2]");
    public static final By submit = By.linkText("确定");
    public static final By expandBtn = By.xpath("(//*[text()='demo'])[1]/preceding-sibling::i");
    public static final By deleteBtn = By.linkText("删除");
    public static final By upBtn = By.linkText("上移");
    public static final By reNameDepart = By.cssSelector(".js_rename_input");
    public static final By save = By.linkText("保存");
    // DepartPage 部门
    public static final By ALL_DEPS = By.cssSelector("[class=jstree-children]>li>a");
    public static final By ALL_SUB_DEPS = By.xpath("(//*[@class='jstree-children'])[2]/li/a");
    public static final By DEP_BTN_UP = By.linkText("上移");
    public static final By DEP_SUBMIT = By.linkText("确定");
    public static final By DEP_BTN_DELETE = By.linkText("删除");
    public static final By DEP_BTN_RENAME = By.cssSelector(".js_rename_input");
    public static final By DEP_BTN_SAVE = By.linkText("保存");
}