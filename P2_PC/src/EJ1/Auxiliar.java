package EJ1;

public class Auxiliar {

	volatile private int a;
	public int N;
	volatile public boolean boolsuma;
	volatile public boolean boolresta;
	volatile int last;
	
	public Auxiliar(int  n) {
		N=n;
		a = 0;
		boolsuma=false;
		boolresta=false;
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