package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContactRemovalTests extends TestBase {

    @Test
    public void canRemoveContact() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData("Imya", "Familia", "", "", "", "", ""));
        }
        app.contacts().removeContact();
    }

    @Test
    public void canAllRemoveContact() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData("Imya", "Familia", "", "", "", "", ""));
            app.contacts().createContact(new ContactData("", "", "ul. Lenina", "1@1.ry", "", "", ""));
        }
        app.contacts().removeAllContact();
        Assertions.assertEquals(0, app.contacts().getCount());
    }

}