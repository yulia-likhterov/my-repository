package zimun.torim.infra.config;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import zimun.torim.infra.web.BrowserType;

public class MainConfig {
	
	//public static String username;
	//public static String password;
	public static BrowserType browserType;
	public static String baseUrl;
	
	public static void initFromFile(String configFilePath) throws Exception {

		InputStream input = new FileInputStream(configFilePath);
		Properties prop = new Properties();
		prop.load(input);

		//username = prop.getProperty("username");
		//password = prop.getProperty("password");
		baseUrl = prop.getProperty("baseUrl");
		browserType = BrowserType.valueOf(prop.getProperty("browserType"));

	}
}