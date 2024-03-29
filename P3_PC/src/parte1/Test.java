
// OSCAR MORUJO FERNANDEZ
package parte1;

public class Test {
	
		private static int N = 1000;
		
		public static void main(String args[]) {
			
			Shared a = new Shared();
			HiloSuma[] sumas= new HiloSuma[N];		//P1
			HiloResta[] restas= new HiloResta[N];;	//P2
			for(int i = 0; i < N; i++) {
				sumas[i]= new HiloSuma(a);
				restas[i]= new HiloResta(a);
			}
			for(int i=0;i < N;i++) {
				sumas[i].start();
				restas[i].start();
			}
			
			for(int i=0; i < N;i++) {
				try {
					sumas[i].join();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				try {
					restas[i].join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			System.out.printf("FIN DE LOS HILOS\n");
			System.out.printf("a = " + a.toString());
		}

}