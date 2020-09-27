// OSCAR MORUJO FERNANDEZ
package EJ1;

public class Lock {
	private volatile boolean boolsuma;
	private volatile boolean boolresta;
	private volatile int last;
	
	public Lock() {
		
	}
	public boolean isSubtracting(){
		return this.boolresta;
	}
	
	public boolean isAdding() {
		return this.boolsuma;
	}
	
	public int get_last() {
		return this.last;
	}
	
	public void setBoolSuma(boolean b) {
		this.boolsuma=b;
	}
	
	public void setBoolResta(boolean b) {
		this.boolresta=b;
	}
	
	public void set_last(int last) {
		this.last=last;
	}
}
