
public class RunwayDirector extends Crew {

	///fields
	private int runwayLength;
	private int chanceToProblem = 25;
	private Queue<Flight> runwayArrivalsQueue;
	private Queue<Flight> runwayDeparturessQueue;
	private Queue<Flight> tecnichalCrewQueue;
	private Queue<Flight> logisticsQueue;
	private Queue<FlightDetails> managementCrewQueue;
	
	//constructor
	public RunwayDirector(int runwayLength, Queue<Flight> runwayArrivalsQueue,Queue<Flight> runwayDeparturessQueue, Queue<Flight> logisticsQueue, Queue<Flight> tecnichalCrewQueue, Queue<FlightDetails> managementCrewQueue) {
		this.runwayLength = runwayLength;
		this.runwayArrivalsQueue = runwayArrivalsQueue;
		this.runwayDeparturessQueue = runwayDeparturessQueue;
		this.logisticsQueue = logisticsQueue;
		this.tecnichalCrewQueue = tecnichalCrewQueue;
		this.managementCrewQueue = managementCrewQueue;
	}
	
	public void run(){//polymorphisem - Crew implements Thread, this function will be in use for Logistic Crew
		while(Airport.flightsCounter <= Airport.getAllFlights().size()){
			if(runwayArrivalsQueue != null)
				handlingArrivals();
			handlingDepartures();
		}
	}
	
	private void handlingArrivals(){ //for arrivals flights
		while(!runwayArrivalsQueue.isEmpty()){////arrival
			Flight flight = runwayArrivalsQueue.extract();
			int sleep = setSleepTime(5, 10);
			Airport.threadSleep(sleep);
			flight.setTotalTime((flight.getTotalTime() + sleep));
			if(problem(chanceToProblem))
				handelProblem(flight);
			else{
				logisticsQueue.insert(flight);
			}
		}
	}

	private void handlingDepartures(){ //for departures
			Flight flight = runwayDeparturessQueue.extract();
			int sleep = setSleepTime(5, 10);
			Airport.threadSleep(sleep);
			flight.setTotalTime((flight.getTotalTime() + sleep));
			addDetailslDeparture(flight);
			
		
	}
	
protected void handelProblem(Flight flight){ //in use when problem occurred
		flight.setLastPosition("runwayDirector");
		tecnichalCrewQueue.insert(flight);
	}

private void addDetailslDeparture(Flight flight){ //when flight leaves - send to management to save details
		FlightDetails flightDetails = new FlightDetails(flight);
		managementCrewQueue.insert(flightDetails);
		
	}
}
