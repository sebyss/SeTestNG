package com.company;


import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;

public class SeTestNG {

    String baseUrl = "http://80.240.140.117/ElysianQuizz/";
    String baseUrl2 = "https://www.cauciucuridirect.ro/";
    String baseUrl3 = "http://newtours.demoaut.com/mercuryregister.php";
    WebDriver driver;

    WebDriverWait waits;
    FluentWait<WebDriver> asteapta;

    @BeforeTest
    public void startDriver(){
        String path = new String("C:\\Users\\SebySZ\\IdeaProjects\\selenium_1\\lib\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", path);
        driver = new ChromeDriver();

       // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // IMPLICIT Sync
       // waits = new WebDriverWait(driver, 10000);

        asteapta = new FluentWait<WebDriver>(driver)
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(1,TimeUnit.SECONDS)
                .ignoring(java.util.NoSuchElementException.class);
    }




            @Test //(invocationCount = 3) => numar de repetari test
                    (priority = 3)
            public void verifyTitle() {

                System.out.println("Test 3");
                driver.get(baseUrl);

                String sebi = driver.getTitle();
                System.out.println("Page title is: "+sebi);

                driver.findElement(By.name("loginForm:userId")).sendKeys("sebastian.miucin");
                driver.findElement(By.name("loginForm:password")).sendKeys("xxxxxxxx");
                driver.findElement(By.name("loginForm:j_id_g_1_6_8")).click();

                Assert.assertEquals("Index",driver.getTitle());

            }




            @Test (priority = 4)
            public void writeDataFromWebSite() throws IOException{
                System.out.println("Test 4");
                driver.get(baseUrl2);

                String width = driver.findElement(By.name("width")).getText();
                String profile = driver.findElement(By.name("profile")).getText();
                String size = driver.findElement(By.name("size")).getText();


                BufferedWriter writer = new BufferedWriter(new FileWriter("cauciucuri.txt"));
                writer.write("Default value for WIDTH: ");
                writer.write(width + "\n");

                writer.write("Default value for PROFILE: ");
                writer.write(profile + "\n");

                writer.write("Default value for SIZE: ");
                writer.write(size + "\n");

                writer.close();


                //===========Unconditional Sync
           try{
               Thread.sleep(3000);

           }
           catch(InterruptedException e) {
               e.printStackTrace();
           }

           }


       @Parameters({"firstName", "lastName", "phone", "email", "adress1", "city", "state", "postalCode", "userN", "password", "tara"})
       @Test (priority = 1)
       public void testLogin(String firstName,String lastname,String phone,String email,String adress1,String city,String state,
                             String postalCode,String userN,String passwd, String tara){


           System.out.println("Test 1");
           String boss = driver.getTitle();
           System.out.println(boss);

           driver.get(baseUrl3);

           asteapta.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstName"))); // Fluent Sync

           driver.findElement(By.name("firstName")).sendKeys(firstName);
           driver.findElement(By.name("lastName")).sendKeys(lastname);
           driver.findElement(By.name("phone")).sendKeys(phone);
           driver.findElement(By.name("userName")).sendKeys(email);
           driver.findElement(By.name("address1")).sendKeys(adress1);
           driver.findElement(By.name("city")).sendKeys(city);
           driver.findElement(By.name("state")).sendKeys(state);
           driver.findElement(By.name("postalCode")).sendKeys(postalCode);

           //===========Conditional Wait (Explicit)
           WebDriverWait waits = new WebDriverWait(driver,10);
           waits.until(ExpectedConditions.visibilityOfElementLocated(By.name("country")));

           Select dropDown = new Select(driver.findElement(By.name("country")));
           dropDown.selectByVisibleText(tara);

           driver.findElement(By.name("email")).sendKeys(userN);
           driver.findElement(By.name("password")).sendKeys(passwd);
           driver.findElement(By.name("confirmPassword")).sendKeys(passwd);

           driver.findElement(By.name("register")).click();

           //driver.close();

       }

       @Test (priority = 2)
       public void register(){

            System.out.println("Test 2");
            asteapta.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("sign-in")));

            WebElement signInLink = driver.findElement(By.linkText("sign-in"));
            signInLink.click();

    }

       @AfterTest
        public void closeDriver(){
        driver.close();
       }


}





