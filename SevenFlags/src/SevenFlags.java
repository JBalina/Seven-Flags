import java.util.Scanner;

public class SevenFlags
{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		int regCustomers;
		int silverCustomers;
		int goldCustomers;
		int simulationLength;
		
		System.out.println("Welcome to Seven Flags!\n\n"
				+ "Please enter the number of regular customers: ");
		regCustomers = input.nextInt();
		
		System.out.println("Please enter the number of silver customers: ");
		silverCustomers= input.nextInt();
		
		System.out.println("Please enter the number of gold customers: ");
		goldCustomers = input.nextInt();
		
		System.out.println("Please enter simulation length: ");
		simulationLength= input.nextInt();
		
		System.out.println("Please enter the duration of Blue Scream of Death (minutes): ");
		int durationBSOD = input.nextInt();
		
		System.out.println("Please enter the capacity of Blue Scream of Death: ");
		int capacityBSOD = input.nextInt();
		
		
		System.out.println("Please enter the holding queue size for Blue Scream of Death: ");
		int holdingqBSOD = input.nextInt();
		
		Ride BSOD = new Ride(durationBSOD, "Blue Scream of Death");
		BSOD.getHoldingQueue().setMaxSize(holdingqBSOD);
		BSOD.setCapacity(capacityBSOD);

		System.out.println("Please enter the duration of Kingda Knuth (minutes): ");
		int durationKK = input.nextInt();
		
		System.out.println("Please enter the capacity of Kingda Knuth: ");
		int capacityKK = input.nextInt();
		
		
		System.out.println("Please enter the holding queue size for Kingda Knuth: ");
		int holdingqKK = input.nextInt();
		
		Ride KK = new Ride(durationKK, "Kingda Knuth");
		KK.getHoldingQueue().setMaxSize(holdingqKK);
		KK.setCapacity(capacityKK);
		
		System.out.println("Please enter the duration of i386 Tower of Terror (minutes): ");
		int durationToT = input.nextInt(); 
		
		System.out.println("Please enter the capacity of i386 Tower of Terror: ");
		int capacityToT= input.nextInt();
		
		System.out.println("Please enter the holding queue size for i386 Tower of Terror: ");
		int holdingqToT = input.nextInt();
		
		Ride TOT = new Ride(durationToT, "i386 Tower of Terror");
		TOT.getHoldingQueue().setMaxSize(holdingqToT);
		TOT.setCapacity(capacityToT);
		
		System.out.println("Please enter the duration of GeForce (minutes): ");
		int durationGF= input.nextInt();
		
		System.out.println("Please enter the capacity of GeForce: ");
		int capacityGF= input.nextInt();
		
		System.out.println("Please enter the holding queue size for GeForce: ");
		int holdingqGF = input.nextInt();
		
		Ride GF = new Ride(durationGF, "GeForce");
		GF.getHoldingQueue().setMaxSize(holdingqGF);
		GF.setCapacity(capacityGF);
		
		Ride[] ridesList = {BSOD, KK, TOT, GF};
		double[] probabilities = {0.25, 0.25, 0.25, 0.25};
		
		System.out.println("------------------------------------------------------------------------------------------");
		Person[] gold = new Person[goldCustomers];
		Person[] silver = new Person[silverCustomers];
		Person[] regular = new Person[regCustomers];
		
		//putting people into random rides lines
		for(int i= 0; i < goldCustomers; i++)
		{
			gold[i] = new Person(i+1);
			gold[i].setMaxLines(3);
			gold[i].getLines().add(RandomGenerator.selectRide(ridesList, probabilities));
			gold[i].getLines().get(gold[i].getLines().size()-1).getVirtualLine().enqueue(gold[i]);
			gold[i].setStatusFromStr("AVAILABLE");
		}
		
		for(int i= 0; i < silverCustomers; i++)
		{
			silver[i] = new Person(i+1);
			silver[i].setMaxLines(2);
			silver[i].getLines().add(RandomGenerator.selectRide(ridesList, probabilities));
			silver[i].getLines().get(silver[i].getLines().size()-1).getVirtualLine().enqueue(silver[i]);
			silver[i].setStatusFromStr("AVAILABLE");
		}
		
