package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;

public class ContactDeletionByModificationTest extends TestBase{

    @Test
    public void testContactDeletionByModification() throws Exception {
        app.getNavigationHelper().gotoContacts();
        app.getContactHelper().modificateContact();
        app.getContactHelper().deleteContact();
    }
}
