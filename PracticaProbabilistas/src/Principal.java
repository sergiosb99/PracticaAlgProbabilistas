import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
	public static void main(String[] args){
		int alto = leerEnteroPositivo("Introduzca el largo de la población: "); 
		int ancho = leerEnteroPositivo("Introduzca el ancho de la población: ");
		Poblacion poblacion = new Poblacion(alto,ancho); // Se inicia con todos sanos (S).
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
		
		/*Persona [][] pueblo = primerEnfermo(poblacion,altoEnfermo,anchoEnfermo);
		poblacion.setMatriz(pueblo); // La población ahora tiene un enfermo (E).*/
		
		poblacion.primerEnfermo(altoEnfermo, anchoEnfermo);
		
		System.out.println(poblacion.toString());
		
		simularPandemia(poblacion);
		
		/*contagios(poblacion);
		representarPoblacion(poblacion);*/

	}
	
	private static void simularPandemia(Poblacion p) {
		while(!p.finPandemia()) {
			p.generarSiguienteIteracion();
		}
		p.toString();
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