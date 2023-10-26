package manager.hbm;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "address_in_groups")
public class ContactInGroupRecord {

    @Id
    public int id;
    public String group_id;

    public ContactInGroupRecord() {
    }

    public ContactInGroupRecord(int id, String group_id) {
        this.id = id;
        this.group_id = group_id;
    }
}
