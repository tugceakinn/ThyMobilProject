import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.remote.DesiredCapabilities;
import selector.Selector;
import selector.SelectorFactory;
import selector.SelectorType;

import java.net.MalformedURLException;
import java.net.URL;

public class BasePage {

    public static boolean localAndroid = true;
    protected static Selector
            selector;

    protected static AppiumDriver appiumDriver;

    @BeforeScenario
    public void beforeScenario() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities
                .setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Motorola");
       // desiredCapabilities.setCapability(MobileCapabilityType.UDID, "ZY322BFSN5");
        desiredCapabilities
                .setCapability(AndroidMobileCapabilityType.APP_PACKAGE,
                        "com.pozitron.pegasus");
        desiredCapabilities
                .setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,
                        "com.monitise.mea.pegasus.ui.splash.SplashActivity");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, true);
        desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300);
        desiredCapabilities.setCapability("unicodeKeyboard", false);
        desiredCapabilities.setCapability("resetKeyboard", false);
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        appiumDriver = new AndroidDriver(url, desiredCapabilities);
        selector = SelectorFactory
                .createElementHelper(localAndroid ? SelectorType.ANDROID : SelectorType.IOS);
    }

    @AfterScenario
    public void afterScenario(){

        if(appiumDriver != null){
            appiumDriver.quit();
        }
    }

}
