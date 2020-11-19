package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class TextBoxTests {
    @Test
    void fillFormTest() {
        Configuration.startMaximized = true;
        open("https://demoqa.com/text-box");

        $("#userName").val("Alex");
        $("#userEmail").val("Egorov@alex.com");
        $("#currentAddress").val("Montenegro");
        $("#permanentAddress").val("Street 1");
        $("#submit").click();

        $("#output").shouldHave(text("Name:Alex\n" +
                "Email:Egorov@alex.com\n" +
                "Current Address :Montenegro\n" +
                "Permananet Address :Street 1"));
        System.out.printf("");
    }

    @Test
    void fillFormWithVariablesTest() {
        String userName = "Alex",
                email = "Egorov@alex.com",
                currentAddress = "Montenegro",
                permanentAddress = "Street 1";

        Configuration.startMaximized = true;
        open("https://demoqa.com/text-box");

        $("#userName").val(userName);
        $("#userEmail").val(email);
        $("#currentAddress").val(currentAddress);
        $("#currentAddress").val(currentAddress);
        $("#permanentAddress").val(permanentAddress);
        $("#submit").click();

        $("#output").shouldHave(text(
                "Name:" + userName + "\n" +
                "Email:Egorov@alex.com\n" +
                "Current Address :Montenegro\n" +
                "Permananet Address :Street 1"));

        $("#output").shouldHave(text(String.format(
                "Name:%s\n" +
                "Email:%s\n" +
                "Current Address :Montenegro\n" +
                "Permananet Address :Street 1", userName, email)));

        $("#name").shouldHave(text(userName));
    }


    @Test
    void wrongEmailTest() {
        Configuration.startMaximized = true;
        open("https://demoqa.com/text-box");

        $("#userName").val("Alex");
        $("#userEmail").val("Egorov");
        $("#currentAddress").val("Montenegro");
        $("#permanentAddress").val("Street 1");
        $("#submit").click();

        $("#userEmail").shouldHave(cssClass("field-error"));

        System.out.printf("");
    }
}
