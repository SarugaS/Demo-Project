package LoginTestCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DataDrivenUsingPOI {

	static List<String> usernamelist = new ArrayList<String>();    //list for username
	static List<String> passwordlist = new ArrayList<String>();     //list for password


	// reading excel file using apache POI
	
	public void excelreading() throws IOException {
		FileInputStream excel = new FileInputStream("E:\\Selenium Practice\\testdata.xls");
		Workbook workbook = new HSSFWorkbook(excel);
		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();

		while (rowIterator.hasNext()) {
			Row rowvalue = rowIterator.next();

			Iterator<Cell> columnIterator= rowvalue.iterator();

			int i=2;                                     //for seperating purpose of username and password
			while (columnIterator.hasNext()) {
				if(i%2==0) {
					Cell username = columnIterator.next();
					usernamelist.add(username.toString());
				}else {
					Cell password = columnIterator.next();
					passwordlist.add(password.toString());
				}
				i++;
			}

		}


	}
	
	
	//open chrome and sending data values 
	
	public void login(String Uname,String Pword) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "E:\\Selenium Practice\\chromedriver_win32\\chromedriver.exe");

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);
		
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		Thread.sleep(3000);


		WebElement username = driver.findElement(By.name("username"));
		username.sendKeys(Uname);

		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys(Pword+Keys.ENTER);
		
		driver.quit();


	}

	
	//iteration of test data for testing with login method
	
	public void executeTest() throws InterruptedException {
		for(int i=0; i<usernamelist.size();i++) {
			login(usernamelist.get(i),passwordlist.get(i));	
		}
	}
	


	//main method to execute the program
	
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		DataDrivenUsingPOI usingpoi = new DataDrivenUsingPOI();
		usingpoi.excelreading();	
		System.out.println("Username list: "+usernamelist);
		System.out.println("password list: "+passwordlist);
		usingpoi.executeTest();


	}

}