		for(int i= 0; i < regCustomers; i++)
		{
			regular[i] = new Person(i+1);
			regular[i].setMaxLines(1);
			regular[i].getLines().add(RandomGenerator.selectRide(ridesList, probabilities));
			regular[i].getLines().get(regular[i].getLines().size()-1).getVirtualLine().enqueue(regular[i]);
			regular[i].setStatusFromStr("AVAILABLE");
		}
		
		for(int i= 0; i < goldCustomers; i++)
		{
			gold[i].getLines().add(RandomGenerator.selectRide(ridesList, probabilities));
			gold[i].getLines().get(gold[i].getLines().size()-1).getVirtualLine().enqueue(gold[i]);
		}
		
		for(int i= 0; i < silverCustomers; i++)
		{
			silver[i].getLines().add(RandomGenerator.selectRide(ridesList, probabilities));
			silver[i].getLines().get(silver[i].getLines().size()-1).getVirtualLine().enqueue(silver[i]);	
		}
		
		for(int i= 0; i < goldCustomers; i++)
		{
			gold[i].getLines().add(RandomGenerator.selectRide(ridesList, probabilities));
			gold[i].getLines().get(gold[i].getLines().size()-1).getVirtualLine().enqueue(gold[i]);
		}
	//putting people in rides faster
		
		for (int i =0; i < ridesList.length; i++)
		{
			for (int j= 0; j < ridesList[i].getCapacity(); j++)
			{
				if (ridesList[i].getVirtualLine().size() != 0)
				{
					ridesList[i].getPeopleOnRide().add(ridesList[i].getVirtualLine().dequeue());
					ridesList[i].getPeopleOnRide().get(ridesList[i].getPeopleOnRide().size()-1).setStatusFromStr("ONRIDE");
				}
			}
		}
	
