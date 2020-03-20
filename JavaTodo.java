
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.json.JsonOutput;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class JavaTodo
{
    String username = "keshavi";
    String accesskey = "n7cGpjq81T4VNJ8ya9CjAwumvNRBSujycArmNVjboqHGztNky8";

    static RemoteWebDriver driver = null;
    String gridURL = "@hub.lambdatest.com/wd/hub";
    boolean status = false;
    public static void main(String[] args)
    {
        new JavaTodo().setUp();

        new JavaTodo().test();
        new JavaTodo().tearDown();
    }
    public void test()
    {
        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
        driver.get("https://app.lambdatest.com/console/realtime");

        driver.findElement(By.name("email")).sendKeys(new CharSequence[]{"keshavi@lambdatest.com"});
        driver.findElement(By.id("userpassword")).sendKeys(new CharSequence[]{"KIki@100%"});
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div/form/div[3]/button")).click();

        driver.findElement(By.xpath("/html/body/app-root/app-console/app-header/header/aside/ul/li[2]/a")).click();

        driver.findElement(By.id("input-text")).sendKeys(new CharSequence[]{"https://www.geeksforgeeks.org/"});

        driver.findElement(By.xpath("/html/body/app-root/app-console/app-header/section/app-test-detail/div[1]/div[1]/div/div[1]/form/div[3]/button")).click();

    }

    public void setUp() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "Firefox");
        capabilities.setCapability("version", "70.0");
        capabilities.setCapability("platform", "win10"); // If this cap isn't specified, it will just get any available one.
        capabilities.setCapability("build", "LambdaTestSampleApp");
        capabilities.setCapability("name", "LambdaTestJavaSample");
        capabilities.setCapability("network", true); // To enable network logs
        capabilities.setCapability("visual", true); // To enable step by step screenshot
        capabilities.setCapability("video", true); // To enable video recording
        capabilities.setCapability("console", true); // To capture console logs
        capabilities.setCapability("resolution","1920x1200");
        try
        {
            driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), capabilities);
        }
        catch (MalformedURLException e)
        {
            System.out.println("Invalid grid URL");
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
    public void tearDown() {
        if (driver != null)
        {
            ((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
            driver.quit(); //really important statement for preventing your test execution from a timeout.
        }
    }
}