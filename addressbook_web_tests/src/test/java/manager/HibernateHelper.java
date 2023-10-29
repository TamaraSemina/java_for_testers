package manager;

import manager.hbm.ContactInGroupRecord;
import manager.hbm.ContactRecord;
import manager.hbm.GroupRecord;
import model.ContactData;
import model.GroupData;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class HibernateHelper extends HelperBase {
    private SessionFactory sessionFactory;

    public HibernateHelper(ApplicationManager manager) {
        super(manager);
        sessionFactory = new Configuration()
                .addAnnotatedClass(GroupRecord.class)
                .addAnnotatedClass(ContactRecord.class)
                .addAnnotatedClass(ContactInGroupRecord.class)
                .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost/addressbook?zeroDateTimeBehavior=convertToNull")
                .setProperty(AvailableSettings.USER, "root")
                .setProperty(AvailableSettings.PASS, "")
                .buildSessionFactory();
    }

    static List<GroupData> convertCroupList(List<GroupRecord> records) {
        return records.stream().map(HibernateHelper::convert).collect(Collectors.toList());
    }

//    static List<GroupData> convertCroupList(List<GroupRecord> records) { /* То же самое, что выше */
//        List<GroupData> result = new ArrayList<>();
//        for (var record : records) {
//            result.add(convert(record));
//        }
//        return result;
//    }

    private static GroupData convert(GroupRecord record) {
        return new GroupData("" + record.id, record.name, record.header, record.footer);
    }

    private static GroupRecord convert(GroupData data) {
        var id = data.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new GroupRecord(Integer.parseInt(id), data.name(), data.header(), data.footer());
    }

    public List<GroupData> getGroupList() {
        return convertCroupList(sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupRecord", GroupRecord.class).list();
        }));
    }

    public long getGroupCount() { /* Schitaet kol-vo group */
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from GroupRecord", Long.class).getSingleResult();
        });
    }

    public void createGroup(GroupData groupData) { /* Zapros k baze dannih, kotoriy sozdaet noviy object */
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(groupData));
            session.getTransaction().commit();
        });
    }


    static List<ContactData> convertContactList(List<ContactRecord> records) {
        return records.stream().map(HibernateHelper::convert).collect(Collectors.toList());
    }

//    static List<ContactData> convertContactList(List<ContactRecord> records) { /* То же самое, что выше */
//        List<ContactData> result = new ArrayList<>();
//        for (var record : records) {
//            result.add(convert(record));
//        }
//        return result;
//    }

    private static ContactData convert(ContactRecord record) {
        return new ContactData().withId("" + record.id)
                .withFirstName(record.firstname)
                .withLastName(record.lastname)
                .withAddress(record.address);
    }

    private static ContactRecord convertContact(ContactData contact) {
        var id = contact.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new ContactRecord(Integer.parseInt(id), contact.firstname(), contact.lastname(), contact.address(), contact.photo());
    }

    public List<ContactData> getContactList() {
        return convertContactList(sessionFactory.fromSession(session -> {
            return session.createQuery("from ContactRecord", ContactRecord.class).list();
        }));
    }

    public long getContactCount() { /* Schitaet kol-vo kontaktov */
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from ContactRecord", Long.class).getSingleResult();
        });
    }

    public void createContact(ContactData contactData) { /* Zapros k baze dannih, kotoriy sozdaet noviy object */
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convertContact(contactData));
            session.getTransaction().commit();
        });
    }

    public List<ContactData> getContactsInGroup(GroupData group) {
        return sessionFactory.fromSession(session -> {
            return convertContactList(session.get(GroupRecord.class, group.id()).contacts);
        });
    }

    public long getCountContactsInGroup() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from ContactInGroupRecord", Long.class).getSingleResult();
        });
    }
}
