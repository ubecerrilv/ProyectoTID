package tid.modelo;

import java.awt.Color;
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
    private Mat hist;
    
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
    
    public ImageIcon convertirMataImgHist() {
    	this.bufImg  = (BufferedImage) HighGui.toBufferedImage(this.matrizActual);
    	// Obtener el ancho y alto de la imagen
        int ancho = this.bufImg.getWidth();
        int alto =this.bufImg.getHeight();

        // Procesar los píxeles de la imagen
        for (int y = 0; y < alto; y++) {
            for (int x = 0; x < ancho; x++) {
                // Obtener el color del píxel en la posición (x, y)
                Color color = new Color(this.bufImg.getRGB(x, y));

                // Verificar si el color es negro
                if (color.equals(Color.BLACK)) {
                    // Cambiar el color a blanco
                	this.bufImg.setRGB(x, y, Color.WHITE.getRGB());
                }

                // Verificar si el color es azul
                if (color.equals(Color.BLUE)) {
                    // Cambiar el color a negro
                	this.bufImg.setRGB(x, y, Color.BLACK.getRGB());
                }
            }
        }//FIN FOR
        this.imagenActual =new ImageIcon(bufImg.getScaledInstance(379, 439, 0));
    	return imagenActual;
    }//FIN CONVERTIR PARA HISTOGRAMA
    	
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

	public Mat getHist() {
		return hist;
	}

	public void setHist(Mat hist) {
		this.hist = hist;
	}
	
	

}
