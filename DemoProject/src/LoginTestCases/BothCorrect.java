package LoginTestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class BothCorrect {
	@Test
	public void loginwithCorrectUserName() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "E:\\Selenium Practice\\chromedriver_win32\\chromedriver.exe");

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);

		driver.navigate().to("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		Thread.sleep(3000);

		WebElement username = driver.findElement(By.name("username"));
		username.sendKeys("Admin");

		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("admin123"+Keys.ENTER);


		driver.quit();
	}

}
