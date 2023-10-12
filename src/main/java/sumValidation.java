import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.files.payload;

import io.restassured.path.json.JsonPath;

public class sumValidation {

	@Test
	public void sumof()
	{
	    //Verify if Sum of all Course prices matches with Purchase Amount
		
		JsonPath js = new JsonPath(payload.CoursePrice());
		int count = js.getInt("courses.size()");
		int sum=0;
		
		for (int i = 0; i < count; i++) 
		{
			int price=js.getInt("courses["+i+"].price");
			int copies=js.getInt("courses["+i+"].copies");
			int amount = price * copies;
			System.out.println(amount);
			
			sum = sum+ amount;   // to add up all three amo unts into sum
			
		}
		System.out.println(sum);
		int purachaseamount =js.getInt("dashboard.purchaseAmount");
        assertEquals(sum, purachaseamount);  		
	}
}
