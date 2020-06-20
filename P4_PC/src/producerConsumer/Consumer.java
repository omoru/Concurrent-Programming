package producerConsumer;


public class Consumer extends Thread {

	private MonitorPC m;
	@SuppressWarnings("unused")
	private int id;
	
	public Consumer(MonitorPC m, int id) {
		this.m=m;
		this.id=id;
	}
	
	public void run() {
		while(true) {
			@SuppressWarnings("unused")
			Producto extraido = this.m.extraer();
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
}
