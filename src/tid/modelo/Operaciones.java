package tid.modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfPoint;
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

    public Imagen ObtenerHistogramaOriginal(Imagen i){
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
        Imagen in = new Imagen(i.getRuta());
        in.setMatrizActual(histImagen);
        
       
        return in;

    }
    
    public Imagen InversionB(Imagen i) {//INVERSION BINARIA
    	Mat src = i.getMatrizActual();
    	Mat dst = new Mat();
    	Core.bitwise_not( src, dst); // Funcion que invierte los bits.
    	Imagen i2 = new Imagen(i.getRuta());
    	i2.setMatrizActual(dst);
    	return i2;
    }
    //TODO adaptar codigo pues InversionB e InversionF hacen lo mismo
    public Imagen InversionF(Imagen i) {//INVERSION FOTOGRAFICA (ESCALA DE GRISES)
    	
    	Mat src = i.getMatrizActual();
    	Mat dst = new Mat();
    	Core.bitwise_not( src, dst); // Funcion que invierte los bits.
    	Imagen i2 = new Imagen(i.getRuta());
    	i2.setMatrizActual(dst);
    	return i2;
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
    	Core.absdiff(src1, src2, dst);
    	Imagen i3 = new Imagen(i.getRuta());
    	i3.setMatrizActual(dst);
    	return i3;
    }
    public Imagen collage(ArrayList<Imagen> imagenes, int x, int y) {//SE DA UN ARRGEL0 DE IMAGENES, REGRESA UNA IMAGEN DE LAS DIMENSIONES X x Y
    	Mat src;
    	int cols = x/imagenes.size();
    	int rows = y;
    	ArrayList<String> rdmCoord = new ArrayList<String>();
    	int rdm1, rdm2, aux;
    	Size sz;
    	src = imagenes.get(0).getMatrizActual();
    	Mat dst = new Mat(y, x, src.type());
    	if(imagenes.size()<=3) {
    		rows = y;
    		cols = x/imagenes.size();
    		sz = new Size(cols,rows);
    		rdm1 = (int)(Math.random() * 1);
    		rdm2 = (int)(Math.random() * imagenes.size());
    		aux = 0;
    		while(aux<imagenes.size()) {
        		if(!rdmCoord.contains(rdm1+", "+rdm2)) {
        			src = imagenes.get(aux).getMatrizActual();
            		Imgproc.resize(src, src, sz);
            		src.copyTo(dst.submat(rdm1 * rows, (rdm1 + 1) * rows, rdm2 * cols, (rdm2 + 1) * cols));
            		aux++;
            		rdmCoord.add(rdm1+", "+rdm2);
        		}
        		rdm1 = (int)(Math.random() * 1);
        		rdm2 = (int)(Math.random() * imagenes.size());
        		
    		}
    	}
    	if(imagenes.size()>3 && imagenes.size()<=6) {
    		rows = y/2;
    		cols = x/3;
    		sz = new Size(cols,rows);
    		rdm1 = (int)(Math.random() * 2);
    		rdm2 = (int)(Math.random() * 3);
    		aux = 0;
    		while(aux<imagenes.size()) {
        		if(!rdmCoord.contains(rdm1+", "+rdm2)) {
        			src = imagenes.get(aux).getMatrizActual();
            		Imgproc.resize(src, src, sz);
            		src.copyTo(dst.submat(rdm1 * rows, (rdm1 + 1) * rows, rdm2 * cols, (rdm2 + 1) * cols));
            		aux++;
            		rdmCoord.add(rdm1+", "+rdm2);
        		}
        		rdm1 = (int)(Math.random() * 2);
        		rdm2 = (int)(Math.random() * 3);
        		
    		}
    	}
    	if(imagenes.size()>6) {
    		rows = y/3;
    		cols = x/3;
    		sz = new Size(cols,rows);
    		rdm1 = (int)(Math.random() * 3);
    		rdm2 = (int)(Math.random() * 3);
    		rdmCoord.add(rdm1+", "+rdm2);
    		aux = 0;
    		while(aux<imagenes.size()) {
        		if(!rdmCoord.contains(rdm1+", "+rdm2)) {
        			src = imagenes.get(aux).getMatrizActual();
            		Imgproc.resize(src, src, sz);
            		src.copyTo(dst.submat(rdm1 * rows, (rdm1 + 1) * rows, rdm2 * cols, (rdm2 + 1) * cols));
            		aux++;
            		rdmCoord.add(rdm1+", "+rdm2);
        		}
        		rdm1 = (int)(Math.random() * 3);
        		rdm2 = (int)(Math.random() * 3);
        		
    		}
    	}
   
    	Imagen i2 = new Imagen();
    	i2.setMatrizActual(dst);
    	return i2;
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
    	Mat dst = new Mat(src.rows(), src.cols(), src.type());
    	int cols = src.cols()/2;
    	int rows = src.rows()/2;
    	Mat aux = new Mat(rows, cols, src.depth());
    	Size sz = new Size(cols,rows);
    	Imgproc.resize(src, src, sz);
    	src.copyTo(dst.submat(rows, rows * 2, 0, cols));
    	Core.flip(src, aux, 1);
    	Imgproc.resize(aux, aux, sz);
    	aux.copyTo(dst.submat(rows, rows * 2 ,cols, cols * 2));
    	Core.flip(aux, aux, 0);
    	aux.copyTo(dst.submat(0, rows, cols, cols * 2));
    	Core.flip(aux, aux, 1);
    	aux.copyTo(dst.submat(0, rows, 0, cols));
    	Imagen i2 = new Imagen(i.getRuta());
    	i2.setMatrizActual(dst);
    	return i2;
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
    	Mat src = i.getMatrizActual();
    	Mat srcGris = new Mat();
    	Imgproc.cvtColor(src, srcGris, Imgproc.COLOR_BGR2GRAY);
    	Mat dst = new Mat(srcGris.rows(), srcGris.cols(), srcGris.type());
    	for (int o = 1; o < srcGris.rows() - 1; o++) {
            for (int j = 1; j < srcGris.cols() - 1; j++) {
                Mat subMatrix = srcGris.submat(o - 1, o + 2, j - 1, j + 2);
                int modeValue = getValorModa(subMatrix);
                dst.put(o, j, modeValue);
            }
        }

    	Imagen i2 = new Imagen(i.getRuta());
    	i2.setMatrizActual(dst);
    	return i2;
    }
    
    private static int getValorModa(Mat subMatriz) {
        HashMap<Integer, Integer> mapaFrecuencias = new HashMap<>();
        for (int i = 0; i < subMatriz.rows(); i++) {
            for (int j = 0; j < subMatriz.cols(); j++) {
                int value = (int) subMatriz.get(i, j)[0];
                if (mapaFrecuencias.containsKey(value)) {
                    mapaFrecuencias.put(value, mapaFrecuencias.get(value) + 1);
                } else {
                    mapaFrecuencias.put(value, 1);
                }
            }
        }
        int valorModa = 0;
        int maxFrecuencia = 0;
        for (Map.Entry<Integer, Integer> entry : mapaFrecuencias.entrySet()) {
            int valor = entry.getKey();
            int frecuencia = entry.getValue();
            if (frecuencia > maxFrecuencia) {
                maxFrecuencia = frecuencia;
                valorModa = valor;
            }
        }
        return valorModa;
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
    	float kdata[] = {-1,-1,-1,-1,9,-1,-1,-1,-1};
    	Imgproc.cvtColor(src, src, Imgproc.COLOR_BGR2GRAY);
    	Imgproc.Laplacian(src, dst,-1, 1);
    	Imagen i2 = new Imagen(i.getRuta());
    	i2.setMatrizActual(dst);
    	return i2;
    }
    // filtro con 8 vecinos terminado, falta comprobar resultados
    public Imagen laplace8(Imagen i) {
    	Mat src = i.getMatrizActual();
    	Mat dst = new Mat();
    	float kdata[] = {-1,-1,-1,-1,9,-1,-1,-1,-1};
    	Mat kernel = new Mat(3,3,CvType.CV_32F);
    	kernel.put( 0, 0, kdata);
    	Imgproc.cvtColor(src, src, Imgproc.COLOR_BGR2GRAY);
    	Imgproc.filter2D(src, dst, -1, kernel);
    	Imagen i2 = new Imagen(i.getRuta());
    	i2.setMatrizActual(dst);
    	return i2;
    }
    public Mat checarGris(Mat src) {
    	Mat srcGris = new Mat();
    	if(src.channels()!=1) {
    		Imgproc.cvtColor(src, srcGris, Imgproc.COLOR_BGR2GRAY);
    	}else {
    		srcGris = src;
    	}
    	return srcGris;
    }
    // TODO agregar condicion por si la imagen ya es gris, no convertirla y evitar errores.
    public Imagen n1(Imagen i) {
    	Mat src = i.getMatrizActual();
    	Mat srcGris = checarGris(src);

        // Crear una nueva matriz para almacenar la imagen filtrada
        Mat dst = new Mat(srcGris.rows(), srcGris.cols(), srcGris.type());

        // Definir el orden del filtro
        int n = 1;

        // Aplicar el filtro de orden n
        for (int o = 1; o < srcGris.rows() - 1; o++) {
            for (int j = 1; j < srcGris.cols() - 1; j++) {
                Mat subMatrix = srcGris.submat(o - 1, o + 2, j - 1, j + 2);
                int nthOrderValue = getNValor(subMatrix, n);
                dst.put(o, j, nthOrderValue);
            }
        }
        Imagen i2 = new Imagen(i.getRuta());
    	i2.setMatrizActual(dst);
    	return i2;
    }

