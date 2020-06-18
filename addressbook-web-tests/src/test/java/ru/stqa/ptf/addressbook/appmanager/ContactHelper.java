package ru.stqa.ptf.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.ptf.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

  public void submitContactCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void modify(ContactData contact) {
    wd.findElement(By.xpath("//img[@alt='Edit']")).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void deleteContacts() {
    wd.findElement(By.xpath("//input[@value='Delete']")).click();
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

  public void create(ContactData contact, boolean b) {
    initNewContact();
    fillContactData(contact, true);
    submitContactCreation();
    returnToContactPage();

  }

  public void deleteContact(ContactData contact) {
    selectById(contact.getId());
    deleteContacts();
    acceptDeletion();
    returnToContactPage();
  }

  public void select(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  private void selectById(int id) {
    wd.findElement(By.cssSelector("input[id='" + id + "']")).click();
  }

  public Set<ContactData> all() {
    Set<ContactData> contacts = new HashSet<ContactData>();
    List<WebElement> rows = wd.findElements(By.name("entry"));
    for (WebElement row : rows) {
      List<WebElement> cells = row.findElements(By.cssSelector("td"));
      String lastName = cells.get(1).getText();
      String firstName = cells.get(2).getText();
      String address = cells.get(3).getText();
      int id = Integer.parseInt(row.findElement(By.cssSelector("input")).getAttribute("id"));
      contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName)
          .withAddress(address));
    }
    return contacts;
  }

}
