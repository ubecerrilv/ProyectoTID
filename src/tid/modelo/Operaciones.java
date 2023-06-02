package tid.modelo;

import java.util.ArrayList;
import java.util.List;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.core.CvType;
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

        //Obtener datos para generar las grï¿½ficas
        float[] bHistData = new float[(int) (hist.total() * hist.channels())];
        hist.get(0, 0, bHistData);
        // Graficaciï¿½n de cada punto para el histograma
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
    
    public Imagen sumar(Imagen i, Imagen i2) {//SUMAR DOS IMAGENES, CONSIDERAR QUE PUEDEN SER DE DIFERENTE TAMAÃ‘O
    	Mat src1 = i.getMatrizActual();
    	Mat src2 = i2.getMatrizActual();
    	Mat dst = new Mat();
    	int sizeSrc1= src1.height()*src1.width();
    	int sizeSrc2= src2.height()*src2.width();
    	if(sizeSrc1 > sizeSrc2) {
    		Imgproc.resize(src2, src2, src1.size(), 0, 0, Imgproc.INTER_LINEAR);
    	}else {
    		Imgproc.resize(src1, src1, src2.size(), 0, 0, Imgproc.INTER_LINEAR);
    	}
    	Core.addWeighted(src1, 0.3, src2, 0.7, 0.0, dst);
    	Imagen i3 = new Imagen(i.getRuta());
    	i3.setMatrizActual(dst);
    	return i3;
    }
    
    public Imagen restar(Imagen i, Imagen i2) {//RESTAR DOS IMAGENES, CONSIDERAR QUE PUEDEN SER DE DIFERENTE TAMAÃ‘O
    	Mat src1 = i.getMatrizActual();
    	Mat src2 = i2.getMatrizActual();
    	Mat dst = new Mat();
    	int sizeSrc1= src1.height()*src1.width();
    	int sizeSrc2= src2.height()*src2.width();
    	if(sizeSrc1 > sizeSrc2) {
    		Imgproc.resize(src2, src2, src1.size(), 0, 0, Imgproc.INTER_LINEAR);
    	}else {
    		Imgproc.resize(src1, src1, src2.size(), 0, 0, Imgproc.INTER_LINEAR);
    	}
    	//TODO arreglar que sale solo un fondo negro
    	Core.absdiff(src1, src2, dst);
    	Imagen i3 = new Imagen(i.getRuta());
    	i3.setMatrizActual(dst);
    	return i3;
    }
    public Imagen collage(ArrayList<Imagen> imagenes, int x, int y) {//SE DA UN ARRGEL0 DE IMAGENES, REGRESA UNA IMAGEN DE LAS DIMENSIONES X x Y
    	return null;
    }
    public Imagen rotIzq(Imagen i) {//ROTAR 90 A LA IZQUIERDA
    	Mat src = i.getMatrizActual();
    	Mat dst = new Mat();
    	Core.rotate(src, dst, Core.ROTATE_90_COUNTERCLOCKWISE);
    	Imagen i2 = new Imagen(i.getRuta());
    	i2.setMatrizActual(dst);
    	return i2;
    }
    public Imagen rotDer(Imagen i) {//ROTAR 90 A LA DERECHA
    	Mat src = i.getMatrizActual();
    	Mat dst = new Mat();
    	Core.rotate(src, dst, Core.ROTATE_90_CLOCKWISE);
    	Imagen i2 = new Imagen(i.getRuta());
    	i2.setMatrizActual(dst);
    	return i2;
    	
    }
    public Imagen espejo(Imagen i) {
    	Mat src = i.getMatrizActual();
    	Mat dst = new Mat(src.width(), src.height(), src.depth());
    	int width = src.cols()/2;
    	int height = src.rows()/2;
    	Size sz = new Size(width,height);
    	// TODO falta hacer el espejo, este es un metodo para generar un "collage"
    	for(int o = 0; o<2; o++) {
    		for(int j = 0; j<2; j++) {
    			Imgproc.resize(src, src, sz);
    			src.copyTo(dst.submat(new Rect(j * width, o * height, width, height)));
    		}
    	}
    	return null;
    }
    
    //OPERACIONES PARA FILTROS
    public Imagen media(Imagen i) {
    	Mat src = i.getMatrizActual();
    	Mat dst = new Mat();
    	Imgproc.blur(src, dst, new Size(3, 3));
    	Imagen i2 = new Imagen(i.getRuta());
    	i2.setMatrizActual(dst);
    	return i2;
    }
    
    public Imagen mediana(Imagen i) {
    	Mat src = i.getMatrizActual();
    	Mat dst = new Mat();
    	Imgproc.medianBlur(src, dst, 3);
    	Imagen i2 = new Imagen(i.getRuta());
    	i2.setMatrizActual(dst);
    	return i2;
    }
    
    public Imagen moda(Imagen i) {
    	return null;
    }
    
    public Imagen gaussiano(Imagen i) {
    	Mat src = i.getMatrizActual();
    	Mat dst = new Mat();
    	Imgproc.GaussianBlur(src, dst, new Size(5, 5), 0);
    	Imagen i2 = new Imagen(i.getRuta());
    	i2.setMatrizActual(dst);
    	return i2;
    }
    
    public Imagen laplace4(Imagen i) {
    	Mat src = i.getMatrizActual();
    	Mat dst = new Mat();
    	//TODO crear imagen grises porque laplaciano trabaja solo con grises
    	Imgproc.Laplacian(src, dst, CvType.CV_16S, 1);
    	return i;
    }
    
    public Imagen laplace8(Imagen i) {
    	return null;
    }
    
    public Imagen n1(Imagen i) {
    	return null;
    }
    
    public Imagen n2(Imagen i) {
    	return null;
    }
    
    public Imagen n3(Imagen i) {
    	return null;
    }
    
    public Imagen n4(Imagen i) {
    	return null;
    }
    
    public Imagen n5(Imagen i) {
    	return null;
    }
    
    public Imagen n6(Imagen i) {
    	return null;
    }
    
    public Imagen n7(Imagen i) {
    	return null;
    }
    
    public Imagen n8(Imagen i) {
    	return null;
    }
    
    public Imagen n9(Imagen i) {
    	return null;
    }
    
    public Imagen prewitt(Imagen i) {
    	return null;
    }
    
    public Imagen sobel(Imagen i) {
    	return null;
    }
    
    public Imagen roberts(Imagen i) {
    	return null;
    }
    
    //OPERACIONES DE MORFOLOGÃ�A
    public Imagen erosion(Imagen i, int[] estruc) {
    	return null;
    }
    public Imagen dilatacion(Imagen i, int[] estruc) {
    	return null;
    }
    

}
