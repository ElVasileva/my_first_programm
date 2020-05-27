package ru.stqa.ptf.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void gotoGroupPage() {
        click(By.id("1"), By.linkText("groups"));
    }

    public void gotoContacts() {
        click(By.id("1"), By.id("content"));
    }
}
