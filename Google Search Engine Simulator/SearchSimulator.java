package simulator;
import java.util.*;

//The primary class to activate the search engine
public class SearchSimulator 
{
	//The webcrawler used to gather the urls
	private static WebCrawler spider;
	
	//An int[] used to hold the pagerank scores for calculation
	private static int[] pageRankScores = new int[30];
	
	//The heap of scores (follows the max heap property)
	private static int[] heapScores;
	
	//An int[] used to hold the first 20 scores in the heap for calculation
	private static int[] first20 = new int[30];
	
	//to store the list of randomly generated pageranks
	private static ArrayList<PageRank> listOfPageRanks = new ArrayList<PageRank>();
	
	//to store the list of urls gathered by the webcrawler
	private static ArrayList<String> listOfUrls = new ArrayList<String>();
	
	//to create and store 30 webpages 
	private static ArrayList<Webpage> listOfWebpages = new ArrayList<Webpage>(30);
	
	//to hold the sorted webpages based on pagerank
	private static ArrayList<Webpage> sortedWebpages = new ArrayList<Webpage>();
	
	//to hold the pagerank scores of the first 20 urls in the heap
	private static ArrayList<Webpage> first20PQ = new ArrayList<Webpage>();
	
	//to hold the webpages for calculation to increase a webpage
	private static ArrayList<Webpage> insertedWebpages = new ArrayList<Webpage>();
	
	//to hold the webpages for calculation to insert a new webpage
	private static ArrayList<Webpage> modifiedWebpages = new ArrayList<Webpage>();
	
	//A heap object to use methods from the heap class
	private static Heap heapObject = new Heap();
	
	//Creates an arraylist of 30 individual page ranks
	public static void generatePageRanks()
	{
		
		int i = 0;
		while( i != 30)
		{
			PageRank aPageRank =  new PageRank();
			listOfPageRanks.add(aPageRank);
			i++;
		}
	}
	
	//Creates an arraylist of 30 urls from the webcrawler
	public static void getUrls()
	{
		ArrayList<String> urls = new ArrayList<String>(spider.getUrls());
		for (int i = 0; i < 30; i++)
		{
			listOfUrls.add(urls.get(i));
		}
	}
	
	//Creates an arraylist of 30 webpages by attaching webpage and url together
	public static void generateWebpages()
	{
		for (int i = 0; i < 30; i++)
		{
			Webpage w = new Webpage(listOfUrls.get(i), listOfPageRanks.get(i));
			listOfWebpages.add(w);
		}
	}
	
	//Prints out a list of 30 webpages with its url and pagerank score
	public static void print()
	{
		for (int i = 0; i < 30; i++)
		{
			System.out.println(i + 1 + ". " + listOfWebpages.get(i).getUrl() + ", PageRank score: " + listOfWebpages.get(i).getScore());
		}
		
	}
	
	//Creates a max heap; this array is separate from the pagerank int[]
	public static void createHeap()
	{
		
		heapScores = new int[sortedWebpages.size()];
		for (int i = 0; i < sortedWebpages.size(); i++)
		{
			heapScores[i] = sortedWebpages.get(i).getScore();
		}
		heapObject.buildMaxHeap(heapScores);
	}
	
	//Transfer the unsorted pagerank scores to an int[] for sorting
	public static void useHeapsort()
	{
		int i = 0;
		while (i != 30)
		{
			pageRankScores[i] = listOfWebpages.get(i).getScore();
			i++;
		}
		//sort the scores
		heapObject.heapsort(pageRankScores);
		
	}
	
	//Create a sorted arraylist of the webpages using the updated indexes of the pagerank array
	public static void updateWebpages()
	{
	
		//Use the index of the int[] to update
		
		//Loop once to compare the pagerank scores
		for (int i = 0; i < 30; i++)
		{
			if (pageRankScores[i] == listOfWebpages.get(i).getScore())
			{
				sortedWebpages.add(listOfWebpages.get(i));
			}
			else
			{
				//Loop a second time to search for the value that is equal to the sorted pagerank score
				for (int j = 0; j < 30; j++)
				{
					if (pageRankScores[i] == listOfWebpages.get(j).getScore())
					{
						sortedWebpages.add(listOfWebpages.get(j));
					}
				}
			}
		  }	
	    }
	
	
	
	//Shows the url and pagerank score of the first ranked webpage
	 public static void viewFirstRanked()
	 {
		 //Use heapextract max to obtain the max value
		 int max = heapObject.heapExtractMax(heapScores);

		 //After obtaining the max value in the int[], search for the webpage with the same pagerank score to print 
		 int i = 0;
		 while ( max != sortedWebpages.get(i).getScore())
		 {
			 i++;
		 }
		 System.out.println(sortedWebpages.get(i).getUrl() + ", PageRank score: " + sortedWebpages.get(i).getScore());
	 }
	 
