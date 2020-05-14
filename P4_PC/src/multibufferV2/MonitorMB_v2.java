package multibufferV2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import multibufferV1.Almacen_multiBuffer;
import multibufferV1.Producto;

public class MonitorMB_v2 implements Almacen_multiBuffer{
	
		private Producto buffer[] = null;
		private int TAM_BUFFER = 0; // TAM BUFFER
		private int ini = 0; // primera posición  a consumir
		private int fin = 0; //primera posición libre para consumir, además  ## fin = (ini + n_elems) % N
		private int n_elems = 0;
		final Lock lock = new ReentrantLock();
		final Condition okConsume = lock.newCondition();
		final Condition okProduce = lock.newCondition();
		
		public MonitorMB_v2(int n) {
			this.TAM_BUFFER=n;
			this.buffer = new Producto[TAM_BUFFER];
		}
		

		@Override
		public void almacenar(Producto[] productos) throws InterruptedException {
			lock.lock();
			try {
				while(productos.length >  TAM_BUFFER - n_elems )
					okProduce.await();	
			
				for (int i = 0; i < productos.length; i++){
					buffer[ini] = productos[i];
					n_elems+=1;
					ini = (ini+1) % TAM_BUFFER;
					}

				System.out.println("Productor " + productos[0].string_id_productor()
						+ " ha almacenado una cadena de longitud " + productos.length);
				System.out.println("Productos disponibles: " + n_elems);
				okConsume.signalAll();
				
				}finally {
					lock.unlock();
				}
		}
	
			
			
			
		

		@Override
		public Producto[] extraer(int n) throws InterruptedException  {
			Producto []  cadena = new  Producto[n];
			lock.lock();
			try {
				while(n_elems <  n )
					okConsume.await();	
			
				for (int i = 0; i < n; i++) {
					cadena[i] = buffer[fin];
					buffer[fin] = null;
					fin = (fin + 1) % TAM_BUFFER;
					n_elems-=1;
					}
				System.out.println("Se ha extraido una cadena de longitud "+ n);
				System.out.println("Productos disponibles: " + n_elems);
				okProduce.signalAll();
				}finally {
					
					lock.unlock();
				}
			return cadena;
		}

	}
			
			

	


