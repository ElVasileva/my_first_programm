package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.Contacts;
import ru.stqa.ptf.addressbook.model.GroupData;
import ru.stqa.ptf.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }

    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      Groups groups = app.db().groups();
      app.contact().create(new ContactData()
              .withFirstName("Andrej").withLastName("Andreev").withAddress("Nauki avenue")
              .withHomePhone("777555333").withEmail("and_and@mail.ru")
              .inGroup(groups.iterator().next())
          , true);
    }
  }

  @Test
  public void testAddContactToGroup() {
    Contacts beforeContacts = app.db().contacts();
    ContactData addedContact = beforeContacts.iterator().next();

    Groups beforeContactGroups = addedContact.getGroups();
    GroupData testGroup = findOrCreateGroup(addedContact);

    app.goTo().homePage();
    app.contact().addContactToGroup(addedContact, testGroup);

    ContactData testedContact = addedContact;
    addedContact = app.db().contacts().stream()
        .filter((c) -> c.getId() == testedContact.getId()).findFirst().get();

    assertThat(addedContact.getGroups().size(), equalTo(beforeContactGroups.size() + 1));

    Groups afterContactGroups = addedContact.getGroups();

    assertThat(afterContactGroups, equalTo(beforeContactGroups.withAdded(testGroup)));

  }

  private GroupData findOrCreateGroup(ContactData addedContact) {
    Groups groups = app.db().groups();
    Groups contactGroups = addedContact.getGroups();

    GroupData result;

    if (contactGroups.size() < groups.size()) {
      for (GroupData group : contactGroups) {
        groups.remove(group);
      }
      result = groups.iterator().next();

    } else {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
      Groups newGroups = app.db().groups();
      int maxId = newGroups.stream().mapToInt((g) -> g.getId()).max().getAsInt();
      result = newGroups.stream().filter((g) -> g.getId() == maxId).findFirst().get();
    }
    return result;
  }
}
