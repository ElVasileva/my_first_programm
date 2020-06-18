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
      app.contact().create(new ContactData()
          .withFirstName("Ivan").withMiddleName("Ivanovich").withLastName("Ivanov")
          .withNickName("Vanja").withTittle("NewTitle").withCompany("Company").withAddress("Saint-Petersburg, Nevsky av., 1")
          .withHomePhone("876876676").withWorkPhone("8798788").withFax("9879898").withEmail("vanja@mail.ru")
          .withEmail2("Ivanov@mail.ru").withEmail3("Iv_iv@mail.ru").withHomePage("VanjaPage")
          .withMobilePhone("7666766").withGroup("new_group").withAddress2("Prosvetschenija, 1").withPhone2("777789")
          .withNotes("NewPerson"), true);
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
