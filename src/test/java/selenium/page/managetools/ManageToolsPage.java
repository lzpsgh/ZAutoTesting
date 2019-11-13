package selenium.page.managetools;

import org.junit.Test;
import org.openqa.selenium.By;
import selenium.page.BasePage;

public class ManageToolsPage extends BasePage {

    public MaterialLibPage toMediaLibrary(){
        findElement(By.cssSelector(".ww_icon_AppMaterialBig")).click();
        return new MaterialLibPage();
    }

}
