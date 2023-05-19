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
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import tid.modelo.ImagenGrises;
import tid.modelo.ImagenRGB;

public class Operaciones {
    public int[][] rGB2Gris(ImagenRGB i) {
        ImagenGrises imagenGris = new ImagenGrises();

        return null;
    }

    public Mat ecualizarImagenRGB(ImagenRGB i){
        Mat src = Imgcodecs.imread(i.getRuta()); // Cargar imagen 
        Imgproc.cvtColor(src, src, Imgproc.COLOR_BGR2GRAY); // Convertir a escala de grises
        Mat dst = new Mat(); // Crear matriz para guardar la imagen ecualizada
        Imgproc.equalizeHist(src, dst); // Ecualizar imagen
        return dst;
    }

    public Mat ecualizarImagen(ImagenGrises i){
        Mat src = Imgcodecs.imread(i.getRuta()); // Cargar imagen
        Mat dst = new Mat(); // Crear matriz para guardar la imagen ecualizada  
        Imgproc.equalizeHist(src, dst); // Ecualizar imagen
        return dst; 
    }

    public Mat ObtenerHistogramaOriginal(ImagenRGB i){
        Mat src = Imgcodecs.imread(i.getRuta()); // Cargar imagen
        
        List<Mat> planosRGB = new ArrayList<>(); // Crear lista de matrices para guardar los planos de la imagen
        Core.split(src, planosRGB); // Dividir la imagen en los planos RGB
        int histTamaño = 256; // Tamaño del histograma  
        float[] rango = {0, 256}; // Rango del histograma
        MatOfFloat histRango = new MatOfFloat(rango); // Crear matriz de rango del histograma
        boolean acumular = false; // Acumular histograma
        Mat histR = new Mat();
        Mat histG = new Mat();
        Mat histB = new Mat(); // Crear matriz para guardar el histograma del plano R, G y B
        // Histograma del plano R, G y B
        Imgproc.calcHist(planosRGB, new MatOfInt(0), new Mat(), histB, new MatOfInt(histTamaño), histRango, acumular);
        Imgproc.calcHist(planosRGB, new MatOfInt(1), new Mat(), histG, new MatOfInt(histTamaño), histRango, acumular);
        Imgproc.calcHist(planosRGB, new MatOfInt(2), new Mat(), histR, new MatOfInt(histTamaño), histRango, acumular);
        // Crear imagen para dibujar el histograma
        int histW = 512; 
        int histH = 400;
        int binW = (int) Math.round((double) histW / histTamaño);
        Mat histImagen = new Mat( histH, histW, CvType.CV_8UC3, new Scalar( 0,0,0));

        // Normalizar el histograma entre 0 y histImagen.rows() para que se encuentre en el rango
        Core.normalize(histB, histB, 0, histImagen.rows(), Core.NORM_MINMAX);
        Core.normalize(histG, histG, 0, histImagen.rows(), Core.NORM_MINMAX);
        Core.normalize(histR, histR, 0, histImagen.rows(), Core.NORM_MINMAX);

        //TODO Dibujar para cada canal
        return null;

    }

}
