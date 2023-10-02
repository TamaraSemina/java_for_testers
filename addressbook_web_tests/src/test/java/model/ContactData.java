package model;

public record ContactData(String firstname, String lastname, String address, String email, String mobilephone) {

    public ContactData() {
        this("", "", "", "", "");
    }

    public ContactData withFirstName(String firstname) {
        return new ContactData(firstname, this.lastname, this.address, this.email, this.mobilephone);
    }

    public ContactData withMainParameters(String firstname, String lastname, String address, String email, String phone) {
        return new ContactData(firstname, lastname, address, email, phone);
    }

}
