package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;
	private LoginPage loginPage;
	private SignupPage signupPage;
	private HomePage homePage;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void unloggedUserCantAccessHomePage(){
		driver.get("http://localhost:" + port + "/home");
		loginPage = new LoginPage(driver);
		//test if the page have the login form
		assertEquals(true,loginPage.isFormLoginNotNull() );
	}
	@Test
	public void unloggedUserCanAccessLoginPage(){
		driver.get("http://localhost:" + port + "/login");
		loginPage = new LoginPage(driver);
		//test if the page have the login form
		assertEquals(true,loginPage.isFormLoginNotNull() );
	}

	@Test
	public void unloggedUserCanAccessSignupPage(){
		driver.get("http://localhost:" + port + "/signup");
		signupPage = new SignupPage(driver);
		//test if the page have the login form
		assertEquals(true,signupPage.isFormSignupNotNull() );
	}

	@Test
	public void loggedUserCanAccessHomePage(){
		//create a user
		driver.get(" http://localhost:" + port + "/signup");
		signupPage = new SignupPage(driver);
		signupPage.registerUser("Jose","Silva","jose","MyPass");

		driver.get("http://localhost:" + port + "/login");
		loginPage = new LoginPage(driver);
		loginPage.login("jose","MyPass");

		driver.get("http://localhost:" + port + "/home");
		homePage = new HomePage(driver,true);
		assertEquals(true,homePage.isContentDivNotNull());
	}

	@Test
	public void loggedUserCantAccessHomePageAfterLogoff(){
		//create a user
		driver.get(" http://localhost:" + port + "/signup");
		signupPage = new SignupPage(driver);
		signupPage.registerUser("Maria","Silva","maria","MyPass");

		driver.get("http://localhost:" + port + "/login");
		loginPage = new LoginPage(driver);
		loginPage.login("maria","MyPass");


		driver.get("http://localhost:" + port + "/home");
		homePage = new HomePage(driver,true);
		homePage.logout();
		driver.get("http://localhost:" + port + "/home");
		homePage = new HomePage(driver,false);
		assertEquals(true,homePage.isContentDivNotNull());
	}

	@Test
	public void createANoteAndCheckItInTheList() throws InterruptedException {
		//create a user
		driver.get(" http://localhost:" + port + "/signup");
		signupPage = new SignupPage(driver);
		signupPage.registerUser("Joana","Silva","joana","MyPass");

		driver.get("http://localhost:" + port + "/login");
		loginPage = new LoginPage(driver);
		loginPage.login("joana","MyPass");


		driver.get("http://localhost:" + port + "/home");
		homePage = new HomePage(driver,true);
		homePage.goToTab(HomePage.TAB_NOTES);
		int qtdNotes = homePage.getNotesCount();
		Thread.sleep(1000);
		homePage.clickNewNote();
		Thread.sleep(1000);
		homePage.setNoteData("Note title", "Note description");
		homePage.clickSubmitNote();

		assertEquals(qtdNotes+1,homePage.getNotesCount());
	}

	@Test
	public void editExistingNote() throws InterruptedException {
		//create a user
		driver.get(" http://localhost:" + port + "/signup");
		signupPage = new SignupPage(driver);
		signupPage.registerUser("Pedro","Silva","pedro","MyPass");

		driver.get("http://localhost:" + port + "/login");
		loginPage = new LoginPage(driver);
		loginPage.login("pedro","MyPass");

		driver.get("http://localhost:" + port + "/home");
		homePage = new HomePage(driver,true);
		homePage.goToTab(HomePage.TAB_NOTES);
		Thread.sleep(1000);
		homePage.clickNewNote();
		Thread.sleep(1000);
		homePage.setNoteData("Note title Pedro", "Note description");
		homePage.clickSubmitNote();

		Thread.sleep(1000);
		homePage.editExistingNote(0);
		Thread.sleep(1000);
		homePage.setNoteData("Note title edited", "Note description edited");
		homePage.clickSubmitNote();

		Thread.sleep(1000);
		assertEquals("Note title edited",homePage.getNoteTitle(0));

	}

	@Test
	public void deleteExistingNote() throws InterruptedException {
		//create a user
		driver.get(" http://localhost:" + port + "/signup");
		signupPage = new SignupPage(driver);
		signupPage.registerUser("Otavio","Silva","otavio","MyPass");

		driver.get("http://localhost:" + port + "/login");
		loginPage = new LoginPage(driver);
		loginPage.login("otavio","MyPass");

		driver.get("http://localhost:" + port + "/home");
		homePage = new HomePage(driver,true);
		homePage.goToTab(HomePage.TAB_NOTES);
		Thread.sleep(1000);
		homePage.clickNewNote();
		Thread.sleep(1000);
		homePage.setNoteData("Note title Otavio", "Note description");
		homePage.clickSubmitNote();

		int qtdNotesBeforeDelete = homePage.getNotesCount();
		Thread.sleep(1000);
		homePage.deleteNote(0);
		Thread.sleep(1000);
		assertEquals(qtdNotesBeforeDelete -1 ,homePage.getNotesCount());

	}

}
