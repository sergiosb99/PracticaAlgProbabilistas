import java.util.ArrayList;

public class Poblacion {

	private int ancho;
	private int alto;
	private ArrayList<Persona[][]>situaciones=new ArrayList<Persona[][]>();
	
	public Poblacion(int alto, int ancho) {
		Persona[][]situacion_inicial = new Persona[alto+2][ancho+2];
		
		for (int i = 0; i < alto+2; i++) {
			for (int j = 0; j < ancho+2; j++) {
				if(i==0||j==0||i==alto+1||j==ancho+1) {
					situacion_inicial[i][j]=Persona.AUXILIAR;
				}else {
					situacion_inicial[i][j] = Persona.NOAFECTADO;
				}
				
			}
		}
		situaciones.add(situacion_inicial);
	}
	
	public ArrayList<Persona[][]> getMatriz() {
		return situaciones;
	}
	
	public Persona[][] copiarSituacion(Persona[][]situacion){
		Persona[][]situacion_actual=new Persona[situacion.length][situacion[0].length];
		
		for(int i=0;i<situacion_actual.length;i++) {
			for(int j=0;j<situacion_actual[0].length;j++) {
				situacion_actual[i][j]=situacion[i][j];
			}
		}
		return situacion_actual;
	}
	
	public void primerEnfermo (int alto, int ancho){
		Persona[][]situacion=situaciones.get(0);
		for (int i = 1; i < situacion.length-1; i++ ) {
			for (int j = 1; j < situacion[0].length-1; j++) {
				if (i == alto && j == ancho) {
					situacion[i][j] = Persona.ENFERMO;
				}
			}
		}
	}
	
