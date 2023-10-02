package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase {

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
