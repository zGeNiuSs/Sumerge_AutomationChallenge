package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    WebDriver driver = null;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement username() {
        return driver.findElement(By.id("user-name"));
    }

    public WebElement password() {
        return driver.findElement(By.id("password"));
    }

    public WebElement loginButton() {
        return driver.findElement(By.id("login-button"));
    }

    public WebElement swagLabsHeader() {
        return driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[1]/div[2]/div"));
    }

    public WebElement errorMsg() {
        return driver.findElement(By.className("error-message-container"));
    }

    public void clearFields() {
        driver.findElement(By.id("user-name")).clear();
        driver.findElement(By.id("password")).clear();
    }
}
