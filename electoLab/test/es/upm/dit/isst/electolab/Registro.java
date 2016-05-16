package es.upm.dit.isst.electolab;

import java.util.regex.Pattern;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import es.upm.dit.isst.electolab.dao.ElectoLabDAO;
import es.upm.dit.isst.electolab.dao.ElectoLabDAOImpl;

public class Registro {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();

    baseUrl = "https://electolab.appspot.com";
    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
  }

  @Test
  public void testRegistro() throws Exception {
	ElectoLabDAO dao = ElectoLabDAOImpl.getInstance();
	String correo = "CORREO";
	String password = "PASSWORD";

    driver.get(baseUrl + "/index.jsp");
    driver.findElement(By.linkText("Registrarse")).click();
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys(correo);
    driver.findElement(By.id("grupo")).clear();
    driver.findElement(By.id("grupo")).sendKeys("prueba");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("prueba");
    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
    driver.findElement(By.linkText("Login")).click();
    driver.findElement(By.id("Email")).clear();
    driver.findElement(By.id("Email")).sendKeys(correo);
    driver.findElement(By.id("next")).click();
    driver.findElement(By.id("Passwd")).clear();
    driver.findElement(By.id("Passwd")).sendKeys(password);
    driver.findElement(By.id("next")).click();
    
    //Comprobacion botones generar informe y guardar simulacion
    driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
    driver.findElement(By.name("titulo")).clear();
    driver.findElement(By.name("titulo")).sendKeys("Caso de prueba");
    driver.findElement(By.xpath("//input[@value='Simular escenario']")).click();
    
    List<WebElement> botonInforme = driver.findElements(By.id("generarInforme"));
    List<WebElement> botonGuardar = driver.findElements(By.id("guardarSimulacion"));
    
    
    //Comprobamos que se habilitan elementos de usuario
    assertTrue(botonGuardar.get(0).getText().equals("Guardar simulación"));
    assertTrue(botonInforme.get(0).getText().equals("Generar informe"));
    
    //Comprobacion del correo del usuario
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (correo.equals(driver.findElement(By.id("correoUsuario")).getText())) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    //Comprobacion de penstana logout
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if ("Logout".equals(driver.findElement(By.linkText("Logout")).getText())) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }
   
    
    //Comprobacion pestana foros de discusion se activa
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if ("Foros de discusión".equals(driver.findElement(By.linkText("Foros de discusión")).getText())) break; } catch (Exception e) {}
    	Thread.sleep(1000);
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
