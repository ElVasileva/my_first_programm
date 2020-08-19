package mantis.appmanager;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(ApplicationManager app) {
    super(app);
  }

  public void groupPage() {
    if (isElementPresent(By.tagName("h1"))
        && wd.findElement(By.tagName("h1")).getText().equals("Groups")
        && isElementPresent(By.name("new"))) {
      return;
    }
    click(By.linkText("groups"));
  }


  public void contacts() {
    click(By.id("content"));
  }

  public void homePage() {
    if (isElementPresent(By.id("maintable"))) {
      return;
    }
    click(By.linkText("home"));
  }

  public void managePage() {
    click(By.cssSelector("a[href='/mantisbt-2.24.1/manage_overview_page.php']"));
  }

  public void manageUsersTab() {
    click(By.cssSelector("a[href='/mantisbt-2.24.1/manage_user_page.php']"));
  }

}
