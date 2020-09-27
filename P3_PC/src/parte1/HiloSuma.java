// OSCAR MORUJO FERNANDEZ
package parte1;
public class HiloSuma extends Thread {
	
	private Shared shared_class;
	
	
	public HiloSuma (Shared s) {
		this.shared_class = s;
	}
	
	public void run() {
		shared_class.increment();		
	}
}