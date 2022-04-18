package simulator;
/*
 * A class to reference the four factors that determine a url's pagerank 
 * Used to keep a web page and its assigned pagerank together
 */
public class PageRank 
{
	//Refers to the frequency and location of a keyword within the web page
	private int frequency;
	
	//Refers to how long the web page has existed
	private int age;
	
	//Refers to the number of other web pages that link to the target web page
	private int relevancy;
	
	//Refers to how much the web page owner has paid for advertisement (may be increased)
	private int sponsor;
	
	//The cumulative page rank score
	private int total;
	
	
	
	//Creates a pagerank score and initializes random values from 1-100 for each factor 
	public PageRank()
	{
		
		//Multiply by 100 to increase the range from 0-99 and add 1 to shift it to 1-100
		this.frequency = (int)(Math.random()*100 + 1);
		this.age = (int)(Math.random()*100 + 1);
		this.relevancy = (int)(Math.random()*100 + 1);
		this.sponsor = (int)(Math.random()*100 + 1);
		
		this.total = this.frequency + this.age + this.relevancy + this.sponsor;
	}
	
	
	//Input: some amount of payment
	//Outcome: the sponsor factor of a webpage is increased
	
	
	//A second constructor for users to create their own pagerank
	public PageRank(int f, int a, int r, int s)
	{
		this.frequency = f;
		this.age = a;
		this.relevancy = r;
		this.sponsor = s;
		
	}
	
	
	//Adds the four factors to calculate the total pagerank score
	public int getPageRank()
	{
		return this.frequency + this.age + this.relevancy + this.sponsor;
	
	}
	
	public void addSponsor(int payment)
	{
		this.sponsor = this.sponsor + payment;
	}

}

