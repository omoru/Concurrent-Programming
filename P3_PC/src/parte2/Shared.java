// ÓSCAR MORUJO FERNÁNDEZ
package parte2;
import java.util.concurrent.*; 

public class Shared implements Almacen {
	private Producto buffer = null;
	private Semaphore full = new Semaphore(0);
	private Semaphore empty = new Semaphore(1);	
	
	public Shared() {
	
	}

	
	@Override
	public void almacenar(Producto producto) {
		try {
			empty.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Productor almacenando "+ producto.tostring());
		this.buffer = producto;	
		full.release();
	}

	@Override
	public Producto extraer() {
		try {
			full.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Producto extraido= buffer;
		
		System.out.println("Consumidor consumiendo " + extraido.tostring());

		this.buffer = null;
		empty.release();
		return extraido;
	}
	
	
}