	//putting people in holding queue
		for (int i= 0; i < ridesList.length; i++)
		{
			for (int j=0; j < ridesList[i].getHoldingQueue().getMaxSize(); j++)
			{
				if (ridesList[i].getVirtualLine().size() != 0)
				{

					ridesList[i].getVirtualLine().peek().setStatusFromStr("HOLDING");
					ridesList[i].getHoldingQueue().enqueue(ridesList[i].getVirtualLine().peek());
					ridesList[i].getVirtualLine().dequeue();
				}
			}
		}
		
		
		//printing out 
		for (int i = 0; i < simulationLength; i++)
		{
			System.out.println("\nAt time " + i + ":");
			System.out.println("\nBlue Scream of Death- time remaining: " + ridesList[0].getTimeLeft());
			System.out.print("On Ride: ");
			for (int j = 0; j < ridesList[0].getPeopleOnRide().size(); j++)
			{
				
				if (ridesList[0].getPeopleOnRide().get(j).getMaxLines() == 3)
				{
					System.out.print("Gold " + ridesList[0].getPeopleOnRide().get(j).getNumber()+ " ");
				}
				else if (ridesList[0].getPeopleOnRide().get(j).getMaxLines() == 2)
				{
					System.out.print("Silver " + ridesList[0].getPeopleOnRide().get(j).getNumber()+ " ");
				}
				else if (ridesList[0].getPeopleOnRide().get(j).getMaxLines() == 1)
				{
					System.out.print("Regular " + ridesList[0].getPeopleOnRide().get(j).getNumber()+ " ");
				}
			}
			
			System.out.print("\nOn Holding Queue: ");
			for (int j = 0; j < ridesList[0].getHoldingQueue().getSize(); j++)
			{
				if (ridesList[0].getHoldingQueue().peek().getMaxLines() == 3)
				{
					System.out.print("Gold " + ridesList[0].getHoldingQueue().peek().getNumber() + " ");
				}
				else if (ridesList[0].getHoldingQueue().peek().getMaxLines() == 2)
				{
					System.out.print("Silver " + ridesList[0].getHoldingQueue().peek().getNumber()+ " ");
				}
				else if (ridesList[0].getHoldingQueue().peek().getMaxLines() == 1)
				{
					System.out.print("Regular " + ridesList[0].getHoldingQueue().peek().getNumber()+ " ");
				}
				
				ridesList[0].getHoldingQueue().enqueue(ridesList[0].getHoldingQueue().dequeue());
			}
			System.out.print("\n");
			System.out.print("On Virtual Line: ");
			for (int j = 0; j < ridesList[0].getVirtualLine().getSize(); j++)
			{	
				if (ridesList[0].getVirtualLine().peek().getMaxLines() == 3)
				{
					System.out.print("Gold " + ridesList[0].getVirtualLine().peek().getNumber() + " ");
				}
				else if (ridesList[0].getVirtualLine().peek().getMaxLines() == 2)
				{
					System.out.print("Silver " + ridesList[0].getVirtualLine().peek().getNumber()+ " ");
				}
				else if (ridesList[0].getVirtualLine().peek().getMaxLines() == 1)
				{
					System.out.print("Regular " + ridesList[0].getVirtualLine().peek().getNumber()+ " ");
				}
				
				ridesList[0].getVirtualLine().enqueue(ridesList[0].getVirtualLine().dequeue());
			}
			System.out.println("\n----------------");
			
			System.out.println("\nKingda Knuth- time remaining: " + ridesList[1].getTimeLeft());
			System.out.print("On Ride: ");
			for (int j = 0; j < ridesList[1].getPeopleOnRide().size(); j++)
			{
				if (ridesList[1].getPeopleOnRide().get(j).getMaxLines() == 3)
				{
					System.out.print("Gold " + ridesList[1].getPeopleOnRide().get(j).getNumber()+ " ");
				}
				else if (ridesList[1].getPeopleOnRide().get(j).getMaxLines() == 2)
				{
					System.out.print("Silver " + ridesList[1].getPeopleOnRide().get(j).getNumber()+ " ");
				}
				else if (ridesList[1].getPeopleOnRide().get(j).getMaxLines() == 1)
				{
					System.out.print("Regular " + ridesList[1].getPeopleOnRide().get(j).getNumber()+ " ");
				}
			}
			
			System.out.print("\nOn Holding Queue: ");
			for (int j = 0; j < ridesList[1].getHoldingQueue().getSize(); j++)
			{
				if (ridesList[1].getHoldingQueue().peek().getMaxLines() == 3)
				{
					System.out.print("Gold " + ridesList[1].getHoldingQueue().peek().getNumber() + " ");
				}
				else if (ridesList[1].getHoldingQueue().peek().getMaxLines() == 2)
				{
					System.out.print("Silver " + ridesList[1].getHoldingQueue().peek().getNumber()+ " ");
				}
				else if (ridesList[1].getHoldingQueue().peek().getMaxLines() == 1)
				{
					System.out.print("Regular " + ridesList[1].getHoldingQueue().peek().getNumber()+ " ");
				}
				
				ridesList[1].getHoldingQueue().enqueue(ridesList[1].getHoldingQueue().dequeue());
			}
			System.out.print("\n");
			System.out.print("On Virtual Line: ");
			for (int j = 0; j < ridesList[1].getVirtualLine().getSize(); j++)
			{
				if (ridesList[1].getVirtualLine().peek().getMaxLines() == 3)
				{
					System.out.print("Gold " + ridesList[1].getVirtualLine().peek().getNumber() + " ");
				}
				else if (ridesList[1].getVirtualLine().peek().getMaxLines() == 2)
				{
					System.out.print("Silver " + ridesList[1].getVirtualLine().peek().getNumber()+ " ");
				}
				else if (ridesList[1].getVirtualLine().peek().getMaxLines() == 1)
				{
					System.out.print("Regular " + ridesList[1].getVirtualLine().peek().getNumber()+ " ");
				}
				ridesList[1].getVirtualLine().enqueue(ridesList[1].getVirtualLine().dequeue());
			}
			
			System.out.println("\n----------------");

			System.out.println("i386 Tower of Terror- time remaining: " + ridesList[2].getTimeLeft());
			System.out.print("On Ride: ");
			for (int j = 0; j < ridesList[2].getPeopleOnRide().size(); j++)
			{
				if (ridesList[2].getPeopleOnRide().get(j).getMaxLines() == 3)
				{
					System.out.print("Gold " + ridesList[2].getPeopleOnRide().get(j).getNumber());
				}
				else if (ridesList[2].getPeopleOnRide().get(j).getMaxLines() == 2)
				{
					System.out.print("Silver " + ridesList[2].getPeopleOnRide().get(j).getNumber());
				}
				else if (ridesList[2].getPeopleOnRide().get(j).getMaxLines() == 1)
				{
					System.out.print("Regular " + ridesList[2].getPeopleOnRide().get(j).getNumber());
				}
			}
			System.out.print("\n");
			System.out.print("On Holding Queue: ");
			for (int j = 0; j < ridesList[2].getHoldingQueue().getSize(); j++)
			{
				if (ridesList[2].getHoldingQueue().peek().getMaxLines() == 2)
				{
					System.out.print("Gold " + ridesList[2].getHoldingQueue().peek().getNumber() + " ");
				}
				else if (ridesList[2].getHoldingQueue().peek().getMaxLines() == 2)
				{
					System.out.print("Silver " + ridesList[2].getHoldingQueue().peek().getNumber()+ " ");
				}
				else if (ridesList[2].getHoldingQueue().peek().getMaxLines() == 1)
				{
					System.out.print("Regular " + ridesList[2].getHoldingQueue().peek().getNumber()+ " ");
				}
				ridesList[2].getHoldingQueue().enqueue(ridesList[2].getHoldingQueue().dequeue());
			}
			System.out.print("\n");
			System.out.print("On Virtual Line: ");
			for (int j = 0; j < ridesList[2].getVirtualLine().getSize(); j++)
			{
				if (ridesList[2].getVirtualLine().peek().getMaxLines() == 3)
				{
					System.out.print("Gold " + ridesList[2].getVirtualLine().peek().getNumber() + " ");
				}
				else if (ridesList[2].getVirtualLine().peek().getMaxLines() == 2)
				{
					System.out.print("Silver " + ridesList[2].getVirtualLine().peek().getNumber()+ " ");
				}
				else if (ridesList[2].getVirtualLine().peek().getMaxLines() == 1)
				{
					System.out.print("Regular " + ridesList[2].getVirtualLine().peek().getNumber()+ " ");
				}
				ridesList[2].getVirtualLine().enqueue(ridesList[2].getVirtualLine().dequeue());
			}
			

			System.out.println("\n----------------");
			System.out.println("\nGeForce- time remaining: " + ridesList[3].getTimeLeft());
			System.out.print("On Ride: ");
			for (int j = 0; j < ridesList[3].getPeopleOnRide().size(); j++)
			{
				if (ridesList[3].getPeopleOnRide().get(j).getMaxLines() == 3)
				{
					System.out.print("Gold " + ridesList[3].getPeopleOnRide().get(j).getNumber() + " ");
				}
				else if (ridesList[3].getPeopleOnRide().get(j).getMaxLines() == 2)
				{
					System.out.print("Silver " + ridesList[3].getPeopleOnRide().get(j).getNumber()+ " ");
				}
				else if (ridesList[3].getPeopleOnRide().get(j).getMaxLines() == 1)
				{
					System.out.print("Regular " + ridesList[3].getPeopleOnRide().get(j).getNumber() + " ");
				}
			}
			
			System.out.print("\nOn Holding Queue: ");
			for (int j = 0; j < ridesList[3].getHoldingQueue().getSize(); j++)
			{
				if (ridesList[3].getHoldingQueue().peek().getMaxLines() == 3)
				{
					System.out.print("Gold " + ridesList[3].getHoldingQueue().peek().getNumber() + " ");
				}
				else if (ridesList[3].getHoldingQueue().peek().getMaxLines() == 2)
				{
					System.out.print("Silver " + ridesList[3].getHoldingQueue().peek().getNumber()+ " ");
				}
				else if (ridesList[3].getHoldingQueue().peek().getMaxLines() == 1)
				{
					System.out.print("Regular " + ridesList[3].getHoldingQueue().peek().getNumber()+ " ");
				}
				ridesList[3].getHoldingQueue().enqueue(ridesList[3].getHoldingQueue().dequeue());
			}
			
			System.out.print("\nOn Virtual Line: ");
			for (int j = 0; j < ridesList[3].getVirtualLine().getSize(); j++)
			{
				if (ridesList[3].getVirtualLine().peek().getMaxLines() == 3)
				{
					System.out.print("Gold " + ridesList[3].getVirtualLine().peek().getNumber() + " ");
				}
				else if (ridesList[3].getVirtualLine().peek().getMaxLines() == 2)
				{
					System.out.print("Silver " + ridesList[3].getVirtualLine().peek().getNumber()+ " ");
				}
				else if (ridesList[3].getVirtualLine().peek().getMaxLines() == 1)
				{
					System.out.print("Regular " + ridesList[3].getVirtualLine().peek().getNumber()+ " ");
				}
				
				ridesList[3].getVirtualLine().enqueue(ridesList[3].getVirtualLine().dequeue());
			}
			System.out.println("\n ----------------");
			
			System.out.println("\nRegular Customers: \nNum   Line\t\t Status\n----------------");
			for (int j = 0; j < regular.length; j++)
			{
				System.out.printf("%d.    %s", j+1, regular[j].getLines().get(0).getAcronym());
				System.out.printf("\t  %s\n", regular[j].getStatus());
			}
			System.out.println("\nSilver Customers: \nNum   Line1       Line2     Status\n----------------");
			for (int j = 0; j < silver.length; j++)
			{
				System.out.printf("%d.    %s", j+1, silver[j].getLines().get(0).getAcronym());
				System.out.printf("\t  %s", silver[j].getLines().get(1).getAcronym());
				System.out.printf("\t  %s\n",  silver[j].getStatus());
			}
			System.out.println("\nGold Customers: \nNum    Line1     Line2      Line3      Status\n----------------");
			for (int j = 0; j < gold.length; j++)
			{
				System.out.printf("%d.    %s", j+1, gold[j].getLines().get(0).getAcronym(), gold[j].getStatus());
				System.out.printf("\t  %s", gold[j].getLines().get(1).getAcronym());
				System.out.printf("\t     %s", gold[j].getLines().get(2).getAcronym());
				System.out.printf("\t  %s\n",  gold[j].getStatus());
			}
			System.out.println("------------------------------------------------------------------------------------------");

			for (int j = 0; j < ridesList.length; j++)
			{
				ridesList[j].setTimeLeft(ridesList[j].getTimeLeft()-1);
				//j = ride number
				if (ridesList[j].getTimeLeft()== 0)
				{
					for (int k = 0; k < ridesList[j].getPeopleOnRide().size(); k++)
					{
						ridesList[j].setCompletedRides(ridesList[j].getCompletedRides()+1);
					}
					ridesList[j].setTimeLeft(ridesList[j].getDuration());
					//k = index of person on ride
					int tempOnRideSize = ridesList[j].getPeopleOnRide().size();
					for (int k = tempOnRideSize - 1; k >= 0; k--)
					{
						
						int temp1 = 0;
						//trying to find index of ride in persons ride list in order to delete the ride from Persons list
						for (int l = 0; l < ridesList[j].getPeopleOnRide().get(k).getLines().size(); l++)
						{
							if ((ridesList[j].getPeopleOnRide().get(k).getLines().get(l).getName().equals(ridesList[j].getName())) &&
							    (ridesList[j].getPeopleOnRide().get(k).getLines().get(l).getDuration() == ridesList[j].getPeopleOnRide().get(k).getLines().get(l).getTimeLeft()))
								{
									temp1 = l;
								}
						}
						ridesList[j].getPeopleOnRide().get(k).setStatusFromStr("AVAILABLE");
						ridesList[j].getPeopleOnRide().get(k).getLines().remove(temp1);
						ridesList[j].getPeopleOnRide().get(k).getLines().add(RandomGenerator.selectRide(ridesList, probabilities));					
						ridesList[j].getPeopleOnRide().get(k).getLines().get(ridesList[j].getPeopleOnRide().get(k).getLines().size()-1).getVirtualLine().enqueue(ridesList[j].getPeopleOnRide().get(k));
						ridesList[j].getPeopleOnRide().get(k).setRideCount(ridesList[j].getPeopleOnRide().get(k).getRideCount()+1);
						ridesList[j].getPeopleOnRide().remove(k);
						//at this point all people from the ride should now be on new lines
					}
					for (int k = 0; k < ridesList[j].getCapacity(); k++)
					{
						if (ridesList[j].getHoldingQueue().size() != 0)
						{
							ridesList[j].getHoldingQueue().peek().setStatusFromStr("ONRIDE");
							ridesList[j].getPeopleOnRide().add(ridesList[j].getHoldingQueue().peek());
							ridesList[j].getHoldingQueue().dequeue();
							//holding queue ppl go to ride
						}
					}
					boolean available = true;
					//Status s = new Status("AVAILABLE");
					while ((ridesList[j].getHoldingQueue().size() < ridesList[j].getHoldingQueue().getMaxSize()) && (ridesList[j].getVirtualLine().size() != 0) && (available))
					{
						if(ridesList[j].getVirtualLine().peek().getStatus().toString().equals("AVAILABLE"))
						{
							ridesList[j].getVirtualLine().peek().setStatusFromStr("HOLDING");
							ridesList[j].getHoldingQueue().enqueue(ridesList[j].getVirtualLine().peek());
							ridesList[j].getVirtualLine().dequeue();
							//virtual queue ppl go to holding queue
						}
						else
						{
							ridesList[j].getVirtualLine().enqueue(ridesList[j].getVirtualLine().dequeue());
						}
						available = false;
						for (int k = 0; k < ridesList[j].getVirtualLine().size(); k++)
						{
							if(ridesList[j].getVirtualLine().peek().getStatus().toString().equals("AVAILABLE"))
							{
								available = true;
							}
							else
							{
								ridesList[j].getVirtualLine().enqueue(ridesList[j].getVirtualLine().dequeue());
							}
						}
					}
				}
				boolean available = true;
				//Status s = new Status("AVAILABLE");
				while ((ridesList[j].getHoldingQueue().size() < ridesList[j].getHoldingQueue().getMaxSize()) && (ridesList[j].getVirtualLine().size() != 0) && (available))
				{
					if(ridesList[j].getVirtualLine().peek().getStatus().toString().equals("AVAILABLE"))
					{
						ridesList[j].getVirtualLine().peek().setStatusFromStr("HOLDING");
						ridesList[j].getHoldingQueue().enqueue(ridesList[j].getVirtualLine().peek());
						ridesList[j].getVirtualLine().dequeue();
						//virtual queue ppl go to holding queue
					}
					else
					{
						ridesList[j].getVirtualLine().enqueue(ridesList[j].getVirtualLine().dequeue());
					}
					available = false;
					for (int k = 0; k < ridesList[j].getVirtualLine().size(); k++)
					{
						if(ridesList[j].getVirtualLine().peek().getStatus().toString().equals("AVAILABLE"))
						{
							available = true;
						}
						else
						{
							ridesList[j].getVirtualLine().enqueue(ridesList[j].getVirtualLine().dequeue());
						}
					}
				}
			}
		} 
		System.out.println("------------------------------------------------------------------------------------------");
		double goldAvg = 0;
		for (int i = 0; i < gold.length; i++)
		{
			goldAvg = goldAvg+gold[i].getRideCount();
			goldAvg = goldAvg/gold.length;
		}
		System.out.println("On average, Gold customers have taken " + goldAvg + " rides.");
		double silverAvg = 0;
		for (int i = 0; i < silver.length; i++)
		{
			silverAvg = silverAvg+silver[i].getRideCount();
			silverAvg = silverAvg/silver.length;
		}
		System.out.println("On average, Silver customers have taken " + silverAvg + " rides.");
		double regAvg = 0;
		for (int i = 0; i < regular.length; i++)
		{
			regAvg = regAvg+regular[i].getRideCount();
			regAvg = regAvg/regular.length;
		}
		System.out.println("On average, Regular customers have taken " + regAvg + " rides.");
		System.out.println("\nBSOD has completed rides for " + ridesList[0].getCompletedRides() + " people." +
							"\nKK has completed rides for " + ridesList[1].getCompletedRides() + " people." +
							"\nToT has completed rides for " + ridesList[2].getCompletedRides() + " people." +
							"\nGF has completed rides for " + ridesList[3].getCompletedRides() + " people.");
	}
}
