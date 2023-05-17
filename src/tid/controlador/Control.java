package tid.controlador;

import tid.modelo.Data;

public interface Control {

	public Data ejecutaComando(String c, Data d, Data d2);
	void setCP(Control c);
}
