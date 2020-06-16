package ru.stqa.ptf.addressbook.model;

public class ContactData {
    private int id;
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
    private final String group;
    private final String address2;
    private final String phone2;
    private final String notes;


    public ContactData(String firstName, String middleName, String lastName, String nickName, String tittle, String company, String address, String homePage, String mobilePhone, String workPhone, String fax, String email, String email2, String email3, String homePhone, String group, String address2, String phone2, String notes) {
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
        this.group = group;
        this.address2 = address2;
        this.phone2 = phone2;
        this.notes = notes;
    }

    public ContactData(int id, String firstName, String lastName, String address) {
        this.id = Integer.MAX_VALUE;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.middleName = null;
        this.nickName = null;
        this.tittle = null;
        this.company = null;
        this.address2 = null;
        this.homePhone = null;
        this.mobilePhone = null;
        this.workPhone = null;
        this.fax = null;
        this.email = null;
        this.email2 = null;
        this.email3 = null;
        this.homePage = null;
        this.group = null;
        this.phone2 = null;
        this.notes = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getGroup() { return group; }

    public String getAddress2() {
        return address2;
    }

    public String getPhone2() {
        return phone2;
    }

    public String getNotes() {
        return notes;
    }


    @Override
    public String toString() {
        return "ContactData{" +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", address='" + address + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        return address != null ? address.equals(that.address) : that.address == null;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

}