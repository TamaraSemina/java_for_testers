package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunction;

public class JamesTests extends TestBase {

    @Test
    void canCreateUser() {
        app.jamesCli().addUser(String.format("%s@localhost", CommonFunction.randomString(8)), "password");
    }

}
