package ru.stqa.ptf.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.ptf.addressbook.model.ContactData;

import static org.testng.Assert.assertTrue;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToContactPage() {
        click(By.id("1"), By.linkText("home page"));
    }

    public void initNewContact() {
        click(By.id("1"), By.linkText("add new"));
    }

    public void fillContactData(ContactData contactData) {
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
        click(By.id("1"), By.xpath("(//input[@name='submit'])[2]"));
    }

    public void modificateContact() {
        click(By.id("1"), By.xpath("//img[@alt='Edit']"));
    }

    public void submitContactModification() {
        click(By.xpath("//img[@alt='Edit']"), By.name("update"));
    }

    public void selectContact() {
        click(By.id("1"), By.name("selected[]"));
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
}
