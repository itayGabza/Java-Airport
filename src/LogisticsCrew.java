
public class LogisticsCrew extends Crew{

	//fields
	private int capacity;
	private String name;
	private int truckIsOnTheWay = 2000;
	private int chanceToProblem = 10;
	private Queue<Flight> logisticsQueue;
	private Queue<Flight> securityQueue;
	private Queue<Flight> tecnichalCrewQueue;
	
	//constructor
	public LogisticsCrew (String name, int capacity, Queue<Flight> logisticsQueue ,Queue<Flight> securityQueue,Queue<Flight> tecnichalCrewQueue ) {
		this.name = name;
		this.logisticsQueue = logisticsQueue;
		this.capacity = capacity;
		this.securityQueue = securityQueue;
		this.tecnichalCrewQueue = tecnichalCrewQueue;
		
	}

	public void run(){//polymorphisem - Crew implements Thread, this function will be in use for Logistic Crew
		while(!ManagementCrew.getDayIsOver())
			handleCargo();
	}

	private void handleCargo(){ //handle to unload a flight (and when not, handle)
		Flight flight = logisticsQueue.extract();
		if(checkingCapacity(flight)){
			unLoadCargo(flight);
		}else{
			waitForTruck(flight);
			unLoadCargo(flight);
			flight.setCargoTrucks(1);
		}
		if(problem(chanceToProblem))
			handelProblem(flight);
		else{
			securityQueue.insert(flight);
		}
	}
	
	private boolean checkingCapacity(Flight flight){ //check if this crew can unload the flight's cargo
		if(this.capacity >= flight.getCargo())
			return true;
		else
			return false;
	}

	private void unLoadCargo(Flight flight){ //unload cargo
		int sleep = setSleepTime((flight.getCargo() / 10));
		Airport.threadSleep(sleep);
		flight.setTotalTime((flight.getTotalTime() + sleep));
	}
	private void waitForTruck(Flight flight){ //sleep and wait to crew
		Airport.threadSleep(truckIsOnTheWay);
		flight.setTotalTime((flight.getTotalTime() + truckIsOnTheWay));///////////change to otherfuel truck time
	}

	protected void handelProblem(Flight flight){ //send to tech crew when problem occured
		flight.setLastPosition("logisticsCrew");
		tecnichalCrewQueue.insert(flight);
	}
}


