package parte2;

// ÓSCAR MORUJO FERNÁNDEZ
public class Test {
	
		private static int N = 10;// NUMERO DE INCREMENTOS/DECREMENTOS
		private static int M = 200; //NUMERO DE PROCESOS
		
		public static void main(String args[]) {
			
			Shared a = new Shared();
			HiloSuma[] sumas= new HiloSuma[M];		//P1
			HiloResta[] restas= new HiloResta[M];;	//P2
			for(int i = 0; i < M; i++) {
				sumas[i]= new HiloSuma(a,N);
				restas[i]= new HiloResta(a,N);
			}
			for(int i=0;i < M;i++) {
				sumas[i].start();
				restas[i].start();
			}
			
			for(int i=0; i < M;i++) {
				try {
					sumas[i].join();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					restas[i].join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			System.out.printf("FIN DE LOS HILOS\n");
			System.out.printf("a = " + a.toString());
		}

}