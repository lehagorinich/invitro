package pages;

import com.codeborne.selenide.*;
import io.cucumber.java.en.And;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.Keys;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class BasicPage {

    private final SelenideElement overCityButton = $x("//*[contains(text(),'Выбрать')]");

    private final SelenideElement cityButton = $x("//*[contains(@class,'city__name')]");

    private final SelenideElement cityInput = $x("//*[@id='select-basket-city-input']");

    private final SelenideElement searchOmskButton = $x("//*[contains(@class,'eac-item')]//*[contains(text(),'Омск')]");

    private final SelenideElement searchInput = $x("//*[contains(@class ,'search__input')]");

    private final SelenideElement analisResultButton=$x("//*[contains(@class,'invitro_header-bottom_right')]");

    private final SelenideElement audienceButton=$x("//*[contains(@id,'buttonOpenPopupTargetSTATICSTRINGFORID')][1]");

    @And("нажать на город")
    public void clickCityButton() {
        cityButton.shouldBe(Condition.visible).click();
    }

    @And("нажать Выбрать другой")
    public void clickOverButton() {
        overCityButton.shouldBe(Condition.visible).click();
    }

    @And("Ввести в поле значение {string} и кликнуть на результат")
    public void enterValueAndClickOnResault(String text) {
        cityInput.shouldBe(Condition.visible).sendKeys(text);
        searchOmskButton.click();
    }

    @And("Убедиться что выбран город {string}")
    public void checkValue(String input) {
        String a = cityButton.getText();
        Assertions.assertThat(a).as("Не совпадает").isEqualTo(input);
    }

    @And("Ввести код анализа {string} в поиск и нажать ENTER")
    public void SearchInput(String text) {
        searchInput.shouldBe(Condition.enabled).clear();
        searchInput.sendKeys(text);
        searchInput.sendKeys(Keys.ENTER);
    }

    @And("Нажать Получить результаты")
    public void clickAnalisResultButton() {
        analisResultButton.shouldBe(Condition.enabled).click();
    }

    @And("Нажать кнопку Выбора аудитории и выбрать {string}")
    public void selectAudience(String value) {
        audienceButton.click();
        $x("//span[contains(text(),'%s')]".formatted(value)).shouldBe(Condition.visible).click();
    }

    @And("Прокликать все меню на сайте")
    public void clichAllHref() {

        ElementsCollection hrefs=$$x("//*[contains(@class,'header-menu__content')]//*[@href]");
        List<String> links =new ArrayList<>();

        hrefs.forEach(x->links.add(x.getAttribute("href")));
        links.forEach(Selenide::open);
    }
}
