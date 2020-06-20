package parte1;

import java.util.Random;

public class Main {
	
	private static int N = 100;
	//private static int delay = 1000;

	public static void main(String args[]) {
		
		MyThread[] threads = new MyThread[N];
		for(int i =0; i < N;i++) {
			Random rand = new Random();
			int delay = rand.nextInt(1000) + 1;
			threads[i] = new MyThread(i,delay);
		}
		
		for(int i = 0;i < N; i++) {
			threads[i].start();
		}
		
		for(int i = 0;i < N; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("FIN DE LOS HILOS");
	}
}
