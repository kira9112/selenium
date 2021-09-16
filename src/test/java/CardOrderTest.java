import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.By.cssSelector;

class CardOrderTest {

    private WebDriver driver;

    @BeforeAll

    static void setUpAll() {
        WebDriverManager.chromedriver().setup();

    }

    @BeforeEach

    void setUp(){

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach

    void tearDown(){
        driver.quit();
        driver = null;
    }

    @Test

    void shouldTestV1() {

        driver.get("http://localhost:9999");
        driver.findElement(cssSelector("[type='text']")).sendKeys("Алексей Сидоров");
        driver.findElement(cssSelector("[type='tel']")).sendKeys("+79656569685");
        driver.findElement(cssSelector(".checkbox__box")).click();
        driver.findElement(cssSelector("button")).click();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(cssSelector("[data-test-id=order-success]")).getText().trim();
        assertEquals(expected, actual);

    }

}