
abstract public class Crew implements Runnable{ 
	

	
	protected  int setSleepTime(int sleep){ //return number of mil-sec to sleep
		return (sleep * 1000);
	}
	
	protected int setSleepTime(int min, int max){ //return random to use in case with range
		int sleep = (int )(Math.random() * (max - min) + min);
		return (sleep * 1000);
	}

	protected  boolean problem(int chanceToProblem){ //Calculate chance to problem (in differents crews) depend on % to occured
		int error = (int)(Math.random()* 100);
		if(error <= chanceToProblem)
			return true;
		return false;
	}
	protected void sleeping(){ //in use in inherited classes
	}
	abstract protected void handelProblem(Flight flight);
}
