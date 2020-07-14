import java.util.Vector;

public class Airport {
	
	//Fields
	private int numOfRunWays = 3;// number of each crew
	private int numOfLogistics = 3;
	private int numOfSecurity = 2;
	private int numOfFuel = 2;
	private int numOfTech = 2;
	private int numOfManagement = 1;
	public static Vector<Flight> allFlights = new Vector<Flight>();
	private Queue<Flight> runwayArrivalsQueue;
	private Queue<Flight> runwayDeparturessQueue;
	private Queue<Flight> logisticsQueue;
	private Queue<Flight> securityQueue;
	private BoundedQueue<Flight> fuelQueue;
	private Queue<FlightDetails> managementCrewQueue;
	private Queue<Flight> tecnichalCrewQueue;
	private Vector<Thread> allCrewsThreads;
	public static int flightsCounter = 0;
	Vector<Crew> allCrews;
	private DataBase db;

	
	

	public Airport(){ //constructor
		
	allCrews = new Vector<Crew>();
	allCrewsThreads = new Vector<Thread>();
	 runwayArrivalsQueue = new Queue<Flight>();
	 runwayDeparturessQueue =new Queue<Flight>();
	 logisticsQueue = new Queue<Flight>();
	 securityQueue = new Queue<Flight>();
	 fuelQueue  = new BoundedQueue<Flight>(8);
	 managementCrewQueue  = new Queue<FlightDetails>();
	 tecnichalCrewQueue  = new Queue<Flight>();
	 parseFile();
	 //db = new DataBase();
		createCrews();
		
	}
	
	private void parseFile() //read from file in loop each line to array
	{

		Vector<String> file;
		
		file = ReaderFile.file2Vector(Consts.FLIGHT_DATA_FILE);

		for(int i = 1; i <file.size(); i++)/////////////change to file size instead of 160
		{

			String arrStr[] = file.get(i).split("\t"); //arr of 1 line from the file, each cell had a word (string)
			String code = arrStr[0];
			int people = Integer.parseInt(arrStr[1]);
			int time = Integer.parseInt(arrStr[2]);
			try
			{
				int cargo = Integer.parseInt(arrStr[3]);
				Flight flight = new Flight(code, people, time, cargo,runwayArrivalsQueue, runwayDeparturessQueue );
				allFlights.add(flight);
			}
			catch(NumberFormatException e)
			{
				String dest = arrStr[3];
				Flight flight = new Flight(code, people, time, dest, runwayArrivalsQueue, runwayDeparturessQueue);
				allFlights.add(flight);
			}
		}
	}

	private void createCrews(){ //make the objects threads and add each to one vector with all Threads
		for (int i = 0; i < numOfRunWays; i++){
			RunwayDirector rwd = new RunwayDirector((i+1),runwayArrivalsQueue,runwayDeparturessQueue, logisticsQueue,tecnichalCrewQueue, managementCrewQueue);
			Thread t = new Thread(rwd);
			allCrewsThreads.add(t);	
		}
		
		for (int i = 0; i < numOfLogistics; i++){
			LogisticsCrew lg = new LogisticsCrew("logisticCrewNumber "+ (i+1), (50 + (i*20)), logisticsQueue, securityQueue,tecnichalCrewQueue );
			Thread t = new Thread(lg);
			allCrewsThreads.add(t);
		}
		
		for (int i = 0; i < numOfSecurity; i++){
			String rank;
			if (i % 2 == 0)
				rank = "junior";
			else
				rank = "senior";
			SecurityMan sm = new SecurityMan(rank, securityQueue,fuelQueue);
			Thread t = new Thread(sm);
			allCrewsThreads.add(t);
		}
		for (int i = 0; i < numOfFuel; i++){
			FuelCrew fc = new FuelCrew("FuelCrewNumber "+ (i+1), (5000 + (i*5000)), fuelQueue, managementCrewQueue,tecnichalCrewQueue );
			Thread t = new Thread(fc);
			allCrewsThreads.add(t);
		}
		for (int i = 0; i < numOfTech; i++){
			TechnicalCrew tc = new TechnicalCrew("TechCrewNumber "+ (i+1),tecnichalCrewQueue,runwayArrivalsQueue,logisticsQueue,securityQueue,fuelQueue, managementCrewQueue );
			Thread t = new Thread(tc);
			allCrewsThreads.add(t);
		}
		ManagementCrew mc = new ManagementCrew("mc", managementCrewQueue,runwayDeparturessQueue, fuelQueue, tecnichalCrewQueue, allCrewsThreads, db);
		Thread t = new Thread(mc);
		allCrewsThreads.add(t);
		
		for(int i = 0; i < allCrewsThreads.size(); i++){
			allCrewsThreads.elementAt(i).start();
		}
		
		for(int i = 0; i < allFlights.size();i++){
			Flight fl = allFlights.get(i);
			Thread f = new Thread(fl);
		       f.start();  
		}
	}
	
	public static Vector<Flight> getAllFlights() {
		return allFlights;
	}

	public void println(Object o){ //printing
		System.out.println(o);
	}
	public void print(Object o){ //printing
		System.out.print(o);
	}
	
	public static void threadSleep(int sleep){ //send thread to sleep static func
		try {
			Thread.sleep(sleep);
		} catch (InterruptedException f) {
			System.out.println("InterruptedException");
		}
	}
	
}


