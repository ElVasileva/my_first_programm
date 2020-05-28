package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTest extends TestBase{

    @Test
    public void testContactDeletion() throws Exception {
        app.getNavigationHelper().gotoContacts();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContacts();
        app.getContactHelper().acceptDeletion();
    }
}
