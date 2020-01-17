import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.apache.commons.lang.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import selector.Selector;
import selector.SelectorFactory;
import selector.SelectorType;

import java.net.MalformedURLException;
import java.net.URL;

public class HookImpl {

    public static boolean localAndroid = true;
    protected static Selector
            selector;

    protected static AppiumDriver appiumDriver;
    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected static IOSDriver<MobileElement> mobileElementIOSDriver;
    protected static FluentWait<AppiumDriver> appiumFluentWait;
    protected boolean localiOS = false;//turn off android


    @BeforeScenario
    public void beforeScenario() throws MalformedURLException {
        if (StringUtils.isEmpty(System.getenv("key"))) {
            if (localiOS) {

                DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                desiredCapabilities
                        .setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
                desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
                desiredCapabilities
                        .setCapability(MobileCapabilityType.UDID, "8cf88820750279d6cd49b48d5a079a9315598778");
                desiredCapabilities
                        .setCapability(IOSMobileCapabilityType.BUNDLE_ID, "trendyol.com.trendyol");
                desiredCapabilities
                        .setCapability(MobileCapabilityType.DEVICE_NAME, "sahaBT iPhone'u");
                desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, true);
                desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12.4.2");
                desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300);
                URL url = new URL("http://127.0.0.1:4723/wd/hub");
                appiumDriver = new IOSDriver<>(url, desiredCapabilities);

            } else {
                DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                desiredCapabilities
                        .setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
                desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "G5");
                 desiredCapabilities.setCapability(MobileCapabilityType.UDID, "LGH860b84c3911");
                desiredCapabilities
                        .setCapability(AndroidMobileCapabilityType.APP_PACKAGE,
                                "com.turkishairlines.mobile");
                desiredCapabilities
                        .setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,
                                "com.turkishairlines.mobile.ui.main.MainActivity");
                desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
                desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, true);
                desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300);
                desiredCapabilities.setCapability("unicodeKeyboard", false);
                desiredCapabilities.setCapability("resetKeyboard", false);
                URL url = new URL("http://127.0.0.1:4723/wd/hub");
                appiumDriver = new AndroidDriver(url, desiredCapabilities);
            }
            selector = SelectorFactory
                    .createElementHelper(!localiOS ? SelectorType.ANDROID : SelectorType.IOS);
        }
    }

        @AfterScenario
        public void afterScenario () {

            if (appiumDriver != null) {
                appiumDriver.quit();
            }
        }

    }

