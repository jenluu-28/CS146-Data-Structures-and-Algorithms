package simulator;

//Manages the contents of a webpage; includes the url and pagerank
public class Webpage 
{
	
	private String url;
	private PageRank score;
	
	
	//Attaches the url of a webpage to a pagerank score
	public Webpage(String url, PageRank score)
	{
		this.url = url; 
		this.score = score;
	}
	
	
	//Returns the url of a webpage
	public String getUrl()
	{
		return this.url;
	}
	
	//Returns the pagerank/score of a webpage
	public int getScore()
	{
		return this.score.getPageRank();
	}
	
	public void increasePayment(int payment)
	{
		score.addSponsor(payment);
	}
	


}
