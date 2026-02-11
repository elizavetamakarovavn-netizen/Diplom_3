package org.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.example.pageobject.LoginPage;
import org.example.pageobject.RegistrationPage;
import org.example.pageobject.utils.RandomUserData;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.*;

public class RegistrationTests {

    @Rule
    public DriverFactory factory = new DriverFactory();

    String name = RandomUserData.randomName();
    String email = RandomUserData.randomEmail();
    String password = RandomUserData.randomPassword();

    @Test
    @DisplayName("Успешная регистрация")
    @Description("Проверяет, что пользователь может успешно зарегистрироваться с корректными данными. " + "После регистрации должна отображаться форма входа.")
    public void testSuccessfulRegistration() {
        LoginPage main = factory.getMainPage();
        RegistrationPage reg = factory.getRegistrationPage();

        main.clickMainLoginButton();
        main.clickRegistrationLink();
        reg.setName(name);
        reg.setEmail(email);
        reg.setPassword(password);
        reg.clickRegisterButton();

        assertTrue("Не открыт экран входа после регистрации", reg.isLoginFormDisplayed());
    }

    @Test
    @DisplayName("Ошибка регистрации при некоррекном пароле")
    @Description("Проверяет, что при регистрации с некорректным (слишком коротким) паролем отображается ошибка " + "'Некорректный пароль'.")
    public void testRegistrationWrongPassword() {
        LoginPage main = factory.getMainPage();
        RegistrationPage reg = factory.getRegistrationPage();

        main.clickMainLoginButton();
        main.clickRegistrationLink();

        reg.setName(name);
        reg.setEmail(email);
        reg.setPassword("7777");
        reg.clickRegisterButton();

        assertEquals("Некорректный пароль", reg.getPasswordErrorText());
    }

    @After
    public void tearDown() {
        WebDriver driver = factory.getDriver();
        LoginPage main = factory.getMainPage();
        if (driver != null) {
            try {
                main.clickPersonalAccountButton();
                main.clickLogoutButton();
            } catch (Exception ignore) {
            }
            driver.quit();
        }
    }
}
