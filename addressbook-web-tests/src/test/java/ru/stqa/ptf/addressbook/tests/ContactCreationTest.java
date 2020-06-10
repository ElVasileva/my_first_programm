package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.GroupData;

import java.util.List;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("new_group", null, null));
    }
    app.getNavigationHelper().gotoContacts();
    List<ContactData> before = app.getContactHelper().getContactList();
//    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().createContact(new ContactData(null,"Ivan", "Ivanovich", "Ivanov", "Vanja", "NewTitle", "Company", "Saint-Petersburg, Nevsky av., 1", "Ivan", "876876676", "8798788", "9879898", "vanja@mail.ru", "Ivanov@mail.ru", "Iv_iv@mail.ru", "7666766", "1", "Prosvetschenija, 1", "777789", "NewPerson"));
    List<ContactData> after = app.getContactHelper().getContactList();
//    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after.size(), before.size() + 1);
//    Assert.assertEquals(after, before + 1);
  }
}
