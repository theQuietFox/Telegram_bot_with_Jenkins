package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Tag;
import pages.components.CalendarComponent;
import pages.components.RegistrationResultsModal;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

@Tag("registration")
public class RegistrationPage {

    private final RegistrationResultsModal registrationResultsModal = new RegistrationResultsModal();
    private String TITLE_TEXT = "Student Registration Form";
    private final SelenideElement
            practiceFormWrapper = $(".practice-form-wrapper"),
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            userDateOfBirth = $("#dateOfBirthInput"),
            userGenderInput = $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            userSubjectsInput = $("#subjectsInput"),
            userHobbiesInput = $("#hobbiesWrapper"),
            userUploadPicture = $("#uploadPicture"),
            userAddressInput = $("#currentAddress"),
            userStateClick = $("#state"),
            stateCityWrapper = $("#stateCity-wrapper"),
            userCityClick = $("#city"),
            submitButton = $("#submit");

    private final String urlPath = "/automation-practice-form";

    @Step("Открываем страницу Practice form")
    public RegistrationPage openPage() {
        open(urlPath);
        practiceFormWrapper.shouldHave(text(TITLE_TEXT));

        return this;
    }
    @Step("Убираем футер и баннер")
    public RegistrationPage removeBanners() {
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");

        return this;
    }

    @Step("Заполняем имя значением {value}")
    public RegistrationPage setUserFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    @Step("Заполняем фамилию значением {value}")
    public RegistrationPage setUserLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    @Step("Заполняем email значением {value}")
    public RegistrationPage setUserEmail(String value) {
        userEmailInput.setValue(value);

        return this;
    }

    @Step("Заполняем пол значением {value}")
    public RegistrationPage setUserGender(String value) {
        userGenderInput.$(byText(value)).click();

        return this;
    }


    @Step("Заполняем номер телефона значением {value}")
    public RegistrationPage setUserMobileNumber(String value) {
        userNumberInput.setValue(value);

        return this;
    }

    @Step("Заполняем дату рождения значением {value}")
    public RegistrationPage setUserBirthDayDate(String day, String month, String year) {
        userDateOfBirth.click();
        CalendarComponent.setDate(day, month, year);

        return this;
    }

    @Step("Заполняем предмет значением {value}")
    public RegistrationPage setUserSubjects(String value) {
        userSubjectsInput.setValue(value).pressEnter();

        return this;
    }

    @Step("Заполняем хобби значением {value}")
    public RegistrationPage setUserHobbies(String value) {
        userHobbiesInput.$(byText(value)).click();

        return this;
    }

    @Step("Загружаем картинку {value}")
    public RegistrationPage uploadUserPicture(String value) {
        File fileToUpload = new File(value);
        userUploadPicture.uploadFile(fileToUpload);

        return this;
    }

    @Step("Заполняем адрес значением {value}")
    public RegistrationPage setUserAddress(String value) {
        userAddressInput.setValue(value);

        return this;
    }

    @Step("Заполняем штат значением {value}")
    public RegistrationPage setUserState(String value) {
        userStateClick.click();
        stateCityWrapper.$(byText(value)).click();

        return this;
    }

    @Step("Заполняем город значением {value}")
    public RegistrationPage setUserCity(String value) {
        userCityClick.click();
        stateCityWrapper.$(byText(value)).click();

        return this;
    }

    @Step("Нажимаем кнопку отправки формы")
    public void clickSubmit() {
        submitButton.click();
    }

    @Step("Проверяем появление модального окна")
    public RegistrationPage verifyModalAppears() {
        RegistrationResultsModal.verifyModalAppears();

        return this;
    }

    @Step("Проверяем содержимое модального окна - в поле {key} лежит значение {value}")
    public RegistrationPage verifyResult(String key, String value) {
        registrationResultsModal.verifyResult(key, value);

        return this;
    }

}



