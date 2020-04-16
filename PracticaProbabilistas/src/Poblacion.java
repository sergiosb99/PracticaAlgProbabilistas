
public class Poblacion {

	int ancho;
	int alto;
	Persona[][] matriz;
	
	public Poblacion(int alto, int ancho) {
		this.matriz = new Persona[alto][ancho];
		
		for (int i = 0; i < alto; i++) {
			for (int j = 0; j < ancho; j++) {
				this.matriz[i][j] = new Persona();
			}
		}
	}
	
	public Persona[][] getMatriz() {
		return matriz;
	}
	
	public void setMatriz(Persona[][] matriz) {
		this.matriz = new Persona[matriz.length][matriz[0].length];

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				this.matriz[i][j] = matriz[i][j];
			}
		}	
	}
}
