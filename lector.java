
import java.util.List;
import java.util.ArrayList;

public class lector {


    public int rios(String texto){
        for (int i =0; i<= texto.length(); i++){
            char[][] matriz = lectorMatrizStrings(texto, i);
            for (char[] fila : matriz) {
            System.out.println(new String(fila));
            
        }
        System.out.println(i);
        }
        return 0;
    }



   
    public char[][] lectorMatrizStrings(String texto, int ancho) {

    String[] palabrasTexto = texto.split(" ");
    List<char[]> filas = new ArrayList<>();

    StringBuilder lineaActual = new StringBuilder();

    for (String palabra : palabrasTexto) {

        
        if (palabra.length() > ancho) { // Devuelvo una matriz con nada para indicar falla
            char [][] respuestaFallida = new char[0][0];
            return respuestaFallida;
        }

        if (lineaActual.length() == 0) {
            lineaActual.append(palabra);
        } else if (lineaActual.length() + 1 + palabra.length() <= ancho) {
            lineaActual.append(" ").append(palabra);
        } else {
            filas.add(convertirLinea(lineaActual.toString(), ancho));
            lineaActual = new StringBuilder(palabra);
        }
    }

    // Agregar última línea
    if (lineaActual.length() > 0) {
        filas.add(convertirLinea(lineaActual.toString(), ancho));
    }

    // Convertir List a char[][]
    char[][] matriz = new char[filas.size()][ancho];
    for (int i = 0; i < filas.size(); i++) {
        matriz[i] = filas.get(i);
    }

    return matriz;
}

private char[] convertirLinea(String linea, int ancho) {
    char[] fila = new char[ancho];

    for (int i = 0; i < ancho; i++) {
        if (i < linea.length()) {
            fila[i] = linea.charAt(i);
        } else {
            fila[i] = '*';
        }
    }

    return fila;
}

    public static void main(String[] args) {

    lector lector = new lector();

    String texto = "El Amazonas es el río más largo de Suramérica y el Magdalena es el más largo que fluye enteramente en Colombia";
    int ancho = 16 ;

    char[][] resultado = lector.lectorMatrizStrings(texto, ancho);

    for (char[] fila : resultado) {
        System.out.println(new String(fila));
    }
    int x = lector.rios(texto);

}
}
