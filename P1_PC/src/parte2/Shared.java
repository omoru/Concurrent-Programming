// ÓSCAR MORUJO FERNÁNDEZ
package parte2;


public class Shared {

	private int value;
	
	public Shared() {
		this.value = 0;
	}
	
	public void increment() {
		this.value= this.value + 1;
	}
	
	public void decrement() {
		this.value= this.value - 1;
	}
	
	public String toString() {
		return Integer.toString(value);
	}

}