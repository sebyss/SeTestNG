package com.company;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class PageContent {

    WebDriver driver;

    By dropDownWidth = By.id("drpTyreWidthSB");
    By dropDownCross = By.id("drpTyreCrossSectionSB");
    By dropDownDiameter = By.id("drpTyreDiameterSB");

    List<WebElement> liOp = new Select (driver.findElement(dropDownWidth)).getOptions();
    List<WebElement> liOp2 = new Select (driver.findElement(dropDownCross)).getOptions();
    List<WebElement> liOp3 = new Select (driver.findElement(dropDownDiameter)).getOptions();

    public PageContent(WebDriver driver){
        this.driver = driver;
    }

    public List<WebElement> getDDWidthOptions(){
        return liOp;
    }

    public List<WebElement> getDDCrossOptions(){
        return liOp2;
    }

    public List<WebElement> getDDdiameterOptions(){
        return liOp3;
    }

    public By getDropDownWidth(){
        return dropDownWidth;
    }

    public By getDropDownCross(){
        return dropDownCross;
    }

    public By getDropDownDiameter(){
        return dropDownDiameter;
    }



}
