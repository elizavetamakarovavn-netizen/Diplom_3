package org.example.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.example.pageobject.utils.EnvConfig.EXPLICITY_TIMEOUT;


public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICITY_TIMEOUT));
    }

    //Кнопка "Войти в аккаунт" на главной странице
    private final By mainLoginButton = By.xpath("//button[text()='Войти в аккаунт']");
    //Ссылка на регистрацию на экране входа в личный кабинет
    private final By registrationLink = By.cssSelector("a.Auth_link__1fOlj[href='/register']");
    //Кнопка "Войти" на экране входа в личный кабинет
    private final By loginButton = By.cssSelector("button.button_button__33qZ0.button_button_type_primary__1O7Bx.button_button_size_medium__3zxIa");
    //Поле "Email" на экране входа в аккаунт
    private final By emailLoginInput = By.xpath("//input[@name='name']");
    //Поле "Пароль" на экране входа в аккаунт
    private final By passwordLoginInput = By.xpath("//input[@name='Пароль']");
    //Заголовок "Соберите бургер" на главной странице
    private final By burgerConstructionHeader = By.xpath("//h1[text()='Соберите бургер']");
    //Кнопка "Личный кабинет" в шапке страницы
    private final By personalAccountButton = By.xpath("//p[text()='Личный Кабинет']");
    //Ссылка для входа в аккаунт на экране регистрации/восстановления пароля
    private final By loginLink = By.xpath("//a[text()='Войти']");
    //Ссылка "Восстановить пароль" на экране входа в аккаунт
    private final By restorePasswordLink = By.xpath("//a[text()='Восстановить пароль']");
    //Кнопка "Выйти" в личном кабинете
    private final By logoutButton = By.xpath("//button[text()='Выход']");


    @Step("Клик по кнопке 'Войти в аккаунт на главной' странице")
    public void clickMainLoginButton() {
        WebElement button = driver.findElement(mainLoginButton);
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICITY_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(button))
                .click();
    }

    @Step("Клик по ссылке на регистрацию на экране входа в личный кабинет")
    public void clickRegistrationLink() {
        WebElement button = driver.findElement(registrationLink);
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICITY_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(button))
                .click();
    }

    @Step("Клик по кнопке 'Войти' на экране входа в личный кабинет")
    public void clickLoginButton() {
        WebElement button = driver.findElement(loginButton);
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICITY_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(button))
                .click();
    }

    @Step("Ввод email")
    public void setEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailLoginInput)).sendKeys(email);
    }

    @Step("Ввод пароля")
    public void setPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordLoginInput)).sendKeys(password);
    }

    @Step("Отображение заголовка 'Соберите бургер'")
    public boolean isBurgerConstructionHeaderVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(burgerConstructionHeader)).isDisplayed();
    }

    @Step("Клик по кнопке 'Личный кабинет' в шапке страницы")
    public void clickPersonalAccountButton() {
        WebElement button = driver.findElement(personalAccountButton);
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICITY_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(button))
                .click();
    }

    @Step("Клик по ссылке на вход в аккаунт на экране регистрации")
    public void clickLoginLink() {
        WebElement button = driver.findElement(loginLink);
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICITY_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(button))
                .click();
    }

    @Step("Клик по ссылке для восстановления пароля на экране входа в аккаунт")
    public void clickRestorePasswordLink() {
        WebElement button = driver.findElement(restorePasswordLink);
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICITY_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(button))
                .click();
    }

    @Step("Клик по кнопке 'Выйти' в личном кабинете")
    public void clickLogoutButton() {
        WebElement button = driver.findElement(logoutButton);
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICITY_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(button))
                .click();
    }
}
