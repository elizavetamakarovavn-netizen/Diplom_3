package org.tests;

import io.qameta.allure.junit4.DisplayName;
import org.example.pageobject.LoginPage;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.*;

public class LoginTests {

    @Rule
    public DriverFactory factory = new DriverFactory();

    String email = "als78@mail.ru";
    String password = "777777";

    @Test
    @DisplayName("Вход по кнопке 'Войти в аккаунт' на главной")
    public void testLoginFromMainPage() {
        LoginPage main = factory.getMainPage();

        main.clickMainLoginButton();
        main.setEmail(email);
        main.setPassword(password);
        main.clickLoginButton();

        assertTrue("Не открылась главная страница конструктора после входа",
                main.isBurgerConstructionHeaderVisible());
    }

    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет'")
    public void testLoginFromPersonalAccountButton() {
        LoginPage main = factory.getMainPage();

        main.clickPersonalAccountButton();
        main.setEmail(email);
        main.setPassword(password);
        main.clickLoginButton();

        assertTrue("Не открылась главная страница конструктора после входа",
                main.isBurgerConstructionHeaderVisible());

    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void testLoginFromRegistrationForm() {
        LoginPage main = factory.getMainPage();

        main.clickMainLoginButton();
        main.clickRegistrationLink();
        main.clickLoginLink();
        main.setEmail(email);
        main.setPassword(password);
        main.clickLoginButton();

        assertTrue("Не открылась главная страница конструктора после входа",
                main.isBurgerConstructionHeaderVisible());

    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void testLoginFromPasswordRestoreForm() {
        LoginPage main = factory.getMainPage();

        main.clickPersonalAccountButton();
        main.clickRestorePasswordLink();
        main.clickLoginLink();
        main.setEmail(email);
        main.setPassword(password);
        main.clickLoginButton();

        assertTrue("Не открылась главная страница конструктора после входа",
                main.isBurgerConstructionHeaderVisible());

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
