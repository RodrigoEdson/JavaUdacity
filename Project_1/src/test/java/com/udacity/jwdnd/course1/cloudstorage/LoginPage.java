package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    @FindBy(id="form-login")
    private WebElement loginForm;

    @FindBy(id="inputUsername")
    private WebElement inputUserName;

    @FindBy(id="inputPassword")
    private WebElement inputPassword;

    @FindBy(id="submit-button")
    private WebElement submitButton;

    @FindBy(id="signup-link")
    private WebElement signupLink;

    public LoginPage(WebDriver webDriver) {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        WebElement marker = wait.until(driver -> driver.findElement(By.id("submit-button")));
        PageFactory.initElements(webDriver, this);
    }

    public boolean isFormLoginNotNull(){
        return (loginForm != null);
    }

    public void login(String username, String password){
        this.inputUserName.sendKeys(username);
        this.inputPassword.sendKeys(password);
        submitButton.click();
    }

}
