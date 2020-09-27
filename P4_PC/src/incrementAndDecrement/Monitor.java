// OSCAR MORUJO FERNANDEZ
package incrementAndDecrement;

public class Monitor {
	
	private int value;
	
	public Monitor() {
		this.value = 0;
	}
	
	public synchronized void decrement() {
		this.value--;
		System.out.println("Se ha decrementado,valor actual: "+ this.value);
	}
	
	public synchronized void increment() {
		this.value++;
		System.out.println("Se ha incrementado,valor actual: "+ this.value);
	}
}
