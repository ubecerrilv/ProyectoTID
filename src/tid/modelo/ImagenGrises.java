package tid.modelo;

public class ImagenGrises implements Data {

    private int[][] matrizIntensidades;
    private String ruta;

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

}
