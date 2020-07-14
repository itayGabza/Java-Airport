
import java.util.ArrayList;

public class Queue<T> {

	//fields
	private ArrayList<T>queue;

	//constructor
	public Queue(){
		queue = new ArrayList<T>();
	}
	
	public synchronized void insert(T item){ //add flight to queue
		queue.add(item);
		notifyAll(); //other Threads should continue now
	}
	public synchronized T extract(){ //extract flight to next queue
		while (queue.isEmpty()) { 
			try{
				wait();   
			}catch(InterruptedException e){
				System.out.println("Exception caught : " + e);
			}
		}
		T item = queue.get(0);
		queue.remove(item);
		notifyAll(); //continue to run
		return item; 
	}
	
	//Methods
	public int size(){
		return queue.size();
	}
	public T get(int index){
		return queue.get(index);
	}
	public boolean isEmpty(){ //check is there obj in queue or not
		if(queue.isEmpty() || queue == null)
			return true;
		return false;
	}
}