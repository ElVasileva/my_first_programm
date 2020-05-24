package ru.stqa.ptf.addressbook.model;

public class ContactData {
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String nickName;
    private final String tittle;
    private final String company;
    private final String address;
    private final String homePhone;
    private final String mobilePhone;
    private final String workPhone;
    private final String fax;
    private final String email;
    private final String email2;
    private final String email3;
    private final String homePage;
    private final String contactGroup;
    private final String address2;
    private final String phone2;
    private final String notes;
    private final String bDDay;
    private final String bDMonht;
    private final String bDYear;
    private final String annyversaryDay;
    private final String annyversaryMonth;
    private final String annyversaryYear;

    public ContactData(String firstName, String middleName, String lastName, String nickName, String tittle, String company, String address, String homePhone, String mobilePhone, String workPhone, String fax, String email, String email2, String email3, String homePage, String contactGroup, String address2, String phone2, String notes, String bDDay, String bDMonht, String bDYear, String annyversaryDay, String annyversaryMonth, String annyversaryYear) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.tittle = tittle;
        this.company = company;
        this.address = address;
        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
        this.workPhone = workPhone;
        this.fax = fax;
        this.email = email;
        this.email2 = email2;
        this.email3 = email3;
        this.homePage = homePage;
        this.contactGroup = contactGroup;
        this.address2 = address2;
        this.phone2 = phone2;
        this.notes = notes;
        this.bDDay = bDDay;
        this.bDMonht = bDMonht;
        this.bDYear = bDYear;
        this.annyversaryDay = annyversaryDay;
        this.annyversaryMonth = annyversaryMonth;
        this.annyversaryYear = annyversaryYear;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getTittle() {
        return tittle;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getFax() {
        return fax;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getHomePage() {
        return homePage;
    }

    public String getContactGroup() {
        return contactGroup;
    }

    public String getAddress2() {
        return address2;
    }

    public String getPhone2() {
        return phone2;
    }

    public String getNotes() {
        return notes;
    }

    public String getbDDay() {
        return bDDay;
    }

    public String getbDMonht() {
        return bDMonht;
    }

    public String getbDYear() {
        return bDYear;
    }

    public String getAnnyversaryDay() {
        return annyversaryDay;
    }

    public String getAnnyversaryMonth() {
        return annyversaryMonth;
    }

    public String getAnnyversaryYear() {
        return annyversaryYear;
    }
}
