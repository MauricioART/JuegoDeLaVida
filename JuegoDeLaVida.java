import java.util.Scanner;
/**
 * Esta clase contiene la interfaz de usuario para poder ordenar los datos enteros de un 
 * archivo con tres diferentes métodos de ordenamiento externo.
 * @author Mauricio Arturo Aguilera Roa
 */
public class JuegoDeLaVida {
    
   /**
    * 
    * @param args 
    */
   public static void main(String[] args){
		
	Scanner sc = new Scanner(System.in);
	Scanner sc2 = new Scanner(System.in);
	boolean continuar = true;
    int salir;
    int reiniciar;
    do{
        reiniciar = 1;
        System.out.println(" ◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆");
        System.out.println(" ◆◆◆◆◆◆◆◆◆◆" + "   Juego de la Vida   " + "◆◆◆◆◆◆◆◆◆◆");
        System.out.println(" ◆◆◆◆◆◆◆◆   " + "Dimensión del tablero" + "   ◆◆◆◆◆◆◆");
        System.out.print(" ◆◆◆◆◆◆ n: ");
        Mundo mundo = new Mundo(sc.nextInt());
        mundo.setVecinos();
        System.out.print("\033\143");
        do{
            mundo.desplegarMundo();
            System.out.println(" ◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆");
            System.out.println(" ◆◆◆◆◆◆◆◆◆" + "   Juego de la Vida   " + "◆◆◆◆◆◆◆◆◆◆1◆");
            System.out.println(" ◆◆◆◆◆◆◆◆◆"  + " Configuración inicial " + "◆◆◆◆◆◆◆◆◆◆");
            System.out.println("Ingresa las coordenadas de las celulas vivas" );
            System.out.print(" ◆◆◆◆◆◆ "  + "Fila: ");
            int f = sc.nextInt();
            System.out.print(" ◆◆◆◆◆◆ "  + "Columna: ");
            int c = sc.nextInt();
            mundo.setEstadoCelula(f, c, true);
            System.out.println(" ◆◆◆◆◆◆◆◆◆"  +"( 1 (Siguiente) o 0 (salir))");
            salir = sc.nextInt();
            System.out.print("\033\143");
        }while(salir != 0);
        do{
            mundo.desplegarMundo();
            System.out.println(" ◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆");
            System.out.println(" ◆◆◆◆◆◆◆◆◆◆" + "   Juego de la Vida   " + "◆◆◆◆◆◆◆◆◆◆");
            System.out.println("┃◆1.-Siguiente estado                     ┃");
            System.out.println("┃◆2.-Reiniciar condiciones                ┃");
            System.out.println("┃◆3.-Salir                                ┃");
            System.out.print("┃◆Opción: ");
            
            switch(sc.nextInt()){
                case 1:
                    mundo.siguienteEstado();
                    break;
                case 2:
                    continuar = false;
                    reiniciar  = 0;
                    break;
                case 3:
                    continuar = false;
                    break;
                default:
                    System.out.println("\t◆◆◆◆OPCIÓN INVALIDA◆◆◆◆");
                    break;	
            }
        }while(continuar);
        System.out.print("\033\143");
    }while(reiniciar == 0);
    sc.close();
    sc2.close();
}
}

   
    
