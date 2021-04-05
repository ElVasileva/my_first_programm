package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.Contacts;
import ru.stqa.ptf.addressbook.model.GroupData;
import ru.stqa.ptf.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class RemoveContactFromGroupTest extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("MyFirstGroup"));
    }

    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      Groups groups = app.db().groups();
      app.contact().create(new ContactData()
              .withFirstName("Andrey").withLastName("Titov").withAddress("Saint-Petersburg")
              .withHomePhone("89111232233").withEmail("1@1.ru")
              .inGroup(groups.iterator().next())
          , true);
    }
  }

  @Test
  public void testAddContactToGroup() {
    Contacts beforeContacts = app.db().contacts();
    ContactData removeContact = beforeContacts.iterator().next();
    GroupData testGroup = findOrAddGroup(removeContact);

    //Небольшой костыль для получения правильного значения getGroups, после подбора или добавления в группу
    ContactData beforeContact = removeContact;
    removeContact = app.db().contacts().stream()
        .filter((c) -> c.getId() == beforeContact.getId()).findFirst().get();
    Groups beforeContactGroups = removeContact.getGroups();

    app.goTo().homePage();
    app.contact().removeContactFromGroup(removeContact, testGroup);

    ContactData testedContact = removeContact;
    removeContact = app.db().contacts().stream()
        .filter((c) -> c.getId() == testedContact.getId()).findFirst().get();

    assertThat(removeContact.getGroups().size(), equalTo(beforeContactGroups.size() - 1));
    Groups afterContactGroups = removeContact.getGroups();
    assertThat(afterContactGroups, equalTo(beforeContactGroups.without(testGroup)));
  }

  private GroupData findOrAddGroup(ContactData removeContact) {
    Groups groups = app.db().groups();
    Groups contactGroups = removeContact.getGroups();
    GroupData result;

    if (contactGroups.size() > 0) {
      result = contactGroups.iterator().next();
    } else {
      GroupData groupToAdd = groups.iterator().next();
      app.goTo().homePage();
      app.contact().addContactToGroup(removeContact, groupToAdd);
      result = groupToAdd;
    }
    return result;
  }
}
