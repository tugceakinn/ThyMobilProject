import com.thoughtworks.gauge.Step;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import model.PassengerInfo;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;


public class StepImplementation extends HookImpl {

    PassengerInfo passengerInfo;


    private MobileElement findElement(By by) {

        WebDriverWait webDriverWait = new WebDriverWait(appiumDriver, 20);
        MobileElement mobileElement = (MobileElement) webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));
        return mobileElement;
    }


    private void clickToElement(By by) {

        findElement(by).click();
    }

    private String getText(By by) {

        return findElement(by).getText();
    }

    private String getAttribute(By by, String attributeName) {

        return findElement(by).getAttribute(attributeName);
    }

    private void sendKeysToElement(By by, String text) {

        findElement(by).sendKeys(text);
    }

    private void swipe(int startX, int startY, int endX, int endY) {

        Dimension dimension = appiumDriver.manage().window().getSize();
        int width = dimension.width;
        int height = dimension.height;
        TouchAction action = new TouchAction(appiumDriver);
        action.press(PointOption.point((width * startX) / 100, (height * startY) / 100))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
                .moveTo(PointOption.point((width * endX) / 100, (height * endY) / 100))
                .release().perform();
    }

    private void waitBySeconds(long seconds) {

        waitByMilliSeconds(seconds * 1000);
    }

    private void waitByMilliSeconds(long milliSeconds) {

        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step("<key> butonuna tıkla")
    public void implementation1(String key) {

        clickToElement(selector.getElementInfoToBy(key));

        waitBySeconds(3);
    }

    @Step("<key> elementine <text> degeri yazılır")
    public void elementisendkeysle(String key, String text) throws Exception {

        sendKeysToElement(selector.getElementInfoToBy(key), text);
    }


    @Step("<startX> ve <startY> baslangic koordinatindan <endX> ve <endY> koordinatına swipe yap")
    public void swipe1(int startX, int startY, int endX, int endY) {

        swipe(startX, startY, endX, endY);
    }


    @Step({"<saniye> saniye bekle"})
    public void waitBySeconds2(int seconds) throws InterruptedException {
        waitByMillis(seconds * 1000);
    }

    @Step({"<milli> milisaniye bekle"})
    public void waitByMillis(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }

    @Step({"back to button"})
    public void backtoButton() {
        // appiumDriver.navigate().back();
        appiumDriver.hideKeyboard();
    }


    @Step("swipe et")
    public void swipeMethod() {
        if (appiumDriver instanceof IOSDriver) {
            Dimension size = appiumDriver.manage().window().getSize();
            int x = size.getWidth() -1;
            int starty = (int) (size.getHeight() * 0.60);
            int endy = (int) (size.getHeight() * 0.10);

            new TouchAction(appiumDriver).longPress(PointOption.point(x, starty))
                    .moveTo(PointOption.point(x, endy))
                    .release().perform();
        } else {
            new TouchAction(appiumDriver).longPress(PointOption.point(330, 800))
                    .moveTo(PointOption.point(330, 568))
                    .release().perform();
        }

        System.out.println("swipe yapıldı");
    }



    @Step("koordinat click")
    public void tikla() {
        MobileElement element = findElement(selector.getElementInfoToBy("aaaaa"));
        TouchAction a = new TouchAction(appiumDriver);
        System.out.println(element.getLocation().x + "y=" + element.getLocation().y);
        a.tap(PointOption.point(element.getLocation().x, element.getLocation().y));
    }


    @Step("Gün tarihinden 2 gün sonrayı seç")
    public void gununtarihiniAlan() {
        int dayToBeSelected = LocalDate.now().getDayOfMonth(); //gün tarihini alır
        //int dayToBeSelected = LocalDate.now().plusDays(2).getDayOfMonth();

    }


    @Step("csv oku")
    public void reaidcsvSample() {
        String csvFile = "/Users/sahabt/Desktop/addresses.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] passenger = line.split(cvsSplitBy);
                passengerInfo = new PassengerInfo(passenger[0], passenger[1], passenger[2], passenger[3], passenger[4], passenger[5], passenger[6], passenger[7], passenger[8], passenger[9]);
                System.out.println("Info" + passenger[0] + ", " + passenger[1] + ", " + passenger[2] + ", " + passenger[3] + ", " + passenger[4] + ", " + passenger[5] + ", " + passenger[6] + ", " + passenger[7] + ", " + passenger[8] + ", " + passenger[9]);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Step("yolcu <index> datalarını oku")
    public void reaidcsvSample(int index) {
        String csvFile = "./addresses.csv";
        int i = 1;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] passenger = line.split(cvsSplitBy);
                if(i == index){
                    passengerInfo = new PassengerInfo(passenger[0], passenger[1], passenger[2], passenger[3], passenger[4], passenger[5], passenger[6], passenger[7], passenger[8],passenger[9]);
                }
                i++;

                System.out.println("Info" + passenger[0] + ", " + passenger[1] + ", " + passenger[2] + ", " + passenger[3] + ", " + passenger[4] + ", " + passenger[5] + ", " + passenger[6] + ", " + passenger[7] + ", " + passenger[8] + ", " + passenger[9]);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Step("<key> elementine <deger> ata")
    public void elementeDegerAta(String key, String deger) throws Exception {
        switch (deger) {
            case "ad":
                sendKeysToElement(selector.getElementInfoToBy(key), passengerInfo.getAd());
                break;
            case "soyad":
                sendKeysToElement(selector.getElementInfoToBy(key), passengerInfo.getSoyad());
                break;

            case "cinsiyet":
                clickToElement(selector.getElementInfoToBy(key));
                break;

            case "dogumyili":
                clickToElement(selector.getElementInfoToBy(key));
                break;

            case "dogumayi":
                clickToElement(selector.getElementInfoToBy(key));
                break;
            case "dogumgünü":
                clickToElement(selector.getElementInfoToBy(key));
                break;
            case "eposta":
                sendKeysToElement(selector.getElementInfoToBy(key), passengerInfo.getMail());
                break;
            case "tc":
                clickToElement(selector.getElementInfoToBy(key));
                break;
            case "tcGir":
                sendKeysToElement(selector.getElementInfoToBy(key), passengerInfo.getTc());
                break;
            case "TekYönUcusButonu":
                clickToElement(selector.getElementInfoToBy(key));
                break;
            case "telefon":
                clickToElement(selector.getElementInfoToBy(key));
                break;

        }
    }

    @Step("2 gün sonrası seçilir")
    public void selectDepDate() {

        int dayToBeSelected = LocalDate.now().plusDays(3).getDayOfMonth();
        int today = LocalDate.now().plusDays(0).getDayOfMonth();
        System.out.println("Departure, day of month : " + dayToBeSelected);

        if (appiumDriver instanceof AndroidDriver) {

            if (today >= 31) {

                waitBySecond(3);
                swipeMethod();
                waitBySecond(3);
                swipeMethod();
                waitBySecond(3);
                swipeMethod();
                waitBySecond(3);
                swipeMethod();
                waitBySecond(5);

                findElement(By.xpath("//*[@text='" + dayToBeSelected + "']")).click();
            } else {

                findElement(By.xpath("//*[@text='" + dayToBeSelected + "']")).click();
            }
        }
    }

    @Step({"Wait <second> seconds"})
    public void waitBySecond(int seconds) {

        try {
            Thread.sleep(seconds * 1000);
            System.out.println(seconds +  " saniye bekleniyor. ");
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step("Rastgele Tıklama <key>")
    public void randomMarka(String key) {
        List<WebElement> boskoltuk = (List<WebElement>) selector.getElementInfoToBy(key);

        int randKoltuk = randomNumber(0, boskoltuk.size());

        boskoltuk.get(randKoltuk).click();
    }

    public static int randomNumber(int start, int end) {
        Random rn = new Random();
        int randomNumber = rn.nextInt(end - 1) + start;
        return randomNumber;
    }


}
