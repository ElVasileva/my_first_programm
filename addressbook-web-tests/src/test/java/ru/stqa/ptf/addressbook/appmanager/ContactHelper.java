package ru.stqa.ptf.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.ptf.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToContactPage() {
        click(By.linkText("home page"));
    }

    public void initNewContact() {
        click(By.linkText("add new"));
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
        bday("bday", contactData.getbDDay(), "(//option[@value='7'])[2]");
        bmonth("bmonth", contactData.getbDMonht(), "//option[@value='June']");
        type(By.name("byear"), contactData.getbDYear());
        aday("aday", contactData.getAnnyversaryDay(), "(//option[@value='7'])[2]");
        amonth("amonth", contactData.getAnnyversaryMonth(), "(//option[@value='June'])[2]");
        type(By.name("ayear"), contactData.getAnnyversaryYear());
        group("new_group", contactData.getContactGroup(), "(//option[@value='7'])[2]");
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
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

}
