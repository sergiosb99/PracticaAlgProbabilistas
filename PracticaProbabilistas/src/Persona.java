
public enum Persona {
	ENFERMO('E',-1),INMUNE('I',0),MUERTO('M',0),NOAFECTADO('N',0),AUXILIAR('0',-1);//AUXILIAR SOLO SIRVE PARA LA MATRIZ
	private char valor;
	private int turno;
	
	Persona(char valor,int turno){
		this.valor=valor;
		this.turno=turno;
	}
	public char getValor() {
		return this.valor;
	}
	public void setValor(char valor) {  //esto hay que borrarlo que es una mierda
		this.valor=valor;
	}
	public int getTurno() {
		return this.turno;
	}
	public void setTurno(int turno) {
		this.turno=turno;
	}
}
