package com.htc.madisonstore;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Flipkart {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Initialize WebDriver based on browser choice
        driver = BrowserFactory.setWebDriver("chrome");

        // Maximize browser window and set implicit wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser after each test method
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testCheckoutProcess() {
        // Open ShopNow website
        driver.get("https://www.flipkart.com");

        // Verify homepage loads successfully
        Assert.assertTrue(driver.getTitle().contains("Online Shopping Site for Mobiles"));

        // Search for a laptop and add to cart
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("laptop", Keys.ENTER);

        WebElement firstSearchResult = driver.findElement(By.xpath("//div[@class='_4rR01T']"));
        firstSearchResult.click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
     
       WebElement addToCartButton = driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[3]/div[1]/div[1]/div[2]/div/ul/li[1]/button/text()"));
       addToCartButton.click();

        // Navigate to the shopping cart
        WebElement cartIcon = driver.findElement(By.xpath("//a[contains(@href, '/viewcart')]"));
        cartIcon.click();

        // Verify correct item is in the cart
        WebElement cartItem = driver.findElement(By.xpath("//div[@class='Og_iXc']//a[contains(@href, '/laptops')]"));
        Assert.assertTrue(cartItem.getText().contains("Laptop"));

        // Proceed to checkout
        WebElement checkoutButton = driver.findElement(By.xpath("//button[@class='_2KpZ6l _2ObVJD _3AWRsL']"));
        checkoutButton.click();

        // User authentication
        WebElement loginForm = driver.findElement(By.xpath("//input[@name='email']"));
        loginForm.sendKeys("your_email@example.com");

        WebElement passwordForm = driver.findElement(By.xpath("//input[@name='password']"));
        passwordForm.sendKeys("your_password");

        WebElement loginButton = driver.findElement(By.xpath("//button[text()='Login']"));
        loginButton.click();

        // Verify user is logged in
        WebElement loggedInUser = driver.findElement(By.xpath("//div[@class='_1TmfNK']"));
        Assert.assertTrue(loggedInUser.getText().contains("Hello, Your Name"));

        // Shipping information
        WebElement addressForm = driver.findElement(By.xpath("//input[@name='address']"));
        addressForm.sendKeys("Your Address");

        // Add additional shipping information as needed

        // Proceed to payment information
        WebElement continueToPaymentButton = driver.findElement(By.xpath("//button[text()='Continue']"));
        continueToPaymentButton.click();

        // Choose payment method

        // Review order summary

        // Assertions for remaining steps can be added based on specific requirements
    }
}



