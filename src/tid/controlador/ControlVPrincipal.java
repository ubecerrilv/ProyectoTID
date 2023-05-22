package tid.controlador;

import tid.modelo.Data;
import tid.modelo.ImagenGrises;
import tid.modelo.ImagenRGB;
import tid.modelo.Operaciones;

public class ControlVPrincipal extends ControlAbs {
	Operaciones op;

	@Override
	public Data ejecutaComando(String c, Data d, Data d2) {//EN ESTA CLASE, REALIZAR TRABAJOS DEL MODELO
		op = new Operaciones();
		switch(c) {
		case Comandos.ECUALIZARGB:
			ImagenRGB img = (ImagenRGB) d;
			return op.ecualizarImagenRGB(img);
			
		case Comandos.ECUALIZAGRIS:
			ImagenGrises img1 = (ImagenGrises)d;
			return op.ecualizarImagen(img1);
			
		}
		return null;//REGRESAR UN MODELO
	}

}
