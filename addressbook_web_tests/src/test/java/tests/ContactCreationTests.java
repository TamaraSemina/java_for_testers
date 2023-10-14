package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {


    @Test
    void canCreateContactWithPhoto() {
        var contact = new ContactData()
                .withFirstName(randomString(10))
                .withLastName(randomString(10))
                .withPhoto(randomFile("src/test/resources/images"));
        app.contacts().createContact(contact);
    }

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
        for (var firstname : List.of("", "firstname")) {
            for (var lastname : List.of("", "lastname")) {
                for (var address : List.of("", "address")) {
                    result.add(new ContactData().withFirstName(firstname).withLastName(lastname).withAddress(address));
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            result.add(new ContactData()
                    .withFirstName(randomString(i * 10))
                    .withLastName(randomString(i * 10))
                    .withAddress(randomString(i * 10)));
        }
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


//    public static List<ContactData> contactProviderWithSomeStaticParameter2() {
//        var lastname = new ContactData("", "", "Semina", "", "", "", "", "");
//        var email = new ContactData("", "", "", "", "", "", "2@2.ru", "");
//        var home = new ContactData("", "", "", "", "", "", "", "6567788");
//        var result = new ArrayList<ContactData>();
//        for (var firstname : List.of("", "firstname")) {
//            for (var address : List.of("", "address")) {
//                for (var email2 : List.of("", "2@2.ru")) {
//                    for (var mobile : List.of("", "+79212323322")) {
//                        result.add(new ContactData("", firstname, lastname.lastname(), address, email.email(), mobile, email2, home.home()));
//                    }
//                }
//            }
//        }
//        for (int i = 0; i < 5; i++) {
//            result.add(new ContactData("", randomString(i * 10), randomString(i * 10), randomString(i * 10), randomString(i * 10), randomString(i * 10), randomString(i * 10), randomString(i * 10)));
//        }
//        return result;
//    }

//    @ParameterizedTest
//    @MethodSource("contactProviderWithSomeStaticParameter2")
//    public void canCreateContactWithSomeStaticParameter2(ContactData contact) {
//        int countContact = app.contacts().getCount();
//        app.contacts().createContact(contact);
//        int newCountContact = app.contacts().getCount();
//        Assertions.assertEquals(countContact + 1, newCountContact);
//    }
}
