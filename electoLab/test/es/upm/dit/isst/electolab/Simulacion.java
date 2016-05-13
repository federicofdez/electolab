package es.upm.dit.isst.electolab;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Simulacion {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://1-dot-electolab.appspot.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testSimulacion() throws Exception {
    driver.get(baseUrl);
    driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
    driver.findElement(By.linkText("Sistema de proporcionalidad y circuscripciones")).click();
    driver.findElement(By.xpath("(//input[@name='sistema'])[2]")).click();
    driver.findElement(By.name("mayoria")).clear();
    driver.findElement(By.name("mayoria")).sendKeys("75");
    driver.findElement(By.xpath("(//input[@name='circunscripciones'])[2]")).click();
    driver.findElement(By.linkText("Reparto de votos y escaños")).click();
    driver.findElement(By.name("Cs:alava")).clear();
    driver.findElement(By.name("Cs:alava")).sendKeys("30");
    driver.findElement(By.name("PODEMOS:alava")).clear();
    driver.findElement(By.name("PODEMOS:alava")).sendKeys("10");
    driver.findElement(By.name("Cs:albacete")).clear();
    driver.findElement(By.name("Cs:albacete")).sendKeys("30");
    driver.findElement(By.name("PP:albacete")).clear();
    driver.findElement(By.name("PP:albacete")).sendKeys("12");
    driver.findElement(By.name("Cs:alicante")).clear();
    driver.findElement(By.name("Cs:alicante")).sendKeys("30");
    driver.findElement(By.name("PP:alicante")).clear();
    driver.findElement(By.name("PP:alicante")).sendKeys("10");
    driver.findElement(By.linkText("3")).click();
    driver.findElement(By.name("PP:castellon")).clear();
    driver.findElement(By.name("PP:castellon")).sendKeys("10");
    driver.findElement(By.name("Cs:castellon")).clear();
    driver.findElement(By.name("Cs:castellon")).sendKeys("30");
    driver.findElement(By.linkText("1")).click();
    driver.findElement(By.name("titulo")).clear();
    driver.findElement(By.name("titulo")).sendKeys("Caso de prueba");
    driver.findElement(By.xpath("//input[@value='Simular escenario']")).click();
    driver.findElement(By.xpath("(//input[@name='circunscripciones'])[1]")).click();
    driver.findElement(By.xpath("//button[@type='submit']")).click();
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