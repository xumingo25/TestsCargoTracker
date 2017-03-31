package test.resources;

import java.awt.Robot;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class AboutFeature {
  public static String curDir = System.getProperty("user.dir");
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  

  @Before
  public void setUp() throws Exception {
	/*
	System.setProperty("webdriver.gecko.driver", curDir
				+ "\\Drivers\\geckodriver.exe");
    driver = new FirefoxDriver();
    
    System.setProperty("webdriver.chrome.driver", curDir
				+ "\\Drivers\\chrome.exe");
	WebDriver driver = new ChromeDriver();

    System.setProperty("webdriver.ie.driver", curDir
			+ "\\Drivers\\IEDriverServer.exe");
    driver = new InternetExplorerDriver();
    
    */
  }

  @Test
  public void testAboutFeature() throws Exception {
	System.setProperty("webdriver.chrome.driver", curDir
				+ "\\Drivers\\chromedriver.exe");
	driver = new ChromeDriver();
	  
	baseUrl = "http://192.168.17.223:8080";
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	int tiempoL = 1000;
	int tiempoML = 5000;
	Robot robot = new Robot();
	
    driver.get(baseUrl + "/cargo-tracker/");
    robot.delay(tiempoML);
    driver.findElement(By.linkText("Administration Interface")).click();
    robot.delay(tiempoL);
    driver.findElement(By.linkText("About")).click();
    robot.delay(tiempoL);
    assertEquals("This interface includes several features for Cargo Administrators:", driver.findElement(By.cssSelector("h3")).getText());
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
