package com.stirling.techtest.TestSuite;

import com.stirling.techtest.pages.LoginPage;
import com.stirling.techtest.pages.UserDataPage;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

public class TechTest {
        LoginPage loginPage = new LoginPage();
        UserDataPage userDataPage = new UserDataPage();


    @Test
    public void techTest(){
        loginPage.doLogin();
        userDataPage.createNewConstructionPhasePlan();
        userDataPage.deleteNewRecord();
        loginPage.doLogout();
    }

    @AfterSuite
    public void quitDriver(){
        loginPage.closeDriver();
    }

}
