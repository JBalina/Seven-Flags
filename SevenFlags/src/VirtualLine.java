import java.util.LinkedList;


public class VirtualLine extends LinkedList {
	private int size;
	
	/**
	 * this method is a constructor of Virtual Line that sets its size to 0.
	 */
	public VirtualLine()
	{
		this.size = 0;
	}
	
	/**
	 * this method adds a object Person to the end of the Virtual Line linked list.
	 * @param p
	 * 		the parameter is a object Person which is added to the line. 
	 */	
	public void enqueue(Person p)
	{
		addLast(p);
		size++;
	}
	
	/** 
	 * this method removes a object Person from the front of the linked list Virtual Line.
	 * @return
	 * 		the Person object who is removed is returned.
	 */
	public Person dequeue() 
	{
		Person temp = (Person) getFirst();
		remove();
		size--;
		return temp;
		
	}
	
	/**
	 * this method allows the user to access the first item in the LinkedList without removing it.
	 */
	public Person peek() 
	{

		return (Person) getFirst();
	}
	
	public boolean isEmpty()
	{
		return isEmpty();
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	
	
	
}
