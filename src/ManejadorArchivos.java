import java.io.*;
import java.util.Map;
import java.util.Scanner;

public class ManejadorArchivos {
    private String linea;
    private String directorio;
    private ContadorPalabras contadorPalabras;
    private boolean archivoLeido;

    private BufferedReader lector;
    private PrintWriter escritor;

    public ManejadorArchivos() {
        linea = null;
        directorio = null;
        contadorPalabras = new ContadorPalabras();
        archivoLeido = false;
    }

    public void leerArchivo() {
        if (directorio == null) {
            System.out.println("Error: no hay directorio elegido.");
            return;
        }

        try {
            lector = new BufferedReader(new FileReader("archivosEntrada/%s.csv".formatted(directorio)));

            while ((linea = lector.readLine()) != null) {
                String[] contenido = linea.split("[\\s,]+");
                for (String s : contenido) {
                    if (contadorPalabras.estaPalabra(s.toLowerCase())) {
                        contadorPalabras.aumentarContadorDe(s.toLowerCase());
                    } else if (tieneSoloLetras(s)) {
                        contadorPalabras.agregarPalabraNueva(s.toLowerCase());
                    }
                }

            }
            lector.close();
            System.out.println("¡Archivo leído!");
            archivoLeido = true;
        } catch (FileNotFoundException e) {
            System.out.println("Error: no se encontró el archivo.");
        } catch (IOException e) {
            System.out.println("Error: no se puede leer.");
        }
    }

    public void guardarArchivo() {
        if (directorio == null) {
            System.out.println("Error: no hay directorio elegido.");
            return;
        }

        if (!archivoLeido) {
            System.out.println("Error: no se ha leído el archivo.");
            return;
        }

        String directorioSalida = "archivosSalida/%s-palabras.csv".formatted(directorio);

        File directorioFile = new File("archivosSalida");
        if (!directorioFile.exists()) {
            directorioFile.mkdirs();
        }

        try (PrintWriter escritor = new PrintWriter(new FileWriter(directorioSalida))) {
            escritor.println("Palabra,Frecuencia");
            for (Map.Entry<String, Integer> entrada : contadorPalabras.getContadorPalabras().entrySet()) {
                escritor.printf("%s,%s\n", entrada.getKey(), entrada.getValue());
            }
            System.out.printf("¡Archivo guardado en %s!%n", directorioSalida);
        } catch (IOException e) {
            System.out.printf("Error al guardar el archivo: %s%n", e.getMessage());
        }
    }


    public void seleccionarArchivo() {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Ingrese el nombre del archivo que desea seleccionar: ");
        directorio = entrada.nextLine();
    }

    public void mostrarRepeticionPalabra() {
        if (directorio == null) {
            System.out.println("Error: no hay directorio elegido.");
            return;
        }

        if (!archivoLeido) {
            System.out.println("Error: no se ha leído el archivo.");
            return;
        }

        Scanner entrada = new Scanner(System.in);
        String palabra;
        boolean palabraEncontrada = false;

        System.out.print("Ingresa la palabra que quieras ver: ");
        palabra = entrada.nextLine().toLowerCase();

        for (Map.Entry<String, Integer> entry : contadorPalabras.getContadorPalabras().entrySet()) {
            if (palabra.equals(entry.getKey())) {
                System.out.printf("La palabra %s se repite %d veces.%n", entry.getKey(), entry.getValue());
                palabraEncontrada = true;
                break;
            }
        }

        if (!palabraEncontrada) {
            System.out.println("Error: la palabra no ha sido encontrada.");
        }
    }

    public void mostrarArchivoSeleccionado() {
        if (directorio == null) {
            System.out.println("Archivo seleccionado: ninguno\n");
        } else {
            System.out.printf("Archivo seleccionado: archivosEntrada/%s.csv%n%n", directorio);
        }
    }

    public void mostrarTodasLasPalabras() {
        if (directorio == null) {
            System.out.println("Error: no hay directorio elegido.");
            return;
        }

        if (!archivoLeido) {
            System.out.println("Error: no se ha leído el archivo.");
            return;
        }

        System.out.println(this);
    }

    private boolean tieneSoloLetras(String s) {
        return s.matches("^[a-zA-Z]+$");
    }

    @Override
    public String toString() {
        return contadorPalabras.toString();
    }
}