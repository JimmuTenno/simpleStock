package baseClasses;

public class Semaphor {
	
	private int counter;
	
	public Semaphor(int counter) {
		this.counter = counter;
	}
	
	public synchronized void up(){
		counter++;	
		this.notify();
	}
	public synchronized void down() throws InterruptedException{
		if(counter == 0)this.wait();
		counter--;
	}
}
