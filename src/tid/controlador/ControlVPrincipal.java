package tid.controlador;

import tid.modelo.Data;

public class ControlVPrincipal extends ControlAbs {

	@Override
	public Data ejecutaComando(String c, Data d, Data d2) {//EN ESTA CLASE, REALIZAR TRABAJOS DEL MODELO
		switch(c) {
		case Comandos.ECUALIZAR:
			break;
		}
		return null;//REGRESAR UN MODELO
	}

}
