
// OSCAR MORUJO FERNANDEZ
package multibufferV1;

public class Producto {
	
	private int id_productor;
		
	public Producto(int id_productor) {
		this.id_productor=id_productor;
		}
			
	
	public String tostring() {
		String[] cadena = this.toString().split("@");
		return "producto " + cadena[1];	
	}
	
	public String string_id_productor() {
			return Integer.toString(this.id_productor);
	}

	
}

