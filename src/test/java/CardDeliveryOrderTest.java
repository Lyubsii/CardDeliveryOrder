import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

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
        Configuration.holdBrowserOpen = true;
        Configuration.timeout = 15;
        Configuration.browserSize = "800x300";
        open("http://localhost:9999/");
        $x("[data-test-id='city'] input").setValue("Москва");
        $x("[data-test-id='name'] input").setValue("Иван Иванов");
        $x("[data-test-id='phone'] input").setValue("+79169356902");
        $x("[data-test-id='agreement']").click();
        $x("//*[text()=\"Забронировать\"]").click();
        $(withText("Успешно! Встреча успешно забронирована на 21.04.2023")).shouldBe(visible);

    }
}

