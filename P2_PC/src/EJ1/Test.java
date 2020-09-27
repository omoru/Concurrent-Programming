// OSCAR MORUJO FERNANDEZ

package EJ1;

public class Test {
		private static int N = 100;
		
		public static void main(String args[]) {
			
			Auxiliar a = new Auxiliar(N);
			Lock lock_last= new Lock();
			HiloSuma suma = new HiloSuma(a,lock_last);		//P1
			HiloResta resta = new HiloResta(a,lock_last);		//P2
			
			suma.start();
			resta.start();
			
			try {
				suma.join();
				resta.join();
			}catch (InterruptedException e) {
				e.printStackTrace();
			} 
			
			
			System.out.printf("FIN DE LOS HILOS\n");
			System.out.printf("a = " + a.toString());
		}

}

