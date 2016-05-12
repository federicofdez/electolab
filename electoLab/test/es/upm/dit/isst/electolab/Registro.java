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
    driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
  }

  @Test
  public void testRegistro() throws Exception {
	ElectoLabDAO dao = ElectoLabDAOImpl.getInstance();

    driver.get(baseUrl + "/index.jsp");
    driver.findElement(By.linkText("Registrarse")).click();
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("CORREO");
    driver.findElement(By.id("grupo")).clear();
    driver.findElement(By.id("grupo")).sendKeys("prueba");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("prueba");
    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
    driver.findElement(By.linkText("Login")).click();
    driver.findElement(By.id("Email")).clear();
    driver.findElement(By.id("Email")).sendKeys("CORREO");
    driver.findElement(By.id("next")).click();
    driver.findElement(By.id("Passwd")).clear();
    driver.findElement(By.id("Passwd")).sendKeys("PASSWORD");
    driver.findElement(By.id("next")).click();
    driver.findElement(By.linkText("Simular escenario")).click();
    List<WebElement> correo = driver.findElements(By.id("correoUsuario"));
    List<WebElement> botonInforme = driver.findElements(By.id("generarInforme"));
    List<WebElement> botonGuardar = driver.findElements(By.id("guardarSimulacion"));
    
    
    //Comprobamos que se habilitan elementos de usuario
    assertTrue(botonGuardar.get(0).getText().equals("Guardar simulación"));
    assertTrue(botonInforme.get(0).getText().equals("Generar informe"));
    //Comprobamos usuario logueado
    assertTrue(correo.size()>0);


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