	 //A condensed way to print the options for the search engine
	 public static void printChoices()
	 {
		 	System.out.println("Type '1' to see the first 20 links in the priority queue.");
			System.out.println("Type '2' to insert a new web url into the heap.");
			System.out.println("Type '3' to see the first ranked web url.");
			System.out.println("Type '4' to choose a link in the priority queue and increase its PageRank score by paying more money.");
			System.out.println("Type '5' to exit.");
	 }
	 
	 
	 //Obtains the first 20 webpages in the heap and prints it
	 public static void priorityQueue()
	 {
		 
		 //Gathers the pagerank scores in the heap for calculation
		for (int i = 10; i < 30; i++)
		{
			first20[i] = sortedWebpages.get(i).getScore();
		}
		heapObject.heapsort(first20);
		
		//Update the arraylist of webpages to show only the first 20 in the priority queue
		for (int i = 10; i <30; i++)
		{
			if (first20[i] == sortedWebpages.get(i).getScore())
			{
				first20PQ.add(sortedWebpages.get(i));
			}
			else
			{
				for (int j = 10 ; j < 30 ; j++)
				{
					if (first20[i] == sortedWebpages.get(j).getScore())
					{
						first20PQ.add(sortedWebpages.get(j));
					}
				}
			}
			
		}
		
		//Print from descending order to show from largest pagerank to smallest
		for (int i = 19; i >= 0; i--)
		{
			System.out.println(20 - i + ". " + first20PQ.get(i).getUrl() + ", PageRank: " + first20PQ.get(i).getScore());
		}	
	 }
	 
	 
	 
	 public static void insertNewWebUrl()
	 {
		 //Ask user for url link
		 Scanner web = new Scanner(System.in);
		 System.out.println("___________________________________");
		 System.out.println("Please enter the url you want to insert in the format of www.xxxx.com.");
		 
		 //Obtain url string
		 String userUrl = web.nextLine();
		 
		 //Ask user for pagerank info
		 System.out.println("Please enter the score of the frequency of your webpage. The score must be between 1 - 100 inclusive.");
		 int freq = web.nextInt();
		 System.out.println("Please enter the score of the age of your webpage. The score must be between 1 - 100 inclusive.");
		 int age =  web.nextInt();
		 System.out.println("Please enter the score of the relevancy of your webpage. The score must be between 1 - 100 inclusive.");
		 int relevancy = web.nextInt();
		 System.out.println("Please enter the score of the sponsorship of your webpage. The score must be between 1 - 100 inclusive.");
		 int sponsor = web.nextInt();
		 
		 //Create new pagerank
		 PageRank userScore = new PageRank(freq, age, relevancy, sponsor);
		 //Create new webpage
		 Webpage userWebpage = new Webpage(userUrl, userScore);
		 
		 //Add webpage into arraylist
		 sortedWebpages.add(userWebpage);
		 

		 //Get the size of the arraylist of the webpages
		 int[] temp;
		 int i = 0;
		 while ( i !=  sortedWebpages.size())
		 {
			i++;
		 }
		 
		 //Initialize an int[] for pagerank calculation
		 temp = new int[i];
		 
		 for (int j = 0; j <  sortedWebpages.size(); j++)
		 {
			 temp[j] =  sortedWebpages.get(j).getScore();
		 }
		 
		 
		 heapObject.maxHeapInsert(temp, userWebpage.getScore());
		 heapObject.heapsort(temp);
		 
		 
		 //Update the sorting of the webpages based on the pagerank scores after heapHeapInsert calculation
		 for (int first = 0; first <  sortedWebpages.size(); first++)
		 {
			 if (temp[first] ==  sortedWebpages.get(first).getScore())
			 {
				 
				 modifiedWebpages.add( sortedWebpages.get(first));
			 }
			 else
			 {
				 for (int second = 0; second <  sortedWebpages.size(); second++)
				 {
					 if (temp[first] ==  sortedWebpages.get(second).getScore())
					 {
						 modifiedWebpages.add(sortedWebpages.get(second));
					 }
				 }
			 }
		 }
		 
		 //Remove duplicate webpage 
		 int index = 0;
		 while
		 (userWebpage.getScore() != modifiedWebpages.get(index).getScore())
		 {
			 index++;
		 }
		 modifiedWebpages.remove(index);
		 
	 }
	 
	 
	 //Allow users to choose any one of the web url links stored in the heap Priority Queue 
	 //and increase its PageRank score and its priority in the queue 
	 public static void increaseWebpage()
	 {
		 Scanner s = new Scanner(System.in);
		 System.out.println("Please specify the index of the webpage you want to increase.");
		 int index = sortedWebpages.size() - s.nextInt();
		 System.out.println("Please specify the amount you want to increase the website's sponsorship by.");
		 int money = s.nextInt();
		 
		 //change list of webpages to sorted webpages 
		 //put updated results in a temp array
		 //put in original array 
		 
		 System.out.println("___________________________________");
		 System.out.println("Target:  " + sortedWebpages.get(index).getUrl() + ", " + sortedWebpages.get(index).getScore());
		 System.out.println("___________________________________");
		 sortedWebpages.get(index).increasePayment(money);
		 
		 
		
		 int arraySize = 0;
		 while (arraySize != sortedWebpages.size())
		 {
			 arraySize++;
		 }
		 
		 //Create a temporary int[] based on the size of sortedWebpages
		 int[] temp = new int[arraySize];
		 
		 //Add the PageRank scores into the temp array
		 for (int i = 0; i < sortedWebpages.size(); i++)
		 {
			 temp[i] = sortedWebpages.get(i).getScore();
		 }
		 
		 
		 heapObject.heapIncreaseKey(temp, index, sortedWebpages.get(index).getScore());
		 heapObject.heapsort(temp);

		
		 //Update the order of the 
		for (int n = 0; n < sortedWebpages.size(); n++)
		{
			if (temp[n] == sortedWebpages.get(n).getScore())
			{
				insertedWebpages.add(sortedWebpages.get(n));
				
			}
			else 
			{
				for (int m = 0; m < sortedWebpages.size(); m++)
				{
					if (temp[n] == sortedWebpages.get(m).getScore())
					{
						insertedWebpages.add(sortedWebpages.get(m));
						
					}
				}
			}
		}
		
		for (int x = 0; x < insertedWebpages.size(); x++)
		{
			sortedWebpages.set(x, insertedWebpages.get(x));
		}
		
		
		System.out.println("");
		 
	 }
	 

