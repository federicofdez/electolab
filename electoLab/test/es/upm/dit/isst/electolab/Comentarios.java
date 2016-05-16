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

public class Comentarios {
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
  public void testComentarios() throws Exception {
	String correo = "CORREO";
	String password = "PASSWORD";
    driver.get(baseUrl);
    //USUARIO REGISTRADO YA
    driver.findElement(By.linkText("Login")).click();
    driver.findElement(By.id("Email")).clear();
    driver.findElement(By.id("Email")).sendKeys(correo);
    driver.findElement(By.id("next")).click();
    driver.findElement(By.id("Passwd")).clear();
    driver.findElement(By.id("Passwd")).sendKeys(password);
    driver.findElement(By.id("next")).click();   
    driver.findElement(By.linkText("Foros de discusión")).click();
    
    //PRUEBA DE GUARDAR
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    driver.findElement(By.name("comentario")).clear();
    driver.findElement(By.name("comentario")).sendKeys("Esto es un comentario para el caso de prueba");
    driver.findElement(By.xpath("//input[@value='Añadir comentario']")).click();
    //Comprobamos comentario y autor
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if ("Esto es un comentario para el caso de prueba".equals(driver.findElement(By.xpath("//table[@id='comentarioTable']/tbody/tr/td[2]")).getText())) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (correo.equals(driver.findElement(By.cssSelector("tr.info.odd > th")).getText())) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    driver.findElement(By.name("comentario")).clear();
    driver.findElement(By.name("comentario")).sendKeys("Segundo comentario de prueba");
    driver.findElement(By.xpath("//input[@value='Añadir comentario']")).click();
    //Comprobamos comentario
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if ("Segundo comentario de prueba".equals(driver.findElement(By.xpath("//table[@id='comentarioTable']/tbody/tr/td[2]")).getText())) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }
    driver.findElement(By.name("comentario")).clear();
    driver.findElement(By.name("comentario")).sendKeys("Tercer comentario de prueba");
    driver.findElement(By.xpath("//input[@value='Añadir comentario']")).click();
    //Comprobamos comentario
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if ("Tercer comentario de prueba".equals(driver.findElement(By.xpath("//table[@id='comentarioTable']/tbody/tr/td[2]")).getText())) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }
    List<WebElement> tabla = driver.findElements(By.id("comentarioTable"));
    //Comprobacion de que hay comentarios
    assertTrue(tabla.get(0).getText() != null);
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