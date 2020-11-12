public class Status {
	
	public enum Statuses {
	AVAILABLE, HOLDING, ONRIDE;
	}
	    Statuses s;
	    
	    public Status(String str)
		{
		}
	    public String toString() {
			return "" + s ;
		} 
	    
	    public Status ()
	    {
	    	
	    }
	    
	    public void setStatus(String str)
		{
			str.toUpperCase();
			if (str.equals("AVAILABLE"))
			{
				s= Statuses.AVAILABLE;
			}
			else if (str.equals("HOLDING"))
			{
				s= Statuses.HOLDING;
			}
			else if (str.equals("ONRIDE"))
			{
				s= Statuses.ONRIDE;
			}
		}
	    
}