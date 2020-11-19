package tests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import java.io.File;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.*;

public class AutomationPracticeForm {

    @Test
    void fillTheFormPositiveCase() {

        // test data

        String  firstName = "Joe";
        String  lastName = "Doe";
        String  userEmail = "test@email.com";
        String  userNumber = "2104443345";
        String  genderId = "3"; // 1 - Male, 2 - Female, 3 - Other
        String  currentAddress = "Test Address, 45 - 56";
        String  month = "January";
        String  day = "17"; // two digits
        String  year = "1992";
        String[] Subjects = {"Chemistry", "English", "Computer Science"}; // Subjects = {"Computer Science", "Chemistry", "English"};
        String[] Hobbies = {"1"}; // 1 - Sports, 2- Reading, 3 - Music
        File file = new File("src/test/java/docs/58832_300x300.jpg");

        // ----------------------------------------------------------------------------------------------------------------

        Configuration.startMaximized = true;
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

        for (String subject : Subjects) {
            $("#subjectsInput").val(subject).pressEnter();
        }

        for (String hobby : Hobbies) {
            $(byAttribute("for", "hobbies-checkbox-" + hobby)).click();
        }

        $("#uploadPicture").uploadFile(file);

        $("#state").scrollTo().click();
        $("#react-select-3-option-2").click();
        $("#city").click();
        $("#react-select-4-option-0").click();

        $("#submit").click();

        // check output table

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $$("tr").filterBy(text("Label")).shouldBe(CollectionCondition.itemWithText("Label Values"));
        $$("tr").filterBy(text("Student Name")).shouldBe(CollectionCondition.itemWithText("Student Name " + firstName + " " + lastName));
        $$("tr").filterBy(text("Student Email")).shouldBe(CollectionCondition.itemWithText("Student Email " + userEmail));

        if (genderId.equals("1")) {
            $$("tr").filterBy(text("Gender")).shouldBe(CollectionCondition.itemWithText("Gender Male"));
        } else if (genderId.equals("2")) {
            $$("tr").filterBy(text("Gender")).shouldBe(CollectionCondition.itemWithText("Gender Female"));
        } else {
            $$("tr").filterBy(text("Gender")).shouldBe(CollectionCondition.itemWithText("Gender Other"));
        }

        $$("tr").filterBy(text("Mobile")).shouldBe(CollectionCondition.itemWithText("Mobile " + userNumber));
        $$("tr").filterBy(text("Date of Birth")).shouldBe(CollectionCondition.itemWithText("Date of Birth " + day + " " + month + "," + year));

        String str =  "";
        for (int i = 0; (Subjects.length - 1) > i; i++) {
            str = str + Subjects[i] + ", ";
        }
        $$("tr").filterBy(text("Subjects")).shouldBe(CollectionCondition.itemWithText("Subjects " + str + Subjects[Subjects.length-1]));

        String str2 = "Hobbies ";
        for (int a = 0; a < Hobbies.length; a++) {
            if (Hobbies[a].equals("1")) {
               str2 = str2 + "Sports";
            } else if (Hobbies[a].equals("2")) {
                str2 = str2 + "Reading";
            } else {
                str2 = str2 + "Music";
            }
            if (a < Hobbies.length  - 1)  {
                str2 = str2 + ", ";
            }
        }
        $$("tr").filterBy(text("Hobbies")).shouldBe(CollectionCondition.itemWithText(str2));

        $$("tr").filterBy(text("Picture")).shouldBe(CollectionCondition.itemWithText("Picture " + file.getName()));
        $$("tr").filterBy(text("Address")).shouldBe(CollectionCondition.itemWithText("Address " + currentAddress));
        $$("tr").filterBy(text("State and City")).shouldBe(CollectionCondition.itemWithText("State and City Haryana Karnal"));

        // check output table closing

        $("#closeLargeModal").should(exist);
        $("#closeLargeModal").shouldHave(type("button"));
        $("#closeLargeModal").click();
        $(".modal-content").shouldNot(exist);

        closeWindow();
        System.out.print("");
    }
}
