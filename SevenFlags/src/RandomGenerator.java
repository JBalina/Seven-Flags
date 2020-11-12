
public class RandomGenerator 
{
	/**
	 * this method returns a random value that corresponds to a random ride 
	 * @param rides
	 * 		the array containing the rides
	 * @param probabilities
	 * 		probability of each ride being selected
	 * @return
	 * 		a ride
	 */
	
	public static Ride selectRide(Ride[] rides, double[] probabilities )
	{
		double random= Math.random();
		Ride ride = new Ride();
		if (random >= 0 && random < probabilities[0])
		{
			ride = rides[0];
		}
		else if (random >= probabilities[0] && random < probabilities[0] + probabilities[1])
		{
			ride = rides[1];
		}
		else if (random >= probabilities[0] && random < probabilities[0] + probabilities[1]+ probabilities[2])
		{
			ride = rides[2];
		}
		else if (random >= probabilities[0] && random < probabilities[0] + probabilities[1]+ probabilities[2]+ probabilities[3])
		{
			ride = rides[3];
		}
		return ride;
		
		
		
	}
}
