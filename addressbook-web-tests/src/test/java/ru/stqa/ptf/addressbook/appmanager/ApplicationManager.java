package ru.stqa.ptf.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  private Properties properties;
  public WebDriver wd;

  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private ContactHelper contactHelper;
  private DbHelper dbHelper;
  private String browser;

  public ApplicationManager(String browser) throws IOException {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

    dbHelper = new DbHelper();

    if (browser.equals(BrowserType.FIREFOX)) {
      wd = new FirefoxDriver();
    } else if (browser.equals(BrowserType.CHROME)) {
      wd = new ChromeDriver();
    } else if (browser.equals(BrowserType.IE)) {
      wd = new InternetExplorerDriver();
    }

      wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
      wd.get(properties.getProperty("web.baseUrl"));
      groupHelper = new GroupHelper(wd);
      navigationHelper = new NavigationHelper(wd);
      sessionHelper = new SessionHelper(wd);
      contactHelper = new ContactHelper(wd);
      sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
    }


    public void login (String username, String password){
      wd.findElement(By.name("user")).click();
      wd.findElement(By.name("user")).clear();
      wd.findElement(By.name("user")).sendKeys(username);
      wd.findElement(By.name("pass")).click();
      wd.findElement(By.name("pass")).clear();
      wd.findElement(By.name("pass")).sendKeys(password);
      wd.findElement(By.xpath("//input[@value='Login']")).click();
    }

    public void stop () {
      wd.quit();
    }

    public void logout () {
      wd.findElement(By.linkText("Logout")).click();
      wd.findElement(By.xpath("//html")).click();
    }

    public GroupHelper group() {
      return groupHelper;
    }

    public NavigationHelper goTo() {
      return navigationHelper;
    }

    public ContactHelper contact() {
      return contactHelper;
    }

    public DbHelper db() {return dbHelper; }
  }
