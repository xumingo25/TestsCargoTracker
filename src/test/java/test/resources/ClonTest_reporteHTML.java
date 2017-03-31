package test.resources;

import java.awt.Robot;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ClonTest_reporteHTML {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  public static String curDir = System.getProperty("user.dir");

  String nombreClase= getClass().getSimpleName();
  LogResult logResult=new LogResult(); 
  
  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void testTC003FeatureBook() throws Exception {
	 System.setProperty("webdriver.chrome.driver", curDir + "\\Drivers\\chromedriver.exe");
	driver = new ChromeDriver();
	  
	try{
	  	
		//Instancia declarada para que Desde aqui se considera tiempo de ejecución reflejado en el Reporte Log 
		logResult.InicioScript(driver);	
	
		baseUrl = "http://192.168.17.223:8080";
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		int tiempoL = 1000;
		int tiempoML = 4000;
			
		Robot robot = new Robot();
		
		
		driver.get(baseUrl + "/cargo-tracker/");
		
		try {
	    	if(driver.findElement(By.linkText("Administration Interface")).isDisplayed()==true)
	    		logResult.passLog("VP Link 'Administration Interface'","Se detecto correctamente Link 'Administration Interface'",driver);
	    	else{
	    		logResult.errorLog("VP Link 'Administration Interface'","No se detecto Link 'Administration Interface'",driver);
	    	}
	    } 
	    catch (Error e) {
	    		logResult.warningLog("VP Link 'Administration Interface'",e.toString(),driver);     
	    }
		
		driver.findElement(By.linkText("Administration Interface")).click();
	    robot.delay(tiempoL);
	    
	    try {
	    	if(driver.findElement(By.linkText("Book")).isDisplayed()==true)
	    		logResult.passLog("VP Link 'Book'","Se detecto correctamente Link 'Book'",driver);
	    	else{
	    		logResult.errorLog("VP Link 'Book'","No se detecto Link 'Book'",driver);
	    	}
	    } 
	    catch (Error e) {
	    		logResult.warningLog("VP Link 'Book'",e.toString(),driver);     
	    }
	    
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
	    
	    try {
	    	if(driver.findElement(By.tagName("html")).getText().contains("Not Routed") && driver.findElement(By.tagName("html")).getText().contains("Routed"))
	    		logResult.passLog("VP ultima pantalla de flujo","Se detecto correctamente ultima pantalla de flujo",driver);
	    	else{
	    		logResult.errorLog("VP ultima pantalla de flujo","No se detecto ultima pantalla de flujo",driver);
	    	}
	    } 
	    catch (Error e) {
	    		logResult.warningLog("VP ultima pantalla de flujo",e.toString(),driver);     
	    }
	    
	    logResult.crearLog(nombreClase); //Hasta aqui se considera ejecución del caso de prueba, definiendo tiempo de fin reflejado en el Reporte Log
		}
	
	
	
	  catch(Exception ex)
		{
		  	 logResult.warningLog(nombreClase, ex.toString(), driver);
		     logResult.crearLog(nombreClase);
		}
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