	public /*char[][]*/void generarSiguienteIteracion(){
		Persona[][]situacion_actual=copiarSituacion(situaciones.get(situaciones.size()-1));
		int turno=situaciones.size()-2;
		
		for (int i = 1; i < situacion_actual.length-1; i++) {
			for (int j = 1; j < situacion_actual[0].length-1; j++) {
				if(situacion_actual[i][j].getValor()==Persona.ENFERMO.getValor()&&situacion_actual[i][j].getTurno()==turno) {
					for(int v=0;v<8;v++) {
						int valor_contagio=(int) Math.floor(Math.random()*101);
						switch(v) {
							case 0:
								if(valor_contagio<30&&situacion_actual[i-1][j-1].getValor()!=Persona.AUXILIAR.getValor()&&(situacion_actual[i-1][j-1].getValor()!=Persona.INMUNE.getValor()&&situacion_actual[i-1][j-1].getValor()!=Persona.MUERTO.getValor()&&situacion_actual[i-1][j-1].getValor()!=Persona.ENFERMO.getValor())) {
									situacion_actual[i-1][j-1]=Persona.ENFERMO;
									situacion_actual[i-1][j-1].setTurno(turno+1);
								}break;
							case 1:
								if(valor_contagio<80&&situacion_actual[i-1][j].getValor()!=Persona.AUXILIAR.getValor()&&(situacion_actual[i-1][j].getValor()!=Persona.INMUNE.getValor()&&situacion_actual[i-1][j].getValor()!=Persona.MUERTO.getValor()&&situacion_actual[i-1][j].getValor()!=Persona.ENFERMO.getValor())) {
									situacion_actual[i-1][j]=Persona.ENFERMO;
									situacion_actual[i-1][j].setTurno(turno+1);
								}break;
							case 2:
								if(valor_contagio<30&&situacion_actual[i-1][j+1].getValor()!=Persona.AUXILIAR.getValor()&&(situacion_actual[i-1][j+1].getValor()!=Persona.INMUNE.getValor()&&situacion_actual[i-1][j+1].getValor()!=Persona.MUERTO.getValor()&&situacion_actual[i-1][j+1].getValor()!=Persona.ENFERMO.getValor())) {
									situacion_actual[i-1][j+1]=Persona.ENFERMO;
									situacion_actual[i-1][j+1].setTurno(turno+1);
								}break;
							case 3:
								if(valor_contagio<80&&situacion_actual[i][j-1].getValor()!=Persona.AUXILIAR.getValor()&&(situacion_actual[i][j-1].getValor()!=Persona.INMUNE.getValor()&&situacion_actual[i][j-1].getValor()!=Persona.MUERTO.getValor()&&situacion_actual[i][j-1].getValor()!=Persona.ENFERMO.getValor())) {
									situacion_actual[i][j-1]=Persona.ENFERMO;
									situacion_actual[i][j-1].setTurno(turno+1);
								}break;
							case 4:
								if(valor_contagio<80&&situacion_actual[i][j+1].getValor()!=Persona.AUXILIAR.getValor()&&(situacion_actual[i][j+1].getValor()!=Persona.INMUNE.getValor()&&situacion_actual[i][j+1].getValor()!=Persona.MUERTO.getValor()&&situacion_actual[i][j+1].getValor()!=Persona.ENFERMO.getValor())) {
									situacion_actual[i][j+1]=Persona.ENFERMO;
									situacion_actual[i][j+1].setTurno(turno+1);
								}break;
							case 5:
								if(valor_contagio<30&&situacion_actual[i+1][j-1].getValor()!=Persona.AUXILIAR.getValor()&&(situacion_actual[i+1][j-1].getValor()!=Persona.INMUNE.getValor()&&situacion_actual[i+1][j-1].getValor()!=Persona.MUERTO.getValor()&&situacion_actual[i+1][j-1].getValor()!=Persona.ENFERMO.getValor())) {
									situacion_actual[i+1][j-1]=Persona.ENFERMO;
									situacion_actual[i+1][j-1].setTurno(turno+1);
								}break;
							case 6:
								if(valor_contagio<80&&situacion_actual[i+1][j].getValor()!=Persona.AUXILIAR.getValor()&&(situacion_actual[i+1][j].getValor()!=Persona.INMUNE.getValor()&&situacion_actual[i+1][j].getValor()!=Persona.MUERTO.getValor()&&situacion_actual[i+1][j-1].getValor()!=Persona.ENFERMO.getValor())) {
									situacion_actual[i+1][j]=Persona.ENFERMO;
									situacion_actual[i+1][j].setTurno(turno+1);
								}break;
							case 7:
								if(valor_contagio<30&&situacion_actual[i+1][j+1].getValor()!=Persona.AUXILIAR.getValor()&&(situacion_actual[i+1][j+1].getValor()!=Persona.INMUNE.getValor()&&situacion_actual[i+1][j+1].getValor()!=Persona.MUERTO.getValor()&&situacion_actual[i+1][j-1].getValor()!=Persona.ENFERMO.getValor())) {
									situacion_actual[i+1][j+1]=Persona.ENFERMO;
									situacion_actual[i+1][j+1].setTurno(turno+1);
								}break;
						}
					}
					int valor_estado=(int) Math.floor(Math.random()*101);
					if(valor_estado<30) {
						situacion_actual[i][j]=Persona.INMUNE;
					}else if(valor_estado<50){
						situacion_actual[i][j]=Persona.MUERTO;
					}
					situacion_actual[i][j].setTurno(turno+1);
				}
			}
		}
		situaciones.add(situacion_actual);
	}
	
	public boolean finPandemia() {//Devuelve true si no hay enfermos, false si quedan
		Persona[][]situacion_actual=situaciones.get(situaciones.size()-1);
		boolean fin=true;
		
		for (int i = 1; i < situacion_actual.length-1&&fin; i++) {
			for (int j = 1; j < situacion_actual[0].length-1; j++) {
				if(situacion_actual[i][j].getValor()==Persona.ENFERMO.getValor()) {
					fin=false;break;
				}
			}
		}
		return fin;
	}
	
	public String toString() {
		String cadena="";
		for(int i=0;i<this.situaciones.size();i++) {
			Persona[][]situacion_actual=this.situaciones.get(i);
			cadena+="Situación: "+i+"\n";
			for (int j = 0; j < situacion_actual.length; j++) {
				for (int v = 0; v < situacion_actual[0].length; v++) {
					if(situacion_actual[j][v].getValor()!=Persona.AUXILIAR.getValor()) {
						cadena+=situacion_actual[j][v].getValor()+" ";
					}
				}
				cadena+="\n";
			}
			cadena+="\n";
		}
		
		return cadena;
	}
}
