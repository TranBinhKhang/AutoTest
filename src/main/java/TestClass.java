import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class TestClass {

    //SELECT is in lesson 10
    ChromeDriver driver;
    @BeforeTest
    public void Setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        Actions action = new Actions(driver);
    }

    @Test
    public void MultipleBrowser(){


        driver.get("https://www.lambdatest.com/selenium-playground/window-popup-modal-demo");
        driver.manage().window().maximize();
        Sleep(3000);
        // It will return the parent window name as a String
        String parent=driver.getWindowHandle();
        driver.findElement(By.xpath("/html/body/div[1]/section[3]/div/div/div[2]/div[1]/a[1]")).click();
        Set<String> s=driver.getWindowHandles();

        // Now iterate using Iterator
        Iterator<String> I1= s.iterator();

        while(I1.hasNext())
        {
            String child_window=I1.next();
            System.out.println("THIS AINT IT CHIEF");
            if(!parent.equals(child_window))
            {
                driver.switchTo().window(child_window);
                driver.manage().window().maximize();
                System.out.println("you got this chief");
                System.out.println(driver.switchTo().window(child_window).getTitle());
                Sleep(1000);
                driver.close();
            }

        }
        driver.switchTo().window(parent);
    }

    @Test
    public void Test(){

        Sleep(3000);
        driver.get("https://www.studentstutorial.com/demo/php/crud?id=1");

        //Click checkbox
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
        for (WebElement checkbox : checkboxes) {
            checkbox.click();
        }
        //click update
        driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/table/tbody/tr[1]/td[7]/a[1]")).click();
        Sleep(1000);
        driver.findElement(By.xpath("/html/body/div[3]/div/div/form/div[3]/button")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();



        Sleep(1000);
        driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[2]/a[1]")).click();
        Sleep(1000);
        driver.findElement(By.xpath("/html/body/div[2]/div/div/form/div[2]/div[1]/input")).sendKeys("HAVA NAGILA HAVA");
        driver.findElement(By.xpath("/html/body/div[2]/div/div/form/div[2]/div[2]/input")).sendKeys("HAVA NAGILA HAVA");
        driver.findElement(By.xpath("/html/body/div[2]/div/div/form/div[2]/div[3]/input")).sendKeys("HAVA NAGILA HAVA");
        driver.findElement(By.xpath("/html/body/div[2]/div/div/form/div[2]/div[4]/input")).sendKeys("HAVA NAGILA HAVA");

        String result = driver.findElement(By.cssSelector("#name")).getAttribute("value");
        Assert.assertEquals(result, "HAVA NAGILA HAVA");


        Sleep(3000);
    }


    public void removeAttribute(WebElement element, String attr) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attr + "')", element);
    }

    public void setAttribute(WebElement element, String attr) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('" + attr + "')", element);
    }

    public String getText(WebElement element) {
        return element.getAttribute("value");
    }

    public void sendText(WebElement element, String input) {
        element.clear();
        Sleep(100);
        element.sendKeys(input);
        Sleep(100);

    }


    @AfterMethod
    public void Cleanup(){
        System.out.println("STEINER LIBERATED BERLIN MIEN FUTEHR");
        Sleep(4000);
    }

    private void Sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception ex) {
            System.out.printf(ex.getMessage());
        }
    }


}
