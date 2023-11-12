package ru.stqa.mantis.manager;

import org.openqa.selenium.By;

public class RegistrationHelper extends HelperBase {
    public RegistrationHelper(ApplicationManager manager) {
        super(manager);
    }

    public void fillFormRegistration(String username, String email) {
        click(By.xpath("//*[@id='login-box']/div/div[2]/a"));
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(By.cssSelector("input[type='submit']"));
        click(By.xpath("//*[@id='login-box']/div/div/div[4]/a"));
    }

    public void confirmRegistration(String url, String username, String password) {
        manager.driver().get(url);
        type(By.name("realname"), username);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.xpath("//*[@id=\"account-update-form\"]/fieldset/span/button\n"));
        type(By.name("username"), username);
        click(By.cssSelector("input[type='submit']"));
        type(By.name("password"), password);
        click(By.cssSelector("input[type='submit']"));
    }

    public void confirmRegistration2(String url, String username, String password) {
        manager.driver().get(url);
        type(By.name("realname"), username);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.xpath("//*[@id=\"account-update-form\"]/fieldset/span/button\n"));
        type(By.name("username"), username);
        click(By.cssSelector("input[type='submit']"));
        type(By.name("password"), password);
        click(By.cssSelector("input[type='submit']"));
    }
}
