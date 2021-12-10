
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;


    public class TestiniumTEST {

        public WebDriver driver;
        final static Logger logger = Logger.getLogger(TestiniumTEST.class);

        @Before
        public void setUp() throws InterruptedException {
            Thread.sleep(10);
            
            logger.info("--- TEST BAŞLADI ---");
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize(); 


        }
        @Test
        public void correctOpen() throws InterruptedException {
            Thread.sleep(10);
            
            driver.get("https://www.lcwaikiki.com/tr-TR/TR");// Belirtilen adres açıldı

            Assert.assertEquals(driver.getTitle(), "LC Waikiki | İlk Alışverişte Kargo Bedava! - LC Waikiki");
            logger.info("Anasayfaya girildi");
            Thread.sleep(500);

            driver.navigate().to("https://www.lcwaikiki.com/tr-TR/TR/giris");
            driver.findElement(By.xpath("//*[@id=\"LoginEmail\"]")).sendKeys("Cantpblk@gmail.com");// Bilgiler text kutusuna yazıldı.
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"Password\"]")).sendKeys("12300123c");
            Thread.sleep(500);
            driver.findElement(By.id("loginLink")).click();
            logger.info("Login yapıldı");
            Thread.sleep(500);

            driver.findElement(By.xpath("//*[@id=\"search_input\"]")).click();
            driver.findElement(By.xpath("//*[@id=\"search_input\"]")).sendKeys("pantolon", Keys.RETURN);//pantolon kelimesi tarandı.
            logger.info("Pantolon kelimesi aratıldı");

            JavascriptExecutor js = ((JavascriptExecutor) driver);
            js.executeScript("window.scrollTo(100, document.body.scrollHeight)");
            Thread.sleep(500);

            driver.navigate().to("https://www.lcwaikiki.com/tr-TR/TR/arama?q=Pantolon&PageIndex=2");
            logger.info("Daha fazla ürün gör tiklandi");
            Thread.sleep(500);


            driver.findElement(By.xpath("//*[@id=\"model_1793779_5377407\"]/div[1]/img[1]")).click(); // Sayfadaki ürünlerin 2. sıradakini seçmek.
            logger.info("2.Sıradaki ürün seçildi.");


            WebElement PPrice = driver.findElement(By.xpath("//*[@id=\"rightInfoBar\"]/div[1]/div/div[2]/div/div/div/span[2]")); //seçilen ürünün belirtilen  fiyatını yazılır.
            String Price = PPrice.getText();
            logger.info("Ürün Fiyatı : " + Price);

            driver.findElement(By.xpath("//*[@id=\"option-size\"]/a[1]")).click(); // ürünü seçimi yapıldığı yer
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"rightInfoBar\"]/div[1]/div/div[4]/div[3]")).click(); //seçilen ürünün adeti girilir.
            logger.info("Ürün sepete eklendi");

            driver.navigate().to("https://www.lcwaikiki.com/tr-TR/TR/sepetim"); // Belirlenen yerin gidildiği yer.
            logger.info("Sepete gidildi");
            Thread.sleep(500);




            WebElement PBasketPrice = driver.findElement(By.xpath("//*[@id=\"ShoppingCartContent\"]/div[1]/div[2]/div[1]/div[3]/div/span[2]")); //Seçilen ürünün fiyatı girilir.
            String BasketPrice = PBasketPrice.getText(); // ürün için belirlenen fiyatı yazdırır.
            logger.info("Ürünün Sepetteki Fiyatı : " + BasketPrice);


            Assert.assertEquals(BasketPrice, Price); //Seçilen ürünün fiyat doğrulaması yapıldıpı yer.
            logger.info("Fiyat doğrulaması yapıldı");



            driver.findElement(By.xpath("//*[@id=\"Cart_AddQuantity_679546195\"]")).click(); //ürünü arttırma işleminin yapıldığı yer.
            logger.info("Urun adeti arttırıldı");
            Thread.sleep(500);

            driver.findElement(By.id("Cart_ProductDelete_678993851")).click();//Sepeti boşalta tıklama
            logger.info("Sepet Boşaltıldı");
            Thread.sleep(500);



            logger.info("Ürün Adedi : 2 ");



            WebElement BasketCheck = driver.findElement(By.xpath("//*[@id=\"ShoppingCartContent\"]/div[1]/div/div/p[1]")); // sepetin için inceleniyor.
            String Check = BasketCheck.getText(); //Yazılan kodu texte yazdırıyor.

            if (Check.equals("Sepetinizde ürün bulunmamaktadır.")) {
                logger.info("Sepet boş");
            }}



    @After
    public void quit() {
        driver.quit();
        logger.info("--- TEST SONLANDI ---");

    }
}
