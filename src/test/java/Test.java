import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test {

    @org.testng.annotations.Test
    public void runGoogle() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com.ua/?hl=ua");
        driver.findElement(By.xpath("//textarea[@name='q']")).sendKeys("Cats\n");
        driver.quit();
    }


    @org.testng.annotations.Test
    public void clickEnterBtn2() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.yahoo.com/");
        driver.findElement(By.xpath("//a[text()='Sign in']")).click();
    }

    @org.testng.annotations.Test
    public void clickEnterBtnv2() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.bing.com/");
        driver.findElement(By.xpath("//input[@name=\"q\"]")).sendKeys("МОЛОТ");
    }

    @org.testng.annotations.Test
    public void clickRozetka() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://rozetka.com.ua/");
        driver.findElement(By.xpath("//input[@name='search']")).sendKeys("Ноутбук\n");
    }


    @org.testng.annotations.Test
    public void clickSteam() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://store.steampowered.com/?l=russian");
        driver.findElement(By.xpath("//input[@name='term']")).sendKeys("CS\n");
    }

    @org.testng.annotations.Test
    public void clickWebka() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.websklad.biz.ua/?product_tag=vesy");
        driver.findElement(By.xpath("//input[@name='s']")).sendKeys("Картины по номерам\n");
    }

    @org.testng.annotations.Test
    public void clickYoutube() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.youtube.com/");
        driver.findElement(By.xpath("//input[@name=\"search_query\"]")).sendKeys("Api test\n");
        driver.quit();
    }


    @org.testng.annotations.Test
    public void clickEnterBinance() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://accounts.binance.com/uk-UA/register");
        driver.findElement(By.xpath("//div[contains(text(), 'Увійти')]"));
    }

    @org.testng.annotations.Test
    public void clickEse() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://easydrop.one/login?next=/catalog-view/3673/2804/");
        driver.findElement(By.xpath("//a[contains(text(),  'Реєстрація')]"));
    }
}

