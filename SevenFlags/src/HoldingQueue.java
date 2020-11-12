public class HoldingQueue extends VirtualLine
{
	private int maxSize;
	
	
	/**
	 * this is a constructor for the HoldingQueue extending from VirtualLine
	 */
	public HoldingQueue()
	{
		super();
		
	}
	
	/**
	 * this is a constructor for HoldingQueue 
	 * @param maxSize
	 * 		the parameter takes in the maximum size it is allowed to have
	 */
	public HoldingQueue(int maxSize)
	{
		super();
		this.maxSize= maxSize;
	}

	/**
	 * this returns the maximum size of the HoldingQueue line
	 * @return
	 * 		the number for maximum size is returned.
	 */
	public int getMaxSize() {
		return maxSize;
	}

	/**
	 * this method allows the user to set the maximum size for the line 
	 * @param maxSize
	 * 		the parameter is an integer that the maximum size should equal to.
	 */
	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}
	
	
}
