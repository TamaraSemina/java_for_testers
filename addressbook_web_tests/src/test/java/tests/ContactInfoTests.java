package tests;

import common.CommonFunction;
import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

    @Test
    void testPhones() {
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
                Stream.of(contact.home(), contact.mobile(), contact.work(), contact.secondary())
                        .filter(s -> s != null && !"".equals(s))
                        .collect(Collectors.joining("\n"))
        ));
        var phones = app.contacts().getPhones();
        Assertions.assertEquals(expected, phones);
    }

    @Test
    void testEmails() {
        if (app.hbm().getContactCount() == 0) {
            var contact = new ContactData()
                    .withFirstName(CommonFunction.randomString(10))
                    .withLastName(CommonFunction.randomString(10))
                    .withPhoto(randomFile("src/test/resources/images"))
                    .withAddress("addresss1")
                    .withAddress2("addresss2")
                    .withEmail("email1")
                    .withEmail2("email2")
                    .withEmail3("email3")
                    .withHome("232")
                    .withMobile("+7921")
                    .withSecondary("656")
                    .withWork("991");
            ;
            app.hbm().createContact(contact);
        }

        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        var emails = app.contacts().getEmails(contact);
        var expected = Stream.of(contact.email(), contact.email2(), contact.email3())
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected, emails);
    }

    @Test
    void testEmailsOnHomePageWithEditPage() {
        if (app.hbm().getContactCount() == 0) {
            var contact = new ContactData()
                    .withFirstName(CommonFunction.randomString(10))
                    .withLastName(CommonFunction.randomString(10))
                    .withPhoto(randomFile("src/test/resources/images"))
                    .withAddress("address1")
                    .withAddress2("address2")
                    .withEmail("email1")
                    .withEmail2("email2")
                    .withEmail3("email3")
                    .withHome("232")
                    .withMobile("+7921")
                    .withSecondary("656")
                    .withWork("991");
            ;
            app.hbm().createContact(contact);
        }

        var contacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(contacts.size());
        var EmailsOnHomePage = app.contacts().getEmailsFromHomePage(contacts.get(index));
        var EmailsOnEditPage = app.contacts().getEmailsFromEditPage(contacts.get(index));
        Assertions.assertEquals(EmailsOnHomePage, EmailsOnEditPage);
    }

    @Test
    void testAddressOnEditPageAndBD() {
        if (app.hbm().getContactCount() == 0) {
            var contact = new ContactData()
                    .withFirstName(CommonFunction.randomString(10))
                    .withLastName(CommonFunction.randomString(10))
                    .withPhoto(randomFile("src/test/resources/images"))
                    .withAddress("addresss1")
                    .withAddress2("addresss2")
                    .withEmail("email1")
                    .withEmail2("email2")
                    .withEmail3("email3")
                    .withHome("232")
                    .withMobile("+7921")
                    .withSecondary("656")
                    .withWork("991");
            ;
            app.hbm().createContact(contact);
        }

        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
                Stream.of(contact.address(), contact.address2())
                        .filter(s -> s != null && !"".equals(s))
                        .collect(Collectors.joining(""))
        ));

        var rnd = new Random();
        var index = rnd.nextInt(contacts.size());
        var expectedAddress = expected.get(contacts.get(index).id());
        var editPageAddress = app.contacts().getAddressFromEditPage(contacts.get(index));
        Assertions.assertEquals(expectedAddress, editPageAddress);
    }

}