	public static void main(String[] args)
	{	
		//Part 1.a: Use a keyword to search and print out 30 results with their urls and page ranks
		System.out.println("Please type in a keyword to begin searching: ");
		Scanner scan = new Scanner(System.in);
		String keyword = scan.nextLine();
		spider = new WebCrawler(keyword);
		spider.search();
		
		//Creates an arraylist of 30 individual page rank scores (unassigned)
		generatePageRanks();
		//Collects the urls from the spider 
		getUrls();
		//Creates an arraylist of 30 webpages ( url + page rank scores)
		generateWebpages();
		//Prints the list of webpages and its url and page rank
		print();
		
		//Part 1.b: Use heapsort to sort the page rank scores and reorder the list 
		System.out.println("___________________________________");
		System.out.println("The current list is not sorted. Would you like to sort the webpages based on their PageRank scores?");
		System.out.println("Please type 'yes' to continue or 'no' to exit.");
		
		String answer = scan.nextLine();
		if (answer.equals("yes"))
		{
			//Sort the pagerank scores
			useHeapsort();
			//Create a sorted arraylist of the webpages using the updated indexes of the pagerank array
			updateWebpages();
			//Print from highest to lowest score
			for (int i = 29; i >= 0; i--)
			{
				System.out.println(30 - i + ". " + sortedWebpages.get(i).getUrl() + ", PageRank: " + sortedWebpages.get(i).getScore());
			}
			
		}
		else if (answer.equals("no"))
		{
			System.out.println("The search engine has terminated.");
		}
		
		
		
	int response = 0;
	while (response != 5)
	{
		System.out.println("___________________________________");
		System.out.println("Would you like to do anything else?");
		System.out.println("___________________________________");
		printChoices();
		
		
		Scanner scan2 = new Scanner(System.in);
		response = scan2.nextInt();
	
		switch (response) 
		{
			//Part 2.a: Use heap priority queue to store the first sorted 20 out of 30 web url links into the heap 
		case 1:
			{
				System.out.println("___________________________________");
				System.out.println("Here are the first 20 links stored in the priority queue: ");
				System.out.println("___________________________________");
				priorityQueue();
				break;
			}
			//Part 2.b: Allow users to insert a new web url link into Heap 
		case 2:
			{
				insertNewWebUrl();
				System.out.println("___________________________________");
				for (int i =  modifiedWebpages.size() -1 ; i >= 0 ; i--)
				{
					System.out.println( modifiedWebpages.size() - i + ". " + modifiedWebpages.get(i).getUrl() + ", PageRank: " + modifiedWebpages.get(i).getScore());
				}
				break;
			}
			
			//Part 2.c: Show the highest ranked url and show the remaining list
		case 3:
			{
				createHeap();
				viewFirstRanked();
				
				break;
				
			}
			//Part 2.d: Allow users to choose any one of the web url links stored in the heap Priority Queue 
			//and increase its PageRank score and its priority in the queue 
		case 4:
			{
				increaseWebpage();
				//previously insertedWebpages
				for (int i = sortedWebpages.size() -1 ; i >= 0 ; i--)
				{
					System.out.println(sortedWebpages.size() - i + ". " + sortedWebpages.get(i).getUrl() + ", PageRank: " + sortedWebpages.get(i).getScore());
				}	
				break;
			}
		
		case 5:
		{
			System.out.println("___________________________________");
			System.out.println("The search engine has terminated.");
			break;
		}
		default:
			printChoices();
		
		}	
	}	
   }

}
