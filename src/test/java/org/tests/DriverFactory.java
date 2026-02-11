package org.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pageobject.ConstructorPage;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.example.pageobject.LoginPage;
import org.example.pageobject.RegistrationPage;

import java.time.Duration;
import static org.example.pageobject.utils.EnvConfig.IMPLICITY_TIMEOUT;

public class DriverFactory extends ExternalResource {

    private WebDriver driver;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;

    public WebDriver getDriver() { return driver; }
    public LoginPage getMainPage() { return loginPage; }
    public RegistrationPage getRegistrationPage() { return registrationPage; }

    @Override
    protected void before() throws Throwable {
        initDriver();
    }

    @Override
    protected void after() {
        if (driver != null) driver.quit();
    }

    public void initDriver() {
        if ("yandex".equals(System.getProperty("browser"))) {
            startYandex();
        } else {
            startChrome();
        }

        driver.get("https://stellarburgers.education-services.ru");
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
        driver.manage().window().setSize(new Dimension(1920, 1080));
    }

    public void startChrome() {
        WebDriverManager.chromedriver()
                .clearDriverCache()
                .setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITY_TIMEOUT));
    }


    private void startYandex() {
        WebDriverManager.chromedriver().driverVersion("140.0.7339.244").setup();
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITY_TIMEOUT));
    }

    public ConstructorPage getConstructorPage() {
        return new ConstructorPage(driver);
    }
}





