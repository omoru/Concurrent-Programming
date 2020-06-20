package EJ3;

import java.util.concurrent.atomic.AtomicInteger;

public class Auxiliar {
	
	volatile public int a;
	volatile public AtomicInteger number;
	volatile public int next;
	volatile public int turn[];
	public int N;
	
	public Auxiliar(int n) {
		
		number = new AtomicInteger(1);
		a = 0;
		N = n;
		next = 1;
		turn = new int[n];
		for (int i = 0; i < n; i++) {
			turn[i] = 0;
		}
	}
	
	public void increase() {
		a++;
	}
	
	public void decrease() {
		a--;
	}
	
	public String toString() {
		return Integer.toString(a);
	}

}
