package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.GroupData;

import java.util.List;

public class ContactModificationTest extends TestBase {

  @Test
  public void testContactModification() throws Exception {
    app.getNavigationHelper().gotoContacts();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("test", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().modificateContact(1);
    app.getContactHelper().fillContactData(new ContactData("Ivan", "Ivanovich", "Ivanov", "Vanja", "NewTitle", "Company", "Saint-Petersburg, Nevsky av., 1", "Ivan", "876876676", "8798788", "9879898", "vanja@mail.ru", "Ivanov@mail.ru", "Iv_iv@mail.ru", "7666766", "1", "Prosvetschenija, 1", "777789", "NewPerson"), false);
    app.getContactHelper().submitContactModification();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());
  }
}
