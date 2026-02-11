package org.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.example.pageobject.LoginPage;
import org.example.pageobject.UserSteps;
import org.example.pageobject.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import static org.apache.http.HttpStatus.*;

import static org.junit.Assert.*;

public class LoginTests {

    @Rule
    public DriverFactory factory = new DriverFactory();

    private UserSteps userSteps;
    private User testUser;

    @Before
    public void setUp() {

        userSteps = new UserSteps();

        testUser = new User()
                .setEmail(RandomStringUtils.randomAlphabetic(10).toLowerCase() + "@mail.ru")
                .setPassword(RandomStringUtils.randomAlphanumeric(6))
                .setName(RandomStringUtils.randomAlphabetic(10));

        userSteps.createUser(testUser)
                .statusCode(SC_OK);
    }

    @Test
    @DisplayName("Вход по кнопке 'Войти в аккаунт' на главной")
    @Description("Проверяет возможность авторизации через кнопку 'Войти в аккаунт' на главной странице. " +
            "После успешного входа должен отображаться заголовок конструктора бургеров.")
    public void testLoginFromMainPage() {
        LoginPage main = factory.getMainPage();

        main.clickMainLoginButton();
        main.setEmail(testUser.getEmail());
        main.setPassword(testUser.getPassword());
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
        main.setEmail(testUser.getEmail());
        main.setPassword(testUser.getPassword());
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
        main.setEmail(testUser.getEmail());
        main.setPassword(testUser.getPassword());
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
        main.setEmail(testUser.getEmail());
        main.setPassword(testUser.getPassword());
        main.clickLoginButton();

        assertTrue("Не открылась главная страница конструктора после входа",
                main.isBurgerConstructionHeaderVisible());

    }

    @After
    public void tearDown() {

        if (userSteps.getAccessToken() != null) {
            userSteps.deleteUser(userSteps.getAccessToken()).statusCode(SC_ACCEPTED);
        }
        WebDriver driver = factory.getDriver();
        if (driver != null) driver.quit();
    }
}
