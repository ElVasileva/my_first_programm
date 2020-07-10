package ru.stqa.ptf.addressbook.model;

import com.google.common.collect.ForwardingSet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Contacts extends ForwardingSet<ContactData> {

  private Set<ContactData> delegate;

  private SessionFactory sessionFactory;

  public Contacts(Contacts contacts) {
    this.delegate = new HashSet<ContactData>(contacts.delegate);
  }

  public Contacts() {
    this.delegate = new HashSet<ContactData>();
  }

  public Contacts(Collection<ContactData> contacts) {
    this.delegate = new HashSet<ContactData>(contacts);
  }

  @Override
  protected Set<ContactData> delegate() {
    return delegate;
  }

  public Contacts withAdded(ContactData contact) {
    Contacts contacts = new Contacts(this);
    contacts.add(contact);
    return contacts;
  }

  public Contacts without(ContactData contact) {
    Contacts contacts = new Contacts(this);
    contacts.remove(contact);
    return contacts;
  }

//  contactGroups = new Group() {
//    Session session = sessionFactory.openSession();
//    session.beginTransaction();
//    List<ContactData> result = session.createQuery("from ContactData").list();
//    for (ContactData contact : result) {
////      System.out.println(contact);
////      System.out.println(contact.getGroups());
//    }
//    session.getTransaction().commit();
//    session.close();
//  }

}
