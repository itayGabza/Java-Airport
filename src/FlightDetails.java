
public class FlightDetails {
	
	//fields
	private String flightCode;
	private int numOfPassengers;
	private String destination = null;
	private int cargo = 0;
	private String type;
	private int repairCost;
	private int totalTime;
	private int suspicious;
	private int cargoTrucks;
	

//Constructor
	public FlightDetails(Flight flight) {
		
		this.flightCode = flight.getFlightCode() ;
		this.numOfPassengers =  flight.getNumOfPassengers();
		this.destination = flight.getDestination();
		this.cargo = flight.getCargo();
		this.type = flight.getType();
		this.totalTime = flight.getTotalTime();
		this.repairCost = flight.getRepairCost();
		this.suspicious = flight.getSuspicious();
		this.cargoTrucks = flight.getCargoTrucks();
	}

//Getters & Setters
	public int getCargoTrucks() {
		return cargoTrucks;
	}
	public int getRepairCost() {
		return repairCost;
	}
	public void setCargoTrucks(int cargoTrucks) {
		this.cargoTrucks = cargoTrucks;
	}
	public int getSuspicious() {
		return suspicious;
	}
	public void setSuspicious(int suspicious) {
		this.suspicious = suspicious;
	}
	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}
	public int getTotalTime() {
		return totalTime;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setRepairCost(int repairCost) {
		this.repairCost = repairCost;
	}
	
	public String toString() { //toString for print
		return "FlightDetails [flightCode=" + flightCode + ", numOfPassengers=" + numOfPassengers + ", destination="
				+ destination + ", cargo=" + cargo + ", type=" + type + "]";
	}
}
