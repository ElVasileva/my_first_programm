package mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class RegistrationHelper extends HelperBase{

  public RegistrationHelper(ApplicationManager app) {
    super(app);
  }

  public void start(String username, String email) {
    wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
    type(By.name("username"), username);
    type(By.name("email"), email);
    click(By.cssSelector("[type=submit]"));
  }

  public void finish(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("button[type='submit']"));
  }

  public void login(String username, String password) throws InterruptedException {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), username);
    click(By.cssSelector("[type=submit]"));
    type(By.name("password"), password);
    click(By.cssSelector("[type=submit]"));

  }

  public HashMap<String, String> selectUser() {
    HashMap<String, String> result = new HashMap<>();
    app.getDriver().findElements(By.cssSelector("tbody [href]"))
        .stream().filter(u -> !u.getText().equals("administrator")).collect(Collectors.toList())
        .get(0).click();
    result.put("user", app.getDriver().findElement(By.name("username")).getAttribute("value"));
    result.put("email", app.getDriver().findElement(By.name("email")).getAttribute("value"));
    app.getDriver().findElement(By.cssSelector("[value='Сбросить пароль']")).click();
    return result;
  }

  public void resetPassword() {
    click(By.xpath("//a[contains(text(),\"Сбросить пароль\")]"));

  }
}

