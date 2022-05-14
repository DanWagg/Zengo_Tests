package com.zengo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;

public class ZengoUITests {

    @Test
    public void uiTests(){
        System.out.println("Starting Zengo tests");

        //Create a driver
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        //Maximize browser window
        //When not maximized, the right menu "hamburger" is displayed.
        //When actually testing, not in the assignment, I Would suggest to test the following as well:
        // 1. In smaller size window.
        // 2. By clicking on Assets-> navigate to Ethereum and click.
        driver.manage().window().maximize();
        System.out.println("browser is opened");

        //Open test page
        String urlZengoMain = "https://zengo.com/";
        driver.get(urlZengoMain);
        System.out.println("The page is opened");

        homePageVerification(urlZengoMain, driver);

        //Open drop down from Assets
        Actions actions = new Actions (driver);
        WebElement menuAssetsButton = driver.findElement(By.linkText("Assets"));
        actions.moveToElement(menuAssetsButton).perform();

        //Click on Ethereum
        //Verification - if not presented the test will fail
        WebElement ethereumButton = driver.findElement(By.xpath("//li[@class='menu-item menu-item-type-post_type menu-item-object-asset zengo-menu-item menu-item-13963']"));
        Assert.assertTrue(ethereumButton.isDisplayed(),"Ethereum is not presented");
        ethereumButton.click();

        //Verify Ethereum wallet heading ***more??**
        WebElement ethereumHeading = driver.findElement(By.xpath("//h1[@class='elementor-heading-title elementor-size-default']"));
        Assert.assertTrue(ethereumHeading.isDisplayed(),"Ethereum heading is not presented");

        //Verify that you were redirected to https://zengo.com/assets/ethereum-wallet/
        String expectedUrlEthereumWallet = "https://zengo.com/assets/ethereum-wallet/";
        String actualUrlEthereumWallet = driver.getCurrentUrl();
        Assert.assertEquals(actualUrlEthereumWallet,expectedUrlEthereumWallet, "This URL is not Zengo's Ethereum Wallet" );

        //Verify that ZenGo logo is displayed successfully - Above logo
        WebElement zenGoLogoUp = driver.findElement(By.xpath("//*[@id=\"page\"]/div[1]/header/div[1]/p[1]/a/img"));
        Assert.assertTrue(zenGoLogoUp.isDisplayed(),"The above ZenGo logo is not displayed");

        //ZenGo - Bottom logo
        //Scroll down till the bottom of the page
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        WebElement zenGoLogoDown = driver.findElement(By.xpath("//footer[@id='colophon']"));
        Assert.assertTrue(zenGoLogoDown.isDisplayed()," The bottom ZenGo logo is not displayed");

        //click on Home page logo
        zenGoLogoUp.click();

        //Verify again that I am in the home page
        homePageVerification(urlZengoMain, driver);

        //close driver
        driver.quit();
        System.out.println("The page is closed");
    }

    private void homePageVerification(String urlZengoMain,WebDriver driver) {
        //Verifications

        //Verify correct URL
        String expectedUrlZengoMain = urlZengoMain;
        String actualUrlZengoMain = driver.getCurrentUrl();
        Assert.assertEquals(actualUrlZengoMain,expectedUrlZengoMain, "This URL is not Zengo's main page" );

        //Verify that the site is displayed successfully ->all below

        //Main page heading title
        WebElement mainHeadingTitle = driver.findElement(By.xpath("//h1[@class='elementor-heading-title elementor-size-default']"));
        Assert.assertTrue(mainHeadingTitle.isDisplayed(),"The main page heading title is not visible");

        //another image element? more core varifications such as menu from right, zengo logo, QR? chat?
    }
}
