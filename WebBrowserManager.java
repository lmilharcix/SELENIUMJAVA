package baseWeb;

/*import java.awt.*;
import java.awt.event.KeyEvent;*/
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

import base.BaseClass;
import net.lightbody.bmp.BrowserMobProxyServer;

import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;

import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v119.network.Network;
import org.openqa.selenium.devtools.v119.network.model.Headers;
import org.openqa.selenium.devtools.v119.network.model.Request;
import org.openqa.selenium.devtools.v119.network.model.Response;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebBrowserManager extends BaseClass {
	public static WebDriver driver;
	String calculatorUrl;

	public BrowserMobProxyServer proxy;
	public Proxy seleniumProxy;
	public List<HarEntry> har2= new ArrayList<>();
	public String harName="dev.dev.v1sta.io";
	String harFileName="dev.dev.v1sta.io.har";

	public static Har har3;
	Har har;
	public File harFile = new File(harFileName);
	//List<HarEntry> har1=har.getLog().getEntries();
	public List<HarEntry> har1= new ArrayList<>();
	
	public WebBrowserManager() throws IOException {
		calculatorUrl = WebPropertyManager.getDataDetails().get("calculator");
	}

	//@BeforeTest
	public void driverSetUP() throws IOException, InterruptedException, AWTException {
		//Proxy Operations
		/*proxy = new BrowserMobProxyServer();
		proxy.setTrustAllServers(true);
		String hostIp = Inet4Address.getLocalHost().getHostAddress();
		proxy.setChainedProxy(InetSocketAddress.createUnresolved("127.0.0.1", 8888));
		//proxy.setMitmManager(ImpersonatingMitmManager.builder().trustAllServers(true).build());
		proxy.start(8888);
		seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
		seleniumProxy.setHttpProxy(hostIp + ":" + proxy.getPort());
		seleniumProxy.setSslProxy(hostIp + ":" + proxy.getPort());
		System.out.println(hostIp+" "+proxy.getPort());
		proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);*/


		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\chromedriver.exe");
		//System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
		//System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
		//System.setProperty("webdriver.chrome.whitelistedIps", "");
		/*DesiredCapabilities seleniumCapabilities = new DesiredCapabilities();
		seleniumCapabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
		seleniumCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

		EnumSet<CaptureType> captureTypes= CaptureType.getAllContentCaptureTypes();
		captureTypes.addAll(CaptureType.getCookieCaptureTypes());
		captureTypes.addAll(CaptureType.getHeaderCaptureTypes());
		captureTypes.addAll(CaptureType.getRequestCaptureTypes());
		captureTypes.addAll(CaptureType.getResponseCaptureTypes());
		proxy.setHarCaptureTypes(captureTypes);


		proxy.newHar(harName);*/
        // Set up the WebDriverManager for chrome driver
        WebDriverManager.chromedriver().setup();
        ChromeOptions ops = new ChromeOptions();
		//ops.addArguments("--headless");
		ops.addArguments("--disable-gpu");
		ops.addArguments("--no-sandbox");
        /*ops.addArguments("--remote-allow-origins=*");  // ChromeDriver 111.0.5563.19 unable to establish connection to chrome
		ops.addArguments("--disable-web-security");
		//ops.addArguments("--ignore-certificate-errors");
		//ops.addArguments("--user-data-dir=C:\\Users\\Ravinder\\AppData\\Local\\Google\\Chrome\\User Data");
		ops.addArguments("--crash-dumps-dir=/tmp");
		ops.addArguments("--no-sandbox");
		ops.addArguments("--disable-dev-shm-usage");*/
		//ops.addArguments("--no-sandbox", "--disable-dev-shm-usage");

		//ops.addArguments("--allow-insecure-localhost");
		//ops.addArguments("--ignore-urlfetcher-cert-requests");
		//ops.merge(seleniumCapabilities);


        driver = new ChromeDriver(ops);
		DevTools devtools =((ChromeDriver) driver).getDevTools();
		devtools.createSession();
		devtools.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty()));
		devtools.addListener(Network.requestWillBeSent(),requestConsumer ->{
			Request request = requestConsumer.getRequest();
			System.out.println(request.getUrl());
			Object authHeader = null;
			if(request.getUrl().contains("https://pricing.dev.jetsmarter.io/api/v1/charter-pricing-backend-api/meta")){
				Headers headers= request.getHeaders();
				for(int i=0;i<headers.size();i++){
					if(headers.get("Authorization")!=null){
						authHeader=headers.get("Authorization");
						break;
					}
				}
				System.out.println(authHeader);
				BaseClass.setAuthorization_Header_Key((String) authHeader);
			}
		});
		devtools.addListener(Network.responseReceived(),responseConsumer ->{
			Response response = responseConsumer.getResponse();
			System.out.println(response.getUrl());
			if(response.getUrl().contains("https://pricing.dev.jetsmarter.io/api/v1/charter-pricing-backend-api/meta")){
				Headers headers= response.getHeaders();
				Object authHeader=headers.get("Authorization");
			}
		});
        //driver.get("https://jetstudio-pricing-eng-release.dev.jetsmarter.io/");
		//driver.manage().window().maximize();
		driver.manage().window().setSize(new Dimension(2000,768));
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		//proxy.newHar(harName);
        driver.get("https://dev.dev.v1sta.io/pricing-calculator");

		//har3=proxy.newHar(harName);
		Thread.sleep(20000);

		//Robot robot = new Robot();
		Thread.sleep(5000);
		System.out.println("About to zoom out");


		/*// To zoom out 3 times
		for(int i=0; i<3; i++){
			driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL,Keys.SUBTRACT));
		}*/

		/*for (int i = 0; i < 3; i++) {
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_SUBTRACT);
			robot.keyRelease(KeyEvent.VK_SUBTRACT);
			robot.keyRelease(KeyEvent.VK_CONTROL);
		}*/

		Set<Cookie>  allcookies=driver.manage().getCookies();
		//har3 = proxy.getHar();
			/*har = proxy.getHar();
			har1=har.getLog().getEntries();
			har.writeTo(harFile);*/
		
	}

	public void driverSetUPWithoutLogs() throws IOException, InterruptedException, AWTException {


		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\chromedriver.exe");
		//System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

		// Set up the WebDriverManager for chrome driver
		//WebDriverManager.chromedriver().setup();
		ChromeOptions ops = new ChromeOptions();
		//ops.addArguments("--headless");
		ops.addArguments("--disable-gpu");
		ops.addArguments("--no-sandbox");



		driver = new ChromeDriver(ops);

		//driver.get("https://jetstudio-pricing-eng-release.dev.jetsmarter.io/");
		//driver.manage().window().maximize();
		driver.manage().window().setSize(new Dimension(2000,768));
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		//proxy.newHar(harName);
		driver.get("https://dev.dev.v1sta.io/pricing-calculator");


		Thread.sleep(20000);


		Thread.sleep(5000);
		System.out.println("About to zoom out");


		/*// To zoom out 3 times
		for(int i=0; i<3; i++){
			driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL,Keys.SUBTRACT));
		}*/

		/*for (int i = 0; i < 3; i++) {
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_SUBTRACT);
			robot.keyRelease(KeyEvent.VK_SUBTRACT);
			robot.keyRelease(KeyEvent.VK_CONTROL);
		}*/

	}

	/*public void setProxy(Har har3){
		this.har3 = har3;
	}
	public Har getProxy(){
		return har3;
	}*/

}
