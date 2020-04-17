
public class Poblacion {

	private int ancho;
	private int alto;
	private char[][] matriz;
	private Persona p;
	
	public Poblacion(int alto, int ancho) {
		this.matriz = new char[alto+2][ancho+2];
		
		for (int i = 0; i < alto+2; i++) {
			for (int j = 0; j < ancho+2; j++) {
				if(i==0||j==0||i==alto+1||j==ancho+1) {
					this.matriz[i][j]='0';
				}else {
					this.matriz[i][j] = Persona.NOAFECTADO.getValor();
				}
				
			}
		}
	}
	
	public char[][] getMatriz() {
		return matriz;
	}
	
	public void setMatriz(char[][] matriz) {
		this.matriz = new char[matriz.length][matriz[0].length];

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				this.matriz[i][j] = matriz[i][j];
			}
		}	
	}
	
	public void primerEnfermo (int alto, int ancho){
		for (int i = 0; i < this.matriz.length; i++ ) {
			for (int j = 0; j < this.matriz[0].length; j++) {
				if (i == alto+1 && j == ancho+1) {
					this.matriz[i][j] = Persona.ENFERMO.getValor(); 
				}
			}
		}
	}
	
	public String toString() {
		String cadena="";
		for (int i = 0; i < this.matriz.length; i++) {
			for (int j = 0; j < this.matriz[0].length; j++) {
				if(this.matriz[i][j]!='0') {
					cadena+=this.matriz[i][j]+" ";
				}
			}
			cadena+="\n";
		}
		return cadena;
	}
}
