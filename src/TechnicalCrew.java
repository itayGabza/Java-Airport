
public class TechnicalCrew extends Crew  {

	//Fields
	private int maxRepairCost = 1000;
	private int minRepairCost = 500;
	private String name;
	private Queue<Flight> runwayArrivalsQueue;
	private Queue<Flight> logisticsQueue;
	private Queue<Flight> securityQueue;
	private BoundedQueue<Flight> fuelQueue;
	private Queue<FlightDetails> managementCrewQueue;
	private Queue<Flight> tecnichalCrewQueue;

	//constructor
	public TechnicalCrew(String name, Queue<Flight> tecnichalCrewQueue,Queue<Flight> runwayArrivalsQueue, Queue<Flight> logisticsQueue, Queue<Flight> securityQueue,  BoundedQueue<Flight> fuelQueue, Queue<FlightDetails> managementCrewQueue) {
		this.name = name;
		this.tecnichalCrewQueue = tecnichalCrewQueue;
		this.runwayArrivalsQueue = runwayArrivalsQueue;
		this.logisticsQueue = logisticsQueue;
		this.securityQueue = securityQueue;
		this.fuelQueue = fuelQueue;
		this.managementCrewQueue = managementCrewQueue;
	}

	public void run(){ //polymorphisem - Crew implements Thread, this function will be in use for Tech Crew
		while(!ManagementCrew.getDayIsOver()){
			Flight flight = tecnichalCrewQueue.extract();
			handelProblem(flight);
			backToQueues(flight);
		}
	}


	protected void handelProblem(Flight flight) { //take care of problems for all flights
		//System.out.println("Technical ready to fix");
		int sleep = setSleepTime(3, 5);
		Airport.threadSleep(sleep);
		flight.setTotalTime((flight.getTotalTime() + sleep));
		 int range = (maxRepairCost - minRepairCost) + 1;     
		 flight.setRepairCost ((int)(Math.random() * range) + minRepairCost);
	}
	
	private void backToQueues(Flight flight){ //after handle a problem - send back to relevent queue
		if(flight.getLastPosition().equals("fuelCrew")){
				Airport.threadSleep(1000);
				flight.setTotalTime((flight.getTotalTime() + 1000));
			FlightDetails flightDetails = new FlightDetails(flight);
			managementCrewQueue.insert(flightDetails);
		}
		else if(flight.getLastPosition().equals("runwayDirector")){
			logisticsQueue.insert(flight);
		}
		else if(flight.getLastPosition().equals("logisticsCrew")){
			securityQueue.insert(flight);
		}
	}
}
