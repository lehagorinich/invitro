package steps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.Given;

public class BeforeStep {
    @Given("Открыт сайт {string}")
    public void openWebSite(String arg0) {
        Configuration.pageLoadTimeout=500000;
        Configuration.timeout=20000;
        Selenide.open(arg0);
    }
}
