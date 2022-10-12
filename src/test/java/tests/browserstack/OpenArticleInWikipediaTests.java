package tests.browserstack;

import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;

public class OpenArticleInWikipediaTests extends TestBase {

    @Test
    @Tag("openPage")
    void openJavaArticle() {

        step("Skip onboarding", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")).click());

        step("Type search", () -> {
            $(AppiumBy.accessibilityId("Search Wikipedia")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text"))
                    .sendKeys("Java");
        });

        step("Verify content found", () ->
                $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));

        step("Open article", () ->
                $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title")).
                        filterBy(text("Java (programming language)")).first().click());

        step("Verify content found", () ->
                $(AppiumBy.xpath("//android.widget.TextView[2][@resource-id='pcs-edit-section-title-description']"))
                        .shouldHave(text("Object-oriented programming language")));
    }

}
