// OSCAR MORUJO FERNANDEZ
package incrementAndDecrement;

public class Main {
	
	private static int N = 1201;
	
	public static void main(String args[]) {
		
		Monitor m = new Monitor();
		HiloSuma[] sumas= new HiloSuma[N];		//P1
		HiloResta[] restas= new HiloResta[N];;	//P2
		for(int i = 0; i < N; i++) {
			sumas[i]= new HiloSuma(m);
			restas[i]= new HiloResta(m);
		}
		for(int i=0;i < N;i++) {
			sumas[i].start();
			restas[i].start();
		}
				
	
	}
}
