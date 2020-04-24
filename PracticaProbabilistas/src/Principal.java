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
			altoEnfermo = (int) Math.floor(Math.random()*alto);
			anchoEnfermo = (int) Math.floor(Math.random()*ancho);
			System.out.println("Alto: "+altoEnfermo+", Ancho: "+anchoEnfermo);
			break;
		case 2:
			System.out.println("¿En que posición quieres poner el primer enfermo de la población?");
			altoEnfermo = leerEnteroPositivoConLimite(0,alto-1,"Alto (0 - " + (alto-1) +"):");
			anchoEnfermo = leerEnteroPositivoConLimite(0,ancho-1,"Ancho (0 - " + (ancho-1) +"):");
			break;
		}
		int porcentajes=leerEnteroPositivoConLimite(1,3,"\n¿Quieres cambiar los porcentajes?\n1)Sí.\n2)No.");
		Poblacion poblacion=null;
		switch(porcentajes) {
			case 1:
				System.out.println("Introduce los valores de contagio a los vecinos para:");
				int vertical_horizontal = leerEnteroPositivoConLimite(0,100,"(1) Vertical y Horizontal");
				int diagonal = leerEnteroPositivoConLimite(0,100,"(2) Diagonales");
				System.out.println("Introduce los valores de cambio de estado para:\n(1) Inmune\n(2) Muerto\n(3) Seguir enfermo");
				int[]porcentajes_cambio=pedirPorcentajes(3);
				
				for(int i=0;i<porcentajes_cambio.length;i++) {
					System.out.print(porcentajes_cambio[i]+" ");
				}
				System.out.println();
				System.out.println("h/v: "+vertical_horizontal);
				System.out.println("d: "+diagonal);

				poblacion = new Poblacion(alto,ancho,vertical_horizontal,diagonal,porcentajes_cambio[0],porcentajes_cambio[1],porcentajes_cambio[2]); // Se inicia con todos sanos (S).
				break;
			case 2:
				poblacion = new Poblacion(alto,ancho,80,30,30,20,50);
				break;
		}		
		simularPandemia(poblacion,altoEnfermo,anchoEnfermo);
	}
	
	private static void simularPandemia(Poblacion p,int altoEnfermo,int anchoEnfermo) {
		p.primerEnfermo(altoEnfermo + 1, anchoEnfermo + 1);
		Persona[][]situacion_actual=p.copiarSituacion(p.getMatriz().get(0));
		while(!p.finPandemia()) {
			situacion_actual = p.generarSiguienteIteracion(situacion_actual);
		}
		System.out.println(p.toString());
	}
	
	// Métodos auxiliares
	
	public static int[] pedirPorcentajes(int num) {
		int[]porcentajes=new int[num];
		int porcentaje_total=100;
		for(int i=0;i<porcentajes.length-1;i++) {

			int porcentaje_actual=leerEnteroPositivo("Introduce un número entero positivo para ("+(i+1)+"): ");
			porcentaje_total=porcentaje_total-porcentaje_actual;
			if(porcentaje_total < 0) {
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
				
				if(numero<0) {
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
				if (opcion < valorMin || opcion > valorMax) {
					throw new Exception("El numero introducido debe estar entre"+ valorMin+" y "+valorMax+", pruebe otra vez:");
				}
			}catch(Exception e) {
				System.out.print("Introduzca un valor valido: ");
				TECLADO.nextLine();
			}
		} while (opcion < valorMin || opcion > valorMax);

		return opcion;
	}
}