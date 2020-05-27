package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactDataByModification;

public class ContactModificationTest extends TestBase{

    @Test
    public void testContactModification() throws Exception {
        app.getNavigationHelper().gotoContacts();
        app.getContactHelper().modificateContact();
        app.getContactHelper().fillContactDataByModification(new ContactDataByModification("Ivan", "Ivanovich", "Ivanov", "Vanja", "NewTitle", "Company", "Saint-Petersburg, Nevsky av., 1", "7666766", "45565776", "777788", "777789", "vanja@mail.ru", "Ivanov@mail.ru", "Iv_iv@mail.ru", "ivanovPage", "Prosvesheniya av., 1", "1", "NewPerson"));
        app.getContactHelper().submitContactModification();
    }
}
