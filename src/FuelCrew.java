
public class FuelCrew extends Crew{

	//fields
	private String name;
	private int fuelCapcity;
	private int fuelLevel;
	private int fuelPerFlight = 1000;
	private int chanceToProblem = 30;
	private int reFullingTruck = 5000;
	private BoundedQueue<Flight> fuelQueue;
	private Queue<FlightDetails> managementCrewQueue;
	private Queue<Flight> tecnichalCrewQueue;

	//constructor
	public FuelCrew(String name, int fuelCapcity,BoundedQueue<Flight> fuelQueue, Queue<FlightDetails> managementCrewQueue, Queue<Flight> tecnichalCrewQueue) {
		this.name = name;
		this.fuelCapcity = fuelCapcity;
		this.fuelLevel = fuelCapcity;
		this.fuelQueue = fuelQueue;
		this.managementCrewQueue = managementCrewQueue;
		this.tecnichalCrewQueue = tecnichalCrewQueue;
	} 

	public void run(){ //polymorphisem - Crew implements Thread, this function will be in use for FuelCrew
		while(!ManagementCrew.getDayIsOver()){
			handelFuel();/////////while??
		}
	}
	
	private void handelFuel(){ //check option to fuel flight
		Flight flight = fuelQueue.extract();
		if(this.fuelLevel >= fuelPerFlight){
			fillingUp(flight);
			if(problem(chanceToProblem))
				handelProblem(flight);
			else
				addToFlightDetails(flight);
		}
		else
			reFuelTruck(flight);
	}

	private void fillingUp(Flight flight){ //fuel a flight and update truck's fuel level
		this.fuelLevel = (this.fuelLevel - fuelPerFlight);
		int sleep = setSleepTime(3, 4);
		Airport.threadSleep(sleep);
		flight.setTotalTime((flight.getTotalTime() + sleep));
	}

	protected void handelProblem(Flight flight) { //when problem occured - update field and send to techCrew
		flight.setLastPosition("fuelCrew");
		tecnichalCrewQueue.insert(flight);
	}
	
	private void reFuelTruck(Flight flight){ //refill truck when out of fuel
		fuelQueue.insert(flight);
		Airport.threadSleep(reFullingTruck);//---------------going to gas station
		fuelLevel = fuelLevel + fuelCapcity;
	}
	
	private void addToFlightDetails(Flight flight){ //send to management to create info about the flight
		FlightDetails flightDetails = new FlightDetails(flight);
		managementCrewQueue.insert(flightDetails);
	}
}
