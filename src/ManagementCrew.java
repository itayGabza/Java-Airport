import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

public class ManagementCrew extends Crew{

	//fields
	private static boolean dayIsOver = false;
	private int totalPassengers = 0;
	private int totalCargo = 0;
	private String popularDestination;//////////////// not done yet <----
	private int totalCosts = 0;
	private int totalSuspicious = 0;
	private int totalFuel = 0;
	private int totalCargoTrucks = 0;
	private String name;
	private Queue<FlightDetails> managementCrewQueue;
	private Queue<Flight> runwayDeparturessQueue;
	private BoundedQueue<Flight> fuelQueue;
	private Queue<Flight> tecnichalCrewQueue;
	private Vector<Thread> allCrewsThreads;
	private DataBase db;
	private TreeMap<String,Integer> mostDest;
	
	
	//constructor
	public ManagementCrew(String name, Queue<FlightDetails> managementCrewQueue,Queue<Flight> runwayDeparturessQueue, BoundedQueue<Flight> fuelQueue, Queue<Flight> tecnichalCrewQueue, Vector<Thread> allCrewsThreads, DataBase db) {
		this.name = name;
		this.managementCrewQueue = managementCrewQueue;
		this.runwayDeparturessQueue = runwayDeparturessQueue;
		this.fuelQueue = fuelQueue;
		this.tecnichalCrewQueue = tecnichalCrewQueue;
		this.allCrewsThreads = allCrewsThreads;
		this.db = db;
		mostDest = new TreeMap<String,Integer>();
	}

	private void checkingEndOfDay(){ //check if there more flights to come/leave
		if(Airport.flightsCounter == Airport.getAllFlights().size())
			dayIsOver = true;
	}

	public void run(){ ////polymorphisem - Crew implements Thread, this function will be in use for Management Crew
		while(!ManagementCrew.getDayIsOver()){
			FlightDetails flightDetails = managementCrewQueue.extract();
			handelFlightDetails(flightDetails);
			printDetails(flightDetails);
			checkingEndOfDay();
		}///////////while day is over
		waitForAllTheardsDone();
		
		printReport();
	}

	private void waitForAllTheardsDone(){ //wait for all threads finished (important before print report)
		for(int i = 0; i < allCrewsThreads.size(); i++)
			allCrewsThreads.elementAt(i).interrupt();
	}

	private void printDetails(FlightDetails flightDetails){ //flight details in the end of flight 
		print("Flight Code: " + flightDetails.getFlightCode()+ " Total Time: " + (flightDetails.getTotalTime() / 1000) + "");
		println(" Total Cost:  "+ flightDetails.getRepairCost());
		if(flightDetails.getType().equals("departure"))
			println(flightDetails.getDestination());
	}
	public void println(Object o){ //printing
		System.out.println(o);
	}

	public void print(Object o){ //printing
		System.out.print(o);
	}
	
	public void printReport(){ //print report in the end of the day
		println("Total Passengers Today: " + totalPassengers);
		println("Total Cargo Today: " + totalCargo);
		println("Most Popular Destination Today: " + getMaxDest());
		println("Total Costs Today: " + totalCosts);
		println("Total Fuel Today: " + totalFuel);
		println("Total Suspicious Objects Today: " + totalSuspicious);
		println("Total Cargo Trucks Today: " + totalSuspicious);
	}
	
	private void handelFlightDetails(FlightDetails flightDetails){ //update and calculate of data in airpot for report in the end of the day
		
		totalPassengers = (totalPassengers + flightDetails.getNumOfPassengers());
		totalCargo = (totalCargo + flightDetails.getCargo());
		totalCosts = (totalCosts + flightDetails.getRepairCost());
		if(flightDetails.getType().equals("arrival"))
			totalFuel = (totalFuel + 1000);
		if(flightDetails.getSuspicious() == 1)
			totalSuspicious ++;
		if(flightDetails.getCargoTrucks() == 1)
			totalCargoTrucks ++;
		if(flightDetails.getType().equals("departure"))
			updateDest(flightDetails.getDestination());
		Airport.threadSleep(2000);
		Airport.flightsCounter++;
//		if(flightDetails.getType().equals("departure"))
//			db.insertToTakeOffsTable("Takeoffs", flightDetails);
//		else
//			db.insertToLandingsTable("Landings", flightDetails);
//		System.out.println("data base succssful");
	}
	
	
	//Getters & Setters
	public static  boolean getDayIsOver() {
		return dayIsOver;
	}
	public int getTotalPassengers() {
		return totalPassengers;
	}
	public void setTotalPassengers(int totalPassengers) {
		this.totalPassengers = this.totalPassengers + totalPassengers;
	}
	public int getTotalCargo() {
		return totalCargo;
	}
	public void setTotalCargo(int totalCargo) {
		this.totalCargo = totalCargo;
	}
	public String getPopularDestination() {
		return popularDestination;
	}
	public void setPopularDestination(String popularDestination) {
		this.popularDestination = this.popularDestination + popularDestination;
	}
	public int getTotalCosts() {
		return totalCosts;
	}
	public void setTotalCosts(int totalCosts) {
		this.totalCosts =this.totalCosts + totalCosts;
	}
	public int getTotalSuspicious() {
		return totalSuspicious;
	}
	public void setTotalSuspicious(int totalSuspicious) {
		this.totalSuspicious = this.totalSuspicious + totalSuspicious;
	}
	public int getTotalFuel() {
		return totalFuel;
	}
	public void setTotalFuel(int fuel) {
		this.totalFuel = this.totalFuel + fuel;
	}
	protected void handelProblem(Flight flight) {

	}
	public String getMaxDest(){
		Set<Map.Entry<String,Integer>> s = mostDest.entrySet();
		Map.Entry<String,Integer> max = null;
		for (Map.Entry<String,Integer> e : s) {
			if (max==null)
				max = e;
			if (e.getValue()>max.getValue())
				max = e;
		}
		return max.getKey();
	}
	
	public void updateDest(String dest){
		if(!this.mostDest.containsKey(dest))
			this.mostDest.put(dest, 1);
		else{
			int times = this.mostDest.get(dest);
			this.mostDest.put(dest, times+1);
		}
	}

}

