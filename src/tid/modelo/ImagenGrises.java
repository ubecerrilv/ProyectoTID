package tid.modelo;

public class ImagenGrises implements Data {

    private int[][] matrizIntensidades;

    public ImagenGrises() {
        super();
    }

    public int[][] getMatrizIntensidades() {
        return matrizIntensidades;
    }

    public void setMatrizIntensidades(int[][] matrizIntensidades) {
        this.matrizIntensidades = matrizIntensidades;
    }

}
