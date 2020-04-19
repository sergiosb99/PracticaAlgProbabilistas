import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
	public static void main(String[] args){
		int alto = leerEnteroPositivo("Introduzca el largo de la población: "); 
		int ancho = leerEnteroPositivo("Introduzca el ancho de la población: ");
		
		System.out.println("Tienes dos opciones:\n1)Generar el primer enfermo aleatoriamente.\n2)Generar el primer enfermo introduciendo la posición exacta.");
		int opcion = leerEnteroPositivoConLimite(1,3,"Introduzca una opción:");
		int altoEnfermo=-1,anchoEnfermo=-1;
		switch(opcion) {
		case 1:
			altoEnfermo = (int) Math.floor(Math.random()*alto+1);
			anchoEnfermo = (int) Math.floor(Math.random()*ancho+1);
			break;
		case 2:
			System.out.println("¿En que posición quieres poner el primer enfermo de la población?");
			altoEnfermo = leerEnteroPositivoConLimite(0,alto,"Alto (0 - " + (alto-1) +"):");
			anchoEnfermo = leerEnteroPositivoConLimite(0,ancho,"Ancho (0 - " + (ancho-1) +"):");
			break;
		}
		int porcentajes=leerEnteroPositivoConLimite(1,3,"\n¿Quieres cambiar los porcentajes?\n1)Sí.\n2)No.");
		Poblacion poblacion=null;
		switch(porcentajes) {
			case 1:
				System.out.println("Introduce los valores de contagio a los vecinoes (1) Vertical y Horizontal (2) Diagonales.");
				int[]porcentajes_contagio=pedirPorcentajes(2);
				System.out.println("Introduce los valores de cambio de estado (1) Enfermo (2) Muerto (3)Seguir igual");
				int[]porcentajes_cambio=pedirPorcentajes(3);
				/*for(int i=0;i<porcentajes_contagio.length;i++) {
					System.out.print(porcentajes_contagio[i]+" ");
				}
				System.out.println();
				for(int i=0;i<porcentajes_cambio.length;i++) {
					System.out.print(porcentajes_cambio[i]+" ");
				}
				System.out.println();*/
				poblacion = new Poblacion(alto,ancho,porcentajes_contagio[0],porcentajes_contagio[1],porcentajes_cambio[0],porcentajes_cambio[1],porcentajes_cambio[2]); // Se inicia con todos sanos (S).
				break;
			case 2:
				poblacion = new Poblacion(alto,ancho,80,30,30,20,50);
				break;
		}
		
		/*Persona [][] pueblo = primerEnfermo(poblacion,altoEnfermo,anchoEnfermo);
		poblacion.setMatriz(pueblo); // La población ahora tiene un enfermo (E).*/
		
		
		
		simularPandemia(poblacion,altoEnfermo,anchoEnfermo);
		
		/*contagios(poblacion);
		representarPoblacion(poblacion);*/

	}
	
	private static void simularPandemia(Poblacion p,int altoEnfermo,int anchoEnfermo) {
		p.primerEnfermo(altoEnfermo, anchoEnfermo);
		while(!p.finPandemia()) {
			p.generarSiguienteIteracion();
		}
		System.out.println(p.toString());
	}
	
	/*private static Persona[][] contagios (Poblacion poblacion) {
		Persona[][] poblacionOriginal = poblacion.getMatriz();
		Persona[][] trasContagiar = null;
		ArrayList<int[]> enfermos = new ArrayList<int[]>();
		enfermos = buscarEnfermos(trasContagiar);
		
		for (int enf = 0; enf < enfermos.size(); enf++) {
			int alto = enfermos.get(enf)[0];
			int ancho = enfermos.get(enf)[1];
			for (int i = 0; i < poblacionOriginal.length; i++) {
				for(int j = 0; j < poblacionOriginal[0].length; i++) {
					if (i == alto || j == ancho) poblacionOriginal[i][j].setEstado(2);
				}
			}
		}
		
		return poblacionOriginal;
	}*/
	
	/*private static ArrayList<int[]> buscarEnfermos(Persona[][] matriz) {
		ArrayList<int[]> enfermos = new ArrayList<int[]>();
		
		for (int i = 0; i < matriz.length; i++ ) {
			for (int j = 0; j < matriz[0].length; j++) {
				if (matriz[i][j].estado == 2) {
					int[] posicionEnfermo = new int[2]; // metemos el alto y ancho  
					posicionEnfermo[1] = matriz.length;
					posicionEnfermo[2] = matriz[0].length;
					enfermos.add(posicionEnfermo);
				}
			}
		}
			
		return enfermos;
	}*/
	
	// Métodos auxiliares

	/*private static void representarPoblacion(Poblacion poblacion) { //Borrar
		System.out.println(" --- POBLACIÓN ---");
		Persona[][] pueblo = poblacion.getMatriz();
		for (int i = 0; i < pueblo.length; i++) {
			for (int j = 0; j < pueblo[0].length; j++) {
				System.out.print(pueblo[i][j] + " ");
			}
			System.out.println();
		}
	}*/
	
	public static int[] pedirPorcentajes(int num) {
		int[]porcentajes=new int[num];
		int porcentaje_total=100;
		for(int i=0;i<porcentajes.length-1;i++) {

			int porcentaje_actual=leerEnteroPositivo("Introduce un número entero positivo: ");
			porcentaje_total=porcentaje_total-porcentaje_actual;
			if(porcentaje_total<=0) {
				i--;
				System.out.println("Valor inválido, supera el número 100.");
			}else {
				porcentajes[i]=porcentaje_actual;
			}
		}
		porcentajes[num-1]=porcentaje_total;
		
		return porcentajes;
	}
	
	public static int leerEnteroPositivo(String mensaje) {
		Scanner TECLADO = new Scanner(System.in);
		int numero = 0;
		boolean valido = true;
		do {
			System.out.print(mensaje);
			try {
				numero = TECLADO.nextInt();
				
				if(numero<=0) {
					valido=false;
					System.out.println("Has introducido un valor no válido, introduzca uno válido.");
				}else {
					valido=true;
				}
				

			}catch(Exception InputMismatchException) {
				System.out.print("El dato introducido no es correcto, introduzca uno valido: ");
				valido=false;
				TECLADO.nextLine();
			}
		}while(!valido);

		return numero;
	}
	
	public static int leerEnteroPositivoConLimite(int valorMin,int valorMax, String mensaje) {
		Scanner TECLADO = new Scanner(System.in);
		int opcion = 0;

		do {
			System.out.println(mensaje);
			try {
				opcion = TECLADO.nextInt();
				if (opcion < valorMin || opcion >= valorMax) {
					throw new Exception("El numero introducido debe estar entre"+ valorMin+" y "+valorMax+", pruebe otra vez:");
				}
			}catch(Exception e) {
				System.out.print("Introduzca un valor valido: ");
				TECLADO.nextLine();
			}
		} while (opcion < valorMin || opcion >= valorMax);

		return opcion;
	}
}