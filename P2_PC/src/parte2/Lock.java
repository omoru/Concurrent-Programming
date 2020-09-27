// OSCAR MORUJO FERNANDEZ
package parte2;

abstract public class Lock {
	
	public Lock() {
		super();
	}
	
	abstract void releaseLock(int id);
	abstract void takeLock(int id);
}
