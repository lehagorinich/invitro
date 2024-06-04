package pages;

import com.alexpozdnyakov.invitro.exception.MyException;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.And;
import org.assertj.core.api.Assertions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.sleep;

public class SelectAnalysPage {

    private final SelenideElement selectAnalys = $x("//*[contains(text(),'Глюкоза (в крови)')]");

    private final SelenideElement amountInAnalysPage = $x("//*[contains(@class,'info-block__section info-block__section--price')]//*[contains(@itemprop,'price')]");

    private final SelenideElement bucketButton = $x("//*[contains(text(),'В корзину')][1]");

    private final SelenideElement amountInBucketPage = $x("//*[contains(@class,'SummaryBlock_cartContent__Vo9PC')]/div/div");

    int value;
    int valueInBucket;

    @And("Выбрать анализ и перейти на его страницу")
    public void selectAnalysAndGoToPage() {
        selectAnalys.scrollTo().click();
    }

    @And("Запомнить цену и нажать кнопку В корзину")
    public void addToBucket() {
        String a = amountInAnalysPage.getText();
        Pattern pattern = Pattern.compile("\\d+"); // Ищем одно или более цифр
        Matcher matcher = pattern.matcher(a);

        if (matcher.find()) {
            String numberString = matcher.group(); // Получаем найденное числовое значение в виде строки
            value = Integer.parseInt(numberString);
            System.out.println(value);
        }
        bucketButton.click();
        sleep(2000);
    }

    @And("Перейти в корзину")
    public void clickOnBucketButton() {
        Selenide.open("https://lk3.invitro.ru/cart");
    }

    @And("Сравнить сумму в корзине с запомненной суммой")
    public void compareAmount() {
        String a = amountInBucketPage.getText();
        Pattern pattern = Pattern.compile("\\d+"); // Ищем одно или более цифр
        Matcher matcher = pattern.matcher(a);

        if (matcher.find()) {
            String numberString = matcher.group(); // Получаем найденное числовое значение в виде строки
            valueInBucket = Integer.parseInt(numberString);
            System.out.println(valueInBucket);
            Assertions.assertThat(value).as("Сумма не совпадает").isEqualTo(valueInBucket);
        }
    }

    @And("Сравнить относительно {string} рублей")
    public void priceComparison(String amount) throws MyException {
        if (valueInBucket > Integer.parseInt(amount)) {
            System.out.println("Цена больше 10000 рублей");
        } else if (valueInBucket < Integer.parseInt(amount)) {
            System.out.println("Цена меньше 10000 рублей");
        } else throw new MyException("Цена 10000 рублей");
    }
}

