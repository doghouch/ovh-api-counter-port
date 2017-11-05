
/** 

  Depression.py Java Port 
  Aaron Hong - Updated November 4th, 2017
  
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import com.ovh.api.OvhApi;
import com.ovh.api.OvhApiException;
import org.json.*;

public class Depression {

	public static void main(String[] args) throws Exception {
		// Property Reading
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream("config.properties");
			prop.load(input);
		} catch (IOException ex) {
			System.out.println("Unexpected Error: config.properties couldn't be found.");
			return;
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					System.out.println("Unexpected Error: config.properties couldn't be found.");
					return;
				}
			}
		}
		// Initializations
		double totalBill = 0;
		int omitted = 0;
		String appKey = prop.getProperty("applicationkey");
		String appSec = prop.getProperty("applicationsecret");
		String appComKey = prop.getProperty("consumerkey");
		String appRegion = prop.getProperty("region");
		String billResponse, currencyCode;
		// Intro Message
		System.out.println("Welcome to Depression.jar. This program will read the total you've spent at OVH to this date.\n(by dawgy)\n");
		// API Handling
		OvhApi apiobj = new OvhApi(appRegion, appKey, appSec, appComKey);
		if (appRegion.equals("ovh-eu")) {
			currencyCode = "EUR";
		} else if (appRegion.equals("ovh-ca")) {
			currencyCode = "CAD";
		} else if (appRegion.equals("ovh-us")) {
			currencyCode = "USD";
		} else {
			currencyCode = "(Unknown Currency)";
		}
		try {
			billResponse = apiobj.get("/me/bill");
			Object[] convertedResponse = billResponse.replaceAll("\\[|\\]|\"", "").split(",");
			for (int i = 0; i < convertedResponse.length; i++) {
				JSONObject cost = new JSONObject(apiobj.get("/me/bill/" + convertedResponse[i]));
				JSONObject withTax = cost.getJSONObject("priceWithTax");
				totalBill = new Double(withTax.get("value").toString()) + totalBill;
				if (i >= 0 && i <= 10) {
				System.out.println(currencyCode + " - Invoice (" + convertedResponse[i] + ") - " + withTax.get("value"));
				} else {
					omitted++;
				}
			}
			System.out.println("\n" + omitted + " invoice(s) omitted.");
			System.out.println("\nGRAND TOTAL:\n" + currencyCode + " " + (double) Math.round(totalBill * 100) / 100);
		} catch (OvhApiException error) {
			System.out.println("Unexpected API Error: " + error);
		}
	}
}
