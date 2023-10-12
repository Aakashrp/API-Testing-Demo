import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.files.ReusableMethods;
import com.files.payload;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJsonLibrary {

	@Test(dataProvider = "Book Details")
	public void addBook(String ibsn, String aisle) {
		// Post Method to add book
		RestAssured.baseURI = "http://216.10.245.166";
		// Dynamically build JSON payload with external input
		String reponse = given().log().all().header("Content-Type", "application/json")
				.body(payload.AddBook("ibsn", "aisle")). // if u want to enter your values for book pass values here in
															// addBook e=method and declared oarameters in json method
				when().post("/Library/Addbook.php").then().assertThat().log().all().statusCode(200).extract().response()
				.asString();

		JsonPath js1 = ReusableMethods.stringToJson(reponse);
		String id = js1.get("ID"); // ID is the combination of ibsn and aisle
		System.out.println(id);
	}

	// Accepting multiple values using data provider

	@DataProvider(name = "Book Details")
	public Object[][] getData() {
		// array = Collection of elements
		// multidimensional array= Collection of arrays
		return new Object[][] { { "shsh", "0213" }, { "iwiw", "3134" }, { "laks", "1243" } }; // If u add 10 book
																								// details and use this
																								// data provider with
																								// @Test then it will
																								// run for 10 times

	}

	// Delete Method
    
	
	@Test(dataProvider = "Book Details")
	public void deleteAPI(String ibsn, String aisle) {
		String reponse = given().log().all().header("Content-Type", "application/json")
				.body(payload.AddBook("ibsn", "aisle")). // if u want to enter your values for book pass values here in
															// addBook e=method and declared parameters in json method
				when().delete("/Library/Addbook.php").then().assertThat().log().all().statusCode(200).extract()
				.response().asString();
		
		JsonPath js1 = ReusableMethods.stringToJson(reponse);
		String id = js1.get("ID"); // ID is the combination of ibsn and aisle
		System.out.println(id);

	}
	
	
	

}
