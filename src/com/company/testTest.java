package com.company;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.server.handler.ClickElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class testTest {

    String baseUrl = "https://www.reifen.com/de/TyreSize/List/CarSummer/205-55-R16?TyreListSort=&Page=1&PageSize=20&TyreSpeedIndexKey=&Reinforced=False&Runflat=False&SnowFlag=False&MSFlag=False&TyreWidth=205.00&TyreCrossSection=55.00&TyreDiameter=16&SelectedTyreOption=None&ConstructionType=R&TyreWidthChangedByNutzart=&OffroadTypeIndex=";
    WebDriver driver;


    @BeforeTest
    public void startDriver() {
        String path = new String("C:\\Users\\SebySZ\\IdeaProjects\\selenium_1\\lib\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", path);
        driver = new ChromeDriver();

    }

    @Test
    public void test() {

        driver.get(baseUrl);


        // driver.findElement(By.className("detailContent col-md-12 rlbl first"));

        int div = 1;
        List<WebElement> dynamicDivs = driver.findElements(By.xpath("//div[starts-with(@class,'custom-col-md-80 col-sm-10 col-xs-9 rlbl')]"));
        for(int i=0; i< dynamicDivs.size();i++){

            //System.out.println(element.getText());

            WebElement pret = dynamicDivs.get(i).findElement(By.className("price"));
            WebElement profil = dynamicDivs.get(i).findElement(By.className("thirdHeaderStyle"));
            WebElement brand = dynamicDivs.get(i).findElement(By.className("secondHeaderStyle"));

            try {
                Thread.sleep(3000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }


                System.out.println("Brand: " + brand.getText() + " profil: "+profil.getText()+" pret: "+pret.getText());

            //driver.navigate().back();
            try {
                Thread.sleep(3000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }


        //driver.get(baseUrl);

    }


    @AfterTest
    public void closeDriver() {
        driver.close();
    }

}

