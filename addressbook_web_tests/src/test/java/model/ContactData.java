package model;

public record ContactData(String id, String firstname, String lastname, String address, String email, String mobile, String email2, String home, String photo) {

    public ContactData() {
        this("", "", "", "", "", "", "", "", "");
    }

    public ContactData withId(String id) {
        return new ContactData(id, firstname, this.lastname, this.address, this.email, this.email2, this.mobile, this.home, this.photo);
    }

    public ContactData withFirstName(String firstname) {
        return new ContactData(this.id, firstname, this.lastname, this.address, this.email, this.email2, this.mobile, this.home, this.photo);
    }

    public ContactData withLastName(String lastname) {
        return new ContactData(this.id, this.firstname, lastname, this.address, this.email, this.email2, this.mobile, this.home, this.photo);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.firstname, this.lastname, address, this.email, this.email2, this.mobile, this.home, this.photo);
    }

    public ContactData withPhoto(String photo) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, this.email, this.email2, this.mobile, this.home, photo);
    }

    public ContactData withMainParameters(String firstname, String lastname, String address, String email, String mobilephone) {
        return new ContactData(this.id, firstname, lastname, address, email, mobilephone, this.email2, this.home, this.photo);
    }
}