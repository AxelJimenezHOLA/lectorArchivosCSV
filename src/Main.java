import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean programaTerminado = false;
        ManejadorArchivos manejador = new ManejadorArchivos();
        Scanner entrada = new Scanner(System.in);

        while (!programaTerminado) {
            System.out.println("\nLECTOR DE ARCHIVOS CSV");
            manejador.mostrarArchivoSeleccionado();
            System.out.println("1. Elegir archivo");
            System.out.println("2. Leer archivo");
            System.out.println("3. Guardar CSV de repetición de palabras");
            System.out.println("4. Ver repetición de palabra");
            System.out.println("5. Ver todas las palabras");
            System.out.println("6. Salir");
            System.out.print("Opción: ");
            try {
                int opcion = entrada.nextInt();
                switch (opcion) {
                    case 1 -> manejador.seleccionarArchivo();
                    case 2 -> manejador.leerArchivo();
                    case 3 -> manejador.guardarArchivo();
                    case 4 -> manejador.mostrarRepeticionPalabra();
                    case 5 -> manejador.mostrarTodasLasPalabras();
                    case 6 -> {
                        System.out.println("Ha salido del programa, ¡hasta pronto!");
                        programaTerminado = true;
                    }
                    default -> System.out.println("Error: opción no valida");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: introduzca solo números.");
                entrada.nextLine();
            }
        }
    }
}