package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTest extends TestBase {

  @Test
  public void testContactDeletion() throws Exception {
    app.getNavigationHelper().gotoContacts();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData(null,"Ivan", "Ivanovich", "Ivanov", "Vanja", "NewTitle", "Company", "Saint-Petersburg, Nevsky av., 1", "Ivan", "876876676", "8798788", "9879898", "vanja@mail.ru", "Ivanov@mail.ru", "Iv_iv@mail.ru", "7666766", "1", "Prosvetschenija, 1", "777789", "NewPerson"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteContacts();
    app.getContactHelper().acceptDeletion();
    List<ContactData> after = app.getContactHelper().getContactList();
//    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    for (int i = 0; 1 < after.size() - 1; i++) {
      Assert.assertEquals(before.get(i), after.get(i));
    }
  }
}
