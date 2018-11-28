package Grid;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Grid_SetUp {
	public static WebDriver driver;

	public static void main(String[]  args) throws MalformedURLException, InterruptedException{

 		String URL = "http://www.seleniumhq.org";
 		DesiredCapabilities cap = DesiredCapabilities.firefox();
 		cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
 		driver = new RemoteWebDriver(new URL("http://192.168.131.156:4444/wd/hub"),cap);
 		driver.navigate().to(URL);
 		Thread.sleep(5000);
 		driver.quit();
 	}		
}