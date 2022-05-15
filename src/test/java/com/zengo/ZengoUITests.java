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

        System.out.println("Starting the tests");
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        System.out.println("The browser is opened");

        //Open test page
        String urlZengoMain = "https://zengo.com/";
        driver.get(urlZengoMain);
        System.out.println("Home page is opened");

        homePageVerifications(urlZengoMain, driver);

        //Open drop down from Assets
        Actions actions = new Actions (driver);
        WebElement menuAssetsButton = driver.findElement(By.linkText("Assets"));
        actions.moveToElement(menuAssetsButton).perform();

        //drop down - Click on Ethereum
        WebElement ethereumButton = driver.findElement(By.xpath("//li[@class='menu-item menu-item-type-post_type menu-item-object-asset zengo-menu-item menu-item-13963']"));
        Assert.assertTrue(ethereumButton.isDisplayed(),"Ethereum is not displayed");
        ethereumButton.click();

        ethereumWalletVerifications(driver);

        //Redirecting to Home Page and verify
        driver.get(urlZengoMain);
        homePageVerifications(urlZengoMain, driver);
        System.out.println("Home page is opened");

        driver.quit();
        System.out.println("The browser is closed");
    }

    private void ethereumWalletVerifications(WebDriver driver) {

        //Verify Ethereum heading
        WebElement ethereumHeading = driver.findElement(By.xpath("//h1[@class='elementor-heading-title elementor-size-default']"));
        Assert.assertTrue(ethereumHeading.isDisplayed(),"Ethereum heading is not displayed");

        //Verify Ethereum logo
        WebElement ethereumLogo = driver.findElement(By.xpath("//img[@alt='Ethereum-wallet']"));
        Assert.assertTrue(ethereumLogo.isDisplayed(),"Ethereum Logo is not displayed");

        //Verify Ethereum Wallet URL
        String expectedUrlEthereumWallet = "https://zengo.com/assets/ethereum-wallet/";
        String actualUrlEthereumWallet = driver.getCurrentUrl();
        Assert.assertEquals(actualUrlEthereumWallet,expectedUrlEthereumWallet, "This URL is not Zengo's Ethereum Wallet");

        //Verify ZenGo Above logo displayed
        WebElement zenGoLogoUp = driver.findElement(By.cssSelector("img[src='//zengo.com/wp-content/themes/zengo/svg/zengologo.svg']"));
        Assert.assertTrue(zenGoLogoUp.isDisplayed(),"The above ZenGo logo is not displayed");

        //ZenGo Bottom logo - Scroll down
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        WebElement zenGoLogoDown = driver.findElement(By.xpath("//footer[@id='colophon']"));
        Assert.assertTrue(zenGoLogoDown.isDisplayed()," The bottom ZenGo logo is not displayed");
    }

    private void homePageVerifications(String urlZengoMain,WebDriver driver) {

        //Verify Home Page URL
        String actualUrlZengoMain = driver.getCurrentUrl();
        Assert.assertEquals(actualUrlZengoMain, urlZengoMain, "This URL is not Zengo's main page" );

        //Verify Home Page heading title
        WebElement mainHeadingTitle = driver.findElement(By.xpath("//h1[@class='elementor-heading-title elementor-size-default']"));
        Assert.assertTrue(mainHeadingTitle.isDisplayed(),"The main page heading title is not displayed");

        //Verify Home Page Scan QR Code title
        WebElement mainScanQrHeading = driver.findElement(By.xpath("//h2[@class='elementor-heading-title elementor-size-default']"));
        Assert.assertTrue(mainScanQrHeading.isDisplayed(),"The main page Scan QR Code heading is not displayed");

        //Verify Home Page QR image
        WebElement mainQrImage = driver.findElement(By.xpath("//img[@alt='Frame(1)']"));
        Assert.assertTrue(mainQrImage.isDisplayed(),"The main page QR image is not displayed");

        //Verify Home Page background two men and tree
        WebElement MenAndTree = driver.findElement(By.xpath("//div[@class='elementor-widget-wrap']"));
        Assert.assertTrue(MenAndTree.isDisplayed(),"The main page background of two men and tree is not displayed");

        //Verify Home Page background sleeping cat and smartphone image
        WebElement mainCatAndSmartphone = driver.findElement(By.xpath("//img[@alt='Cat and mokcup(3)(1)']"));
        Assert.assertTrue(mainCatAndSmartphone.isDisplayed(),"The main page sleeping cat and smartphone image is not displayed");

        //Scroll down to Home Page elementor swiper
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        WebElement mainElementorSwiper = driver.findElement(By.xpath("//div[@class='elementor-section-wrap']"));
        Assert.assertTrue(mainElementorSwiper.isDisplayed(),"The main page elementor swiper is not displayed");
    }
}
