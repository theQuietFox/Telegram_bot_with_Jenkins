package irakulikova;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import utils.FakerDataCreator;
import utils.SetUp;
import utils.StudentData;



public class RegistrationFormWithFakerTest extends SetUp {
    RegistrationPage registrationPage = new RegistrationPage();
    StudentData studentData = FakerDataCreator.getRandomStudent();

    @Test
    @Feature("Форма регистрации студента")
    @Story("Заполнение формы регистрации")
    @Owner("ru.qa.irakulikova")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Testing Form", url = "https://demoqa.com/automation-practice-form")
    @DisplayName("Заполняем форму регистрации сгенерированными данными")
    @Tag("remote")
    void successfulRegistrationForm() {

        SelenideLogger.addListener("allure", new AllureSelenide());

        registrationPage.openPage()
                .removeBanners()
                .setUserFirstName(studentData.getFirstName())
                .setUserLastName(studentData.getLastName())
                .setUserEmail(studentData.getUserEmail())
                .setUserGender(studentData.getGender())
                .setUserMobileNumber(studentData.getUserPhone())
                .setUserBirthDayDate(studentData.getDay(),
                        studentData.getMonth(),
                        studentData.getYear())
                .setUserSubjects(studentData.getSubject())
                .setUserHobbies(studentData.getHobby())
                .uploadUserPicture(studentData.getFilePath())
                .setUserAddress(studentData.getAddress())
                .setUserState(studentData.getState())
                .setUserCity(studentData.getCity())
                .clickSubmit();

        registrationPage.verifyModalAppears()
                .verifyResult("Student Name", studentData.getFirstName() + " " +
                        studentData.getLastName())
                .verifyResult("Student Email", studentData.getUserEmail())
                .verifyResult("Gender", studentData.getGender())
                .verifyResult("Mobile", studentData.getUserPhone())
                .verifyResult("Date of Birth", studentData.getDay() + " "
                        + studentData.getMonth() + "," + studentData.getYear())
                .verifyResult("Subjects", studentData.getSubject())
                .verifyResult("Hobbies", studentData.getHobby())
                .verifyResult("Picture", studentData.getFilePath()
                        .substring(studentData.getFilePath().lastIndexOf("/") + 1))
                .verifyResult("Address", studentData.getAddress())
                .verifyResult("State and City", studentData.getState() + " "
                        + studentData.getCity());

    }
}