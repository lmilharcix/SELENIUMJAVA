package baseWeb;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class WebPropertyManager {
	/*
	 * Read property file
	 */

	public static Properties readPropertiesFile() throws IOException {
		FileInputStream fis = null;
		Properties prop = null;
		try {
			String projectPath = System.getProperty("user.dir");
			fis = new FileInputStream(projectPath + "/src/test/resources/webProperty.properties");
			prop = new Properties();
			prop.load(fis);
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			fis.close();
		}
		return prop;
	}

	/*
	 * environment mapping base on property file key 
	 */

	public static HashMap<String, String> getDataDetails() throws IOException {

		HashMap<String, String> map = new HashMap<String, String>();

		// Please enable below line when run from TestRunner
		 String env = readPropertiesFile().getProperty("env");

		// Use to run from command line
		//String env = System.getProperty("environment");

		System.out.println("*********** " + env + " ***********");
		if (env.equalsIgnoreCase("dev")) {
			map.put("calculator", readPropertiesFile().getProperty("devCalculator"));			
		}

		else if (env.equalsIgnoreCase("prod")) {
			map.put("calculator",
					readPropertiesFile().getProperty("prodCalculator"));
		}
		
		else if (env.equalsIgnoreCase("uat")) {
			map.put("calculator", readPropertiesFile().getProperty("uatCalculator"));
		}
		
		return map;

	}

}
