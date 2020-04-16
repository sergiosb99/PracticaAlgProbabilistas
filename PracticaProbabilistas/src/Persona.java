
public class Persona {

	int estado;
	/*Estados:
	 * 1 ---> Sano
	 * 2 ---> Enfermo
	 * 3 ---> Muerto
	 * 4 ---> Inmune*/
	
	public Persona(){ // Por defecto esta sana.
		this.estado = 1;
	}
	
	public int getEstado() {
		return estado;
	}
	
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	public String toString() {
		String estado = "";
		switch(getEstado()) {
			case 1: estado = "S"; break;
			case 2: estado = "E"; break;
			case 3: estado = "M"; break;
			case 4: estado = "I"; break;
		}
		return estado;
	}
}
