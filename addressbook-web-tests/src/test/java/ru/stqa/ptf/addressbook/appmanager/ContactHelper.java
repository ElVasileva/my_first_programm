package ru.stqa.ptf.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.ptf.addressbook.model.ContactData;

public class ContactHelper extends HelperBase{

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


//        bday(contactData, By.name("bday"));
//        wd.findElement(By.name("bmonth")).click();
//        new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(contactData.getbDMonht());
//        wd.findElement(By.xpath("//option[@value='June']")).click();
//        wd.findElement(By.name("byear")).click();
//        wd.findElement(By.name("byear")).clear();
//        wd.findElement(By.name("byear")).sendKeys(contactData.getbDYear());
//        wd.findElement(By.name("aday")).click();
//        new Select(wd.findElement(By.name("aday"))).selectByVisibleText(contactData.getAnnyversaryDay());
//        wd.findElement(By.xpath("(//option[@value='7'])[2]")).click();
//        wd.findElement(By.name("amonth")).click();
//        new Select(wd.findElement(By.name("amonth"))).selectByVisibleText(contactData.getAnnyversaryMonth());
//        wd.findElement(By.xpath("(//option[@value='June'])[2]")).click();

        type(By.name("ayear"), contactData.getAnnyversaryYear());
        type(By.name("email3"), contactData.getEmail3());
        type(By.name("homepage"), contactData.getHomePage());

        wd.findElement(By.name("ayear")).click();
        wd.findElement(By.name("ayear")).clear();
        wd.findElement(By.name("ayear")).sendKeys(contactData.getAnnyversaryYear());
        wd.findElement(By.name("new_group")).click();
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getContactGroup());
        wd.findElement(By.xpath("(//option[@value='7'])[2]")).click();

        type(By.name("address2"), contactData.getAddress2());
        type(By.name("phone2"), contactData.getPhone2());
        type(By.name("notes"), contactData.getNotes());
    }

    private void bday(ContactData contactData, By bday) {
        wd.findElement(bday).click();
        new Select(wd.findElement(bday)).selectByVisibleText(contactData.getbDDay());
        wd.findElement(By.xpath("//option[@value='7']")).click();
    }


    public void submitContactCreation() {
        wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
    }

}
