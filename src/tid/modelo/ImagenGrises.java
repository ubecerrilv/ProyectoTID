package tid.modelo;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;

import javax.swing.ImageIcon;

import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;

public class ImagenGrises implements Data {

    private int[][] matrizIntensidades;
    private String ruta;
    private Mat matrizActual;
    private ImageIcon imagenActual;
    
    public ImagenGrises(String ruta) {
    	this.matrizActual = Imgcodecs.imread(ruta);
    }
    
    public ImageIcon convertirMatAImg(Mat m) {
    	Image img = HighGui.toBufferedImage(m);
    	this.imagenActual = new ImageIcon(img);
    	return imagenActual;
    	
    }
    public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public ImagenGrises() {
        super();
    }

    public int[][] getMatrizIntensidades() {
        return matrizIntensidades;
    }

    public void setMatrizIntensidades(int[][] matrizIntensidades) {
        this.matrizIntensidades = matrizIntensidades;
    }
    
    
    public Mat getMatrizActual() {
		return matrizActual;
	}

	public void setMatrizActual(Mat matrizActual) {
		this.matrizActual = matrizActual;
	}

	public ImageIcon getImagenActual() {
		return imagenActual;
	}

	public void setImagenActual(ImageIcon imagenActual) {
		this.imagenActual = imagenActual;
	}
    
}
