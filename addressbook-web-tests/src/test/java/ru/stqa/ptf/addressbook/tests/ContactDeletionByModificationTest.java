package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionByModificationTest extends TestBase{

    @Test
    public void testContactDeletionByModification() throws Exception {
        app.getNavigationHelper().gotoContacts();
        app.getContactHelper().modificateContact();
        app.getContactHelper().deleteContact();
    }
}
