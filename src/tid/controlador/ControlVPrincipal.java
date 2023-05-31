package tid.controlador;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
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
	Imagen ag;
	
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
			Imagen respuesta = new Imagen();
			respuesta.setMatrizActual(op.ObtenerHistogramaOriginal(imagenes.get(0)));
			return respuesta;		
			
		case Comandos.HISTN:
			Imagen img3 = (Imagen)d;
			Imagen respuesta1 = new Imagen();
			respuesta1.setMatrizActual(op.ObtenerHistogramaOriginal(img3));
			return respuesta1;

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
			Imagen img4 = (Imagen)d;
			Imagen respuesta2 = op.InversionB(img4);
			this.imagenes.add(respuesta2);
			return respuesta2;
			
		case Comandos.INVF:
			Imagen img5 = (Imagen)d;
			Imagen respuesta3 = op.InversionF(img5);
			this.imagenes.add(respuesta3);
			return respuesta3;
			
		case Comandos.ADD:
			Imagen img6 = (Imagen)d;
			Imagen img7 = (Imagen)d2;
			Imagen respuesta4 = op.sumar(img6, img7);
			this.imagenes.add(respuesta4);
			return respuesta4;
			
		case Comandos.SUS:
			Imagen img8 = (Imagen)d;
			Imagen img9 = (Imagen)d2;
			Imagen respuesta5 = op.restar(img8, img9);
			this.imagenes.add(respuesta5);
			return respuesta5;
		}//FIN SWITCH
		
		
		return null;//REGRESAR UN MODELO
	}//FIN EJECUTA  COMANDO
	
	public Imagen  collage(ArrayList<Imagen> imagenes, int x, int y) {
		op = new Operaciones();
		Imagen res = op.collage(imagenes, x, y);
		return res;
	}//FIN COLLAGE

}//FIN CLASE
