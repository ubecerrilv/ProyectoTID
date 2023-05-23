package tid.controlador;

import java.util.ArrayList;

import tid.modelo.Data;
import tid.modelo.ImagenGrises;
import tid.modelo.ImagenRGB;
import tid.modelo.Operaciones;

public class ControlVPrincipal extends ControlAbs {
	Operaciones op;
	ArrayList<Data> imagenes;
	int imgInd;

	@Override
	public Data ejecutaComando(String c, Data d, Data d2) {//EN ESTA CLASE, REALIZAR TRABAJOS DEL MODELO
		op = new Operaciones();
		switch(c) {
		case Comandos.BUSCA:
			imagenes =  new ArrayList<Data>();
			imgInd=0;
			ImagenRGB img0 = (ImagenRGB)d;
			imagenes.add(img0);
			break;
				
		case Comandos.ECUALIZARGB:
			ImagenRGB img = (ImagenRGB) d;
			ImagenGrises res = op.ecualizarImagenRGB(img);
			imagenes.add(res);
			imgInd++;
			return res;
			
		case Comandos.ECUALIZAGRIS:
			ImagenGrises img1 = (ImagenGrises)d;
			ImagenGrises res2 = op.ecualizarImagen(img1);
			imagenes.add(res2);
			imgInd++;
			return res2;
			
		case Comandos.ATRAS:
			if(imgInd>0) {
				imgInd-=1;
				return imagenes.get(imgInd);
			}
			
			
		}
		return null;//REGRESAR UN MODELO
	}

}
