package tid.controlador;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import tid.gui.VentanaG;
import tid.modelo.Data;
import tid.modelo.Imagen;
import tid.modelo.Operaciones;

public class ControlVPrincipal extends ControlAbs {
	Operaciones op;
	ArrayList<Imagen> imagenes;
	int imgInd;
	VentanaG ventGuar;
	Imagen ag, img1, img2, res;
	
	public ControlVPrincipal(VentanaG ventG) {
		this.ventGuar = ventG;
	}
	

	@Override
	public Data ejecutaComando(String c, Data d, Data d2) {//EN ESTA CLASE, REALIZAR TRABAJOS DEL MODELO
		op = new Operaciones();
		switch(c) {
		case Comandos.BUSCA:
			imagenes =  new ArrayList<Imagen>();
			imgInd=0;
			img1 = (Imagen)d;
			imagenes.add(img1);
			break;
				
		case Comandos.ECUALIZARGB:
			img1 = (Imagen) d;
			res = op.ecualizarImagenRGB(img1);
			imagenes.add(res);
			imgInd++;
			return res;
			
		case Comandos.ECUALIZAGRIS:
			img1 = (Imagen)d;
			res = op.ecualizarImagen(img1);
			imagenes.add(res);
			imgInd++;
			return res;
			
		case Comandos.ATRAS:
			if(imgInd>0) {
				imgInd-=1;
				res = imagenes.get(imgInd);
				return res;
			}else {
				return imagenes.get(0);
			}
			
		case Comandos.ADELANTE:
			if(imgInd<imagenes.size()-1) {
				imgInd+=1;
				return imagenes.get(imgInd);
			}else {
				return imagenes.get(imagenes.size()-1);
			}
			
		case Comandos.HISTA:
			res=op.ObtenerHistogramaOriginal(imagenes.get(0));
			return res;		
			
		case Comandos.HISTN:
			img1 = (Imagen)d;
			res = op.ObtenerHistogramaOriginal(img1);
			return res;

		case Comandos.GUARDA:
			ventGuar.setBounds(0, 0, 500, 150);
			ventGuar.setLocationRelativeTo(null);
			ventGuar.getNombreR().setSelectionStart(0);
			ventGuar.getNombreR().setSelectionEnd(ventGuar.getNombreR().getText().length());
			ventGuar.setVisible(true);
			this.ag = (Imagen)d;
			break;
			
		case Comandos.GUARDO:
			String nombre = ventGuar.getName();
			String ruta = ventGuar.getRoute();
			String ext = ventGuar.getExtention();
			
			try {
				File archivo = new File(ruta+"\\"+nombre+"."+ext);
				archivo.createNewFile();
				ImageIO.write(this.ag.getBufImg(), ext, archivo);
				JOptionPane.showMessageDialog(null, "Imagen guardada");
				
			}catch(IOException e1){
				JOptionPane.showMessageDialog(null, "Error al guardar la imagen");
			}//FIN TRY
			ventGuar.setVisible(false);
			
			break;
			
		case Comandos.INVB:
			img1 = (Imagen)d;
			res = op.InversionB(img1);
			this.imagenes.add(res);
			imgInd++;
			return res;
			
		case Comandos.INVF:
			img1 = (Imagen)d;
			res = op.InversionF(img1);
			this.imagenes.add(res);
			imgInd++;
			return res;
			
		case Comandos.ADD:
			img1 = (Imagen)d;
			img2 = (Imagen)d2;
			res = op.sumar(img1, img2);
			this.imagenes.add(res);
			imgInd++;
			return res;
			
		case Comandos.SUS:
			img1 = (Imagen)d;
			img2 = (Imagen)d2;
			res = op.restar(img1, img2);
			this.imagenes.add(res);
			imgInd++;
			return res;
			
		case Comandos.ROTARIZQUIERDA:
			img1 = (Imagen)d;
			res = op.rotIzq(img1);
			imagenes.add(res);
			imgInd++;
			return res;
			
		case Comandos.ROTARDERECHA:
			img1 = (Imagen)d;
			res = op.rotDer(img1);
			imagenes.add(res);
			imgInd++;
			return res;
			
		case Comandos.ESPEJO:
			img1 = (Imagen)d;
			res = op.espejo(img1);
			imagenes.add(res);
			imgInd++;
			return res;
			
		case Comandos.MEDIA:
			img1 = (Imagen)d;
			res =op.media(img1);
			imagenes.add(res);
			imgInd++;
			return res;
			
		case Comandos.MEDIANA:
			img1 = (Imagen)d;
			res =op.mediana(img1);
			imagenes.add(res);
			imgInd++;
			return res;
			
		case Comandos.MODA:
			img1 = (Imagen)d;
			res =op.moda(img1);
			imagenes.add(res);
			imgInd++;
			return res;
			
		case Comandos.GAUSS:
			img1 = (Imagen)d;
			res =op.gaussiano(img1);
			imagenes.add(res);
			imgInd++;
			return res;
			
		case Comandos.N1:
			img1 = (Imagen)d;
			res =op.n1(img1);
			imagenes.add(res);
			imgInd++;
			return res;
			
		case Comandos.N2:
			img1 = (Imagen)d;
			res =op.n2(img1);
			imagenes.add(res);
			imgInd++;
			return res;
			
		case Comandos.N3:
			img1 = (Imagen)d;
			res =op.n3(img1);
			imagenes.add(res);
			imgInd++;
			return res;
			
		case Comandos.N4:
			img1 = (Imagen)d;
			res =op.n4(img1);
			imagenes.add(res);
			imgInd++;
			return res;
			
		case Comandos.N5:
			img1 = (Imagen)d;
			res =op.n5(img1);
			imagenes.add(res);
			imgInd++;
			return res;
			
		case Comandos.N6:
			img1 = (Imagen)d;
			res =op.n6(img1);
			imagenes.add(res);
			imgInd++;
			return res;
			
		case Comandos.N7:
			img1 = (Imagen)d;
			res =op.n7(img1);
			imagenes.add(res);
			imgInd++;
			return res;
			
		case Comandos.N8:
			img1 = (Imagen)d;
			res =op.n8(img1);
			imagenes.add(res);
			imgInd++;
			return res;
			
		case Comandos.N9:
			img1 = (Imagen)d;
			res =op.n9(img1);
			imagenes.add(res);
			imgInd++;
			return res;
			
		case Comandos.LP4:
			img1 = (Imagen)d;
			res =op.laplace4(img1);
			imagenes.add(res);
			imgInd++;
			return res;
			
		case Comandos.LP8:
			img1 = (Imagen)d;
			res =op.laplace8(img1);
			imagenes.add(res);
			imgInd++;
			return res;
			
		case Comandos.PRE:
			img1 = (Imagen)d;
			res =op.prewitt(img1);
			imagenes.add(res);
			imgInd++;
			return res;
			
		case Comandos.SOBEL:
			img1 = (Imagen)d;
			res =op.sobel(img1);
			imagenes.add(res);
			imgInd++;
			return res;
			
		case Comandos.ROBERT:
			img1 = (Imagen)d;
			res =op.roberts(img1);
			imagenes.add(res);
			imgInd++;
			return res;
			
		case Comandos.EROSION://CAMBIAR ESTTO
			img1 = (Imagen)d;
			res =op.erosion(img1, null);
			imagenes.add(res);
			imgInd++;
			return res;
			
		case Comandos.DILATACION://CAMBIAR ESTTO
			img1 = (Imagen)d;
			res =op.dilatacion(img1, null);
			imagenes.add(res);
			imgInd++;
			return res;	
		}//FIN SWITCH
		
		
		return null;//REGRESAR UN MODELO
	}//FIN EJECUTA  COMANDO
	
	public Imagen  collage(ArrayList<Imagen> imagenes, int x, int y) {
		op = new Operaciones();
		Imagen res = op.collage(imagenes, x, y);
		return res;
	}//FIN COLLAGE

}//FIN CLASE
