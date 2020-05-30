package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase{

    @Test
    public void testContactModification() throws Exception {
        app.getNavigationHelper().gotoContacts();
        app.getContactHelper().modificateContact();
        app.getContactHelper().fillContactData(new ContactData ("Ivan", "Ivanovich", "Ivanov", "Vanja", "NewTitle", "Company", "Saint-Petersburg, Nevsky av., 1", "Ivan", "Ivanovich", "Ivanov", "Vanja", "NewTitle", "Company", "Saint-Petersburg, Nevsky av., 1", "7666766", "45565776", "777788", "777789", "vanja@mail.ru", "Ivanov@mail.ru", "Iv_iv@mail.ru", "ivanovPage", "test1", "Prosvesheniya av., 1", "1", "NewPerson"), false);
        app.getContactHelper().submitContactModification();
    }
}
