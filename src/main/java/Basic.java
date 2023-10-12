import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.files.ReusableMethods;

public class Basic {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		// Validate API if Add place is working or not

		// given - all input details
		// when - Submit the API
		// Then - validate the response
        // Content of the file to String-> content of file can convert into Bytes-> Bytes data to String
		
		System.out.println(" **********************************  POST REQUEST **********************************");

		RestAssured.baseURI = "https://reqres.in";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-type", "application/json")
		//		.body(payload.addUser()).when().post("api/users").then().assertThat().statusCode(201)
				.body(new String(Files.readAllBytes(Paths.get("F:\\API\\adddetails.json"))))   // Pass Static Json file direclty by converting bytes to string 
				.when().post("api/users").then().assertThat().statusCode(201)
				.body("job", equalTo("leader")) // static package are not shown by eclipse u have to import it ..equalTo() is of hamcrest package
												
				.header("Server", "cloudflare").extract().response().asString(); // extract the response in string
		// If u don't want to use log().all() methods then u can use extract method to
		
		// print the response on console
		System.out.println(response);

		JsonPath js = new JsonPath(response); // Creating Json path object which takes input as string and convert into
												// json
		String name = js.getString("name");
		System.out.println(name);

		// User added now update the job of the user ---> and using get method to
		// validate it is correct or not

		System.out.println(" **********************************  PUT REQUEST **********************************");
		// Update job using put method
		RestAssured.baseURI = "https://reqres.in";
		given().log().all().header("Content-type", "application/json")
				.body("{\r\n" + "    \"name\": \"Jack\",\r\n" + "    \"job\": \"leader first\"\r\n" + "}").when()
				.put("api/users/2").then().assertThat().statusCode(200)
				.body("job", equalTo("leader first"));

		// Get request
		System.out.println(" **********************************  GET REQUEST **********************************");
		String getResponse= given().log().all().queryParam("first_name", "Janet").
				when().get("api/users/2").
				then().assertThat().statusCode(200).header("Content-Type", "application/json; charset=utf-8").
				body("data.id", equalTo(2)).extract().response().asString();
		
		//Whenever u r using JsonPath save response in string then pass it (to get response use extract().reponse().asString() methods)
		
		JsonPath js1=ReusableMethods.stringToJson(getResponse);  //this method will return object  so save in JsonPath js1
		String output =js1.getString("data");
        System.out.println(output); 
        
      //  Assert.assertEquals(getResponse,output);
        
		
	}

}
