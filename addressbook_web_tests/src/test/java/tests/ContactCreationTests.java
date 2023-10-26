package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunction;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {


    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();
//        for (var firstname : List.of("", "firstname")) {
//            for (var lastname : List.of("", "lastname")) {
//                for (var address : List.of("", "address")) {
//                    result.add(new ContactData().withFirstName(firstname).withLastName(lastname).withAddress(address));
//                }
//            }
//        }
//        for (int i = 0; i < 5; i++) {
//            result.add(new ContactData()
//                    .withFirstName(CommonFunction.randomString(i * 10))
//                    .withLastName(CommonFunction.randomString(i * 10))
//                    .withAddress(CommonFunction.randomString(i * 10)));
//        }

        /* Для xml формата */
//        var mapper = new XmlMapper();
//        var value = mapper.readValue(new File("contacts.xml"), new TypeReference<List<ContactData>>() {});

        /* Для json формата */
        var json = Files.readString(Paths.get("contacts.json"));
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(json, new TypeReference<List<ContactData>>() {
        });

        result.addAll(value);
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateContact(ContactData contact) {
        var oldContacts = app.contacts().getList();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(newContacts.get(newContacts.size() - 1).id()).withAddress("").withPhoto("src/test/resources/images/avatar.jpg"));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }

    public static List<ContactData> singleRandomContact() {
        return List.of(new ContactData()
                .withFirstName(CommonFunction.randomString(10))
                .withLastName(CommonFunction.randomString(20))
                .withAddress(CommonFunction.randomString(30)));
    }

    @ParameterizedTest
    @MethodSource("singleRandomContact")
    public void canCreateSingleContact(ContactData contact) {
        var oldContact = app.hbm().getContactList();
        app.contacts().createContact(contact);
        var newContacts = app.hbm().getContactList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        var maxId = newContacts.get(newContacts.size() - 1).id();

        var expectedList = new ArrayList<>(oldContact);
        expectedList.add(contact.withId(maxId));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }


    @Test
    void canCreateContactWithPhoto() {
        var contact = new ContactData()
                .withFirstName(CommonFunction.randomString(10))
                .withLastName(CommonFunction.randomString(10))
                .withPhoto(randomFile("src/test/resources/images"));
        app.contacts().createContact(contact);
    }

    @Test
    void canCreateContactInGroup() {
        var contact = new ContactData()
                .withFirstName(CommonFunction.randomString(10))
                .withLastName(CommonFunction.randomString(10))
                .withPhoto(randomFile("src/test/resources/images"));

        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var group = app.hbm().getGroupList().get(0);

        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().createContact(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void canNotCreateContact(ContactData contact) {
        int contactCount = app.contacts().getCount();
        app.contacts().createContact(contact);
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount, newContactCount);
    }

    public static List<ContactData> negativeContactProvider() {
        var result = new ArrayList<ContactData>(List.of(
                new ContactData("", "Imya'", "", "", "src/test/resources/images/avatar.jpg")));
        return result;
    }
}
