package model;

public record ContactData(String firstname, String lastname) {

    public ContactData() {
        this("", "");
    }

    public ContactData withFirstName(String firstname) {
        return new ContactData(firstname, this.lastname);
    }

    public ContactData withLastName(String lastname) {
        return new ContactData(this.firstname, lastname);
    }

}
