package model;

public record ContactData(String id,
                          String firstname,
                          String lastname,
                          String address,
                          String photo,
                          String home,
                          String mobile,
                          String work,
                          String secondary
) {

    public ContactData() {
        this("", "", "", "", "src/test/resources/images/avatar.jpg", "", "", "", "");
    }

    public ContactData withId(String id) {
        return new ContactData(id, firstname, this.lastname, this.address, this.photo, this.home, this.mobile, this.work, this.secondary);
    }

    public ContactData withFirstName(String firstname) {
        return new ContactData(this.id, firstname, this.lastname, this.address, this.photo, this.home, this.mobile, this.work, this.secondary);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.firstname, this.lastname, address, this.photo, this.home, this.mobile, this.work, this.secondary);
    }

    public ContactData withLastName(String lastname) {
        return new ContactData(this.id, this.firstname, lastname, this.address, this.photo, this.home, this.mobile, this.work, this.secondary);
    }

    public ContactData withPhoto(String photo) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, photo, this.home, this.mobile, this.work, this.secondary);
    }

    public ContactData withHome(String home) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, this.photo, home, this.mobile, this.work, this.secondary);
    }

    public ContactData withMobile(String mobile) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, this.photo, this.home, mobile, this.work, this.secondary);
    }

    public ContactData withWork(String work) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, this.photo, this.home, this.mobile, work, this.secondary);
    }

    public ContactData withSecondary(String secondary) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, this.photo, this.home, this.mobile, this.work, secondary);
    }
}