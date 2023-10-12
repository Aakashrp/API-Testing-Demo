import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import serializePojo.AddPlace;
import serializePojo.Location;

import java.util.ArrayList;
import java.util.List;

public class SerializeTest1 {

	public static void main(String Args[])
	{
		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setAddress("29, side layout, cohen 09");
		p.setLanguage("French-IN");
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("http://google.com");
		p.setName("Jhon");
		
		List<String> l1 = new ArrayList<String>();
        l1.add("shoe park");
        l1.add("shop");
        p.setTypes(l1);
        
        Location l = new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        p.setLocation(l);
        
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		Response res = given().log().all().queryParam("key", "qaclick123").body(p)
				.when().post("/maps/api/place/add/json").then().assertThat().statusCode(200).extract().response();
		
		String responseString = res.toString();
		System.out.println(responseString);
		
	}
	
}