private static int getNValor(Mat subMatriz, int n) {
    int[] valores = new int[9];
    int idx = 0;
    for (int i = 0; i < subMatriz.rows(); i++) {
        for (int j = 0; j < subMatriz.cols(); j++) {
            valores[idx++] = (int) subMatriz.get(i, j)[0];
        }
    }
    Arrays.sort(valores);
    return valores[n-1];
}

    
    public Imagen n2(Imagen i) {
    	Mat src = i.getMatrizActual();
    	Mat srcGris = checarGris(src);
        // Crear una nueva matriz para almacenar la imagen filtrada
        Mat dst = new Mat(srcGris.rows(), srcGris.cols(), srcGris.type());

        // Definir el orden del filtro
        int n = 2;

        // Aplicar el filtro de orden n
        for (int o = 1; o < srcGris.rows() - 1; o++) {
            for (int j = 1; j < srcGris.cols() - 1; j++) {
                Mat subMatrix = srcGris.submat(o - 1, o + 2, j - 1, j + 2);
                int nthOrderValue = getNValor(subMatrix, n);
                dst.put(o, j, nthOrderValue);
            }
        }
        Imagen i2 = new Imagen(i.getRuta());
    	i2.setMatrizActual(dst);
    	return i2;
    }
    
    public Imagen n3(Imagen i) {
    	Mat src = i.getMatrizActual();
    	Mat srcGris = checarGris(src);

        // Crear una nueva matriz para almacenar la imagen filtrada
        Mat dst = new Mat(srcGris.rows(), srcGris.cols(), srcGris.type());

        // Definir el orden del filtro
        int n = 3;

        // Aplicar el filtro de orden n
        for (int o = 1; o < srcGris.rows() - 1; o++) {
            for (int j = 1; j < srcGris.cols() - 1; j++) {
                Mat subMatrix = srcGris.submat(o - 1, o + 2, j - 1, j + 2);
                int nthOrderValue = getNValor(subMatrix, n);
                dst.put(o, j, nthOrderValue);
            }
        }
        Imagen i2 = new Imagen(i.getRuta());
    	i2.setMatrizActual(dst);
    	return i2;
    }
    
    public Imagen n4(Imagen i) {
    	Mat src = i.getMatrizActual();
    	Mat srcGris = checarGris(src);
        // Crear una nueva matriz para almacenar la imagen filtrada
        Mat dst = new Mat(srcGris.rows(), srcGris.cols(), srcGris.type());

        // Definir el orden del filtro
        int n = 4;

        // Aplicar el filtro de orden n
        for (int o = 1; o < srcGris.rows() - 1; o++) {
            for (int j = 1; j < srcGris.cols() - 1; j++) {
                Mat subMatrix = srcGris.submat(o - 1, o + 2, j - 1, j + 2);
                int nthOrderValue = getNValor(subMatrix, n);
                dst.put(o, j, nthOrderValue);
            }
        }
        Imagen i2 = new Imagen(i.getRuta());
    	i2.setMatrizActual(dst);
    	return i2;
    }
    
    public Imagen n5(Imagen i) {
    	Mat src = i.getMatrizActual();
    	Mat dst = new Mat();
    	Imgproc.medianBlur(src, dst, 3);
    	Imagen i2 = new Imagen(i.getRuta());
    	i2.setMatrizActual(dst);
    	return i2;
    }
    
    public Imagen n6(Imagen i) {
    	Mat src = i.getMatrizActual();
    	Mat srcGris = checarGris(src);

        // Crear una nueva matriz para almacenar la imagen filtrada
        Mat dst = new Mat(srcGris.rows(), srcGris.cols(), srcGris.type());

        // Definir el orden del filtro
        int n = 6;

        // Aplicar el filtro de orden n
        for (int o = 1; o < srcGris.rows() - 1; o++) {
            for (int j = 1; j < srcGris.cols() - 1; j++) {
                Mat subMatrix = srcGris.submat(o - 1, o + 2, j - 1, j + 2);
                int nthOrderValue = getNValor(subMatrix, n);
                dst.put(o, j, nthOrderValue);
            }
        }
        Imagen i2 = new Imagen(i.getRuta());
    	i2.setMatrizActual(dst);
    	return i2;
    }
    
    public Imagen n7(Imagen i) {
    	Mat src = i.getMatrizActual();
    	Mat srcGris = checarGris(src);

        // Crear una nueva matriz para almacenar la imagen filtrada
        Mat dst = new Mat(srcGris.rows(), srcGris.cols(), srcGris.type());

        // Definir el orden del filtro
        int n = 7;

        // Aplicar el filtro de orden n
        for (int o = 1; o < srcGris.rows() - 1; o++) {
            for (int j = 1; j < srcGris.cols() - 1; j++) {
                Mat subMatrix = srcGris.submat(o - 1, o + 2, j - 1, j + 2);
                int nthOrderValue = getNValor(subMatrix, n);
                dst.put(o, j, nthOrderValue);
            }
        }
        Imagen i2 = new Imagen(i.getRuta());
    	i2.setMatrizActual(dst);
    	return i2;
    }
    
    public Imagen n8(Imagen i) {
    	Mat src = i.getMatrizActual();
    	Mat srcGris = checarGris(src);

        // Crear una nueva matriz para almacenar la imagen filtrada
        Mat dst = new Mat(srcGris.rows(), srcGris.cols(), srcGris.type());

        // Definir el orden del filtro
        int n = 8;

        // Aplicar el filtro de orden n
        for (int o = 1; o < srcGris.rows() - 1; o++) {
            for (int j = 1; j < srcGris.cols() - 1; j++) {
                Mat subMatrix = srcGris.submat(o - 1, o + 2, j - 1, j + 2);
                int nthOrderValue = getNValor(subMatrix, n);
                dst.put(o, j, nthOrderValue);
            }
        }
        Imagen i2 = new Imagen(i.getRuta());
    	i2.setMatrizActual(dst);
    	return i2;
    }
    
    public Imagen n9(Imagen i) {
    	Mat src = i.getMatrizActual();
    	Mat srcGris = checarGris(src);
        // Crear una nueva matriz para almacenar la imagen filtrada
        Mat dst = new Mat(srcGris.rows(), srcGris.cols(), srcGris.type());
        // Definir el orden del filtro
        int n = 9;
        // Aplicar el filtro de orden n
        for (int o = 1; o < srcGris.rows() - 1; o++) {
            for (int j = 1; j < srcGris.cols() - 1; j++) {
                Mat subMatrix = srcGris.submat(o - 1, o + 2, j - 1, j + 2);
                int nthOrderValue = getNValor(subMatrix, n);
                dst.put(o, j, nthOrderValue);
            }
        }
        Imagen i2 = new Imagen(i.getRuta());
    	i2.setMatrizActual(dst);
    	return i2;
    }
    
    public Imagen prewitt(Imagen i) {
    	Mat src = i.getMatrizActual();
    	Mat srcGris = checarGris(src);
    	Mat dst = new Mat();
    	float kdatax[] = {1,1,1,0,0,0,-1,-1,-1};
    	float kdatay[] = {-1,0,1,-1,0,1,-1,0,1};
    	Mat kernelx = new Mat(3,3,CvType.CV_32F);
    	Mat kernely = new Mat(3,3,CvType.CV_32F);
    	kernelx.put( 0, 0, kdatax);
    	kernely.put( 0, 0, kdatay);
    	Imgproc.GaussianBlur(srcGris, srcGris, new Size(3, 3), 0);
    	Imgproc.filter2D(srcGris, dst, -1, kernelx);
    	Imgproc.filter2D(srcGris, dst, -1, kernely);
    	Imagen i2 = new Imagen(i.getRuta());
    	i2.setMatrizActual(dst);
    	return i2;
    }
    
   
    public Imagen sobel(Imagen i) {
    	Mat src = i.getMatrizActual();
    	Mat srcGris = checarGris(src);
    	Mat dst = new Mat();
    	Mat aux1 = new Mat();
    	Mat aux2 = new Mat();
    	Imgproc.GaussianBlur(srcGris, srcGris, new Size(3, 3), 0);
    	Imgproc.Sobel(srcGris, aux1, -1, 1, 0, 3);
    	Imgproc.Sobel(srcGris, aux2, -1, 0, 1, 3);
    	Core.add(aux1, aux2, dst);
    	Imagen i2 = new Imagen(i.getRuta());
    	i2.setMatrizActual(dst);
    	return i2;
    }
    
    public Imagen roberts(Imagen i) {
    	Mat src = i.getMatrizActual();
    	Mat srcGris = checarGris(src);
    	Mat dst = new Mat();
    	float kdatax[] = {1,0,0,-1};
    	float kdatay[] = {0,1,-1,0};
    	Mat kernelx = new Mat(2,2,CvType.CV_32F);
    	Mat kernely = new Mat(2,2,CvType.CV_32F);
    	kernelx.put( 0, 0, kdatax);
    	kernely.put( 0, 0, kdatay);
    	Imgproc.GaussianBlur(srcGris, srcGris, new Size(3, 3), 0);
    	Imgproc.filter2D(srcGris, dst, -1, kernelx);
    	Imgproc.filter2D(srcGris, dst, -1, kernely);
    	Imagen i2 = new Imagen(i.getRuta());
    	i2.setMatrizActual(dst);
    	return i2;
    }
    
    //OPERACIONES DE MORFOLOGÃ�A
    public Imagen erosion(Imagen i, int[][] estruc, int col, int filas) {
    	Mat src = i.getMatrizActual();
    	Mat dst = new Mat();
    	Mat element = new Mat(estruc.length, estruc[0].length, CvType.CV_8U);
        for (int k = 0; k < estruc.length; k++) {
            for (int j = 0; j < estruc[0].length; j++) {
                element.put(k, j, estruc[k][j]);
            }
        }
    	Imgproc.erode(src, dst, element, new Point(col, filas));
    	Imagen i2 = new Imagen(i.getRuta());
    	i2.setMatrizActual(dst);
    	return i2;
    }
    public Imagen dilatacion(Imagen i, int[][] estruc, int col, int filas) {
    	Mat src = i.getMatrizActual();
    	Mat dst = new Mat();    	
    	Mat element = new Mat(estruc.length, estruc[0].length, CvType.CV_8U);
        for (int k = 0; k < estruc.length; k++) {
            for (int j = 0; j < estruc[0].length; j++) {
                element.put(k, j, estruc[k][j]);
            }
        }
    	Imgproc.dilate( src, dst, element );
    	Imagen i2 = new Imagen(i.getRuta());
    	i2.setMatrizActual(dst);
    	return i2;
    }
    
    public Imagen picosHist (Imagen o) {
    	// Cambiar fondo a negro
   	 Mat src = o.getMatrizActual();
   	 byte[] srcData = new byte[(int) (src.total() * src.channels())];
   	 src.get(0, 0, srcData);
   	 for (int i = 0; i < src.rows(); i++) {
   		for (int j = 0; j < src.cols(); j++) {
   			if (srcData[(i * src.cols() + j) * 3] == (byte) 255 && srcData[(i * src.cols() + j) * 3 + 1] == (byte) 255
   			&& srcData[(i * src.cols() + j) * 3 + 2] == (byte) 255) {
   				srcData[(i * src.cols() + j) * 3] = 0;
   				srcData[(i * src.cols() + j) * 3 + 1] = 0;
   				srcData[(i * src.cols() + j) * 3 + 2] = 0;
   			}//FIN IF
   		}//FIN FOR
   	 }//FIN FOR
   	 src.put(0, 0, srcData);

   	 // Crear elemento estructurante para dilatar
   	 Mat kernel = new Mat(3, 3, CvType.CV_32F);

   	 float[] kernelData = new float[(int) (kernel.total() * kernel.channels())];
   	 kernelData[0] = 1; kernelData[1] = 1; kernelData[2] = 1;
   	 kernelData[3] = 1; kernelData[4] = -8; kernelData[5] = 1;
   	 kernelData[6] = 1; kernelData[7] = 1; kernelData[8] = 1;
   	 kernel.put(0, 0, kernelData);
   	 
   	 //Aplicar filtro laplaciano y evitar valores negativos
   	 Mat imgLaplacian = new Mat();
   	 Imgproc.filter2D(src, imgLaplacian, CvType.CV_32F, kernel);
   	 Mat sharp = new Mat();
   	 src.convertTo(sharp, CvType.CV_32F);
   	 Mat imgResult = new Mat();
   	 Core.subtract(sharp, imgLaplacian, imgResult);
   	 
   	 imgResult.convertTo(imgResult, CvType.CV_8UC3);
   	 imgLaplacian.convertTo(imgLaplacian, CvType.CV_8UC3);
   	 
   	 Mat bw = new Mat();
   	 Imgproc.cvtColor(imgResult, bw, Imgproc.COLOR_BGR2GRAY);
   	 Imgproc.threshold(bw, bw, 40, 255, Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);
   	 
   	 //Algoritmo de tranformacion de distancia
   	 Mat dist = new Mat();
   	 Imgproc.distanceTransform(bw, dist, Imgproc.DIST_L2, 3);
   	 // Normalize the distance image for range = {0.0, 1.0}
   	 // so we can visualize and threshold it
   	 Core.normalize(dist, dist, 0.0, 1.0, Core.NORM_MINMAX);
   	 Mat distDisplayScaled = new Mat();
   	 Core.multiply(dist, new Scalar(255), distDisplayScaled);
   	 Mat distDisplay = new Mat();
   	 distDisplayScaled.convertTo(distDisplay, CvType.CV_8U);
   	 
   	 // Umbralizar para obtener los picos
   	 Imgproc.threshold(dist, dist, 0.4, 1.0, Imgproc.THRESH_BINARY);
   	 //Dilatar la imagen
   	 Mat kernel1 = Mat.ones(3, 3, CvType.CV_8U);
   	 Imgproc.dilate(dist, dist, kernel1);
   	 Mat distDisplay2 = new Mat();
   	 dist.convertTo(distDisplay2, CvType.CV_8U);
   	 Core.multiply(distDisplay2, new Scalar(255), distDisplay2);
    
   	Imagen i2 = new Imagen(o.getRuta());
	i2.setMatrizActual(distDisplay2);
	return i2;
   	 
    }//FIN PICOS HIST
    
    public Imagen minHist(Imagen i) {
    	return null;
    }//FIN MINIMOS DEL HISTOGRAMA
 
    public Imagen segmentar (Imagen o) {
    	 // Cambiar fondo a negro
    	 Mat src = o.getMatrizActual();
    	 byte[] srcData = new byte[(int) (src.total() * src.channels())];
    	 src.get(0, 0, srcData);
    	 for (int i = 0; i < src.rows(); i++) {
    		for (int j = 0; j < src.cols(); j++) {
    			if (srcData[(i * src.cols() + j) * 3] == (byte) 255 && srcData[(i * src.cols() + j) * 3 + 1] == (byte) 255
    			&& srcData[(i * src.cols() + j) * 3 + 2] == (byte) 255) {
    				srcData[(i * src.cols() + j) * 3] = 0;
    				srcData[(i * src.cols() + j) * 3 + 1] = 0;
    				srcData[(i * src.cols() + j) * 3 + 2] = 0;
    			}//FIN IF
    		}//FIN FOR
    	 }//FIN FOR
    	 src.put(0, 0, srcData);

    	 // Crear elemento estructurante para dilatar
    	 Mat kernel = new Mat(3, 3, CvType.CV_32F);

    	 float[] kernelData = new float[(int) (kernel.total() * kernel.channels())];
    	 kernelData[0] = 1; kernelData[1] = 1; kernelData[2] = 1;
    	 kernelData[3] = 1; kernelData[4] = -8; kernelData[5] = 1;
    	 kernelData[6] = 1; kernelData[7] = 1; kernelData[8] = 1;
    	 kernel.put(0, 0, kernelData);
    	 
    	 //Aplicar filtro laplaciano y evitar valores negativos
    	 Mat imgLaplacian = new Mat();
    	 Imgproc.filter2D(src, imgLaplacian, CvType.CV_32F, kernel);
    	 Mat sharp = new Mat();
    	 src.convertTo(sharp, CvType.CV_32F);
    	 Mat imgResult = new Mat();
    	 Core.subtract(sharp, imgLaplacian, imgResult);
    	 
    	 imgResult.convertTo(imgResult, CvType.CV_8UC3);
    	 imgLaplacian.convertTo(imgLaplacian, CvType.CV_8UC3);
    	 
    	 Mat bw = new Mat();
    	 Imgproc.cvtColor(imgResult, bw, Imgproc.COLOR_BGR2GRAY);
    	 Imgproc.threshold(bw, bw, 40, 255, Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);
    	 
    	 //Algoritmo de tranformacion de distancia
    	 Mat dist = new Mat();
    	 Imgproc.distanceTransform(bw, dist, Imgproc.DIST_L2, 3);
    	 // Normalize the distance image for range = {0.0, 1.0}
    	 // so we can visualize and threshold it
    	 Core.normalize(dist, dist, 0.0, 1.0, Core.NORM_MINMAX);
    	 Mat distDisplayScaled = new Mat();
    	 Core.multiply(dist, new Scalar(255), distDisplayScaled);
    	 Mat distDisplay = new Mat();
    	 distDisplayScaled.convertTo(distDisplay, CvType.CV_8U);
    	 
    	 // Umbralizar para obtener los picos
    	 Imgproc.threshold(dist, dist, 0.4, 1.0, Imgproc.THRESH_BINARY);
    	 //Dilatar la imagen
    	 Mat kernel1 = Mat.ones(3, 3, CvType.CV_8U);
    	 Imgproc.dilate(dist, dist, kernel1);
    	 Mat distDisplay2 = new Mat();
    	 dist.convertTo(distDisplay2, CvType.CV_8U);
    	 Core.multiply(distDisplay2, new Scalar(255), distDisplay2);
    	 
    	 Mat dist_8u = new Mat();
    	 dist.convertTo(dist_8u, CvType.CV_8U);
    	 
    	 List<MatOfPoint> contours = new ArrayList<>();
    	 Mat hierarchy = new Mat();
    	 Imgproc.findContours(dist_8u, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
    	 
    	 Mat markers = Mat.zeros(dist.size(), CvType.CV_32S);

    	 for (int i = 0; i < contours.size(); i++) {
    		Imgproc.drawContours(markers, contours, i, new Scalar(i + 1), -1);
    	 }

    	 Mat markersScaled = new Mat();
    	 markers.convertTo(markersScaled, CvType.CV_32F);
    	 Core.normalize(markersScaled, markersScaled, 0.0, 255.0, Core.NORM_MINMAX);
    	 Imgproc.circle(markersScaled, new Point(5, 5), 3, new Scalar(255, 255, 255), -1);
    	 Mat markersDisplay = new Mat();
    	 markersScaled.convertTo(markersDisplay, CvType.CV_8U);
    	 HighGui.imshow("Markers", markersDisplay);
    	 Imgproc.circle(markers, new Point(5, 5), 3, new Scalar(255, 255, 255), -1);
    	 // Aplicar watershed algoritmo
    	 
    	 Imgproc.watershed(imgResult, markers);
    	 Mat mark = Mat.zeros(markers.size(), CvType.CV_8U);
    	 markers.convertTo(mark, CvType.CV_8UC1);
    	 Core.bitwise_not(mark, mark);

    	//Generar colores aleatorios
    	 Random rng = new Random(12345);
    	 List<Scalar> colors = new ArrayList<>(contours.size());
    	 for (int i = 0; i < contours.size(); i++) {
    	 int b = rng.nextInt(256);
    	 int g = rng.nextInt(256);
    	 int r = rng.nextInt(256);
    	 colors.add(new Scalar(b, g, r));
    	 }
    	 // Crear la imagen final
    	 Mat dst = Mat.zeros(markers.size(), CvType.CV_8UC3);
    	 byte[] dstData = new byte[(int) (dst.total() * dst.channels())];
    	 dst.get(0, 0, dstData);
    	 
    	 //Llenar la imagen con los colores aleatorios
    	 int[] markersData = new int[(int) (markers.total() * markers.channels())];
    	 markers.get(0, 0, markersData);
    	 
    	 for (int i = 0; i < markers.rows(); i++) {
    		for (int j = 0; j < markers.cols(); j++) {
    		int index = markersData[i * markers.cols() + j];
    			if (index > 0 && index <= contours.size()) {
    				dstData[(i * dst.cols() + j) * 3 + 0] = (byte) colors.get(index - 1).val[0];
    				dstData[(i * dst.cols() + j) * 3 + 1] = (byte) colors.get(index - 1).val[1];
    				dstData[(i * dst.cols() + j) * 3 + 2] = (byte) colors.get(index - 1).val[2];
    			} else {
    				dstData[(i * dst.cols() + j) * 3 + 0] = 0;
    				dstData[(i * dst.cols() + j) * 3 + 1] = 0;
    				dstData[(i * dst.cols() + j) * 3 + 2] = 0;
    			}//FIN IF
    		}//FIN FOR
    	 }//FIN FOR
    	 
    	 dst.put(0, 0, dstData);
    	 //Regresar la imagen resultado
    	 
    	Imagen i2 = new Imagen(o.getRuta());
    	i2.setMatrizActual(dst);
    	return i2;
    }//FIN SEGMENTAR
 
    
}//FIN CLASE
