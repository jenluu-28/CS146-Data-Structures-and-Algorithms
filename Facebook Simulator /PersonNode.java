package MicroFaceBook;

//A class that manages all methods related to a PersonNode for a FriendLinkedList
//Modeled after CS 46B 
public class PersonNode 
{
	
	private Person person;
	private PersonNode next;
	
	//Create a PersonNode
	public PersonNode(Person person)
	{
		this.person = person;
	}
	
	//Sets the next pointer for a PersonNode
	public void setNext(PersonNode next)
	{
		this.next = next;
	}
	
	//Returns the next PersonNode 
		public PersonNode getNext()
		{
			return this.next;
		}
	
	//Returns the Person object
	public Person getPerson()
	{
		return this.person;
	}
}
