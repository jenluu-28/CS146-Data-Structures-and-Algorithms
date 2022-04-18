package MicroFaceBook;

//A class that manages all the methods related to a FriendLinkedList
//Modeled after CS 46B
public class FriendLinkedList 
{
	
	private PersonNode head; 
	private PersonNode tail;
	
	
	//Inserts a person into the LinkedList at the head 
	public void insertAtHead(Person person)
	{
		//Create a new Person Node
		PersonNode newNode = new PersonNode(person);
					
		//If the LinkedList is empty, p is the head
		if (head == null && tail == null)
			{
				//One node is in the list
				head = newNode;
				tail = newNode;
				tail.setNext(null);
				head.setNext(null);
					
			}
		else
			{
				//More than one node is in the list
				newNode.setNext(head);
				head = newNode;
			}
	}
	
	
	//Search for a Person in the FriendLinkedList and delete him/her
	public void deletePerson(Person person)
	{
		//Begin search at the head
		PersonNode current = head;
		String currentName = current.getPerson().getName();
		String targetName = person.getName();
		
		//Case 1: The list is empty
		if (head == null && tail == null)
		{
			System.out.println("The friend list is empty.");
		}
		//Case 2: The list has one element
		else if (currentName.equals(targetName))
		{
			//Remove the previous head
			head = current.getNext();
		}
		//Case 3: The list has more than one element
		else
		{
			while (current != null)
			{
				if (currentName.equals(targetName))
				{
					PersonNode nextNode = current.getNext();
					current.setNext(nextNode);
				}
				//Continue to iterate until target is found
				current = current.getNext();
			}
		}
	}
	
	
	//Returns true when the first node in the list whose name is equal to the target name 
	//If no such person is found, return null
	public boolean findPerson(Person person)
	{
		boolean done = false; 
			
		//Gets the target person's name
		String targetName = person.getName();
			
		//Start at the head
		PersonNode current = head;
		
		//Check if the list is empty
		if (head == null & tail == null)
		{
			System.out.println("The list is empty.");
		}
		
		while (current != null)
		{
			String currentName = current.getPerson().getName();
				
			//Return true if the current node matches the target name
			if (currentName.equals(targetName))
			{
				done = true;
			}
			//Proceed to the next node if not
			current = current.getNext();
		}
		//Return false if there are no matches
		return done;
	  }
	
	
	//Print the name of the Persons in the list
	public void printList()
	{

		//Begin at the head
		PersonNode current = head;
		
		//Check if the list is empty
		if (head == null && tail == null)
		{
			System.out.println("The list is empty.");
		}
		else
		{
			//Traverse through the list
			while (current != null)
			{
				String currentName = current.getPerson().getName();
				System.out.print(currentName + ", ");
				
				//Continue to iterate until there is nothing left to point next to
				current = current.getNext();
			}
		}
	}
	
	//Tester
	public static void main(String[] args)
	{
		Person wendy = new Person("Wendy", new FriendLinkedList());
		Person willow = new Person("Willow", new FriendLinkedList());
		Person wilbert = new Person("Wilber", new FriendLinkedList());
		Person webber = new Person("Webber", new FriendLinkedList());
		Person wilson = new Person("Wilson", new FriendLinkedList());
		 
		FriendLinkedList test = new FriendLinkedList();
		
		test.insertAtHead(wendy);
		test.insertAtHead(willow);
		test.insertAtHead(wilbert);
		test.insertAtHead(webber);
		test.insertAtHead(wilson);
		
		test.printList();	
	}
}

