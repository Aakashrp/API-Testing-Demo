import com.files.payload;

import io.restassured.path.json.JsonPath;

public class ComplexJson {

	// If the developer is developing the API and u know the format u can start
	// developing script using mock API
	public static void main(String Args[]) {
		JsonPath js = new JsonPath(payload.CoursePrice());

		// Print No of courses returned by API
		int count = js.getInt("courses.size()");
		System.out.println(count);

		// Print Purchase Amount

		int totalAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(totalAmount);

		// Print Title of the first course
		String firstCourse = js.get("courses[0].title");
		System.out.println(firstCourse);

		// Print All course titles and their respective Prices
        //Use for loop and courses main branch . title or price 
		
		for (int i = 0; i < count; i++) {
			String courses = js.get("courses[" + i + "].title"); // to add i as a variable in between string use "+i+"
			System.out.println(js.get("courses[" + i + "].price").toString()); // use to string in syso to print prices if u r not sure what is the type
			System.out.println(courses);
		}   
		
		//Print no of copies sold by RPA Course
	    System.out.println("Print no of copies sold by RPA Course");

	    for (int i = 0; i < count; i++) {
			String courses = js.get("courses[" + i + "].title");
		    if(courses.equalsIgnoreCase("RPA")) 	
		    {
		    	//copies sold
		    	int copies=js.get("courses[" + i + "].copies");
                System.out.println(copies);
                break;   // Once we get RPA copies it will stop the loop 
		    }
	    }	
	    


          	    
	    
	}
}
