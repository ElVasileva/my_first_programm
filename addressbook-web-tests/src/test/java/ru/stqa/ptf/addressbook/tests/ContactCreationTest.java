package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getNavigationHelper().gotoGroupPage();
      app.getGroupHelper().createGroup(new GroupData("new_group", null, null));
    }
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData("Ivan", "Ivanovich", "Ivanov", "Vanja", "NewTitle", "Company", "Saint-Petersburg, Nevsky av., 1", "Ivan", "876876676", "8798788", "9879898", "vanja@mail.ru", "Ivanov@mail.ru", "Iv_iv@mail.ru", "7666766", "new_group", "Prosvetschenija, 1", "777789", "NewPerson");
    app.getContactHelper().createContact(contact, true);
    app.getContactHelper().returnToContactPage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
