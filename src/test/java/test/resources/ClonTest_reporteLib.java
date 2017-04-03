package test.resources;

import java.awt.Robot;
import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

import test.resources.*;


public class ClonTest_reporteLib {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  public static String curDir = System.getProperty("user.dir");
  MetodosReutilizables mr =new MetodosReutilizables();
  String nombreClase= getClass().getSimpleName();
  
  String nombreCarpeta="ReporteResultados";

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void testTC003FeatureBook() throws Exception {
	 System.setProperty("webdriver.chrome.driver", curDir
				+ "\\Drivers\\chromedriver.exe");
	driver = new ChromeDriver();
	  
	//baseUrl = "http://192.168.17.223:8080";
	baseUrl = "http://192.168.17.188:8080";
	
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
	int tiempoL = 1000;
	int tiempoML = 3500;
	Robot robot = new Robot();
	
	ExtentReports logger = ExtentReports.get(ClonTest_reporteLib.class);    	
	mr.crearCarpetaReporte(nombreCarpeta);	
	mr.crearCarpetaDePruebas(nombreCarpeta, nombreClase);	
	System.out.println("nombre de la clase es:" +nombreClase);	
	logger.init(curDir + "\\"+nombreCarpeta+"\\"+nombreClase+"\\ReporteResultados.html",true);
	
	mr.crearCarpetaDeEvidencias(nombreCarpeta, nombreClase);
	
	logger.startTest("TC Web Track");
	
	logger.log(LogStatus.INFO, "Se lanzara browser con la web definida");
	
	
	driver.get(baseUrl + "/cargo-tracker/");
	
	File evidenciaPantallaInicial = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);  	
	String pathEvidencia_pantallaInicial = curDir + "\\"+ nombreCarpeta +"\\"+ nombreClase + "\\"+ "Evidencias" + "\\1. Pantalla Inicial.png";	
	FileUtils.copyFile(evidenciaPantallaInicial, new File(pathEvidencia_pantallaInicial));
	logger.attachScreenshot(pathEvidencia_pantallaInicial);
	
	driver.findElement(By.linkText("Administration Interface")).click();
    robot.delay(tiempoL);    
    logger.log(LogStatus.INFO, "Se Hizo click sobre la opción ´Administration Interface´ ");
    
    
    File evidenciaPantallaAdminInterf = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);  	
	String pathEvidencia_AdminInterf = curDir + "\\"+ nombreCarpeta +"\\"+ nombreClase + "\\"+ "Evidencias" + "\\2. Pantalla Admin Interface.png";	
	FileUtils.copyFile(evidenciaPantallaAdminInterf, new File(pathEvidencia_AdminInterf));
	logger.attachScreenshot(pathEvidencia_AdminInterf);
	
    driver.findElement(By.linkText("Book")).click();
    robot.delay(tiempoL);
    logger.log(LogStatus.INFO, "Se Hizo click sobre la opción ´Book´ ");
    
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
    
    File evidenciaUltimaPantalla = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);  	
	String pathEvidencia_ultimaPantalla = curDir + "\\"+ nombreCarpeta +"\\"+ nombreClase + "\\"+ "Evidencias" + "\\3. Ultima Pantalla.png";	
	FileUtils.copyFile(evidenciaUltimaPantalla, new File(pathEvidencia_ultimaPantalla));
	logger.attachScreenshot(pathEvidencia_ultimaPantalla);
    
    
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
