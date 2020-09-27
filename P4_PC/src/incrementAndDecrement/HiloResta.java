// OSCAR MORUJO FERNANDEZ
package incrementAndDecrement;

public class HiloResta extends Thread {
	
	private Monitor m;
	
	public HiloResta(Monitor m) {
		this.m=m;
	}
	
	public void run() {
		m.decrement();
	}

}
