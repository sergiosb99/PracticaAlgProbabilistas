import java.util.ArrayList;

public class Poblacion {

	private ArrayList<Persona[][]>situaciones=new ArrayList<Persona[][]>();
	private int CONTAGIO_HV;
	private int CONTAGIO_D;
	private int PROB_INM;
	private int PROB_MUERTE;
	
	public Poblacion(int alto, int ancho,int contagio_hv,int contagio_d,int prob_inm,int prob_muerte,int prob_seguir) {
		this.CONTAGIO_HV=contagio_hv;
		this.CONTAGIO_D=contagio_d;
		this.PROB_INM=prob_inm;
		this.PROB_MUERTE=prob_muerte;
		
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
	
	public Persona[][] generarSiguienteIteracion(Persona[][]situacion_actual){
		Persona[][]situacion_nueva=copiarSituacion(situacion_actual);

		for (int i = 1; i < situacion_actual.length-1; i++) {
			for (int j = 1; j < situacion_actual[0].length-1; j++) {
				if(situacion_actual[i][j].getValor()==Persona.ENFERMO.getValor()) {
					for(int v=0;v<8;v++) {
						int valor_contagio=(int) Math.floor(Math.random()*100+1);
						switch(v) {
							case 0:
								if(valor_contagio<=CONTAGIO_D&&situacion_actual[i-1][j-1].getValor()!=Persona.AUXILIAR.getValor()&&(situacion_actual[i-1][j-1].getValor()!=Persona.INMUNE.getValor()&&situacion_actual[i-1][j-1].getValor()!=Persona.MUERTO.getValor()&&situacion_actual[i-1][j-1].getValor()!=Persona.ENFERMO.getValor())) {
									situacion_nueva[i-1][j-1]=Persona.ENFERMO;
								}break;
							case 1:
								if(valor_contagio<=CONTAGIO_HV&&situacion_actual[i-1][j].getValor()!=Persona.AUXILIAR.getValor()&&(situacion_actual[i-1][j].getValor()!=Persona.INMUNE.getValor()&&situacion_actual[i-1][j].getValor()!=Persona.MUERTO.getValor()&&situacion_actual[i-1][j].getValor()!=Persona.ENFERMO.getValor())) {
									situacion_nueva[i-1][j]=Persona.ENFERMO;
								}break;
							case 2:
								if(valor_contagio<=CONTAGIO_D&&situacion_actual[i-1][j+1].getValor()!=Persona.AUXILIAR.getValor()&&(situacion_actual[i-1][j+1].getValor()!=Persona.INMUNE.getValor()&&situacion_actual[i-1][j+1].getValor()!=Persona.MUERTO.getValor()&&situacion_actual[i-1][j+1].getValor()!=Persona.ENFERMO.getValor())) {
									situacion_nueva[i-1][j+1]=Persona.ENFERMO;
								}break;
							case 3:
								if(valor_contagio<=CONTAGIO_HV&&situacion_actual[i][j-1].getValor()!=Persona.AUXILIAR.getValor()&&(situacion_actual[i][j-1].getValor()!=Persona.INMUNE.getValor()&&situacion_actual[i][j-1].getValor()!=Persona.MUERTO.getValor()&&situacion_actual[i][j-1].getValor()!=Persona.ENFERMO.getValor())) {
									situacion_nueva[i][j-1]=Persona.ENFERMO;
								}break;
							case 4:
								if(valor_contagio<=CONTAGIO_HV&&situacion_actual[i][j+1].getValor()!=Persona.AUXILIAR.getValor()&&(situacion_actual[i][j+1].getValor()!=Persona.INMUNE.getValor()&&situacion_actual[i][j+1].getValor()!=Persona.MUERTO.getValor()&&situacion_actual[i][j+1].getValor()!=Persona.ENFERMO.getValor())) {
									situacion_nueva[i][j+1]=Persona.ENFERMO;
								}break;
							case 5:
								if(valor_contagio<=CONTAGIO_D&&situacion_actual[i+1][j-1].getValor()!=Persona.AUXILIAR.getValor()&&(situacion_actual[i+1][j-1].getValor()!=Persona.INMUNE.getValor()&&situacion_actual[i+1][j-1].getValor()!=Persona.MUERTO.getValor()&&situacion_actual[i+1][j-1].getValor()!=Persona.ENFERMO.getValor())) {
									situacion_nueva[i+1][j-1]=Persona.ENFERMO;
								}break;
							case 6:
								if(valor_contagio<=CONTAGIO_HV&&situacion_actual[i+1][j].getValor()!=Persona.AUXILIAR.getValor()&&(situacion_actual[i+1][j].getValor()!=Persona.INMUNE.getValor()&&situacion_actual[i+1][j].getValor()!=Persona.MUERTO.getValor()&&situacion_actual[i+1][j].getValor()!=Persona.ENFERMO.getValor())) {
									situacion_nueva[i+1][j]=Persona.ENFERMO;
								}break;
							case 7:
								if(valor_contagio<=CONTAGIO_D&&situacion_actual[i+1][j+1].getValor()!=Persona.AUXILIAR.getValor()&&(situacion_actual[i+1][j+1].getValor()!=Persona.INMUNE.getValor()&&situacion_actual[i+1][j+1].getValor()!=Persona.MUERTO.getValor()&&situacion_actual[i+1][j+1].getValor()!=Persona.ENFERMO.getValor())) {
									situacion_nueva[i+1][j+1]=Persona.ENFERMO;
								}break;
						}
					}
					int valor_estado=(int) Math.floor(Math.random()*100+1);
					if(valor_estado<=PROB_INM) {
						situacion_nueva[i][j]=Persona.INMUNE;
					}else if(valor_estado<=PROB_INM+PROB_MUERTE+1){
						situacion_nueva[i][j]=Persona.MUERTO;
					}					
				}
			}
		}
		situaciones.add(situacion_nueva);
		return situacion_nueva;
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
						cadena+=situacion_actual[j][v].getValor()+" "; //GETVALOR
					}
				}
				cadena+="\n";
			}
			cadena+="\n";
		}
		return cadena;
	}
}