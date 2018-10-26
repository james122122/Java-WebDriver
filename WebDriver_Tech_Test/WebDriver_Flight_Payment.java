/* James Dolphin - Revolution IT Technical Assessment
 * Web Flight Purchase Automated Script
 * 
 * Coded in Java with Selenium WebDriver and Junit dependencies
 * Requires JDK installation and system build path for Java and ChromeDriver
 * Ensure that System.setProperty is set to your chromedriver.exe path
 * Referenced libraries "Selenium Standalone Server" and "Java WebDriver Language Bindings"
 * found at: https://www.seleniumhq.org/download/
 */


import org.openqa.selenium.*; //WebDriver, By, WebElement, ChromeDriver class

import org.openqa.selenium.chrome.*; //includes ChromeDriver class

import org.openqa.selenium.support.ui.Select; //for WebDriver Dropdown select functionality

import java.util.*; //used for List functionality

import org.junit.Test;

public class WebDriver_Flight_Payment {

	@Test
	public void WebDriver_Test() {
	
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		//go to website
		driver.get("http://newtours.demoaut.com/mercurywelcome.php");
		
		//wait for 1s
		waitTimer();
		
		//---First page---
		
		//find username text field and enter username
		WebElement username = driver.findElement(By.name("userName"));
		username.sendKeys("mercury");
		
		waitTimer();
		
		//find password text field and enter password
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("mercury");
		
		waitTimer();
		
		//submit form
		WebElement login = driver.findElement(By.name("login"));
		login.submit();

		//wait until new page is reached
		checkPage("Find a Flight: Mercury Tours:", driver);
		
		//---Second page---

		//select the Trip Type radio field
		List<WebElement>tripType = driver.findElements(By.name("tripType"));
		tripType.get(1).click(); //click the "one way" option
		
		waitTimer();
		
		//select departure location, select "Sydney"
		Select departFrom = new Select(driver.findElement(By.name("fromPort")));
		departFrom.selectByVisibleText("Sydney");
		
		waitTimer();
		
		//select arrival location, select "London"
		Select arriveAt = new Select(driver.findElement(By.name("toPort")));
		arriveAt.selectByVisibleText("London");
		
		waitTimer();
		
		List<WebElement>serviceClass = driver.findElements(By.name("servClass"));
		serviceClass.get(2).click(); //select first class
		
		waitTimer();
		
		WebElement findFlights = driver.findElement(By.name("findFlights"));
		findFlights.submit(); //submit this page's form

		//check that the new page has been reached
		checkPage("Select a Flight: Mercury Tours", driver);
		
		//---Third page---
			
		WebElement nextPage = driver.findElement(By.name("results"));
		nextPage.submit(); //submit this page's form
		
		//check that the new page has been reached
		checkPage("Book a Flight: Mercury Tours", driver);
		
		
		//---Fourth page----
		
		//find the first name field
		WebElement fName = driver.findElement(By.name("passFirst0"));
		fName.sendKeys("James");
		
		waitTimer();
		
		//find the last name field
		WebElement lName = driver.findElement(By.name("passLast0"));
		lName.sendKeys("Dolphin");
		
		waitTimer();
		
		//find the credit card number field, and enter the number
		WebElement CCNo = driver.findElement(By.name("creditnumber"));
		CCNo.sendKeys("572844210093");
		
		waitTimer();
		
		//select the ticketless checkbox
		driver.findElement(By.name("ticketLess")).click();
		
		WebElement submitForm = driver.findElement(By.name("bookflight"));
		submitForm.submit(); //submit the final form
		
		//confirm that the form has been completed correctly
		checkPage("Flight Confirmation: Mercury Tours", driver);
		
		waitTimer();
		
		//close the window when complete
		
		driver.quit();
}

	//function to handle waiting at 1s intervals
	void waitTimer()
	{
		try {
				Thread.sleep(1000);
			} catch(InterruptedException e) {
				System.out.println("Interrupted");
			}
	}
	
	//function to validate page
	void checkPage(String pName, WebDriver driver)
	{
		while(!driver.getTitle().equals(pName))
		{
			waitTimer();
		}
		System.out.println("Current Page: " + pName);
		//print the current page in the console
	}
}