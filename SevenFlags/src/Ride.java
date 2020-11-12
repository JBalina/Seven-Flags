import java.util.ArrayList;
import java.util.List;

public class Ride 
{
	private int duration;
	private int timeLeft;
	private int capacity;
	private String name;
	private VirtualLine virtualLine;
	private HoldingQueue holdingQueue;
	private List<Person> peopleOnRide;
	private String acronym= null;
	private int CompletedRides;
	
	public Ride (int duration, String name)
	{
		this.duration= duration;
		this.name = name;
		this.timeLeft= duration;
		this.holdingQueue = new HoldingQueue();
		this.virtualLine = new VirtualLine();
		this.peopleOnRide = new ArrayList<Person>();
		this.CompletedRides = 0;
		if (name.equals("Blue Scream of Death"))
		{
			acronym = "BSOD";
		}
		else if (name.equals("Kingda Knuth"))
		{
			acronym = "KK";
		}
		else if (name.equals("i386 Tower of Terror"))
		{
			acronym = "ToT";
		}
		else if (name.equals("GeForce"))
		{
			acronym = "GF";
		}
	}
	
	
	/**
	 * this is an empty constructor for the Ride class
	 */
	public Ride() 
	{
			
	}
	
	/**
	 * this is a string that abbreviates the Rides names to abbreviations of the four rides.
	 * @return
	 * 		returns the abbreviated String.
	 */
	public String getAcronym() {
		return acronym;
	}
	
	/**
	 * this method adds object Person to the end of the list of the Rides list.
	 */
	public void enqueue()
	{
		peopleOnRide.add(holdingQueue.dequeue());
		holdingQueue.enqueue(virtualLine.dequeue());
		peopleOnRide.get(peopleOnRide.size()-1).setStatusFromStr("ONRIDE");
		//holdingQueue.getLast().
	}
	
	/**
	 * this method clears the list of object Person from the Rides list.
	 */
	public void dequeue()
	{
		peopleOnRide.clear();
	}
	
	/**
	 * this allows the user to get the duration of the ride
	 * @return
	 * 		returns a number that represents the time 
	 */
	public int getDuration() 
	{
		return duration;
	}
	
	/**
	 * this allows the user to set the duration of the ride
	 * @param duration
	 * 		the parameter integer is what the duration should equal to
	 */
	public void setDuration(int duration) 
	{
		this.duration = duration;
	}
	
	/**
	 * this method provides the time that is left of the ride
	 * @return
	 * 		the time that is left is returned as an integer.
	 */
	public int getTimeLeft() 
	{
		return timeLeft;
	}
	
	/**
	 * this method allows the user to set the time left of the rides.
	 * @param timeLeft
	 * 		the parameter is what the time left should equal to.
	 */
	public void setTimeLeft(int timeLeft) 
	{
		this.timeLeft = timeLeft;
	}
	
	/** 
	 * this method allows the user to get the name of the ride
	 * @return
	 * 		returns the name of the ride in String form
	 */
	public String getName() 
	{
		return name;
	}
	
	/**
	 * this method allows the user to set the name of the ride
	 * @param name
	 * 		the string parameter is what the name should equal to/
	 */
	public void setName(String name) 
	{
		this.name = name;
	}
	
	/**
	 * this provides the VirtualLine linked list that is associated with the ride
	 * @return
	 * 		the VirtualLine of the ride is returned
	 */
	public VirtualLine getVirtualLine() 
	{
		return virtualLine;
	}
	
	/**
	 * the method allows the user to set the VirtualLine linked list for the ride
	 * @param virtualLine
	 * 		the parameter of type VirtualLine is what the ride's virtual line should equal to. 
	 */
	public void setVirtualLine(VirtualLine virtualLine) 
	{
		this.virtualLine = virtualLine;
	}
	
	/**
	 * this method allows the user to get the HoldingQueue linked list that is associated with the ride
	 * @return
	 * 		the HoldingQueue of the ride is returned
	 */
	public HoldingQueue getHoldingQueue() 
	{
		return holdingQueue;
	}
	
	/**
	 * this method allows the user to set the HoldingQueue linked list for the ride
	 * @param holdingQueue
	 * 		the parameter of the type HoldingQueue is what the ride's Holding Queue should equal to
	 */
	public void setHoldingQueue(HoldingQueue holdingQueue) 
	{
		this.holdingQueue = holdingQueue;
	}
	
	/**
	 * this method provides the list that contains the object Persons in the list
	 * @return
	 * 		the list containing the Persons on the ride is returned
	 */
	public List<Person> getPeopleOnRide() 
	{
		return peopleOnRide;
	}
	
	/** 
	 * this method allows the user to set the list of the ride which contains object Person
	 * @param peopleOnRide
	 * 		the parameter is what the Persons list should equal to.
	 */
	public void setPeopleOnRide(List<Person> peopleOnRide) 
	{
		this.peopleOnRide = peopleOnRide;
	}
	
	/**
	 * this provides the capacity of the ride
	 * @return
	 * 		the capacity is returned as an integer
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * this allows the user to set the capacity of a ride
	 * @param capacity
	 * 		this integer parameter is what the capacity should equal to.
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	
	public int getCompletedRides() {
		return CompletedRides;
	}


	public void setCompletedRides(int completedRides) {
		CompletedRides = completedRides;
	}
	
	
}
