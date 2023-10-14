package model;

public record ContactData(String id, String firstname, String lastname, String address, String photo) {

    public ContactData() {
        this("", "", "", "", "src/test/resources/images/avatar.jpg");
    }

    public ContactData withId(String id) {
        return new ContactData(id, firstname, this.lastname, this.address, this.photo);
    }

    public ContactData withFirstName(String firstname) {
        return new ContactData(this.id, firstname, this.lastname, this.address, this.photo);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.firstname, this.lastname, address, this.photo);
    }

    public ContactData withLastName(String lastname) {
        return new ContactData(this.id, this.firstname, lastname, this.address, this.photo);
    }

    public ContactData withPhoto(String photo) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, photo);
    }

//    public ContactData withMainParameters(String firstname, String lastname, String address) {
//        return new ContactData(this.id, firstname, lastname, address, this.photo);
//    }
}