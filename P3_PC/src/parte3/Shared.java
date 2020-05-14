// ÓSCAR MORUJO FERNÁNDEZ
package parte3;

import java.util.concurrent.Semaphore;


public class Shared implements Almacen {
	private Producto buffer[];
	private int N; // TAM BUFFER
	private int ini;
	private int fin;
	private Semaphore full;
	private Semaphore empty;
	private Semaphore semP;
	private Semaphore semC;

	
	public Shared(int tam_buf) {
		this.N = tam_buf;
		this.ini=0;
		this.fin=0;
		this.buffer= new Producto[N];
		this.full = new Semaphore(0);
		this.empty= new Semaphore(N);
		this.semC = new Semaphore(1);
		this.semP= new Semaphore(1);
	}

	
	@Override
	public void almacenar(Producto producto) {
		try {
			empty.acquire();
			semP.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Productor almacenando "+ producto.tostring()+" en posicion "+ fin);
		this.buffer[fin] = producto;
		fin =(fin + 1)% N;
		
		semP.release();
		full.release();
	}

	@Override
	public Producto extraer() {
		try {
			full.acquire();
			semC.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Producto extraido= this.buffer[ini];
		System.out.println("Consumidor extrayendo " + extraido.tostring()+" de la posición " + ini);
		this.buffer[ini] = null;
		ini = (ini + 1)% N;
		
		semC.release();
		empty.release();
		return extraido;
	}
}
