package MicroFaceBook;
import java.util.Scanner;


//A simulator class for the micro version of Facebook
public class MiniFacebook 
{

	private static DivHash d = new DivHash();
	private static MultHash m = new MultHash();
	
	//Print the option for the user
	public static void printChoices()
	{
		System.out.println("");
		System.out.println("Enter '1' to register users in the MicroFacebook database. Max: 50 users");
		System.out.println("Enter '2' to record a person as a new friend.");
		System.out.println("Enter '3' to remove a person from a friend list.");
		System.out.println("Enter '4' to search a person's friend list.");
		System.out.println("Enter '5' to see if two people are friends with each other.");
		System.out.println("Enter '6' to log out.");
		System.out.println("");
	}	
	
	public static void main(String[] args)
	{	
		System.out.println("Hello user! Welcome to Minibook.");
		
		printChoices();
		
		int response = 0;
		while (response != 7)
		{
			Scanner scan = new Scanner(System.in);
			response = scan.nextInt();
			
			switch(response)
			{
			
			case 1:
				{
					Scanner register = new Scanner(System.in);
					System.out.println("Please enter in the name of who you wish to register.");
					
					String p = register.nextLine();
					
					Person person = new Person(p, new FriendLinkedList());
		
					d.register(person);
					m.register(person);
					
					
					System.out.println("Press 'd' or 'm' to see the database from the division or multiplication method.");
					System.out.println("Enter 'x' if you wish to move on.");
					String type = register.nextLine();
					if (type.equals("d"))
					{
						d.printHashTable();
						
					}
					else if (type.equals("m"))
					{
						m.printHashTable();
						
					}
					else if (type.equals("x"))
					{
						printChoices();
						break;
					}
					
					printChoices();
					break;
					
					
				}
				
			case 2:
				{
					Scanner add = new Scanner(System.in);
					System.out.println("Please specify who you are.");
					
					String original = add.nextLine();
					
					System.out.println("Please specify who you want to add as a friend");
					
					String friend = add.nextLine();
					
					Person originalP = new Person(original, null);
					Person friendP = new Person(friend, null);
					
					System.out.println("Please type 'd' or 'm' to specify which database to perform this action in.");
					
					String answer = add.nextLine();
					
					if (answer.equals("d"))
					{
						d.chainedHashInsert(originalP, friendP);
						d.chainedHashInsert(friendP, originalP);
					}
					else if (answer.equals("m"))
					{
						m.chainedHashInsert(originalP, friendP);
						m.chainedHashInsert(friendP, originalP);
					}
					
	
					System.out.println("");					
					
					
					printChoices();
					break;
				}
				
			case 3:
				{
					Scanner remove = new Scanner(System.in);
					System.out.println("Please specify who you are.");
					
					String original = remove.nextLine();
					
					System.out.println("Please specify who you want to unfriend.");
					
					String friend = remove.nextLine();
					
					Person originalP = new Person(original, null);
					Person friendP = new Person(friend, null);
					
					System.out.println("Please type 'd' or 'm' to specify which database to perform this action in.");
					String answer = remove.nextLine();
					
					
					if (answer.equals("d"))
					{
						d.chainedHashDelete(originalP, friendP);
						d.chainedHashDelete(friendP, originalP);
					}
					else if (answer.equals("m"))
					{
						m.chainedHashDelete(originalP, friendP);
						m.chainedHashDelete(friendP, originalP);
						
					}
					
					printChoices();
					break;
				}
				
				
			case 4:
				{
					Scanner search = new Scanner(System.in);
					System.out.println("Please enter in the name of who you wish to search in the database");
					
					String p = search.nextLine();
					
					Person person = new Person(p, null);
					
					System.out.println("Please type 'd' or 'm' to specify which database to search in.");
					
					String answer = search.nextLine();
					
					if (answer.equals("d"))
					{
						d.chainedHashSearch(person);
					}
					else if (answer.equals("m"))
					{
						m.chainedHashSearch(person);
					}
					
	
					printChoices();
					break;
				}
			
				
			case 5:
				{
					Scanner check = new Scanner(System.in);
					System.out.println("Please enter in the name of the first person");
					
					String original = check.nextLine();
					
					System.out.println("Please enter in the name of the second person");
					
					String friend = check.nextLine();
					
					
					System.out.println("Please type 'd' or 'm' to specify which database to perform this action in.");
					
					String answer = check.nextLine();
					
					if (answer.equals("d"))
					{
						d.checkTwoFriends(original, friend);
					}
					else if (answer.equals("m"))
					{
						m.checkTwoFriends(original, friend);
					}
					
					
					printChoices();
					break;
				}
				
			case 6:
				{
					System.out.println("Thank you for using MiniBook! The program will now terminate.");
					break;
				}
				
				
			default:
				{
					printChoices();
				}
			}	
		}
		
	}
	
}
