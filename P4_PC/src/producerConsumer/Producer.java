// OSCAR MORUJO FERNANDEZ
package producerConsumer;


public class Producer extends Thread {
	

	private MonitorPC m;
	@SuppressWarnings("unused")
	private int id;
	
	public Producer(MonitorPC m, int id) {
		this.m=m;
		this.id=id;
	}
	
	public void run() {

		while(true) {
			Producto p = new Producto();
			this.m.almacenar(p);
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
