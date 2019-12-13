import com.thoughtworks.gauge.Step;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StepImplementation extends BasePage {

    private MobileElement findElement(By by){

        WebDriverWait webDriverWait = new WebDriverWait(appiumDriver,20);
        MobileElement mobileElement = (MobileElement) webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));
        return mobileElement;
    }

    private void clickToElement(By by){

        findElement(by).click();
    }

    private String getText(By by){

        return findElement(by).getText();
    }

    private String getAttribute(By by, String attributeName){

        return findElement(by).getAttribute(attributeName);
    }

    private void sendKeysToElement(By by,String text){

        findElement(by).sendKeys(text);
    }

    private void swipe(int startX, int startY, int endX, int endY){

        Dimension dimension = appiumDriver.manage().window().getSize();
        int width = dimension.width;
        int height = dimension.height;
        TouchAction action = new TouchAction(appiumDriver);
        action.press(PointOption.point((width*startX)/100, (height*startY)/100))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
                .moveTo(PointOption.point((width*endX)/100, (height*endY)/100))
                .release().perform();
    }

    private void waitBySeconds(long seconds){

        waitByMilliSeconds(seconds*1000);
    }

    private void waitByMilliSeconds(long milliSeconds){

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

        sendKeysToElement(selector.getElementInfoToBy(key),text);
    }


    @Step("<startX> ve <startY> baslangic koordinatindan <endX> ve <endY> koordinatına swipe yap")
    public void swipe1(int startX, int startY, int endX, int endY) {

        swipe(startX, startY, endX, endY);
    }



    @Step("mesajlar uygulamasını başlat")
    public void mesajlaruygulamasinagirilmesi(){

        Activity activity = new Activity("com.google.android.apps.messaging", "com.google.android.apps.messaging.ui.conversationlist.ConversationListActivity");
        //activity.setAppWaitActivity()
        ((AndroidDriver) appiumDriver).startActivity(activity);
    }

    @Step("Sohbet Başlat butonuna tıkla")
    public void sohbetbaslatbutonunatikla(){
        appiumDriver.findElement(By.xpath("//android.widget.ImageView[@content-desc=\"Yeni mesaj\"]")).click();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step("<key> Sohbet Başlat butonuna tıkla")
    public void implementation(String key) {

        clickToElement(selector.getElementInfoToBy(key));

        waitBySeconds(3);
    }

    @Step("Alıcı kısmına numara girilir")
    public void alicikisminagirilir(){
        appiumDriver.findElement(By.xpath("//android.widget.MultiAutoCompleteTextView[@content-desc=\"Kime\"]")).sendKeys("05059208988");

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Step("Gönderilecek mesaj yazılır")
    public void gönderilecekmesajyazilir(){

        ((AndroidDriver) appiumDriver).pressKey(new KeyEvent(AndroidKey.ENTER));

        appiumDriver.findElement(By.xpath("//android.widget.EditText[@content-desc=\"Mesaj yaz\"]")).sendKeys("hello");


        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Step({"<saniye> saniye bekle"})
    public void waitBySeconds2(int seconds) throws InterruptedException {
        waitByMillis(seconds * 1000);
    }
    @Step({"<milli> milisaniye bekle"})
    public void waitByMillis(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }



}
