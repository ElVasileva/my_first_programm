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
    app.getContactHelper().createContact(new ContactData("Ivan", "Ivanovich", "Ivanov", "Vanja", "NewTitle", "Company", "Saint-Petersburg, Nevsky av., 1", "7666766", "45565776", "777788", "777789", "vanja@mail.ru", "Ivanov@mail.ru", "Iv_iv@mail.ru", "ivanovPage", "new_group", "Prosvesheniya av., 1", "1", "NewPerson"));
    List<ContactData> after = app.getContactHelper().getContactList();
//    Assert.assertEquals(after.size(), before.size() + 1);
  }
}
