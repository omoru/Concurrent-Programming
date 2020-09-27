// OSCAR MORUJO FERNANDEZ
package multibufferV2;
import multibufferV1.Producto;

public class Producer extends Thread {
	private MonitorMB_v2 m;
	private int id;
	private int tam;
	
	
	public Producer(MonitorMB_v2 m, int id,int tam) {
		this.m=m;
		this.id=id;
		this.tam=tam;
	}
	
	public void run() {

		while(true) {
			int numero = (int) (Math.random() * (this.tam/2)) + 1;
			Producto[] p = new Producto[numero];
			for(int i=0; i < numero;i++) {
				p[i]= new Producto(this.id);
			}
			try {
				this.m.almacenar(p);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
