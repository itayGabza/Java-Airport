import java.util.ArrayList;

public class BoundedQueue<T> {

	//Fields
	private ArrayList<T>boundedQueue;
	private int maxSize = 8;

	public BoundedQueue(int maxSize){ //constructor
		boundedQueue = new ArrayList<T>();
		this.maxSize = maxSize;
	}

	public synchronized void insert(T item) { //add to queue
		while(boundedQueue.size()>=maxSize){
			try{
				wait();
			}catch(InterruptedException e){
				System.out.println("InterruptedException");
			}
		}
		boundedQueue.add(item);
		notifyAll();  
	}

	public synchronized T extract() //extract an obj T (generic) from queue to next crew handle
	{
		while (boundedQueue.isEmpty()) {
			try{
				wait();
			}catch (InterruptedException e){
				System.out.println("InterruptedException");
			}
		}
		T item = boundedQueue.get(0);
		boundedQueue.remove(item);
		notifyAll();
		return item;  

	}

	//Getters & Setters
	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}
	
	public int size(){
		return boundedQueue.size();
	}
	
	public T get(int index){
		return boundedQueue.get(index);
	}
}
