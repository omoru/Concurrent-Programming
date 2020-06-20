package EJ2;

public class Test {
	
	private static int N = 301;
	
	public static void main(String args[]) {
		Auxiliar a = new Auxiliar(2*N);
		LockRompeEmpate mtx = new LockRompeEmpate(a);
		Hilo hilos[] = new Hilo[2*N];
		
		for(int i = 0; i < 2*N; i++)
			hilos[i] = new Hilo(a, mtx, i);
		
		for(int i = 0; i < 2*N; i++)
			hilos[i].start();
		
		for(int i = 0; i < 2*N; i++)
			try {
				hilos[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		System.out.printf("FIN DE LOS HILOS\n");
		System.out.printf("a = " + a.toString());
		
	}
}
