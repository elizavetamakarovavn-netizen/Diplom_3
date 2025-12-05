package org.example.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.example.pageobject.utils.EnvConfig.EXPLICITY_TIMEOUT;


public class RegistrationPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICITY_TIMEOUT));
    }

    //Поле ввода "Имя"
    private final By nameRegInput = By.xpath("//label[text()='Имя']/following-sibling::input");
    //Поле ввода "Email"
    private final By emailRegInput = By.xpath("//label[text()='Email']/following-sibling::input");
    //Поле ввода "Пароль"
    private final By passwordRegInput = By.cssSelector("input[type='password'][name='Пароль']");
    //Кнопка "Зарегистрироваться"
    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    //Ошибка при вводе некорректного пароля
    private final By passwordError = By.cssSelector("p.input__error.text_type_main-default");
    //Заголовок формы входа
    private final By loginFormHeader = By.xpath("//h2[contains(text(),'Вход')]");

    @Step("Ввод имени")
    public void setName(String name) {
        driver.findElement(nameRegInput).sendKeys(name);
    }

    @Step("Ввод email")
    public void setEmail(String email) {
        driver.findElement(emailRegInput).sendKeys(email);
    }

    @Step("Ввод пароля")
    public void setPassword(String password) {
        driver.findElement(passwordRegInput).sendKeys(password);
    }

    @Step("Клик по кнопке 'Зарегистрироваться'")
    public void clickRegisterButton() {
        WebElement button = driver.findElement(registerButton);
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICITY_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(button))
                .click();
    }

    @Step("Проверка, что открыта форма входа после регистрации")
    public boolean isLoginFormDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginFormHeader)).isDisplayed();
    }

    @Step("Получение текста ошибки при вводе некорректного пароля")
    public String getPasswordErrorText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordError)).getText();
    }
}
