public enum Persona {
	ENFERMO('E'),INMUNE('I'),MUERTO('M'),NOAFECTADO('N'),AUXILIAR('0');//AUXILIAR SOLO SIRVE PARA LA MATRIZ
	private char valor;
	
	Persona(char valor){
		this.valor=valor;
	}
	public char getValor() {
		return this.valor;
	}
	public void setValor(char valor) { 
		this.valor=valor;
	}
}