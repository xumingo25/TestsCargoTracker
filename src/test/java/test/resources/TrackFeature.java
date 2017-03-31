package test.resources;

import java.awt.Robot;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TrackFeature {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  public static String curDir = System.getProperty("user.dir");

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void testTC002FeatureTrack() throws Exception {
	  System.setProperty("webdriver.chrome.driver", curDir
				+ "\\Drivers\\chromedriver.exe");
	driver = new ChromeDriver();
	  
	baseUrl = "http://192.168.17.223:8080";
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	int tiempoC = 1000;
	int tiempoL = 1500;
	Robot robot = new Robot();
  driver.get(baseUrl + "/cargo-tracker/");
		robot.delay(tiempoC);
	    driver.findElement(By.linkText("Administration Interface")).click();
	    driver.findElement(By.linkText("Track")).click();
	    robot.delay(tiempoC);
	    driver.findElement(By.id("trackingForm:trackIdInput_input")).clear();
	    driver.findElement(By.id("trackingForm:trackIdInput_input")).sendKeys("ABC123");
	    robot.delay(tiempoL);
	    driver.findElement(By.id("trackingForm:j_idt30")).click();
	    robot.delay(tiempoC);
	    String validadorTexto = driver.findElement(By.cssSelector("#result > strong")).getText();
	    assertEquals("Handling History", validadorTexto);
	    robot.delay(tiempoC);
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
