package LoginTestCases;

import java.io.FileInputStream;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


public class Login{

	WebDriver driver;
	String[][] data;


	//open chrome before the test and hold it for entire test to be done
	@BeforeTest
	public void chromeopening() {
		System.setProperty("webdriver.chrome.driver", "E:\\Selenium Practice\\chromedriver_win32\\chromedriver.exe");

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);

	}

	//closing the browser after the testing is done
	@AfterTest
	public void excitchrome() {
		driver.quit();
	}


	//method to read the excel file and store the data into a string array
	public String[][] readexcel() throws BiffException, IOException{
		FileInputStream excel = new FileInputStream("E:\\Selenium Practice\\testdata.xls");  //path to excel file
		Workbook workbook = Workbook.getWorkbook(excel);                                      //open the workbook 
		Sheet sheet = workbook.getSheet(0);                                                   //go to sheet 1
		int rowcount = sheet.getRows();                                                       //number of rows that contains data
		int columncount = sheet.getColumns();                                                 //number of columns that contains data

		String testdata[][] = new String[rowcount-1][columncount];                          //array to store the data getting from excel

		for (int i=1;i<rowcount;i++) {
			for (int j=0;j<columncount;j++) {
				testdata[i-1][j]=sheet.getCell(j,i).getContents();
			}
		}

		return testdata;

	}


	//data provider to get the data in the string array
	@DataProvider(name = "Logindata")
	public String[][] loginDataProvider() throws BiffException, IOException {
		data = readexcel();
		System.out.println(data);
		return data;
	}


	//calling for data provider and do the testing for each scenario
	@Test(dataProvider = "Logindata")
	public void loginwithCorrectUserName(String Uname,String Pword) throws InterruptedException {

		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		Thread.sleep(3000);


		WebElement username = driver.findElement(By.name("username"));
		username.sendKeys(Uname);

		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys(Pword+Keys.ENTER);


	}

}
