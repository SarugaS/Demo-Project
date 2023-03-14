package LoginTestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BothIncorrect {
	@Test
	@Parameters({"User","Pass"})
	public void loginwithCorrectUserName(String Uname,String Pword) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "E:\\Selenium Practice\\chromedriver_win32\\chromedriver.exe");

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);

		driver.navigate().to("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		Thread.sleep(3000);

		WebElement username = driver.findElement(By.name("username"));
		username.sendKeys(Uname);

		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys(Pword+Keys.ENTER);


		driver.quit();
	}

}
