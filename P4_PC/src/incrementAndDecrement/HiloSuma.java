// OSCAR MORUJO FERNANDEZ
package incrementAndDecrement;

public class HiloSuma extends Thread{
	
	private Monitor m;
	
	public HiloSuma(Monitor m) {
		this.m=m;
	}
	
	public void run() {
		m.increment();
	}
}
