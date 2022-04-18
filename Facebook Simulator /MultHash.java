package MicroFaceBook;

//A class that manages all the methods related to MultHash
public class MultHash 
{
	//A global hash table that acts as a database to hold all the users
	//The hash table size is 15 for max space
	private static PersonNode[] hashTable = new PersonNode[15];
	
	
	public int multHashFunction(Person p)
	{
		int hashCode;
		
		double A = (Math.sqrt(5) - 1)/2;
		int key = p.getKey();
		int m = hashTable.length;
		
		hashCode = (int)( m * ((key * A) % 1));
		
		return hashCode;
		
	}
	//Inserts a new user into the hash table database
		public void register(Person person)
		{
			//Create a PersonNode
			PersonNode newNode = new PersonNode(person);
			
			int key = multHashFunction(person);
			
			//Case 1: The slot is already empty
			if (hashTable[key] == null)
			{
				hashTable[key] = newNode;
				newNode.setNext(null);
			}
			//Case 2: When the slot is occupied, insert at the head
			else 
			{
				//Define element that is currently occupying the slot space
				PersonNode current = hashTable[key];
				
				//The new element will point to the current element
				newNode.setNext(current);
				
				//Set the head pointer to the new node
				hashTable[key] = newNode;
			}
		}
		
		
		//The first String represents the person with the primary list, the second String represents the friend to delete
		public void chainedHashDelete(Person original, Person friend)
		{
		
			//Get the slot index of the original user
			int key = multHashFunction(original);
			
			
			PersonNode current = hashTable[key];
			
			//Check to see if the list is empty
			if (current == null)
			{
				nullMessage(original);
			}
			else
			{
				//Start at the head of list
				while (current != null)
				{
					String currentName = current.getPerson().getName();
					String originalName = original.getName();
					
					//Unfriend if a match is found
					if (currentName.equals(originalName))
					{
						
						current.getPerson().unFriend(friend);
						current.getPerson().getFriendList();
						System.out.println("");
					}
					//Continue to iterate until a found is found
					current = current.getNext();
				}
			}
			
		}
		
		
		//Search for a Person's name in the database
		public void chainedHashSearch(Person person)
		{	
			int key = multHashFunction(person);

			PersonNode current = hashTable[key];
			
			if (hashTable[key] == null)
			{
				nullMessage(person);
			}
			else
			{
				while (current != null)
				{
					String currentName = current.getPerson().getName();
					String targetName = person.getName();
					
					if (currentName.equals(targetName))
					{
						
						System.out.println(currentName + " is in the database.");
						current.getPerson().getFriendList();
						System.out.println("");
					}
					current = current.getNext();
				}
			}
			
		
		}
		
		
		//A helper method that returns true if a friend is in the original user's list
		public boolean searchList(PersonNode current, Person original, Person friend)
		{
			boolean done = false;
			while (current != null)
			{
				String currentName = current.getPerson().getName();
				String originalName = original.getName();
				
				if (currentName.equals(originalName))
						{
							if (current.getPerson().findFriend(friend) == true)
							{
								done = true;
							}
						}
				//Continue to iterate until a match is found
				current = current.getNext();
			}
			return done;
		}
		
		
		//Checks if two people are friends
		public void checkTwoFriends(String one, String two)
		{
			
			Person original = new Person(one, null);
			Person friend = new Person(two, null);
			
			int key1 = multHashFunction(original);
			boolean done = false;
			PersonNode current = hashTable[key1];
			
			//Search through the original user's list to see if the friend is present
			if (current == null)
			{
				nullMessage(original);
			}
			else
			{
				//Start searching at the head
				done = searchList(current, original, friend);
		
			}
			
			int key2 = multHashFunction(friend);
			boolean done2 = false;
			PersonNode current2 = hashTable[key2];
		
			//Search through the friend's list to see if the original user is present
			if (current2 == null)
			{
				nullMessage(friend);
			}
			else 
			{
				done2 = searchList(current2, friend, original);
				
			}
			
			if (done == true && done2 == true)
			{
				System.out.println("Yes.");
			}
			else
			{
				System.out.println("No.");
			}
			
		}
		
		
		//Record a new friend into a user's friend linked list
		public void chainedHashInsert(Person original, Person friend)
		{
			//Find the original user in the database
			int key = multHashFunction(original);
			
			//Use the slot index to find the original user 
			PersonNode current = hashTable[key];
					
			if (current == null)
			{
				nullMessage(original);
			}
			else
			{
				//Check the original user's friend list
				//Begin with the head 
				while (current != null)
				{
					String currentName = current.getPerson().getName();
					String originalName = original.getName();
					
					//Add the new Friend to the list if a match is found
					if (currentName.equals(originalName));
					{
						current.getPerson().makeFriend(friend);
						current.getPerson().getFriendList();
						System.out.println("");
						
					}
					//Iterate until a match is found
					current = current.getNext();
				}
				
			}
		}
		
		//A message to print if a desired Person is not present
		public void nullMessage(Person p)
		{
			System.out.println("User " + p.getName() + " does not exist.");
		}
		
		
		//Prints the hash table database
		public static void printHashTable()
		{
			for (int i = 0; i < hashTable.length; i++)
			{
				
				System.out.print("Slot " + i + ": ");
				
				PersonNode target = hashTable[i];

				while (target != null)
				{
					String name = target.getPerson().getName();
					System.out.print(name + " --> ");
					target = target.getNext();
				}
				System.out.print("/");
				System.out.println();
			}
		}
		
		
		
		public static void main(String[] args)
		{
			MultHash m = new MultHash();
			
			Person jen = new Person("Jen", new FriendLinkedList());
			Person ben = new Person("Ben", new FriendLinkedList());
			Person pen = new Person("Pen", new FriendLinkedList());
			Person shen = new Person("Shen", new FriendLinkedList());
			Person wendy = new Person("Wendy", new FriendLinkedList());
		
			m.register(jen);
			m.register(ben);
			m.register(shen);
			m.register(wendy);
			m.register(pen);
			
			m.chainedHashInsert(shen, wendy);
			m.chainedHashInsert(wendy, shen);
			
			printHashTable();
			
			shen.getFriendList();
			wendy.getFriendList();
			
			
			
		}

}
