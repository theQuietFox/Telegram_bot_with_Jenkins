package utils;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.components.Attach;

import java.util.Map;

public class SetUp {

    @BeforeAll
    static void setUpTest() {

        Configuration.baseUrl = System.getProperty("base_url", "https://demoqa.com" );
        Configuration.remote = "https://user1:1234@" +
                System.getProperty("selenoid_url", "selenoid.autotests.cloud/wd/hub");
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion = System.getProperty("browser_version", "100.0");
        Configuration.browserSize = System.getProperty("browser_size", "1920x1080");



        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));

        Configuration.browserCapabilities = capabilities;
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}
