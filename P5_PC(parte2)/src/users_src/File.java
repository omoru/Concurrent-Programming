package users_src;

import java.io.Serializable;
import java.util.ArrayList;

public class File implements Serializable {
	private String filename;
	private String ruta;
	
	public File(String filename,String ruta) {
		this.filename=filename;
		this.ruta=ruta;
	}
	
	public String getFilename() {
		return filename;
	}
	
	public String getRuta() {
		return ruta;
	}

}
