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
                      la liga española de la temporada 2023-2024.
Prueba de Aceptacion: Verificar que se muestre la tabla de posiciones de la liga española
                      correctamente con los 20 equipos de la temporada 2023-2024.
1. Ingresar a la pagina de ESPN
2. Hacer click en la opción Futbol en el menu
3. Hacer click en Todos los torneos
4. Seleccionar la liga española
5. Seleccionar posiciones
6. Verificar que se muestren los 20 equipos de la temporada 2023-2024
Resultado Esperado: Se debe mostrar la tabla de posiciones de la liga española
                    correctamente con los 20 equipos de la temporada 2023-2024.

 */
public class ResultadosLIGAEspTest {
    private WebDriver driver;
    @BeforeTest
    public void setDriver() throws Exception{
        String path = "/Users/HP/Desktop/chromedriver-win64/chromedriver";
        System.setProperty("webdriver.chrome.driver", path);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    @Test
    public void verificarTablaPosicionesLigaEsp(){
        /*Preparacion*/
        // 1. Ingresar a la pagina de ESPN
        String espnUrl = "https://espndeportes.espn.com/";
        driver.get(espnUrl);
        try{
            TimeUnit.SECONDS.sleep(3);
        }catch (Exception e){
            System.out.println(e);
        }
        /*LOGICA DE LA PRUEBA*/
        // 2. Hacer click en la opción Futbol en el menu
        WebElement fut = driver.findElement(By.xpath("//*[@id=\"global-nav\"]/ul/li[1]/a"));
        fut.click();
        //Mover el mouse fuera del submenu para que no se superponga
        Actions action = new Actions(driver);
        action.moveByOffset(0, 0).perform();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {
            System.out.println(e);
        }
        // 3. Hacer click en Todos los torneos
        WebElement torneos = driver.findElement(By.xpath("//*[@id=\"global-nav-secondary\"]/div/ul/li[6]/a/span[1]"));
        torneos.click();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {
            System.out.println(e);
        }
        // 4. Seleccionar la liga española
        WebElement ligaEsp = driver.findElement(By.xpath("//*[@id=\"fittPageContainer\"]/div[3]/div/div/div/div[2]/div[1]/div/div[3]/div/section/a"));
        ligaEsp.click();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {
            System.out.println(e);
        }
        //5. Seleccionar posiciones
        WebElement pos = driver.findElement(By.xpath("//*[@id=\"global-nav-secondary\"]/div/ul/li[4]/a"));
        pos.click();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e) {
            System.out.println(e);
        }
        List<WebElement> equipos = driver.findElements(By.className("number"));
        System.out.println("Cantidad de equipos: " + equipos.size());
        Assert.assertEquals(20, equipos.size());

    }
    @AfterTest
    public void closeDriver(){
        driver.quit();
    }

}
