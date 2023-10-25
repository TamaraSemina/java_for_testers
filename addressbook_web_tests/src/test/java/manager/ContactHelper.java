package manager;

import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

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

    public void createContact(ContactData contact, GroupData group) {
        openContactPage();
        fillContactForm(contact);
        selectGroup(group);
        submitContactCreation();
        returnToHomePage();
    }

    private void selectGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }

    public void removeContact(ContactData contact) {
        openHomePage();
        selectContact(contact);
        removeSelectedContact();
        acceptRemoval();
        openHomePage();
    }

    public void removeAllContact() {
        openHomePage();
        selectAllContact();
        removeSelectedContact();
        acceptRemoval();
    }


    public void openContactPage() {
        click(By.linkText("add new"));
    }

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstname());
        type(By.name("lastname"), contact.lastname());
        type(By.name("address"), contact.address());
        attach(By.name("photo"), contact.photo());
    }

    public void modifyContact(ContactData contact, ContactData modifiedContact) {
        openHomePage();
        initContactModification(contact);
        fillContactForm(modifiedContact);
        submitContactModification();
        returnToHomePage();
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

    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
    }

    private void selectAllContact() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }

    private void removeSelectedContact() {
//        click(By.cssSelector(".left:nth-child(8) > input"));
        click(By.xpath("//input[@value='Delete']"));
    }

    public boolean isContactPresent() {
        openHomePage();
        return manager.isElementPresent(By.name("selected[]"));
    }

    private void submitContactModification() {
        manager.driver.findElement(By.name("update")).click();
    }

    private void initContactModification(ContactData contact) {
        click(By.cssSelector(String.format("a[href='edit.php?id=%s']", contact.id())));
    }

    public int getCount() {
        openHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getList() {
        openHomePage();
        var contacts = new ArrayList<ContactData>();
        var trs = manager.driver.findElements(By.xpath("//tr[@name=\'entry\']"));
        for (var test : trs) {
            var checkbox = test.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            var firstname = test.findElement(By.xpath("td[3]")).getText();
            var lastname = test.findElement(By.xpath("td[2]")).getText();
            contacts.add(new ContactData().withId(id).withFirstName(firstname).withLastName(lastname));
        }
        return contacts;
    }

    //        public List<ContactData> getList() {
//        openHomePage();
//        var contacts = new ArrayList<ContactData>();
//        var trs = manager.driver.findElements(By.xpath("//table[@class='sortcompletecallback-applyZebra']/tbody/tr[preceding-sibling::tr]"));
//        for (var test : trs) {
//            var name = test.getText();
//            var checkbox = test.findElement(By.name("selected[]"));
//            var id = checkbox.getAttribute("value");
//            contacts.add(new ContactData().withId(id).withFirstName(name));
//        }
//        return contacts;
//    }
}
