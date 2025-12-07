package org.tests;

import io.qameta.allure.Description;
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
    @Description("Проверяет возможность авторизации через кнопку 'Войти в аккаунт' на главной странице. " +
            "После успешного входа должен отображаться заголовок конструктора бургеров.")
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
    @Description("Проверяет, что пользователь может перейти к форме авторизации через кнопку 'Личный кабинет' и успешно войти. " +
            "После входа отображается главная страница конструктора бургеров.")
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
    @Description("Проверяет, что со страницы регистрации можно перейти на форму авторизации по ссылке 'Войти' " +
            "и успешно выполнить вход. После авторизации открывается главная страница конструктора.")
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
    @Description("Проверяет возможность перехода к форме авторизации со страницы восстановления пароля " +
            "и успешный вход в систему. После входа должна отображаться главная страница конструктора бургеров.")
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
