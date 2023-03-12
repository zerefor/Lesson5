import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FormQATest {
    private WebDriver driver;

    @BeforeEach
    public void start() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        String url = "https://demoqa.com/automation-practice-form";
        driver.get(url);
        driver.manage().window().maximize();
    }

    @Test
    public void doPost() throws InterruptedException {

        WebElement firstName = driver.findElement(By.cssSelector("#firstName"));
        firstName.sendKeys("Rero");

        WebElement lastName = driver.findElement(By.cssSelector("[id='lastName']"));
        lastName.sendKeys("ReroRero");

        WebElement userEmail = driver.findElement(By.cssSelector("[id='userEmail']"));
        userEmail.sendKeys("ReroReroRero@gmail.com");

        WebElement userNumber = driver.findElement(By.cssSelector("[id='userNumber']"));
        userNumber.sendKeys("2142112345");

        WebElement currentAddress = driver.findElement(By.cssSelector("[id='currentAddress']"));
        currentAddress.sendKeys("Matrix");

        WebElement genderRadio = driver.findElement(By.cssSelector("label[for='gender-radio-1']"));
        genderRadio.click();

        driver.findElement(By.cssSelector("#dateOfBirthInput"));

        WebElement checkboxClick1 = driver.findElement(By.cssSelector("label[for*='checkbox-1']"));
        WebElement checkboxClick2 = driver.findElement(By.cssSelector("label[for*='checkbox-2']"));
        WebElement checkboxClick3 = driver.findElement(By.cssSelector("label[for*='checkbox-3']"));

        checkboxClick1.click();
        checkboxClick2.click();
        checkboxClick3.click();

        WebElement userDate = driver.findElement(By.cssSelector("div[class*='datepicker'] input[type='text']"));
        userDate.click();

        WebElement monthDate = driver.findElement(By.cssSelector("[class*='month-select']"));
        Select monthSelect = new Select(monthDate);
        monthSelect.getFirstSelectedOption();

        WebElement yearDate = driver.findElement(By.cssSelector("[class*='year-select']"));
        Select yearSelect = new Select(yearDate);
        yearSelect.getFirstSelectedOption();

        WebElement dayDate = driver.findElement(By.cssSelector("[class*='day--001 react-datepicker__day--weekend'][tabindex='-1']"));
        dayDate.click();

        WebElement state = driver.findElement(By.cssSelector("[id='react-select-3-input']"));
        state.sendKeys("Uttar Pradesh");
        state.sendKeys(Keys.RETURN);

        WebElement city = driver.findElement(By.cssSelector("[id='react-select-4-input']"));
        city.sendKeys("Agra");
        city.sendKeys(Keys.RETURN);

        WebElement subjects = driver.findElement(By.cssSelector("[id='subjectsInput']"));
        subjects.sendKeys("English");
        subjects.sendKeys(Keys.RETURN);

        By fileInput = By.cssSelector("[id='uploadPicture']");
        String filePath = "C:/Users/User/Downloads/unnamed.png";
        driver.findElement(fileInput).sendKeys(filePath);

        WebElement submitButton = driver.findElement(By.cssSelector("[id='submit']"));
        submitButton.click();

        Thread.sleep(1000);

        WebElement nameTable = driver.findElement(By.cssSelector("table[class*='table']  tbody tr:nth-child(1) td:nth-child(2)"));
        String nameTableString = nameTable.getText();
        String firstNameCheck = firstName.getAttribute("value");
        String lastNameCheck = lastName.getAttribute("value");
        Assertions.assertEquals(firstNameCheck + " " + lastNameCheck, nameTableString);

        WebElement emailTable = driver.findElement(By.cssSelector("table[class*='table']  tbody tr:nth-child(2) td:nth-child(2)"));
        String emailTableString = emailTable.getText();
        String userEmailCheck = userEmail.getAttribute("value");
        Assertions.assertEquals(userEmailCheck, emailTableString);

        WebElement genderTable = driver.findElement(By.cssSelector("table[class*='table']  tbody tr:nth-child(3) td:nth-child(2)"));
        String genderTableString = genderTable.getText();
        String genderRadioCheck = genderRadio.getText();
        Assertions.assertEquals(genderRadioCheck, genderTableString);

        Thread.sleep(1000);

        WebElement numberTable = driver.findElement(By.cssSelector("table[class*='table']  tbody tr:nth-child(4) td:nth-child(2)"));
        String numberTableString = numberTable.getText();
        String userNumberCheck = userNumber.getAttribute("value");
        Assertions.assertEquals(userNumberCheck, numberTableString);

        WebElement birthDateTable = driver.findElement(By.cssSelector("table[class*='table']  tbody tr:nth-child(5) td:nth-child(2)"));
        String brithDateTableString = birthDateTable.getText();
        Assertions.assertEquals("01 April,2023", brithDateTableString);

        WebElement subjectTable = driver.findElement(By.cssSelector("table[class*='table']  tbody tr:nth-child(6) td:nth-child(2)"));
        String subjectTableString = subjectTable.getText();
        WebElement subjectList = driver.findElement(By.cssSelector("[class*='subjects-auto-complete__value']"));
        List<WebElement> list = subjectList.findElements(By.cssSelector("[class*='css-1rhbuit-multiValue']"));

        Thread.sleep(1000);
        for (WebElement item : list) {
            String listCheck = item.getText();
            Assertions.assertEquals(listCheck, subjectTableString);
        }

        WebElement hobbiesTable = driver.findElement(By.cssSelector("table[class*='table']  tbody tr:nth-child(7) td:nth-child(2)"));
        String hobbiesTableString = hobbiesTable.getText();
        String checkboxString1 = checkboxClick1.getText();
        String checkboxString2 = checkboxClick2.getText();
        String checkboxString3 = checkboxClick3.getText();
        Assertions.assertEquals(checkboxString1 + ", " + checkboxString2 + ", " + checkboxString3, hobbiesTableString);

        WebElement pictureTable = driver.findElement(By.cssSelector("table[class*='table']  tbody tr:nth-child(8) td:nth-child(2)"));
        String pictureTableString = pictureTable.getText();
        WebElement pictureText = driver.findElement(By.cssSelector("[id='uploadPicture']"));
        String pictureString = pictureText.getAttribute("value");
        Assertions.assertEquals(pictureString.substring(pictureString.lastIndexOf("\\") + 1), pictureTableString);

        WebElement addressTable = driver.findElement(By.cssSelector("table[class*='table']  tbody tr:nth-child(9) td:nth-child(2)"));
        String addressTableString = addressTable.getText();
        String addressString = currentAddress.getAttribute("value");
        Assertions.assertEquals(addressString, addressTableString);

        WebElement stateCityTable = driver.findElement(By.cssSelector("table[class*='table']  tbody tr:nth-child(10) td:nth-child(2)"));
        String stateCityTableString = stateCityTable.getText();
        WebElement stateCityMain = driver.findElement(By.cssSelector("[id='stateCity-wrapper']"));
        List<WebElement> listStateCity = stateCityMain.findElements(By.cssSelector("[class='col-md-4 col-sm-12']"));

        Thread.sleep(1000);
        String cityStateCheck = null;
        String temp = null;
        for (WebElement item : listStateCity) {
            String listCheckStateCity = item.getText();
            if (cityStateCheck == null) {
                cityStateCheck = listCheckStateCity;
                temp = cityStateCheck;
            } else {
                cityStateCheck = temp + " " + listCheckStateCity;
            }
        }
        Assertions.assertEquals(cityStateCheck, stateCityTableString);
    }

    @AfterEach
    public void end() {
        driver.quit();
    }
}