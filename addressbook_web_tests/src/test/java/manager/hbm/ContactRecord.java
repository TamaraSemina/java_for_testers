package manager.hbm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table (name = "addressbook")
public class ContactRecord {

    @Id
    public String nickname = "";
    public String company = "";
    public String title = "";

    public String middlename = "";
    public String home = "";
    public String mobile = "";

    public String work = "";

    public String fax = "";
    public String email = "";
    public String email2 = "";
    public String email3 = "";
    public String im = "";
    public String im2 = "";
    public String im3 = "";
    public String homepage = "";
    public String bmonth = "";
    public int bday = 0;

    public String byear = "";
    public int aday = 0;
    public String amonth = "";
    public String ayear = "";
    public String address2 = "";
    public String phone2 = "";
    public String notes = "";

    @Column(name = "photo")
    public String photo = "";

    @Column(name = "id")
    public int id;
    @Column(name = "firstname")
    public String firstname;
    @Column(name = "lastname")
    public String lastname;
    @Column(name = "address")
    public String address;

    public ContactRecord() {    }

    public ContactRecord(int id, String firstname, String lastname, String address, String photo) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.photo = photo;
    }

}
