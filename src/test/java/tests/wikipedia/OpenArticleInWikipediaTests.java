package tests.wikipedia;

import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

public class OpenArticleInWikipediaTests extends TestBase {

    @Test
    void openJavaArticle() {

        step("Type search", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")).click();
            $(AppiumBy.accessibilityId("Search Wikipedia")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text"))
                    .sendKeys("Java");
            $(AppiumBy.accessibilityId("Java (programming language)")).click();
        });

        step("Verify content found", () ->
                $(AppiumBy.accessibilityId("Java (programming language)"))
                        .shouldHave(text("Java (programming language)")));
    }

}
