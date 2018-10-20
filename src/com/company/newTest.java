package com.company;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.server.handler.ClickElement;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.beans.Visibility;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class newTest {

    String baseUrl = "https://www.reifen.com/";
    WebDriver driver;
    Wait<WebDriver> asteapta ;

    @BeforeTest
    public void startDriver() {
        String path = new String("C:\\Users\\SebySZ\\IdeaProjects\\selenium_1\\lib\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", path);
        driver = new ChromeDriver();

        asteapta = new FluentWait<WebDriver>(driver)
                //how much to wait
                .withTimeout(20, TimeUnit.SECONDS)
                // frequency of check
                .pollingEvery(1, TimeUnit.SECONDS)
                // ignore exception
                .ignoring(java.util.NoSuchElementException.class);
        driver.get(baseUrl);
    }

    @Test
    public void test() throws FileNotFoundException {



        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        try {
            Thread.sleep(6000);
        }
        catch (Exception e){

        }

        PrintWriter pw = new PrintWriter(new File("test.csv"));
        StringBuilder sb = new StringBuilder();
        sb.append("Brand");
        sb.append(',');
        sb.append("Profil");
        sb.append(',');
        sb.append("Pret");
        sb.append(',');
        sb.append('\n');

        asteapta.until(ExpectedConditions.visibilityOfElementLocated(By.id("drpTyreWidthSB"))); //====================

        List<WebElement> liOp = new Select(driver.findElement(By.id("drpTyreWidthSB"))).getOptions();

        asteapta.until(ExpectedConditions.visibilityOfAllElements(liOp));

        for (WebElement eachElem : liOp) {

            asteapta.until(ExpectedConditions.visibilityOfAllElements(liOp));
            //asteapta.until(ExpectedConditions.visibilityOf(eachElem));

            new Select(driver.findElement(By.id("drpTyreWidthSB"))).selectByIndex(liOp.indexOf(eachElem));
            driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

            asteapta.until(ExpectedConditions.visibilityOfElementLocated(By.id("drpTyreCrossSectionSB"))); //================

            List<WebElement> liOp2 = new Select(driver.findElement(By.id("drpTyreCrossSectionSB"))).getOptions();

            asteapta.until(ExpectedConditions.visibilityOfAllElements(liOp2));



            if(!liOp2.isEmpty()) {
                for (WebElement eachElem2 : liOp2) {

                    //asteapta.until(ExpectedConditions.visibilityOf();

                    driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

                    new Select(driver.findElement(By.id("drpTyreCrossSectionSB"))).selectByIndex(liOp2.indexOf(eachElem2));

                    asteapta.until(ExpectedConditions.visibilityOfElementLocated(By.id("drpTyreDiameterSB")));

                    List<WebElement> liOp3 = new Select(driver.findElement(By.id("drpTyreDiameterSB"))).getOptions();

                    //asteapta.until(ExpectedConditions.visibilityOfAllElements(liOp3));

                    if(!liOp3.isEmpty()) {
                        for (WebElement eachElem3 : liOp3) {

                            //asteapta.until(ExpectedConditions.visibilityOf(eachElem3));

                            new Select(driver.findElement(By.id("drpTyreDiameterSB"))).selectByIndex(liOp3.indexOf(eachElem3));

                            try {
                                Thread.sleep(3000);
                            }catch (Exception e){

                            }

                            driver.findElement(new By.ByXPath("//*[@id=\"content-groesse\"]/div[3]/span[2]/button")).click();
                            driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);


                            asteapta.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"bottomArticleCount\"]/ul/li[3]/button/span")));
                            driver.findElement(By.xpath("//*[@id=\"bottomArticleCount\"]/ul/li[3]/button/span")).click();

                            driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);

                            List<WebElement> dynamicDivs = driver.findElements(By.xpath("//div[starts-with(@class,'custom-col-md-80 col-sm-10 col-xs-9 rlbl')]"));

                            asteapta.until(ExpectedConditions.visibilityOfAllElements(dynamicDivs));

                            if(!dynamicDivs.isEmpty()) {
                                for (int i = 0; i < dynamicDivs.size(); i++) {

                                    WebElement pret = dynamicDivs.get(i).findElement(By.className("price"));
                                    WebElement profil = dynamicDivs.get(i).findElement(By.className("thirdHeaderStyle"));
                                    WebElement brand = dynamicDivs.get(i).findElement(By.className("secondHeaderStyle"));


                                    System.out.println("Brand: " + brand.getText() + " profil: " + profil.getText() + " pret: " + pret.getText());




                                    sb.append(brand.getText());
                                    sb.append(',');
                                    sb.append(profil.getText());
                                    sb.append(',');
                                    sb.append(pret.getText());
                                    sb.append('\n');

                                    pw.write(sb.toString());
                                    pw.close();


                                    driver.get(baseUrl);
                                    driver.manage().timeouts().pageLoadTimeout(60,TimeUnit.SECONDS);
                                    asteapta.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"frmTyreSizeSB\"]/div[2]")));

                                    try{
                                        Thread.sleep(25000);
                                    }
                                    catch (Exception e){

                                    }

                                }
                            }else {
                                continue;
                            }


                            }
                    }else {
                        continue;
                    }

                }
            }else {
                continue;
            }

        }

        pw.close();

    }
        @AfterTest
        public void closeDriver () {
            driver.close();
        }

    }
