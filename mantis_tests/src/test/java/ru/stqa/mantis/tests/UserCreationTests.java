package ru.stqa.mantis.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunction;
import ru.stqa.mantis.model.DeveloperMailUser;

import java.time.Duration;

public class UserCreationTests extends TestBase {
    DeveloperMailUser user;

    @Test
    void canRegistrationUserDeveloperMail() {
        var password = "password";
        user = app.developerMail().addUser();
        var email = String.format("%s@developermail.com", user.name());

        app.registration().fillFormRegistration(user.name(), email);

        var message = app.developerMail().receive(user, Duration.ofSeconds(60));
        var url = CommonFunction.extractUrl2(message);

        app.registration().confirmRegistration(url, user.name(), password);

        app.http().login(user.name(), password);
        Assertions.assertTrue(app.http().isLoggedIn());
    }

    @AfterEach
    void deleteMailUser() {
        app.developerMail().deleteUser(user);
    }
}
