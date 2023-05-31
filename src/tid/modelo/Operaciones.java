package tid.modelo;

import java.util.ArrayList;
import java.util.List;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Operaciones {

    public Imagen ecualizarImagenRGB(Imagen i){
        Mat src = i.getMatrizActual();
        Imgproc.cvtColor(src, src, Imgproc.COLOR_BGR2GRAY); // Convertir a escala de grises
        Mat dst = new Mat(); // Crear matriz para guardar la imagen ecualizada

        Imgproc.equalizeHist(src, dst); // Ecualizar imagenGris
        Imagen i2 = new Imagen(i.getRuta());
        i2.setMatrizActual(dst);
  
        return i2;
    }

    public Imagen ecualizarImagen(Imagen i){
        Mat src = i.getMatrizActual(); // Cargar imagen
        Mat dst = new Mat(); // Crear matriz para guardar la imagen ecualizada  
        Imgproc.equalizeHist(src, dst); // Ecualizar imagen
        i.setMatrizActual(dst);
        return i;
    }

    public Mat ObtenerHistogramaOriginal(Imagen i){
    	 Mat src = i.getMatrizActual(); // Cargar imagen
        
        List<Mat> plano = new ArrayList<>(); // Crear lista de matrices para guardar los planos de la imagen
        Core.split(src, plano);
        int histTamano = 256; // Tamanio del histograma  
        float[] rango = {0, 256}; // Rango del histograma
        MatOfFloat histRango = new MatOfFloat(rango); // Crear matriz de rango del histograma
        boolean acumular = false; // Acumular histograma
        Mat hist = new Mat();
        Imgproc.calcHist(plano, new MatOfInt(0), new Mat(), hist, new MatOfInt(histTamano), histRango, acumular);
        // Crear imagen para dibujar el histograma
        int histW = 512; 
        int histH = 400;
        int binW = (int) Math.round((double) histW / histTamano);
        Mat histImagen = new Mat( histH, histW, CvType.CV_8UC3, new Scalar( 0,0,0));

        // Normalizar el histograma entre 0 y histImagen.rows() para que se encuentre en el rango
        Core.normalize(hist, hist, 0, histImagen.rows(), Core.NORM_MINMAX);

        //Obtener datos para generar las gr�ficas
        float[] bHistData = new float[(int) (hist.total() * hist.channels())];
        hist.get(0, 0, bHistData);
        // Graficaci�n de cada punto para el histograma
        for(int j = 1; j < histTamano; j++ ) {
            Imgproc.line(histImagen, new Point(binW * (j - 1), histH - Math.round(bHistData[j - 1])),
                    new Point(binW * (j), histH - Math.round(bHistData[j])), new Scalar(255, 0, 0), 2);
        }
        return histImagen;

    }
    
    public Imagen InversionB(Imagen i) {//INVERSION BINARIA
    	Mat src = i.getMatrizActual();
    	Mat dst = new Mat();
    	Core.bitwise_not( src, dst); // Funcion que invierte los bits.
    	i.setMatrizActual(dst);
    	return null;
    }
    
    public Imagen InversionF(Imagen i) {//INVERSION FOTOGRAFICA (ESCALA DE GRISES)
    	return null;
    }
    
    public Imagen sumar(Imagen i, Imagen i2) {//SUMAR DOS IMAGENES, CONSIDERAR QUE PUEDEN SER DE DIFERENTE TAMAÑO
    	Mat src1 = i.getMatrizActual();
    	Mat src2 = i2.getMatrizActual();
    	Mat dst = new Mat();
    	Core.add(src1, src2, dst); // Falta hacer las imagenes del mismo tamano para evitar errores.
    	return null;
    }
    
    public Imagen restar(Imagen i, Imagen i2) {//RESTAR DOS IMAGENES, CONSIDERAR QUE PUEDEN SER DE DIFERENTE TAMAÑO
    	return null;
    }
    public Imagen collage(ArrayList<Imagen> imagenes, int x, int y) {//SE DA UN ARRGEL0 DE IMAGENES, REGRESA UNA IMAGEN DE LAS DIMENSIONES X x Y
    	return null;
    }
    public Imagen rotIzq(Imagen i) {//ROTAR 90 A LA IZQUIERDA
    	Mat src = i.getMatrizActual();
    	Mat dst = new Mat();
    	Core.rotate(src, dst, Core.ROTATE_90_COUNTERCLOCKWISE);
    	i.setMatrizActual(dst);
    	return i;
    }
    public Imagen rotDer(Imagen i) {//ROTAR 90 A LA DERECHA
    	Mat src = i.getMatrizActual();
    	Mat dst = new Mat();
    	Core.rotate(src, dst, Core.ROTATE_90_CLOCKWISE);
    	i.setMatrizActual(dst);
    	return i;
    }
    

}
