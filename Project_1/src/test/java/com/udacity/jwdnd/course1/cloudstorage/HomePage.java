package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomePage {

    public static final String TAB_FILES = "FILES";
    public static final String TAB_NOTES = "NOTES";
    public static final String TAB_CREDENTIALS = "CREDENTIALS";

    @FindBy(id="contentDiv")
    private WebElement contentDiv;

    @FindBy(id="logout-submit-button")
    private WebElement logoutSubmitButton;

    @FindBy(id="nav-files-tab")
    private WebElement navFiles;

    @FindBy(id="nav-notes-tab")
    private WebElement navNotes;

    @FindBy(id="nav-credentials-tab")
    private WebElement navCredentials;


    @FindBy(id="note-title")
    private WebElement noteTitle;

    @FindBy(id="note-description")
    private WebElement noteDescription;

    @FindBy(id="button-new-note")
    private WebElement buttonNewNote;

    @FindBy(id="noteSubmit")
    private WebElement buttonSubmitNote;

    @FindBy(xpath="//table[@id='notesTable']/tbody/tr")
    private List<WebElement> noteRowsList;

    public HomePage(WebDriver webDriver , boolean testLoad) {
        if (testLoad) {
            WebDriverWait wait = new WebDriverWait(webDriver, 10);
            WebElement marker = wait.until(driver -> driver.findElement(By.id("mainScript")));
        }
        PageFactory.initElements(webDriver, this);
    }

    public boolean isContentDivNotNull(){
        return (contentDiv != null);
    }

    public void logout(){
        logoutSubmitButton.click();
    }

    public void goToTab(String tab){
        switch(tab) {
            case HomePage.TAB_FILES:
                this.navFiles.click();
                break;
            case HomePage.TAB_NOTES:
                this.navNotes.click();
                break;
            case HomePage.TAB_CREDENTIALS:
                this.navCredentials.click();
                break;
        }
    }

    public void clickNewNote(){
        this.buttonNewNote.click();
    }
    public  void setNoteData(String noteTitle, String noteDescription){
        this.noteTitle.clear();
        this.noteTitle.sendKeys(noteTitle);
        this.noteDescription.clear();
        this.noteDescription.sendKeys(noteDescription);
    }
    public void clickSubmitNote(){
        this.buttonSubmitNote.submit();
    }

    public int getNotesCount(){
        return noteRowsList.size();
    }

    public void editExistingNote(int noteIndex) {
        this.noteRowsList.get(noteIndex).findElement(By.name("edit-note")).click();
    }

    public String getNoteTitle(int noteIndex) {
        return this.noteRowsList.get(noteIndex).findElement(By.name("noteTitle")).getText();
    }

    public void deleteNote(int noteIndex) {
        this.noteRowsList.get(noteIndex).findElement(By.name("form-note")).submit();
    }
}
