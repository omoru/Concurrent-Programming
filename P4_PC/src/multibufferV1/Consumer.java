// OSCAR MORUJO FERNANDEZ
package multibufferV1;

public class Consumer extends Thread{
	private MonitorMB m;
	@SuppressWarnings("unused")
	private int id;
	private int tam;
	
	public Consumer(MonitorMB m, int id,int tam) {
		this.m=m;
		this.id=id;
		this.tam= tam;
		}
	
	public void run() {
		while(true) {
			int numero = (int) (Math.random() * (this.tam/2))+ 1;
			@SuppressWarnings("unused")
			Producto[] extraido =this.m.extraer(numero);
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	}
}
