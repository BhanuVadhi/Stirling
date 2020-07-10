package com.stirling.techtest.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.logging.Logger;

public class UserDataPage extends LoginPage {

    private static final Logger log = Logger.getLogger(UserDataPage.class.getName());
    public UserDataPage() {
        waitForPageToLoad();
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(xpath = "(//ul[contains(@class,'js-she-dropdown-menu')]//li[contains(@class,'she-has-submenu')])[8]")
    private WebElement menu_Modules;

    @FindBy(xpath = "//a[contains(@href,'Contractor/ConstructionPhase/Page')]")
    private WebElement menu_Contractor;

    @FindBy(xpath = "//a[contains(@href,'Contractor/ConstructionPhase/Page/')][2]")
    private WebElement menu_ConstructionPhasePlan;

    @FindBy(xpath = "//a[contains(@class,('btn-large')) and contains(@href,'Contractor/ConstructionPhase/Create')]")
    private WebElement  newRecordButton ;

    @FindBy(css = "#SheConstructionPhase_FileSigned")
    private WebElement Signed_field;

    @FindBy(xpath = "//button[contains(@class,'ui-datepicker-trigger')]")
    private WebElement datePicker;

    @FindBy(xpath = "//td[contains(@class,'ui-datepicker-days-cell-over') and contains(@class,'ui-datepicker-today')]")
    private WebElement selectTodaysDate;

    @FindBy(xpath = "//button[contains(@value,'Close')]")
    private WebElement buttonSaveAndClose;

    @FindBy(xpath = "//a[contains(@id,'Delete')]")
    private WebElement deleteRecord;

    private WebElement elementUserMangeRecord;

    String signedUserXpath="//a[@title='"+username+"']";
    String userManageRecord = "#manageRecord";
    String userDeleteRecord = "#cogDelete";




    public void createNewConstructionPhasePlan(){
        menu_Modules.click();
        menu_Contractor.click();
        menu_ConstructionPhasePlan.click();
        Assert.assertTrue(newRecordButton.isDisplayed(),"Create new record button not available.");
        newRecordButton.click();
        waitForPageToLoad();
        Signed_field.sendKeys(username);
        datePicker.click();
        selectTodaysDate.click();
        buttonSaveAndClose.click();
        waitForPageToLoad();
        Assert.assertTrue(webDriver.findElement(By.xpath(signedUserXpath)).isDisplayed(),"New Record created not available.");
    }

    public void deleteNewRecord(){
        Assert.assertTrue(webDriver.findElement(By.xpath(signedUserXpath)).isDisplayed(),"New Record created not available.");
        String userRef = webDriver.findElement(By.xpath(signedUserXpath)).getAttribute("href");
        userRef = userRef.replaceAll("\\D+","");
        elementUserMangeRecord= webDriver.findElement(By.cssSelector(userManageRecord+userRef));
        elementUserMangeRecord.click();
        elementUserMangeRecord= webDriver.findElement(By.cssSelector(userDeleteRecord+userRef));
        elementUserMangeRecord.click();
        Assert.assertFalse(webDriver.findElement(By.xpath(signedUserXpath)).isDisplayed(),"Newly created record still exists.");
    }
}
