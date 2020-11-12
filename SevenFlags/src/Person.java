import java.util.ArrayList;
import java.util.List;


public class Person {
	private int number;
	private int maxLines;	
	private List<Ride> ridelines;
	private Status s;
	private int rideCount;
	
	/**
	 * this method is a constructor for object Person
	 * @param number
	 * 		the number is an id for each person
	 * @throws IllegalArgumentException
	 * 		throws exception if the number is negative
	 */
	public Person(int number) throws IllegalArgumentException
	{
		if (number >= 0)
		{
			this.number = number;
			this.ridelines = new ArrayList<Ride>();
			this.s = new Status();
			this.rideCount = 0;
		}
		else 
			throw new IllegalArgumentException();
	}
	
	public Person()
	{
		// TODO Auto-generated constructor stub
	}
	/**
	 * this method allows user to set the id
	 * @param number
	 * 		the number is what the id would equal to.
	 */
	public void setNumber(int number) {
		this.number = number;
	}
	
	/**
	 * this method allows the user to set the number of maximum lines a person can go into
	 * @param maxLines
	 * 		the integer parameter is what the maximum lines should equal to.
	 */
	public void setMaxLines(int maxLines) {
		this.maxLines = maxLines;
	}

	/**
	 * this method allows the user to create a list with object Rides
	 * @param lines
	 * 		the List of rides is what the object's rides list should equal to.
	 */
	public void setLines(List<Ride> lines) {
		this.ridelines = lines;
	}

	/**
	 * this method allows the user to set the status of the person object.
	 * @param s
	 * 		the parameter s is of type Status and that's what the person's status should equal to.
	 */
	public void setStatus(Status s) 
	{
		this.s = s;
	}
	
	public void setStatusFromStr(String s)
	{
		this.s.setStatus(s);
	}

	/**
	 * this method provides the id of the object person
	 * @return
	 * 		returns the id
	 */
	public int getNumber()
	{
		return number;
	}

	/** 
	 * this method provides the number of max lines a person could have
	 * @return
	 * 		returns the number of maximum lines
	 */
	public int getMaxLines()
	{
		return maxLines;
	}
	
	/**
	 * this method provides the list of rides a person object has. 
	 * @return
	 * 		the list of rides
	 */
	public List<Ride> getLines() 
	{
		return ridelines;
	}

	/**
	 * this method provides the status of a person 
	 * @return
	 * 		the status is returned.
	 */
	public Status getStatus()
	{
		return s;
	}
	
	public int getRideCount()
	{
		return rideCount;
	}
	
	public void setRideCount(int rideCount)
	{
		this.rideCount = rideCount;
	}
}
