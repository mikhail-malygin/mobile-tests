package tests.local;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import drivers.BrowserstackMobileDriver;
import drivers.EmulatorDeviceDriver;
import drivers.RealDeviceDriver;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static io.qameta.allure.Allure.step;


public class TestBase {
    static String deviceHost = System.getProperty("deviceHost", "${deviceHost}");

    @BeforeAll
    public static void setUp() {
        switch (deviceHost) {
            case ("browserstack"):
                Configuration.browser = BrowserstackMobileDriver.class.getName();
                break;
            case ("real"):
                Configuration.browser = RealDeviceDriver.class.getName();
            default:
                Configuration.browser = EmulatorDeviceDriver.class.getName();
        }

        Configuration.browserSize = null;
    }

    @BeforeEach
    public void startDriver() {
        addListener("AllureSelenide", new AllureSelenide());

        open();
    }

    @AfterEach
    public void afterEach() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();

        step("Close driver", Selenide::closeWebDriver);
    }
}
