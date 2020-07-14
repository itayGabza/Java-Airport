
public class SecurityMan extends Crew{

	//fields
	private String rank;
	private double checkingTime = 2000;/////////////////////change in gui
	private int chanceToProblem = 5;
	private int handlingSuspicious = 2000;
	private Queue<Flight> securityQueue;
	private BoundedQueue<Flight> fuelQueue;
	
	//constructor
	public SecurityMan(String rank,Queue<Flight> securityQueue,BoundedQueue<Flight> fuelQueue) {
		this.rank = rank;
		this.securityQueue = securityQueue;
		this.fuelQueue = fuelQueue;
		
	}

	public void run(){ //polymorphisem - Crew implements Thread, this function will be in use for Logistic Crew
		while(!ManagementCrew.getDayIsOver()){
			handelSecurity();
		}
	}
	
	private void handelSecurity(){ //in use as long as there flights to check
		Flight flight = securityQueue.extract();
		Airport.threadSleep((int) checkingTime);
		flight.setTotalTime((flight.getTotalTime() +(int) checkingTime));
		if(problem(chanceToProblem))
			handelProblem(flight);
		fuelQueue.insert(flight);
	}
	
	protected void handelProblem(Flight flight){ //in case of problem - send
		flight.setLastPosition("securityMan");
		Airport.threadSleep(handlingSuspicious); //Neutralizing the bomb
		flight.setTotalTime((flight.getTotalTime() + handlingSuspicious));
		flight.setSuspicious(1);
	}
}
