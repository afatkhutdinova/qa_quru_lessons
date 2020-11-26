package tests;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AutomationPracticeForm {

    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
    }

    @Test
    void positiveSubmitFormTest() {

        // test data
        String  firstName = "Joe";
        String  lastName = "Doe";
        String  userEmail = "test@email.com";
        String  userNumber = "2104443345";
        String  genderId = "2"; // 1 - Male, 2 - Female, 3 - Other
        String  currentAddress = "Test Address, 45 - 56";
        String  month = "January";
        String  day = "17"; // two digits
        String  year = "1992";
        String[] Subjects = {"Chemistry", "English", "Computer Science"};
        //String[] Hobbies = {"1", "2"}; // 1 - Sports, 2- Reading, 3 - Music
        File file = new File("src/test/resources/58832_300x300.jpg");

        // actions
        open("https://demoqa.com/automation-practice-form");
        $("#firstName").val(firstName);
        $("#lastName").val(lastName);
        $("#userEmail").val(userEmail);
        $(by("for", "gender-radio-" + genderId)).click();
        $("#userNumber").val(userNumber);

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionContainingText(month);
        $(".react-datepicker__year-select").selectOptionByValue(year);
        $(".react-datepicker__day--0" + day).click();

        $("#currentAddress").val(currentAddress);

        $("#subjectsInput").val(Subjects[0]).pressEnter();
        $("#subjectsInput").val(Subjects[1]).pressEnter();
        $("#subjectsInput").val(Subjects[2]).pressEnter();

        $(byText("Sports")).click();
        $(byText("Reading")).click();

        $("#uploadPicture").uploadFile(file);

        $("#state").scrollTo().click();
        $(byText("Haryana")).click();
        $("#city").click();
        $(byText("Karnal")).click();

        $("#submit").click();

        // check output table
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $x("//td[text()='Student Name']").sibling(0).shouldHave(text(firstName + " " + lastName));
        $x("//td[text()='Student Email']").sibling(0).shouldHave(text(userEmail));
        $x("//td[text()='Gender']").sibling(0).shouldHave(text("Female"));
        $x("//td[text()='Mobile']").sibling(0).shouldHave(text(userNumber));
        $x("//td[text()='Date of Birth']").sibling(0).shouldHave(text(day + " " + month + "," + year));

        $x("//td[text()='Subjects']").sibling(0).shouldHave(text(Subjects[0]));
        $x("//td[text()='Subjects']").sibling(0).shouldHave(text(Subjects[1]));
        $x("//td[text()='Subjects']").sibling(0).shouldHave(text(Subjects[2]));

        $x("//td[text()='Hobbies']").sibling(0).shouldHave(text("Sports"));
        $x("//td[text()='Hobbies']").sibling(0).shouldHave(text("Reading"));

        $x("//td[text()='Picture']").sibling(0).shouldHave(text(file.getName()));
        $x("//td[text()='Address']").sibling(0).shouldHave(text(currentAddress));
        $x("//td[text()='State and City']").sibling(0).shouldHave(text("Haryana Karnal"));
    }
}
