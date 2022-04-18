package MicroFaceBook;

//A class that manages all methods related to a Person object
public class Person 
{
	
	private String name;
	private FriendLinkedList friendRecord;
	private int key;
	
	
	//Create a Person object with a name and FriendLinkedList
	public Person(String n, FriendLinkedList f)
	{
		this.name = n;
		this.friendRecord = f;
		
		this.key = convertToInt(n);
	}
	
	
	//Add a friend into a Person's FriendLinkedList
	public void makeFriend(Person p)
	{
		this.friendRecord.insertAtHead(p);
	}
	
	
	//Remove a friend from a Person's FriendLinkedList
	public void unFriend(Person p)
	{
		this.friendRecord.deletePerson(p);
	}
	
	
	//Search a Person's FriendLinkedList to see if a friend is in there
	public boolean findFriend(Person p)
	{
		return this.friendRecord.findPerson(p);
	}
	
	//Print a Person's FriendLinkedList
	public void getFriendList()
	{
		System.out.println(name + "'s friends:");
		friendRecord.printList();
	}
	
	
	//Return a Person's name
	public String getName()
	{
		return this.name;
	}
	
	//Return the natural number equivalent of a Person's name
	public int getKey()
	{
		return this.key;
	}
	
	//Convert a name into a natural number based on how many letters it has
	//Exclude the spaces
	public int convertToInt(String name)
	{
		String noSpaces = name.replace(" ", "");
		return noSpaces.length();
	}
	
	//Test
	public static void main(String[] args)
	{
		Person wendy = new Person("Wendy", new FriendLinkedList());
		
		System.out.println(wendy.getKey());
		System.out.println("Expected: 5");
	}

}
