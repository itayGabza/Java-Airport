
public class Flight implements Runnable{

	//fields
	private String flightCode;
	private int numOfPassengers;
	private String destination;
	private int cargo;
	private String type;
	private int time;
	private String lastPosition = null;
	private int repairCost = 0;
	private int totalTime;
	private int suspicious;
	private int cargoTrucks;
	private Queue<Flight> runwayArrivalsQueue;
	private Queue<Flight> runwayDeparturessQueue;
	
	//constructor for departure
	public Flight(String flightcode, int numOfPassengers,int time ,String destination, Queue<Flight> runwayArrivalsQueue, Queue<Flight> runwayDeparturessQueue ){ //departures constructor
		this.flightCode = flightcode;
		this.time = time;
		this.numOfPassengers = numOfPassengers;
		this.destination = destination;
		this.type = "departure";
		this.cargo = 0;
		this.runwayArrivalsQueue = runwayArrivalsQueue;
		this.runwayDeparturessQueue = runwayDeparturessQueue;
	}
	
	//constructor for arrival
	public Flight(String flightcode, int numOfPassengers,int time, int cargo, Queue<Flight> runwayArrivalsQueue,Queue<Flight> runwayDeparturessQueue ){ //arrivals constr
		this.flightCode = flightcode;
		this.numOfPassengers = numOfPassengers;
		this.time = time;
		this.cargo = cargo;
		this.type = "arrival";
		destination = null;
		this.runwayArrivalsQueue = runwayArrivalsQueue;
		this.runwayDeparturessQueue = runwayDeparturessQueue;
	}
	
	public void run(){ //run (class implement Thread) func!
		try{
			Thread.sleep(this.time*1000);
			//System.out.println("flight ready for runway");
		} catch(InterruptedException e){
			System.out.println("Error");
		}
		if(cargo == 0){
			runwayDeparturessQueue.insert(this);
		//	System.out.println("departure queue");
		}
		else{
			runwayArrivalsQueue.insert(this);
			//System.out.println("arrival queue");
		}
	}
	
	//Getters & Setters
	public int getSuspicious() {
		return suspicious;
	}
	public void setSuspicious(int suspicious) {
		this.suspicious = suspicious;
	}
	public int getCargoTrucks() {
		return cargoTrucks;
	}
	public void setCargoTrucks(int cargoTrucks) {
		this.cargoTrucks = cargoTrucks;
	}
	public int getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public String getLastPosition() {
		return lastPosition;
	}
	public void setLastPosition(String lastPosition) {
		this.lastPosition = lastPosition;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public int getCargo() {
		return cargo;
	}
	public void setCargo(int cargo) {
		this.cargo = cargo;
	}
	public String getFlightCode() {
		return flightCode;
	}
	public void setFlightCode(String flightCode) {
		this.flightCode = flightCode;
	}
	public int getNumOfPassengers() {
		return numOfPassengers;
	}
	public void setNumOfPassengers(int numOfPassengers) {
		this.numOfPassengers = numOfPassengers;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getRepairCost() {
		return repairCost;
	}
	public void setRepairCost(int repairCost) {
		this.repairCost = repairCost;
	}


}
