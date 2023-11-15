import java.util.List;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
/*
Historia de Usuario:  Como usuario habitual de ESPN
                      quiero ver la tabla de posiciones de
                      la formula 1 ordenada por puntos.

Prueba de Aceptacion: Verificar que se muestre la tabla de posiciones de la formula 1
                      correctamente ordenada por puntos.

1. Ingresar a la pagina de ESPN
2. Hacer click en el buscador y poner "Formula 1"
3. Hacer click en el link de Formula 1
4. Hacer click en el link de Posiciones
5. Verificar que el primer piloto sea el que tenga mas puntos

Resultado Esperado: Se debe mostrar la tabla de posiciones de la formula 1
                    y el primer piloto debe ser el que tenga mas puntos.
 */
public class ResultadosFormula1ESPNTest {

        private WebDriver driver;

        @BeforeTest
        public void setDriver() throws Exception{

            String path = "/Users/HP/Desktop/chromedriver-win64/chromedriver";

            System.setProperty("webdriver.chrome.driver", path);

            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

        }

        @Test
        public void verificarTablaPosicionesFormula1(){
            /*Preparacion*/
            // 1. Ingresar a la pagina de ESPN
            String espnUrl = "https://espndeportes.espn.com/";
            driver.get(espnUrl);
            try{
                TimeUnit.SECONDS.sleep(2);
            }catch (Exception e){
                System.out.println(e);
            }
            /*LOGICA DE LA PRUEBA*/
            // 2. Hacer click en el buscador y poner "Formula 1"
            WebElement searchIcon = driver.findElement(By.xpath("//*[@id=\"global-header\"]/div[2]/ul/li[1]"));
            searchIcon.click();

            WebElement searchBox = driver.findElement(By.xpath("//*[@id=\"global-search-input\"]"));
            searchBox.sendKeys("Formula 1");
            // Presionar la tecla Enter
            Actions actions = new Actions(driver);
            actions.sendKeys(searchBox, Keys.ENTER).build().perform();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {
                System.out.println(e);
            }
            // 3. Hacer click en F1
            WebElement f1Link = driver.findElement(By.xpath("//*[@id=\"23bf83ee-6d7a-3957-8b67-8014ddb9ca47\"]"));
            f1Link.click();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                System.out.println(e);
            }
            // 4. Hacer click en el link de Posiciones
            WebElement posicionesLink = driver.findElement(By.xpath("//*[@id=\"global-nav-secondary\"]/div/ul/li[3]/a"));
            posicionesLink.click();
            try{
                TimeUnit.SECONDS.sleep(3);
            }catch (Exception e){
                System.out.println(e);
            }
            /*VERIFICACION*/
            // 5. Verificar que el primer piloto tenga mas puntos
            List<WebElement> puntosPilotos = driver.findElements(By.className("stat-cell"));
            String puntosPrimerPiloto = puntosPilotos.get(0).getText();
            System.out.println("Puntos del primer piloto: " + puntosPrimerPiloto);
            String puntosSegundoPiloto = puntosPilotos.get(1).getText();
            System.out.println("Puntos del segundo piloto: " + puntosSegundoPiloto);
            Assert.assertTrue(Integer.parseInt(puntosPrimerPiloto) > Integer.parseInt(puntosSegundoPiloto));
        }

        @AfterTest
        public void closeDriver(){
            driver.quit();
        }

}
