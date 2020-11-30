package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class AlfaBankArchivedDepositsTest {

    @Test
    void archivedDepositsSizeTest() {
        open("https://alfabank.ru/make-money/archive/");
        $$(byText("Депозиты")).find(visible).click();
        $$(byAttribute("data-widget-name", "CatalogCard")).shouldHave(size(5));
    }
}
