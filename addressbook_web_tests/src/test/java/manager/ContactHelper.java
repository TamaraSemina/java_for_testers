package manager;

import model.ContactData;
import org.openqa.selenium.By;

public class ContactHelper extends HelperBase {
    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createContact(ContactData contact) {
        openContactPage();
        fillContactForm(contact);
        submitContactCreation();
        returnToHomePage();
    }

    public void removeContact() {
        openHomePage();
        selectContact();
        removeSelectedContact();
        acceptRemoval();
    }

    public void openContactPage() {
        click(By.linkText("add new"));
    }

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstname());
        type(By.name("lastname"), contact.lastname());
    }

    private void submitContactCreation() {
        click(By.cssSelector("input:nth-child(87)"));
    }

    private void openHomePage() {
        click(By.linkText("home"));
    }

    private void returnToHomePage() {
        click(By.linkText("home page"));
    }

    private void selectContact() {
        click(By.name("selected[]"));
    }

    private void removeSelectedContact() {
        click(By.cssSelector(".left:nth-child(8) > input"));
    }
}
