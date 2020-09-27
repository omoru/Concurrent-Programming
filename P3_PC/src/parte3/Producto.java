// OSCAR MORUJO FERNANDEZ
package parte3;


public class Producto {
	
	
	public Producto() {
		
	}
	
	public String tostring() {
		String[] cadena = this.toString().split("@");
		return "producto " + cadena[1];
		
	}

}
