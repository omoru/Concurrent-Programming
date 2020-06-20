package EJ2;

public class Auxiliar {

	volatile private int a;
	public int N;
	volatile public int in[];
	volatile public int last[];
	
	
	public Auxiliar(int  n) {
		N=n;
		a = 0;
		in = new int[N];
		last = new int[N];
		for (int i = 0; i < N; i++){
			in[i] = -1;
			last[i] = -1;
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
