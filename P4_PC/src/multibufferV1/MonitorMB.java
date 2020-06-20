package multibufferV1;

public class MonitorMB implements Almacen_multiBuffer{
	
	private Producto buffer[] = null;
	private int TAM_BUFFER = 0; // TAM BUFFER
	private int ini = 0; // primera posición  a consumir
	private int fin = 0; //primera posición libre para consumir, además  ## fin = (ini + n_elems) % N
	private int n_elems = 0;
	
	
	public MonitorMB(int n) {
		this.TAM_BUFFER=n;
		this.buffer = new Producto[TAM_BUFFER];
		this.ini=0;
		this.fin=0;
		this.n_elems=0;
	}
	

	@Override
	synchronized public void almacenar(Producto[] productos) {
		
		while(productos.length >  TAM_BUFFER - n_elems ) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for (int i = 0; i < productos.length; i++){
			buffer[fin] = productos[i];
			n_elems+=1;
			fin = (fin+1) % TAM_BUFFER;
			}

		System.out.println("Productor " + productos[0].string_id_productor()
				+ " ha almacenado una cadena de longitud " + productos.length);
		System.out.println("Productos disponibles: " + n_elems);
		notifyAll();
		
	}

	@Override
	synchronized public Producto[] extraer(int n) {
		while(n_elems < n) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Producto []  cadena = new  Producto[n];
		for (int i = 0; i < n; i++) {
			cadena[i] = buffer[ini];
			buffer[ini] = null;
			ini = (ini + 1) % TAM_BUFFER;
			n_elems-=1;
			}
		System.out.println("Se ha extraido una cadena de longitud "+ n);
		System.out.println("Productos disponibles: " + n_elems);
		notifyAll();
		
		return cadena;
	}

}
