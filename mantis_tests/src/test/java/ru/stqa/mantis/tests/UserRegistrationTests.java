package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;

public class UserRegistrationTests extends TestBase {

    @Test
    void canRegistrationUser(String username) {
        var email = String.format("%s@localhost", username);
        //создать пользователя (JamesHelper)
        //заполнить форму (браузер)
        // ждем поулчения письма (MailHelper)
        //извлекаем ссылку из письма
        //проходим по ссылки  и завершаем регитсрацию (браузер)
        // проверить, что пользователь может залогиниться (HttpSessionHelper)
        //
    }
}
