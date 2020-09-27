// OSCAR MORUJO FERNANDEZ
package parte2;
import java.util.concurrent.*; 

public class Shared implements Almacen {
	private Producto buffer = null;
	private Semaphore full = new Semaphore(0);
	private Semaphore empty = new Semaphore(1);	
	private Semaphore e = new Semaphore(1);
	private int np;
	private int nc;
	private int dp;
	private int dc;
	
	public Shared() {
		this.np=0;
		this.nc=0;
		this.dp=0;
		this.dc =0;
	}

	
	@Override
	public void almacenar(Producto producto) {
		try {
			e.acquire();
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
