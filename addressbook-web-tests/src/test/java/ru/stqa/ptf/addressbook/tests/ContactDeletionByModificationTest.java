package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionByModificationTest extends TestBase {

  @Test
  public void testContactDeletionByModification() throws Exception {
    app.goTo().contacts();
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData("Ivan", "Ivanovich", "Ivanov", "Vanja", "NewTitle", "Company", "Saint-Petersburg, Nevsky av., 1", "Ivan", "876876676", "8798788", "9879898", "vanja@mail.ru", "Ivanov@mail.ru", "Iv_iv@mail.ru", "7666766", "1", "Prosvetschenija, 1", "777789", "NewPerson"), true);
    }
    List<ContactData> before = app.contact().list();
    app.contact().modificate(before.size() - 1);
    app.contact().deleteContact();
    app.contact().returnToContactPage();
    List<ContactData> after = app.contact().list();

    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);

  }
}
