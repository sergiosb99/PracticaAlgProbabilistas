
public enum Persona {
	ENFERMO('E'),INMUNE('I'),MUERTO('M'),NOAFECTADO('N');
	private char valor;
	
	Persona(char valor){
		this.valor=valor;
	}
	char getValor() {
		return this.valor;
	}
	void setValor(char valor) {  //esto hay que borrarlo que es una mierda
		this.valor=valor;
	}
}
