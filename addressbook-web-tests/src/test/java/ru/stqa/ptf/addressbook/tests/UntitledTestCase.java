package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class UntitledTestCase {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testUntitledTestCase() throws Exception {
    driver.get("http://localhost/addressbook/");
    driver.findElement(By.xpath("//td/input")).click();
    driver.findElement(By.xpath("//table[@id='maintable']/tbody/tr[3]/td/input")).click();
    driver.findElement(By.cssSelector("#23")).click();
    driver.findElement(By.xpath("//tr[5]/td/input")).click();
    driver.findElement(By.xpath("//table[@id='maintable']/tbody/tr[6]/td/input")).click();
    new Select(driver.findElement(By.xpath("//table[@id='maintable']/tbody/tr[7]/td/input"))).selectByVisibleText("");
    driver.findElement(By.id("33")).click();
    driver.findElement(By.id("34")).click();
    driver.findElement(By.id("35")).click();
    driver.findElement(By.id("36")).click();




    driver.get("http://localhost/addressbook/");
    driver.findElement(By.xpath("//table[@id='maintable']/tbody/tr[11]/td[2]")).click();
    driver.findElement(By.xpath("//table[@id='maintable']/tbody/tr[10]/td[2]")).click();
    driver.findElement(By.id("35")).click();
    driver.findElement(By.xpath("//table[@id='maintable']/tbody/tr[10]/td[2]")).click();
    driver.findElement(By.xpath("//table[@id='maintable']/tbody/tr[6]/td")).click();
    driver.findElement(By.xpath("//table[@id='maintable']/tbody/tr[2]/td")).click();
    driver.findElement(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[3]")).click();
    driver.findElement(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[4]")).click();
    driver.findElement(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[5]")).click();
    driver.findElement(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[6]")).click();
    driver.findElement(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[7]")).click();
    driver.findElement(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]")).click();
    driver.findElement(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[9]")).click();
    driver.findElement(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[10]")).click();
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }



}
