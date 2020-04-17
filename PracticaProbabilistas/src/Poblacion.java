import java.util.ArrayList;

public class Poblacion {

	private int ancho;
	private int alto;
	private ArrayList<char[][]>situaciones=new ArrayList<char[][]>();
	private Persona p;
	
	public Poblacion(int alto, int ancho) {
		char[][]situacion_inicial = new char[alto+2][ancho+2];
		
		for (int i = 0; i < alto+2; i++) {
			for (int j = 0; j < ancho+2; j++) {
				if(i==0||j==0||i==alto+1||j==ancho+1) {
					situacion_inicial[i][j]='0';
				}else {
					situacion_inicial[i][j] = Persona.NOAFECTADO.getValor();
				}
				
			}
		}
		situaciones.add(situacion_inicial);
	}
	
	public ArrayList<char[][]> getMatriz() {
		return situaciones;
	}
	
	public char[][] copiarSituacion(char[][]situacion){
		char[][]situacion_actual=new char[situacion.length][situacion[0].length];
		
		for(int i=0;i<situacion_actual.length;i++) {
			for(int j=0;j<situacion_actual[0].length;j++) {
				situacion_actual[i][j]=situacion[i][j];
			}
		}
		return situacion_actual;
	}
	
	/*public void setMatriz(char[][] matriz) {
		this.matriz = new char[matriz.length][matriz[0].length];

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				this.matriz[i][j] = matriz[i][j];
			}
		}	
	}*/
	
	public void primerEnfermo (int alto, int ancho){
		char[][]situacion=situaciones.get(0);
		for (int i = 0; i < situacion.length; i++ ) {
			for (int j = 0; j < situacion[0].length; j++) {
				if (i == alto+1 && j == ancho+1) {
					situacion[i][j] = Persona.ENFERMO.getValor(); 
				}
			}
		}
	}
	
	public /*char[][]*/void generarSiguienteIteracion(){
		char[][]situacion_actual=copiarSituacion(situaciones.get(situaciones.size()-1));
		
		for (int i = 1; i < situacion_actual.length-1; i++) {
			for (int j = 1; j < situacion_actual[0].length-1; j++) {
				if(situacion_actual[i][j]==Persona.ENFERMO.getValor()) {
					for(int v=0;v<8;v++) {
						int valor_contagio=(int) Math.floor(Math.random()*101);
						switch(v) {
							case 0:
								if(valor_contagio<30&&situacion_actual[i-1][j-1]!='0'&&(situacion_actual[i-1][j-1]!=Persona.INMUNE.getValor()&&situacion_actual[i-1][j-1]!=Persona.MUERTO.getValor()&&situacion_actual[i-1][j-1]!=Persona.ENFERMO.getValor())) {
									situacion_actual[i-1][j-1]=Persona.ENFERMO.getValor();
								}break;
							case 1:
								if(valor_contagio<80&&situacion_actual[i-1][j]!='0'&&(situacion_actual[i-1][j]!=Persona.INMUNE.getValor()&&situacion_actual[i-1][j]!=Persona.MUERTO.getValor()&&situacion_actual[i-1][j]!=Persona.ENFERMO.getValor())) {
									situacion_actual[i-1][j]=Persona.ENFERMO.getValor();
								}break;
							case 2:
								if(valor_contagio<30&&situacion_actual[i-1][j+1]!='0'&&(situacion_actual[i-1][j+1]!=Persona.INMUNE.getValor()&&situacion_actual[i-1][j+1]!=Persona.MUERTO.getValor()&&situacion_actual[i-1][j+1]!=Persona.ENFERMO.getValor())) {
									situacion_actual[i-1][j+1]=Persona.ENFERMO.getValor();
								}break;
							case 3:
								if(valor_contagio<80&&situacion_actual[i][j-1]!='0'&&(situacion_actual[i][j-1]!=Persona.INMUNE.getValor()&&situacion_actual[i][j-1]!=Persona.MUERTO.getValor()&&situacion_actual[i][j-1]!=Persona.ENFERMO.getValor())) {
									situacion_actual[i][j-1]=Persona.ENFERMO.getValor();
								}break;
							case 4:
								if(valor_contagio<80&&situacion_actual[i][j+1]!='0'&&(situacion_actual[i][j+1]!=Persona.INMUNE.getValor()&&situacion_actual[i][j+1]!=Persona.MUERTO.getValor()&&situacion_actual[i][j+1]!=Persona.ENFERMO.getValor())) {
									situacion_actual[i][j+1]=Persona.ENFERMO.getValor();
								}break;
							case 5:
								if(valor_contagio<30&&situacion_actual[i+1][j-1]!='0'&&(situacion_actual[i+1][j-1]!=Persona.INMUNE.getValor()&&situacion_actual[i+1][j-1]!=Persona.MUERTO.getValor()&&situacion_actual[i+1][j-1]!=Persona.ENFERMO.getValor())) {
									situacion_actual[i+1][j-1]=Persona.ENFERMO.getValor();
								}break;
							case 6:
								if(valor_contagio<80&&situacion_actual[i+1][j]!='0'&&(situacion_actual[i+1][j]!=Persona.INMUNE.getValor()&&situacion_actual[i+1][j]!=Persona.MUERTO.getValor()&&situacion_actual[i+1][j-1]!=Persona.ENFERMO.getValor())) {
									situacion_actual[i+1][j]=Persona.ENFERMO.getValor();
								}break;
							case 7:
								if(valor_contagio<30&&situacion_actual[i+1][j+1]!='0'&&(situacion_actual[i+1][j+1]!=Persona.INMUNE.getValor()&&situacion_actual[i+1][j+1]!=Persona.MUERTO.getValor()&&situacion_actual[i+1][j-1]!=Persona.ENFERMO.getValor())) {
									situacion_actual[i+1][j+1]=Persona.ENFERMO.getValor();
								}break;
						}
					}
					int valor_estado=(int) Math.floor(Math.random()*101);
					if(valor_estado<30) {
						situacion_actual[i][j]=Persona.INMUNE.getValor();
					}else if(valor_estado<50){
						situacion_actual[i][j]=Persona.MUERTO.getValor();
					}
				}
			}
		}
		situaciones.add(situacion_actual);
		System.out.println(this.toString());
	}
	
	public boolean finPandemia() {//Devuelve true si no hay enfermos, false si quedan
		char[][]situacion_actual=situaciones.get(situaciones.size()-1);
		boolean fin=true;
		
		for (int i = 1; i < situacion_actual.length-1&&fin; i++) {
			for (int j = 1; j < situacion_actual[0].length-1; j++) {
				if(situacion_actual[i][j]==Persona.ENFERMO.getValor()) {
					fin=false;break;
				}
			}
		}
		return fin;
	}
	
	public String toString() {
		String cadena="";
		for(int i=0;i<this.situaciones.size();i++) {
			char[][]situacion_actual=this.situaciones.get(i);
			cadena+="Situación: "+i+"\n";
			for (int j = 0; j < situacion_actual.length; j++) {
				for (int v = 0; v < situacion_actual[0].length; v++) {
					if(situacion_actual[j][v]!='0') {
						cadena+=situacion_actual[j][v]+" ";
					}
				}
				cadena+="\n";
			}
			cadena+="\n";
		}
		
		return cadena;
	}
}
