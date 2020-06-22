package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.Contacts;
import ru.stqa.ptf.addressbook.model.GroupData;
import ru.stqa.ptf.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("new_group"));
    }
  }

  @Test
  public void testContactCreation() throws Exception {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/Screenshot_9.png");
    ContactData contact = new ContactData()
        .withFirstName("Ivan").withMiddleName("Ivanovich").withLastName("Ivanov")
        .withNickName("Vanja").withTittle("NewTitle").withCompany("Company").withAddress("Saint-Petersburg, Nevsky av., 1")
        .withHomePhone("876876676").withWorkPhone("8798788").withFax("9879898").withEmail("vanja@mail.ru")
        .withEmail2("Ivanov@mail.ru").withEmail3("Iv_iv@mail.ru").withHomePage("VanjaPage")
        .withMobilePhone("7666766").withGroup("new_group").withAddress2("Prosvetschenija, 1").withPhone2("777789")
        .withNotes("NewPerson").withPhoto(photo);
    app.contact().create(contact, true);
    app.contact().returnToContactPage();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(
        before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test
  public void testCurrentDir() {
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/Screenshot_9.png");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }
}
