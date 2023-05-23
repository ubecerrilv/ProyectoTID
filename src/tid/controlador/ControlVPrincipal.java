package tid.controlador;

import java.util.ArrayList;

import tid.modelo.Data;
import tid.modelo.Imagen;
import tid.modelo.Operaciones;

public class ControlVPrincipal extends ControlAbs {
	Operaciones op;
	ArrayList<Imagen> imagenes;
	int imgInd;

	@Override
	public Data ejecutaComando(String c, Data d, Data d2) {//EN ESTA CLASE, REALIZAR TRABAJOS DEL MODELO
		op = new Operaciones();
		switch(c) {
		case Comandos.BUSCA:
			imagenes =  new ArrayList<Imagen>();
			imgInd=0;
			Imagen img0 = (Imagen)d;
			imagenes.add(img0);
			break;
				
		case Comandos.ECUALIZARGB:
			Imagen img = (Imagen) d;
			Imagen res = op.ecualizarImagenRGB(img);
			imagenes.add(res);
			imgInd++;
			return res;
			
		case Comandos.ECUALIZAGRIS:
			Imagen img1 = (Imagen)d;
			Imagen res2 = op.ecualizarImagen(img1);
			imagenes.add(res2);
			imgInd++;
			return res2;
			
		case Comandos.ATRAS:
			if(imgInd>0) {
				imgInd-=1;
				System.out.println(imgInd);
				return imagenes.get(imgInd);
			}else {
				return null;
			}
			
		case Comandos.ADELANTE:
			if(imgInd<imagenes.size()-1) {
				imgInd+=1;
				return imagenes.get(imgInd);
			}else {
				return null;
			}
			
		case Comandos.HISTA:
			Imagen img2 = (Imagen)d;
			op.ObtenerHistogramaOriginal(img2, "original");
			break;
			
		case Comandos.HISTN:
			Imagen img3 = (Imagen)d;
			op.ObtenerHistogramaOriginal(img3, "ecualizado");
			break;
			
		}
		return null;//REGRESAR UN MODELO
	}

}
