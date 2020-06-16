package ru.stqa.ptf.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.ptf.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToContactPage() {
    click(By.linkText("home"));
  }

  public void initNewContact() {
    click(By.linkText("add new"));
  }

  public void fillContactData(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("middlename"), contactData.getMiddleName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("nickname"), contactData.getNickName());
    type(By.name("title"), contactData.getTittle());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("fax"), contactData.getFax());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    type(By.name("homepage"), contactData.getHomePage());
    type(By.name("address2"), contactData.getAddress2());
    type(By.name("phone2"), contactData.getPhone2());
    type(By.name("notes"), contactData.getNotes());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  private void amonth(String amonth, String annyversaryMonth, String s) {
    wd.findElement(By.name(amonth)).click();
    new Select(wd.findElement(By.name(amonth))).selectByVisibleText(annyversaryMonth);
    wd.findElement(By.xpath(s)).click();
  }

  private void aday(String aday, String annyversaryDay, String s) {
    wd.findElement(By.name(aday)).click();
    new Select(wd.findElement(By.name(aday))).selectByVisibleText(annyversaryDay);
    wd.findElement(By.xpath(s)).click();
  }

  private void bmonth(String bmonth, String s, String s2) {
    wd.findElement(By.name(bmonth)).click();
    new Select(wd.findElement(By.name(bmonth))).selectByVisibleText(s);
    wd.findElement(By.xpath(s2)).click();
  }

  private void bday(String bday, String s, String s2) {
    wd.findElement(By.name(bday)).click();
    new Select(wd.findElement(By.name(bday))).selectByVisibleText(s);
    wd.findElement(By.xpath(s2)).click();
  }

  private void group(String new_group, String contactGroup, String s) {
    wd.findElement(By.name(new_group)).click();
    new Select(wd.findElement(By.name(new_group))).selectByVisibleText(contactGroup);
    wd.findElement(By.xpath(s)).click();
  }


  public void submitContactCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void modificateContact(int index) {
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void deleteContacts() {
    wd.findElement(By.xpath("//input[@value='Delete']")).click();
  }

  public void deleteContact() {
    wd.findElement(By.xpath("(//input[@name='update'])[3]")).click();
  }

  public void acceptDeletion() {
    assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
  }

  private boolean acceptNextAlert = true;

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = wd.switchTo().alert();
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

  public void createContact(ContactData contact, boolean b) {
    initNewContact();
    fillContactData(contact, true);
    submitContactCreation();
    returnToContactPage();

  }

  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//img[@alt='Edit']"));
  }

  public int getContactCount() {
    return wd.findElements(By.xpath("//img[@alt='Edit']")).size();
  }


  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> rows = wd.findElements(By.name("entry"));
    for (WebElement row : rows) {
      List<WebElement> cells = row.findElements(By.cssSelector("td"));
      String lastName = cells.get(1).getText();
      String firstName = cells.get(2).getText();
      String address = cells.get(3).getText();
      int id = Integer.parseInt(row.findElement(By.cssSelector("input")).getAttribute("id"));
      ContactData contact = new ContactData(id, firstName,lastName, address);
      contacts.add(contact);
    }
    return contacts;
  }

}
