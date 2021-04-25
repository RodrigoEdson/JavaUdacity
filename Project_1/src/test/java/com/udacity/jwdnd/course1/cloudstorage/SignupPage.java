package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignupPage {

    @FindBy(id="form-signup")
    private WebElement signupForm;

    @FindBy(id="inputFirstName")
    private WebElement inputFirstName;

    @FindBy(id="inputLastName")
    private WebElement inputLastName;

    @FindBy(id="inputUsername")
    private WebElement inputUsername;

    @FindBy(id="inputPassword")
    private WebElement inputPassword;

    @FindBy(id="submit-button")
    private WebElement submitButton;

    public SignupPage(WebDriver webDriver) {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        WebElement marker = wait.until(driver -> driver.findElement(By.id("submit-button")));
        PageFactory.initElements(webDriver, this);
    }

    public boolean isFormSignupNotNull(){
        return (signupForm != null);
    }

    public void setFieldsText (String firstName, String lastName, String username, String password){
        this.inputFirstName.sendKeys(firstName);
        this.inputLastName.sendKeys(lastName);
        this.inputUsername.sendKeys(username);
        this.inputPassword.sendKeys(password);
    }

    public void registerUser(String firstName, String lastName, String username, String password){
        this.setFieldsText(firstName,lastName,username,password);
        this.submitButton.click();
    }

}
