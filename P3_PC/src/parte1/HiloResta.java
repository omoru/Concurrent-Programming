// ÓSCAR MORUJO FERNÁNDEZ
package parte1;
public class HiloResta extends Thread {
	
	private Shared shared_class;
	
	
	public HiloResta (Shared s) {
		this.shared_class = s;
	}
	
	public void run() {
		shared_class.decrement();
	}
}
