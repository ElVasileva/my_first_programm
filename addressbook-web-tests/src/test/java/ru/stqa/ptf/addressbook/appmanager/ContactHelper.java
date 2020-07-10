package ru.stqa.ptf.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.Contacts;
import ru.stqa.ptf.addressbook.model.GroupData;

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
    attach(By.name("photo"), contactData.getPhoto());

    if (creation) {
      if (contactData.getGroups().size() > 0) {
        Assert.assertTrue(contactData.getGroups().size() == 1);
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
      }
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

//  public void modify(ContactData contact) {
//    wd.findElement(By.cssSelector("img[alt=\"Edit\"]")).click();
//  }

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
    contactCache = null;
    returnToContactPage();

  }

  public void deleteContact(ContactData contact) {
    selectById(contact.getId());
    deleteContacts();
    acceptDeletion();
    contactCache = null;
    returnToContactPage();
  }

  public void modifyContact(ContactData contact) {
    selectById(contact.getId());
    initContactModificationById(contact.getId());
    fillContactData(contact, false);
    submitContactModification();
    contactCache = null;
    returnToContactPage();
  }

  public void modifyContactById(ContactData contact) {
    selectById(contact.getId());
    fillContactData(contact, false);
    submitContactModification();
    contactCache = null;
    returnToContactPage();
  }


  public void selectById(int id) {
    wd.findElement(By.cssSelector("input[id='" + id + "']")).click();
  }

  public void selectContact(ContactData contact) {
    selectById(contact.getId());
  }

  public int count() {
    return wd.findElements(By.xpath("//img[@alt='Edit']")).size();
  }

  private Contacts contactCache = null;


  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }

    contactCache = new Contacts();
    List<WebElement> rows = wd.findElements(By.name("entry"));
    for (WebElement row : rows) {
      List<WebElement> cells = row.findElements(By.cssSelector("td"));
      String lastName = cells.get(1).getText();
      String firstName = cells.get(2).getText();
      String address = cells.get(3).getText();
      String allEmails = cells.get(4).getText();
      String allPhones = cells.get(5).getText();
      int id = Integer.parseInt(row.findElement(By.cssSelector("input")).getAttribute("id"));
      contactCache.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName)
          .withAddress(address).withAllPhones(allPhones).withAllEmails(allEmails));
    }
    return new Contacts(contactCache);
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String phone2 = wd.findElement(By.name("phone2")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname)
        .withAddress(address).withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
        .withPhone2(phone2).withEmail(email).withEmail2(email2).withEmail3(email3);


  }

  private void initContactModificationById(int id) {
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value= '%s']", id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();
  }

  public void addContactToGroup(ContactData contact, GroupData group) {
    selectById(contact.getId());
    selectById(group.getId());
    wd.findElement(By.name("add to")).click();
    returnGroupToAdd();
  }

  public void removeContactFromGroup(ContactData contact, GroupData group) {
    goToGroupContactsPage(group.getId());
    selectById(contact.getId());
    wd.findElement(By.name("remove")).click();
    returnGroupToAdd();
  }


  public void returnGroupToAdd() {
    if (isElementPresent(By.id("maintable"))) {
      return;
    }
    click(By.xpath("//a[contains(text(),\"group page\")]"));
  }

  public void goToContactGroup() {
    click(By.xpath("//div[@id='content']/div/i/a"));
  }

  private void selectGroupById(int groupid) {
    new Select(wd.findElement(By.name("to_group"))).selectByValue(String.valueOf(groupid));

  }


  public void goToGroupContactsPage(int groupid) {
    if (isElementPresent(By.id("maintable")) && isElementPresent(By.name("remove"))) {
      return;
    }
    new Select(wd.findElement(By.name("group"))).selectByValue(String.valueOf(groupid));
  }

}
