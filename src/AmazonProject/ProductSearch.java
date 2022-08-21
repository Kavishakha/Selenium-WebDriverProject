package AmazonProject;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ProductSearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		//Launch Amazon.in
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MICROSECONDS);
		
		
		//Search for Samsung Products
		WebElement Search = driver.findElement(By.xpath("//input[@name='field-keywords']"));
		Search.sendKeys("Samsung");
		
		WebElement GoBtn = driver.findElement(By.id("nav-search-submit-button"));
		GoBtn.click();
		
//		WebElement Result1 = driver.findElement(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']"));
//		Result1.click();
		
		List<WebElement> ProdName = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//h2"));
		
		List<WebElement> Prices = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//span[@class='a-price']"));
		
		
		//Print all the products along with Prices
		System.out.println("Total Products are: " + ProdName.size());
		for (int i=0; i<ProdName.size(); i++) {

			System.out.println(ProdName.get(i).getText() + " " + Prices.get(i).getText());
			
		}
		
		String ParentWin = driver.getWindowHandle();
		String ExpectedValue = ProdName.get(0).getText();
		
		Set<String> allWins = driver.getWindowHandles();
		
		for (String win: allWins) {
			
			System.out.println(win);
			
			if(!win.equals(ParentWin)) {
				
				driver.switchTo().window(win);
			}
		}
		
		WebElement title = driver.findElement(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']"));
		String str = title.getText();
		
		if(str.equals(ExpectedValue)) {
			System.out.println("Title is matching");
		}
		else {
			System.out.println("Title is not matching");
		}		
		
	}
		
}
