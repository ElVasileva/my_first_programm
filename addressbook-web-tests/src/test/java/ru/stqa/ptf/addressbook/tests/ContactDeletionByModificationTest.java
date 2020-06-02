package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;

public class ContactDeletionByModificationTest extends TestBase {

  @Test
  public void testContactDeletionByModification() throws Exception {
    app.getNavigationHelper().gotoContacts();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("test", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null));
    }
    app.getContactHelper().modificateContact();
    app.getContactHelper().deleteContact();
  }
}
