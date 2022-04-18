package SSN;



//A class that implements the creation of social security numbers
public class Number 
{
	//Holds the SSN
	private String ssNumber;
	
	public Number()
	{
		//Multiply by 900 to get the range from 0 - 899 and add by 100 to shift the range from 100 - 999
		int area = (int)(Math.random()*900 + 100);
		//Multiply by 90 to get the range from 0 - 89 and add by 10 to shift the range from 10 - 99
		int group = (int)(Math.random()*90 + 10);
		//Multiply by 9000 to get the range from 0 - 8999 and add by 1000 to shift the range from 1000 - 9999
		int serial = (int)(Math.random()*9000 + 1000);
		
		
		this.ssNumber  = area + "-" + group + "-" + serial;
		
	}
	
	//Getter method for the SSN string
	public String getSSN()
	{
		return ssNumber;
	}
	
	

}
