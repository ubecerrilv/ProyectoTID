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

import tid.modelo.ImagenGrises;
import tid.modelo.ImagenRGB;

public class Operaciones {
    public int[][] rGB2Gris(ImagenRGB i) {
        ImagenGrises imagenGris = new ImagenGrises();

        return null;
    }

    public ImagenRGB ecualizarImagenRGB(ImagenRGB i){
        Mat src = i.getMatrizActual();
        Imgproc.cvtColor(src, src, Imgproc.COLOR_BGR2GRAY); // Convertir a escala de grises
        Mat dst = new Mat(); // Crear matriz para guardar la imagen ecualizada

        Imgproc.equalizeHist(src, dst); // Ecualizar imagen
        i.setMatrizActual(dst);

        Imgproc.equalizeHist(src, dst); // Ecualizar imagenGris
        i.setMatrizActual(dst);
        return i;
    }

    public ImagenGrises ecualizarImagen(ImagenGrises i){
        Mat src = i.getMatrizActual(); // Cargar imagen
        Mat dst = new Mat(); // Crear matriz para guardar la imagen ecualizada  
        Imgproc.equalizeHist(src, dst); // Ecualizar imagen
        i.setMatrizActual(dst);
        return i;
    }

    public Mat ObtenerHistogramaOriginal(ImagenRGB i){
    	 Mat src = i.getMatrizActual(); // Cargar imagen
        
        List<Mat> planosRGB = new ArrayList<>(); // Crear lista de matrices para guardar los planos de la imagen
        Core.split(src, planosRGB); // Dividir la imagen en los planos RGB
        int histTamano = 256; // Tamanio del histograma  
        float[] rango = {0, 256}; // Rango del histograma
        MatOfFloat histRango = new MatOfFloat(rango); // Crear matriz de rango del histograma
        boolean acumular = false; // Acumular histograma
        Mat histR = new Mat();
        Mat histG = new Mat();
        Mat histB = new Mat(); // Crear matriz para guardar el histograma del plano R, G y B
        // Histograma del plano R, G y B
        Imgproc.calcHist(planosRGB, new MatOfInt(0), new Mat(), histB, new MatOfInt(histTamano), histRango, acumular);
        Imgproc.calcHist(planosRGB, new MatOfInt(1), new Mat(), histG, new MatOfInt(histTamano), histRango, acumular);
        Imgproc.calcHist(planosRGB, new MatOfInt(2), new Mat(), histR, new MatOfInt(histTamano), histRango, acumular);
        // Crear imagen para dibujar el histograma
        int histW = 512; 
        int histH = 400;
        int binW = (int) Math.round((double) histW / histTamano);
        Mat histImagen = new Mat( histH, histW, CvType.CV_8UC3, new Scalar( 0,0,0));

        // Normalizar el histograma entre 0 y histImagen.rows() para que se encuentre en el rango
        Core.normalize(histB, histB, 0, histImagen.rows(), Core.NORM_MINMAX);
        Core.normalize(histG, histG, 0, histImagen.rows(), Core.NORM_MINMAX);
        Core.normalize(histR, histR, 0, histImagen.rows(), Core.NORM_MINMAX);

        //Obtener datos para generar las gr�ficas
        float[] bHistData = new float[(int) (histB.total() * histB.channels())];
        histB.get(0, 0, bHistData);
        float[] gHistData = new float[(int) (histG.total() * histG.channels())];
        histG.get(0, 0, gHistData);
        float[] rHistData = new float[(int) (histR.total() * histR.channels())];
        histR.get(0, 0, rHistData);
        // Graficaci�n de cada punto para el histograma
        for(int j = 1; j < histTamano; j++ ) {
            Imgproc.line(histImagen, new Point(binW * (j - 1), histH - Math.round(bHistData[j - 1])),
                    new Point(binW * (j), histH - Math.round(bHistData[j])), new Scalar(255, 0, 0), 2);
            Imgproc.line(histImagen, new Point(binW * (j - 1), histH - Math.round(gHistData[j - 1])),
                    new Point(binW * (j), histH - Math.round(gHistData[j])), new Scalar(0, 255, 0), 2);
            Imgproc.line(histImagen, new Point(binW * (j - 1), histH - Math.round(rHistData[j - 1])),
                    new Point(binW * (j), histH - Math.round(rHistData[j])), new Scalar(0, 0, 255), 2);
        }
        return histImagen;

    }
    
    public void Inversion(ImagenGrises i) {
    	Mat src = Imgcodecs.imread(i.getRuta());
    	Mat dst = new Mat();
    	Core.bitwise_not( src, dst); // Funcion que invierte los bits.
    	i.setMatrizActual(dst);
    }
    

}
