package tid.modelo;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;

// Clase que contiene la imagen RGB 
public class Imagen implements Data {
    private int[][] matrizR;
    private int[][] matrizG;
    private int[][] matrizB;
    String ruta;
    
    private Mat matrizActual;
    private ImageIcon imagenActual;
	private BufferedImage bufImg;
    
    public Imagen(String ruta) {
    	if(ruta != null && ruta.compareTo("")!=0) {
    		this.ruta = ruta;
    		this.matrizActual = Imgcodecs.imread(ruta);
    		this.bufImg  = (BufferedImage) HighGui.toBufferedImage(this.matrizActual);
    	}
    }
    
    public ImageIcon convertirMatAImg() {
    	this.bufImg  = (BufferedImage) HighGui.toBufferedImage(this.matrizActual);
    	this.imagenActual =new ImageIcon(bufImg.getScaledInstance(379, 439, 0));
    	return imagenActual;
    	
    }
    	
    public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public Imagen() {
        super();
    }

    public int[][] getMatrizR() {
        return matrizR;
    }

    public void setMatrizR(int[][] matrizR) {
        this.matrizR = matrizR;
    }

    public int[][] getMatrizG() {
        return matrizG;
    }

    public void setMatrizG(int[][] matrizG) {
        this.matrizG = matrizG;
    }

    public int[][] getMatrizB() {
        return matrizB;
    }

    public void setMatrizB(int[][] matrizB) {
        this.matrizB = matrizB;
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

	public BufferedImage getBufImg() {
		return bufImg;
	}

	public void setBufImg(BufferedImage bufImg) {
		this.bufImg = bufImg;
	}

}
