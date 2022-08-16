package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.CredentialsConfig;
import config.MobileConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackMobileDriver implements WebDriverProvider {

    static CredentialsConfig credentialsConfig = ConfigFactory.create(CredentialsConfig.class);
    private final MobileConfig mobileConfig;

    public BrowserstackMobileDriver() {
        this.mobileConfig = ConfigFactory.create(MobileConfig.class, System.getProperties());
    }

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MutableCapabilities mutableCapabilities = new MutableCapabilities();
        mutableCapabilities.merge(capabilities);

        mutableCapabilities.setCapability("browserstack.user", credentialsConfig.user());
        mutableCapabilities.setCapability("browserstack.key", credentialsConfig.key());

        mutableCapabilities.setCapability("app", "bs://0eabb6cecb69e963e4fa4a173a457e6f78456510");

        mutableCapabilities.setCapability("device", mobileConfig.getDevice());
        mutableCapabilities.setCapability("os_version", mobileConfig.getOsVersion());

        mutableCapabilities.setCapability("project", "Mobile Tests");
        mutableCapabilities.setCapability("build", "browserstack-build-1");
        mutableCapabilities.setCapability("name", "first_test");



        return new RemoteWebDriver(getBrowserstackUrl(), mutableCapabilities);
    }

    public static URL getBrowserstackUrl() {
        try {
            return new URL("http://hub.browserstack.com/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }


}
