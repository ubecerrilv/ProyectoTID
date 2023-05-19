package tid.modelo;

// Clase que contiene la imagen RGB 
public class ImagenRGB implements Data {
    private int[][] matrizR;
    private int[][] matrizG;
    private int[][] matrizB;

    public ImagenRGB() {
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
}
