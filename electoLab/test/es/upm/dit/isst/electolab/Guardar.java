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

public class Guardar {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://electolab.appspot.com";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testGuardar() throws Exception {
    driver.get(baseUrl);
    //USUARIO REGISTRADO YA
    driver.findElement(By.linkText("Login")).click();
    driver.findElement(By.id("Email")).clear();
    driver.findElement(By.id("Email")).sendKeys("CORREO");
    driver.findElement(By.id("next")).click();
    driver.findElement(By.id("Passwd")).clear();
    driver.findElement(By.id("Passwd")).sendKeys("PASSWORD");
    driver.findElement(By.id("next")).click();
    //PRUEBA DE GUARDAR
    driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
    driver.findElement(By.linkText("Sistema de proporcionalidad y circuscripciones")).click();
    driver.findElement(By.name("titulo")).clear();
    driver.findElement(By.name("titulo")).sendKeys("Caso de prueba");
    driver.findElement(By.xpath("//input[@value='Simular escenario']")).click();
    driver.findElement(By.xpath("(//input[@name='sistema'])[2]")).click();
    driver.findElement(By.xpath("(//input[@name='circunscripciones'])[2]")).click();
    driver.findElement(By.name("mayoria")).clear();
    driver.findElement(By.name("mayoria")).sendKeys("70");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.id("guardarSimulacion")).click();
    
      try {
        assertEquals("Caso de prueba", driver.findElement(By.cssSelector("input[type=\"submit\"]")).getAttribute("value"));
      } catch (Error e) {
        verificationErrors.append(e.toString());
      }
      driver.findElement(By.xpath("//input[@value='Caso de prueba']")).click();


      driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
      for (int second = 0;; second++) {
      	if (second >= 60) fail("timeout");
      	try { if ("SAINTE".equals(driver.findElement(By.id("sistemaProp")).getText())) break; } catch (Exception e) {}
      	Thread.sleep(1000);
      }

      for (int second = 0;; second++) {
      	if (second >= 60) fail("timeout");
      	try { if ("COMUNIDADES".equals(driver.findElement(By.id("circunscripcionElegida")).getText())) break; } catch (Exception e) {}
      	Thread.sleep(1000);
      }

      for (int second = 0;; second++) {
      	if (second >= 60) fail("timeout");
      	try { if ("70".equals(driver.findElement(By.xpath("//div[@id='main-content']/div/div[3]/div[2]")).getText())) break; } catch (Exception e) {}
      	Thread.sleep(1000);
      }
    
    List<WebElement> sistema = driver.findElements(By.id("sistemaProp"));
    List<WebElement> circunscripcion = driver.findElements(By.id("circunscripcionElegida"));
    assertTrue(sistema.get(0).getText().equals("SAINTE"));
    assertTrue(circunscripcion.get(0).getText().equals("COMUNIDADES"));
    
    

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