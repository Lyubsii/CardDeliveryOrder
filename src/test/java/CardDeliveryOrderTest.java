import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;


class ChromeTest {

    WebDriver driver;

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);

    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void formSubmissionTest() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("menu-item__control")).sendKeys("Москва");
        driver.findElement(By.cssSelector("input__box")).sendKeys("Иван Иванов");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79169356902");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.tagName("button__text")).click();
        String expected = "Встреча успешно забронирована на 20.04.2023";
        String actual = driver.findElement(By.cssSelector("icon-button__text")).getText().trim();
        Assertions.assertEquals (expected, actual);
    }
}

