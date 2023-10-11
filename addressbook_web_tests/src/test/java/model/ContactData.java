package model;

public record ContactData(String id, String firstname, String lastname, String address, String email, String mobile, String email2, String home) {

    public ContactData() {
        this("", "", "", "", "", "", "", "");
    }

    public ContactData withId(String id) {
        return new ContactData(id, firstname, this.lastname, this.address, this.email, this.email2, this.mobile, this.home);
    }
    public ContactData withFirstName(String firstname) {
        return new ContactData(this.id, firstname, this.lastname, this.address, this.email, this.email2, this.mobile, this.home);
    }

    public ContactData withMainParameters(String firstname, String lastname, String address, String email, String mobilephone) {
        return new ContactData(this.id, firstname, lastname, address, email, mobilephone, this.email2, this.home);
    }

    public ContactData withSomeParameters(String lastname, String address, String email2, String homephone) {
        return new ContactData(this.id, this.firstname, lastname, address, this.email, this.mobile, email2, homephone);
    }
}
