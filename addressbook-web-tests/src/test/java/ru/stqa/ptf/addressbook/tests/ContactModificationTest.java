package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTest extends TestBase {

  @Test
  public void testContactModification() throws Exception {
    app.getNavigationHelper().gotoContacts();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData(null,"Ivan", "Ivanovich", "Ivanov", "Vanja", "NewTitle", "Company", "Saint-Petersburg, Nevsky av., 1", "Ivan", "876876676", "8798788", "9879898", "vanja@mail.ru", "Ivanov@mail.ru", "Iv_iv@mail.ru", "7666766", "1", "Prosvetschenija, 1", "777789", "NewPerson"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData(null,"Ivan", "Ivanovich", "Ivanov", "Vanja", "NewTitle", "Company", "Saint-Petersburg, Nevsky av., 1", "Ivan", "876876676", "8798788", "9879898", "vanja@mail.ru", "Ivanov@mail.ru", "Iv_iv@mail.ru", "7666766", "1", "Prosvetschenija, 1", "777789", "NewPerson");
    app.getContactHelper().fillContactData(contact, false);
    app.getContactHelper().modificateContact(before.size() - 1);
    app.getContactHelper().fillContactData(contact, false);
    app.getContactHelper().submitContactModification();
    List<ContactData> after = app.getContactHelper().getContactList();
//    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
