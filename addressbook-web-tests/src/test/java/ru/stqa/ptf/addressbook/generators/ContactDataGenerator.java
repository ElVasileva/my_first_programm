package ru.stqa.ptf.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {


  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    if (format.equals("csv")) {
      saveAsCsv(contacts, new File(file));
    } else if (format.equals("xml")) {
      saveAsXml(contacts, new File(file));
    } else if (format.equals("json")) {
      saveAsJson(contacts, new File(file));
    } else {
      System.out.println("Unrecognized format" + format);
    }
  }

  private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    Writer writer = new FileWriter(file);
    writer.write(json);
    writer.close();
  }

  private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
    XStream xStream = new XStream();
    xStream.processAnnotations(GroupData.class);
    String xml = xStream.toXML(contacts);
    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close();
  }

  private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    Writer writer = new FileWriter(file);
    for (ContactData contact : contacts) {
      writer.write(String.format("%s;%s;%s\n", contact.getFirstName(), contact.getMiddleName(),
          contact.getLastName(), contact.getNickName(), contact.getTittle(), contact.getCompany(),
          contact.getAddress(), contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(),
          contact.getFax(), contact.getEmail(), contact.getEmail2(), contact.getEmail3(),
          contact.getHomePage(), contact.getGroup(), contact.getAddress2(), contact.getPhone2(),
          contact.getNotes()));
    }
    writer.close();
  }

  private List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactData().withFirstName(String.format("firstname %s", i))
          .withMiddleName(String.format("middlename %s", i)).withLastName(String.format("lastname %s", i))
          .withNickName(String.format("nickname %s", i)).withTittle(String.format("tittle %s", i))
          .withCompany(String.format("company %s", i)).withAddress(String.format("address %s", i))
          .withHomePhone(String.format("homephone %s", i)).withMobilePhone(String.format("mobilephone %s", i))
          .withWorkPhone(String.format("workphone %s", i)).withFax(String.format("fax %s", i))
          .withEmail(String.format("email %s", i)).withEmail2(String.format("email2 %s", i))
          .withEmail3(String.format("email3 %s", i)).withHomePage(String.format("homepage %s", i))
          .withAddress2(String.format("address2 %s", i)).withPhone2(String.format("phone2 %s", i))
          .withNotes(String.format("notes %s", i)));
    }
    return contacts;
  }
}
