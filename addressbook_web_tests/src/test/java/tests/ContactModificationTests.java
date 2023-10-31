package tests;

import common.CommonFunction;
import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactModificationTests extends TestBase {

    @Test
    void canModifyContact() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "Imya", "Familia", "","src/test/resources/images/avatar.jpg", "", "", "", ""));
        }
        var oldContacts = app.hbm().getContactList();

        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var testData = new ContactData().withFirstName("modified name");

        app.contacts().modifyContact(oldContacts.get(index), testData);

        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);

        expectedList.set(index, testData.withId(oldContacts.get(index).id()));
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    void canAddContactToGroup() {
        if (app.hbm().getContactCount() == 0) {
            var contact = new ContactData()
                    .withFirstName(CommonFunction.randomString(10))
                    .withLastName(CommonFunction.randomString(10))
                    .withPhoto(randomFile("src/test/resources/images"));
            app.contacts().createContact(contact);
        }

        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "Imya", "Familia", "", "src/test/resources/images/avatar.jpg", "", "", "", ""));
        }

        var group = app.hbm().getGroupList().get(0);
        var contact = app.hbm().getContactList().get(0);
        var oldContacts = app.hbm().getContactList();

        app.contacts().addContactToGroup(contact, group);

        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };

        var newContacts = app.hbm().getContactList();
        newContacts.sort(compareById);
        oldContacts.sort(compareById);
        Assertions.assertEquals(newContacts, oldContacts);
    }
}
