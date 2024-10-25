import java.util.HashMap;

public class ContadorPalabras {
    private HashMap<String, Integer> contadorPalabras;

    public ContadorPalabras() {
        contadorPalabras = new HashMap<>();
    }

    public void agregarPalabraNueva(String palabra) {
        contadorPalabras.put(palabra, 1);
    }

    public void aumentarContadorDe(String palabra) {
        contadorPalabras.put(palabra, contadorPalabras.get(palabra) + 1);
    }

    public int obtenerContadorDe(String palabra) {
        return contadorPalabras.get(palabra);
    }

    public boolean estaPalabra(String palabra) {
        return contadorPalabras.get(palabra) != null;
    }

    public HashMap<String, Integer> getContadorPalabras() {
        return contadorPalabras;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String palabra : contadorPalabras.keySet()) {
            sb.append(palabra).append(": ").append(contadorPalabras.get(palabra)).append("\n");
        }
        return sb.toString();
    }
}