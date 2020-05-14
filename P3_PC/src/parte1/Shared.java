// ÓSCAR MORUJO FERNÁNDEZ
package parte1;
import java.util.concurrent.*; 

public class Shared {

	private int value;
	private Semaphore sem;
	
	
	public Shared() {
		this.value = 0;
		this.sem = new Semaphore(1);
		
	}
	
	public void increment() {
		P();
		System.out.println("Sumando");
		this.value= this.value + 1;
		V();
	}
	
	public void decrement() {
		P();
		System.out.println("Restando");
		this.value= this.value - 1;
		V();
	}
	
	private void P() {
		try {
			sem.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void V() {
		sem.release();
	}
	
	public String toString() {
		return Integer.toString(value);
	}

}