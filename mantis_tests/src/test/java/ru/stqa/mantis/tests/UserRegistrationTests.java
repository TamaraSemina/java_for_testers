package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunction;
import ru.stqa.mantis.model.MantisUserData;

public class UserRegistrationTests extends TestBase {


    @Test
    void canRegistrationUser() {
        var username = String.format(CommonFunction.randomString(8));
        var email = String.format("%s@localhost", username);

        app.jamesCli().addUser(email, "password");
        app.registration().fillFormRegistration(username, email);

        var url = app.mail().extractUrl(email, "password");
        app.registration().confirmRegistration(url, username, "password");

        app.http().login(username, "password");
        Assertions.assertTrue(app.http().isLoggedIn());
    }

    @Test
    void canRegistrationUserViaJamesAndMantisApiHelper() {
        var username = String.format(CommonFunction.randomString(8));
        var email = String.format("%s@localhost", username);
        var password = "password";

        app.jamesApi().addUser(email, password);

        app.rest().mantisAddUser(new MantisUserData()
                .withUsername(username)
                .withPassword(password)
                .withRealName(username)
                .withEmail(email));

        var url = app.mail().extractUrl(email, password);
        app.registration().confirmRegistration(url, username, password);

        app.http().login(username, password);
        Assertions.assertTrue(app.http().isLoggedIn());
    }
}

