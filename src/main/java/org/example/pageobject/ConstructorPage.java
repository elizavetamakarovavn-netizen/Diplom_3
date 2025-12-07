package org.example.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.example.pageobject.utils.EnvConfig.EXPLICITY_TIMEOUT;

public class ConstructorPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public ConstructorPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICITY_TIMEOUT));
    }

    //Вкладка "Булки"
    private final By bunsTab = By.xpath("//span[text()='Булки']");
    //Вкладка "Соусы"
    private final By saucesTab = By.xpath("//span[text()='Соусы']");
    //Вкладка "Начинки"
    private final By fillingsTab = By.xpath("//span[text()='Начинки']");
    //Активная вкладка
    private final By activeTab = By.xpath("//div[contains(@class,'tab_tab_type_current')]//span");

    @Step("Клик по вкладке 'Булки'")
    public void clickBunsTab() {
        scrollIntoView(bunsTab);
        wait.until(ExpectedConditions.elementToBeClickable(bunsTab)).click();
    }
    @Step("Клик по вкладке 'Соусы'")
    public void clickSaucesTab() {
        scrollIntoView(saucesTab);
        wait.until(ExpectedConditions.elementToBeClickable(saucesTab)).click();
    }
    @Step("Клик по вкладке 'Начинки'")
    public void clickFillingsTab() {
        scrollIntoView(fillingsTab);
        wait.until(ExpectedConditions.elementToBeClickable(fillingsTab)).click();
    }

    @Step("Получение имени активной вкладки")
    public String getActiveTabName() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(activeTab));
        return driver.findElement(activeTab).getText();
    }

    @Step("Ожидание нужной активной вкладки")
    public void waitForActiveTab(String expected) {
        wait.until(driver -> driver.findElement(activeTab).getText().equals(expected));
    }

    @Step("Скролл вкладок")
    public void scrollTo(String ingredientName) {
        WebElement element = driver.findElement(By.xpath("//h2[text()='" + ingredientName + "']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    @Step("Скролл до видимости")
    private void scrollIntoView(By locator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }
}
