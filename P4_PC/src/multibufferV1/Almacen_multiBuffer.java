// OSCAR MORUJO FERNANDEZ
package multibufferV1;

public interface Almacen_multiBuffer {
	
	public  void  almacenar(Producto []  productos) throws InterruptedException ;
	public  Producto []  extraer(int n) throws InterruptedException ;
	
}
