package model;

public record ContactData(String firstname, String lastname, String address, String email, String mobile, String email2, String home) {

    public ContactData() {
        this("", "", "", "", "", "", "");
    }

    public ContactData withFirstName(String firstname) {
        return new ContactData(firstname, this.lastname, this.address, this.email, this.email2, this.mobile, this.home);
    }

    public ContactData withMainParameters(String firstname, String lastname, String address, String email, String mobilephone) {
        return new ContactData(firstname, lastname, address, email, mobilephone, this.email2, this.home);
    }

    public ContactData withSomeParameters(String lastname, String address, String email2, String homephone) {
        return new ContactData(this.firstname, lastname, address, this.email, this.mobile, email2, homephone);
    }
}
