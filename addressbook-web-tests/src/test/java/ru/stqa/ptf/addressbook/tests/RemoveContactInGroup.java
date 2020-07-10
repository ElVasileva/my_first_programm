package ru.stqa.ptf.addressbook.tests;

import org.hibernate.SessionFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.Contacts;
import ru.stqa.ptf.addressbook.model.GroupData;
import ru.stqa.ptf.addressbook.model.Groups;

import static org.testng.Assert.assertEquals;

public class RemoveContactInGroup extends TestBase {

  private SessionFactory sessionFactory;

  @BeforeMethod
  public void ensurePreconditionsGroup() {
    Groups groups = app.db().groups();
    Contacts contacts = app.db().contacts();
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }

    if (app.db().contacts().size() == 0) {
      app.goTo().contacts();
      app.contact().create(new ContactData()
          .withFirstName("Ivan").withMiddleName("Ivanovich").withLastName("Ivanov")
          .withNickName("Vanja").withTittle("NewTitle").withCompany("Company").withAddress("Saint-Petersburg, Nevsky av., 1")
          .withHomePhone("876876676").withWorkPhone("8798788").withFax("9879898").withEmail("vanja@mail.ru")
          .withEmail2("Ivanov@mail.ru").withEmail3("Iv_iv@mail.ru").withHomePage("VanjaPage")
          .withMobilePhone("7666766").inGroup(groups.iterator().next()).withAddress2("Prosvetschenija, 1").withPhone2("777789")
          .withNotes("NewPerson"), true);
    }
  }

  @Test
  public void testRemoveContactInGroup() throws Exception {
    Groups groups = app.db().groups();
    ContactData selectedContact = app.db().contacts().iterator().next();
    GroupData selectedGroup = app.db().groups().iterator().next();
    Groups groupsBefore = selectedContact.getGroups();

    if (selectedContact.getGroups().contains(selectedGroup)) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }

    app.goTo().contacts();
    app.contact().addContactToGroup(selectedContact, selectedGroup);
    Groups groupsAfter = selectedContact.getGroups();
    assertEquals(groupsAfter.size(), groupsBefore.size() + 1);

    app.contact().goToContactGroup();
    app.contact().removeContactFromGroup(selectedContact, selectedGroup);
    Groups groupsAfterRemove = selectedContact.getGroups();
    assertEquals(groupsAfterRemove.size(), groupsAfter.size() - 1);
    }

  }
