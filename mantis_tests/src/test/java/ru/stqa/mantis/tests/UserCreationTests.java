package ru.stqa.mantis.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.model.DeveloperMailUser;

public class UserCreationTests extends TestBase {
    DeveloperMailUser user;

    @Test
    void canRegistrationUserDeveloperMail() {
        var password = "password";
        user = app.developerMail().addUser();
        var email = String.format("%s@developermail.com", user.name());

//        app.registration().fillFormRegistration(username, email);
//
//        var url = app.mail().extractUrl(email, "password");
//        app.registration().confirmRegistration(url, username, "password");
//
//        app.http().login(username, "password");
//        Assertions.assertTrue(app.http().isLoggedIn());
    }

    @AfterEach
    void deleteMailUser() {
        app.developerMail().deleteUser(user);
    }
}
