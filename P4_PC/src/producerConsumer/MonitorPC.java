package producerConsumer;



public class MonitorPC implements Almacen {
	
	private  Producto buffer[] = null;
	private  final int N; // TAM BUFFER
	private  int ini = 0; // primera posición  a consumir
	private  int fin = 0; //primera posición libre para consumir, además  ## fin = (ini + n_elems) % N
	private  int n_elems = 0;
	
	
	public MonitorPC(int n) {
		this.N=n;
		this.buffer = new Producto[N];
		this.ini=0;
		this.fin=0;
		this.n_elems=0;
	}
	
	@Override
	synchronized public void almacenar(Producto producto) {
		while(n_elems == N) {
			try {
				System.out.println("Esperando a almacenar "+ producto.tostring());
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.buffer[fin]= producto;
		
		this.n_elems+=1;
		System.out.println("Almacenando " + producto.tostring() +",hay " + n_elems+" elementos.");

		this.fin = (this.fin + 1) % N;
		notify();
		
	}
	@Override
	synchronized public Producto extraer() {
		while(n_elems == 0) {
			try {
				System.out.println("Esperando para consumir");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Producto p = this.buffer[ini];// copiamos el producto a extraer
		this.buffer[ini]= null;
		this.n_elems-=1;
		System.out.println("Consumiendo "+ p.tostring()+",quedan "+ n_elems+" elementos.");
		this.ini = (this.ini + 1) % N;
		notify();
		return p;
	}


	
	
	

}
