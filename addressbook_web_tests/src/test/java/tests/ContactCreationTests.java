package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> negativeContactCreation() {
        var result = new ArrayList<ContactData>(List.of(
                new ContactData("Imya'", "", "", "", "", "", "")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("negativeContactCreation")
    public void canNotCreateContact(ContactData contact) {
        int contactCount = app.contacts().getCount();
        app.contacts().createContact(contact);
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount, newContactCount);
    }

    @Test
    public void canCreateContact() {
        app.contacts().createContact(new ContactData("Tamara", "Semina", "", "", "", "", ""));
    }

    @Test
    public void canCreateContactWithFirstName()     {
        app.contacts().createContact((new ContactData().withFirstName("First Name Test")));
    }

    @Test
    public void canCreateContactWithMainParameters()    {
        app.contacts().createContact((new ContactData().withMainParameters("First Name Test", "Last Name Test", "ul. Lenina", "test@test.ru", "+79213334455")));
    }

    @Test
    public void canCreateContactWithSomeParameters() {
        app.contacts().createContact((new ContactData().withSomeParameters("namelast", "ul. Mira", "2@2.ru", "2322323")));
    }
}
