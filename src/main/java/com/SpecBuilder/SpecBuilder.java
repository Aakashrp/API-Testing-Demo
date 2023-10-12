			package com.SpecBuilder;

import static io.restassured.RestAssured.given;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import serializePojo.AddPlace;
import serializePojo.Location;

import java.util.ArrayList;
import java.util.List;

public class SpecBuilder {

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
        
        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
        .setContentType(ContentType.JSON).build();     //Return will be  RequestSpecification of the object u created and using build method it will return the obj
        
        
		//RestAssured.baseURI = "https://rahulshettyacademy.com";
		//Response res = given().log().all().queryParam("key", "qaclick123").body(p)
		//.when().post("/maps/api/place/add/json").then().assertThat().statusCode(200).extract().response();
		
        //When you use request specification the code will be reduced and no need to declare it again and agian
        ResponseSpecification resspec= new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();  // return type is ResponseSpecification 
        
        RequestSpecification res = given().spec(req).body(p);
        
        		 Response response= res.when().post("/maps/api/place/add/json")       // directly taking res and u can continue 
        		.then().spec(resspec).extract().response();
        
		String responseString = response.asString();
		System.out.println(responseString);
		
	}
	
}
