package com.RemoteWebDriver.TestCases;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SeleniumTest {

	Function<String, RemoteWebDriver> rDriverFunction = browserName ->{
			try {
				return browserName.equalsIgnoreCase("chrome")?new RemoteWebDriver(new URL("http://localhost:4545/wd/hub"), DesiredCapabilities.chrome())
						: new RemoteWebDriver(new URL("http://localhost:4545/wd/hub"), DesiredCapabilities.firefox());
			} catch (Exception e) {
				throw new RuntimeException("Browser not to be Initiated" + e.getMessage());
		}
	};

	@DataProvider
	public Object[][] getBrowserInformation() {
		return new Object[][] { { "chrome" }, { "firefox" } };
	}

	@Test(priority = 1, description = "Test Execution with Remote Webdriver and Dockerfile and Docker Compose", dataProvider = "getBrowserInformation")
	public void tcSeleniumRemoteExecution(String broswerOpt) throws MalformedURLException, InterruptedException {
		RemoteWebDriver rDriver = rDriverFunction.apply(broswerOpt);
		rDriver.manage().window().maximize();
		rDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		rDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		rDriver.navigate().to("https://demo.nopcommerce.com/");
		Thread.sleep(2000);
		Assert.assertEquals(rDriver.getTitle(), "nopCommerce demo store");
		rDriver.quit();
	}

}
