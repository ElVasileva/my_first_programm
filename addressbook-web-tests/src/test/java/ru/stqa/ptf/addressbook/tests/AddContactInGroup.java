package ru.stqa.ptf.addressbook.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.Contacts;
import ru.stqa.ptf.addressbook.model.GroupData;
import ru.stqa.ptf.addressbook.model.Groups;

import java.util.List;

import static java.util.stream.Collectors.toSet;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class AddContactInGroup extends TestBase {

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

  @Ignore
  @Test
  public void testAddContactInGroup() throws Exception {
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


//    Groups groupsBefore = selectedContact.getGroupsCollection();
//    selectedContact.getGroupsCollection();


//    Groups groupsBefore = app.db().contacts().getGroups();
//    contacts.getGroups();

//    if (selectedContact.getGroups().stream().collect(selectedGroup)) {
//      app.goTo().groupPage();
//      app.group().create(new GroupData().withName("test1"));
//    }
//    if (contacts.getGroups().stream().collect(selectedGroup)) {
//      app.goTo().groupPage();
//      app.group().create(new GroupData().withName("test1"));
//    }


//    app.contact().selectContact(selectedContact);
//    app.contact().addContactToGroup(selectedContact, selectedGroup);


//    Groups groupsAfter = app.db().contacts().getGroups();
//    assertEquals(groupsAfter.size(), groupsBefore.size() + 1);



//    app.goTo().contacts();
//    Contacts result = app.db().contacts();
//    GroupData groupTemp = app.db().groups().iterator().next();
//    ContactData contactTemp = app.db().contacts().iterator().next();
//
//    app.contact().selectContact(contactTemp);
//
//    if (app.db().contacts().stream().filter((c -> c.getGroups.containsInAllGroups()).collect(toSet()))) {
//      app.goTo().groupPage();
//      app.group().create(new GroupData().withName("test1"));
//    }
//    app.contact().addContactToGroup(groupTemp);
//    app.db().contacts().getGroups;
//    app.contact().addedToGroup(contactTemp, group);
//    assertThat(contactTemp.getGroups(), (contains(group)));
//    app.contact().selectById
//    ContactsGroups before = app.contact().getcontact.getGroups()
//    Contacts before = app.db().contacts();
//    ContactData deletedContact = before.iterator().next();
//    app.contact().deleteContact(deletedContact);
//    assertThat(app.contact().count(), equalTo(before.size() - 1));
//    Contacts after = app.db().contacts();
//
//    assertEquals(after.size(), before.size() - 1);
//    assertThat(after, equalTo(before.without(deletedContact)));
//    verifyContactListInUI();

  }
}
