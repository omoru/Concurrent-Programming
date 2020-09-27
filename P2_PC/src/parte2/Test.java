// OSCAR MORUJO FERNANDEZ
package parte2;

public class Test {
	private static int N = 30;// NUMERO DE INCREMENTOS/DECREMENTOS
	private static int M =100; //NUMERO DE PROCESOS
	
	public static void main(String args[]) {
		
		Auxiliar a = new Auxiliar();
		Thread[] hilos = new Thread[2*M];
		Lock lock;
		
		//lock = new LockRompeEmpate(2*M);
		lock= new LockTicket(2*M);
		//lock = new LockBakery(2*M);
		for(int i=0; i < 2*M;i+=2) {
			hilos[i]= new HiloResta(N,a,i,lock);
			hilos[i+1] =new HiloSuma(N,a,i+1,lock);
		}
		
		for(int i =0; i < 2*M;i++) {
			hilos[i].start();
		}
		
		for(int i =0; i < 2*M;i++) {
			try {
				hilos[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.printf("FIN DE LOS HILOS\n");
		System.out.printf("a = " + a.toString());
	}

}
