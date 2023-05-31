package tid.controlador;

import java.util.ArrayList;

import tid.modelo.Data;
import tid.modelo.Imagen;

public interface Control {

	public Data ejecutaComando(String c, Data d, Data d2);
	void setCP(Control c);
	public Imagen collage(ArrayList<Imagen> imagenes, int x, int y);
}
