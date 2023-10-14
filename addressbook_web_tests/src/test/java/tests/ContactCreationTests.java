package tests;

import model.ContactData;
import model.GroupData;
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
                .withPhoto("src/test/resources/images/avatar.jpg");
        app.contacts().createContact(contact);
    }

//    public static List<ContactData> contactProvider() {
//        var result = new ArrayList<ContactData>();
//        for (var firstname : List.of("", "firstname")) {
//            for (var lastname : List.of("", "lastname")) {
//                for (var address : List.of("", "address")) {
//                    result.add(new ContactData().withFirstName(firstname).withLastName(lastname).withAddress(address));
//                }
//            }
//        }
//        for (int i = 0; i < 5; i++) {
//            result.add(new ContactData()
//                    .withFirstName(randomString(i * 10))
//                    .withLastName(randomString(i * 10))
//                    .withAddress(randomString(i * 10)));
//        }
//        return result;
//    }

//    @ParameterizedTest
//    @MethodSource("contactProvider")
//    public void canCreateContact(ContactData contact) {
//        var oldContacts = app.contacts().getList();
//        app.contacts().createContact(contact);
//        var newContacts = app.contacts().getList();
//        Comparator<ContactData> compareById = (o1, o2) -> {
//            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
//        };
//        newContacts.sort(compareById);
//        var expectedList = new ArrayList<>(oldContacts);
//        expectedList.add(contact.withId(newContacts.get(newContacts.size() - 1).id()).withLastName("").withAddress(""));
//        expectedList.sort(compareById);
//        Assertions.assertEquals(newContacts, expectedList);
//    }

//    public static List<ContactData> contactProviderWithSomeStaticParameter() {
//        var firstname = new ContactData("", "Tamara", "", "", "", "", "", "","");
//        var address = new ContactData("", "", "", "ul. Lenina", "", "", "", "","");
//        var mobile = new ContactData("", "", "", "", "", "+79213434455", "", "","");
//        var email2 = new ContactData("", "", "", "", "", "", "2@2.ru", "","");
//        var result = new ArrayList<ContactData>();
//        for (var lastname : List.of("", "lastname")) {
//            for (var email : List.of("", "email@1.ru")) {
//                for (var home : List.of("", "2323322")) {
//                    result.add(new ContactData("", firstname.firstname(), lastname, address.address(), email, mobile.mobile(), email2.email2(), home));
//                }
//            }
//        }
//        for (int i = 0; i < 5; i++) {
//            result.add(new ContactData("", randomString(i * 10), randomString(i * 10), randomString(i * 10), randomString(i * 10), randomString(i * 10), randomString(i * 10), randomString(i * 10)));
//        }
//        return result;
//    }

//    @ParameterizedTest
//    @MethodSource("contactProviderWithSomeStaticParameter")
//    public void canCreateContactWithSomeStaticParameter(ContactData contact) {
//        int countContact = app.contacts().getCount();
//        app.contacts().createContact(contact);
//        int newCountContact = app.contacts().getCount();
//        Assertions.assertEquals(countContact + 1, newCountContact);
//    }

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

    @ParameterizedTest
    @MethodSource("negativeContactCreation")
    public void canNotCreateContact(ContactData contact) {
        int contactCount = app.contacts().getCount();
        app.contacts().createContact(contact);
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount, newContactCount);
    }
    public static List<ContactData> negativeContactCreation() {
        var result = new ArrayList<ContactData>(List.of(
                new ContactData("", "Imya'", "", "", "", "", "", "","")));
        return result;
    }
}
