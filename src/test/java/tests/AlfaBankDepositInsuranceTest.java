package tests;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

/* Запрограммируйте тест переход на страницу Вклады->Страхование
вкладов, используя для поиска sibling(), preceding(), parent(), closest()*/

public class AlfaBankDepositInsuranceTest {

    @Test
    void DepositInsuranceContentTest() {

        open("https://alfabank.ru/");
        $$(byText("Вклады")).get(0).click();
        $(".a1Etq03").scrollTo();
        $(byText("Страхование вкладов")).click();

        SelenideElement firstTab =  $$(".f1Etq03 .a1Etq03").first(); // первый из четырех табов внутри "Страхование вкладов"

        $$(".a1Etq03").shouldHaveSize(4); //проверка, что табов всего 4
        firstTab.$("div span").shouldHave(text("Альфа-Банк является участником системы обязательного страхования вкладов"));
        firstTab.sibling(0).$("div span").shouldHave(text("Федеральный закон")); // 2nd tab
        firstTab.sibling(1).$("div span").shouldHave(text("Страхованию подлежат")); //3rd tab
        firstTab.sibling(2).scrollTo();
        firstTab.sibling(2).$("div span").shouldHave(text("Как происходит возмещение средств?")); //4th tab
        firstTab.parent().parent().shouldHave(attribute("data-widget-name","BlockV2"));
        firstTab.closest("div").shouldHave(attribute("class", "f1Etq03"));
    }
}
