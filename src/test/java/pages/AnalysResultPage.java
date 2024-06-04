package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.And;
import org.assertj.core.api.Assertions;

import static com.codeborne.selenide.Selenide.$x;

public class AnalysResultPage {

    private final SelenideElement namePage = $x("//*[contains(text(),'Введите индивидуальный')]");

    private final SelenideElement findResultButton = $x("//*[contains(text(),'Найти результаты')]");

    private final SelenideElement error = $x("//*[contains(@class,'UnauthResultsPage_error__m2C-2')]");

    private final SelenideElement errorInput = $x("//*[contains(@class,'Input_error')]");

    private final SelenideElement orderNumberField = $x("//*[contains(@name,'orderNumber')]");

    private final SelenideElement birthdayField = $x("//*[contains(@name,'birthday')]");

    private final SelenideElement lastnameField = $x("//*[contains(@name,'lastName')]");

    private final SelenideElement selectMonth = $x("//*[contains(@class,'react-datepicker__month-select')]");

    private final SelenideElement selectYear = $x("//*[contains(@class,'react-datepicker__year-select')]");

    private final SelenideElement setDecember = $x("//*[contains(text(),'декабрь')]");

    private final SelenideElement setYear = $x("//*[contains(text(),'2000')]");

    private final SelenideElement setDay = $x("//*[contains(@class,'react-datepicker__day react-datepicker__day--011')]");

    @And("Заголовок страницы равен {string}")
    public void clickCityButton(String a) {
        String name = namePage.getText();
        Assertions.assertThat(name).as("Другой заголовок страницы").isEqualTo(a);
    }

    @And("Нажать Найти результаты")
    public void clickfindResultButton() {
        findResultButton.shouldBe(Condition.enabled).click();
    }

    @And("Появилось предупреждение {string}")
    public void checkErrorMassage(String arg0) {
        String a = error.getText();
        Assertions.assertThat(a).as("Предупреждение не появилось").isEqualTo(arg0);
    }

    @And("Поля подсвечены красным")
    public void checkErrorInput() {
        Boolean b = errorInput.isDisplayed();
        Assertions.assertThat(b).as("Поля не подсвечены красным").isTrue();
    }

    @And("Заполнить поле Код ИНЗ значением {string}")
    public void setOrderNumberField(String value) {
        orderNumberField.shouldBe(Condition.enabled).clear();
        orderNumberField.sendKeys(value);

        Assertions.assertThat(orderNumberField.getValue()).as("Введено другое значение").isEqualTo(value);
    }

    @And("Заполнить поле Дата рождения значением {string}")
    public void setBirthdayField(String birthday) {
        birthdayField.shouldBe(Condition.enabled).click();
        selectMonth.click();
        setDecember.click();
        selectYear.click();
        setYear.click();
        setDay.click();

        Assertions.assertThat(birthdayField.getValue()).as("Веденно другое значение").isEqualTo(birthday);
    }

    @And("Заполнить поле Фамилия значением {string}")
    public void setLastname(String lastname) {
        lastnameField.shouldBe(Condition.enabled).clear();
        lastnameField.sendKeys(lastname);

        Assertions.assertThat(lastnameField.getValue()).as("Введено другое значение").isEqualTo(lastname);
    }
}
