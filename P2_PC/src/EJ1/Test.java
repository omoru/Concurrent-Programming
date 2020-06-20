package EJ1;


public class Test {
		private static int N = 10;
		
		public static void main(String args[]) {
			
			Auxiliar a = new Auxiliar(N);
			HiloSuma suma = new HiloSuma(a);		//P1
			HiloResta resta = new HiloResta(a);		//P2
			
			suma.start();
			resta.start();
			
			
			System.out.printf("FIN DE LOS HILOS\n");
			System.out.printf("a = " + a.toString());
		}

}

