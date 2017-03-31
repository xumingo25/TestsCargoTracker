package test.resources;

import java.awt.Robot;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BookFeature {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  public static String curDir = System.getProperty("user.dir");

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void testTC003FeatureBook() throws Exception {
	 System.setProperty("webdriver.chrome.driver", curDir
				+ "\\Drivers\\chromedriver.exe");
	driver = new ChromeDriver();
	  
	baseUrl = "http://192.168.17.223:8080";
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	int tiempoL = 1000;
	int tiempoML = 5000;
	Robot robot = new Robot();
	driver.get(baseUrl + "/cargo-tracker/");
	driver.findElement(By.linkText("Administration Interface")).click();
    robot.delay(tiempoL);
    driver.findElement(By.linkText("Book")).click();
    robot.delay(tiempoL);
    driver.findElement(By.xpath("//div[@id='j_idt29:origin']/div[3]/span")).click();
    robot.delay(tiempoML);
    driver.findElement(By.xpath("//div[@id='j_idt29:origin_panel']/div/ul/li[2]")).click();
    robot.delay(tiempoML);
    driver.findElement(By.id("j_idt29:j_idt36")).click();
    robot.delay(tiempoL);
    driver.findElement(By.xpath("//div[@id='j_idt29:destination']/div[3]/span")).click();
    driver.findElement(By.id("j_idt29:j_idt37")).click();
    robot.delay(tiempoL);
    driver.findElement(By.linkText("29")).click();
    robot.delay(tiempoL);
    driver.findElement(By.id("dateForm:bookBtn")).click();
    robot.delay(tiempoL);
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
