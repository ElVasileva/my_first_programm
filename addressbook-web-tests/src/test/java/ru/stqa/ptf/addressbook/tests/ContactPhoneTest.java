package ru.stqa.ptf.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactPhoneTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().contacts();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
          .withFirstName("Ivan").withMiddleName("Ivanovich").withLastName("Ivanov")
          .withNickName("Vanja").withTittle("NewTitle").withCompany("Company").withAddress("Saint-Petersburg, Nevsky av., 1")
          .withHomePhone("876876676").withWorkPhone("8798788").withFax("9879898").withEmail("vanja@mail.ru")
          .withEmail2("Ivanov@mail.ru").withEmail3("Iv_iv@mail.ru").withHomePage("VanjaPage")
          .withMobilePhone("7666766").withGroup("new_group").withAddress2("Prosvetschenija, 1").withPhone2("777789")
          .withNotes("NewPerson"), true);
    }
  }

  @Test
  public void testContactPhones() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getHomePhone(), equalTo(cleaned(contactInfoFromEditForm.getHomePhone())));
    assertThat(contact.getMobilePhone(), equalTo(cleaned(contactInfoFromEditForm.getMobilePhone())));
    assertThat(contact.getWorkPhone(), equalTo(cleaned(contactInfoFromEditForm.getWorkPhone())));

  }

  public String cleaned (String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }
}
