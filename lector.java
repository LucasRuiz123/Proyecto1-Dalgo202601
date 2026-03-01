    /*
    Autores:
    Camilo José Ochoa Romero - 202321705
    Lucas Ruiz Yockteng- 202321331
    */

    import java.util.List;
    import java.util.ArrayList;
    import java.util.Arrays;

    public class lector {


        public int rios(String texto) {

    int maxRios = 0;
    int anchoPreferido = texto.length();

    for (int i = 1; i <= texto.length(); i++) {

        int lenRio = lectorMatrizStrings(texto, i);

        if (lenRio > maxRios) {
            maxRios = lenRio;
            anchoPreferido = i;
        }
        // Si empata, nos quedamos con el más pequeño
        else if (lenRio == maxRios && i < anchoPreferido) {
            anchoPreferido = i;
        }
    }

    System.out.println("Ancho preferido: " + anchoPreferido);
    System.out.println("Río más largo: " + maxRios);

    return maxRios;
}



    
        public int lectorMatrizStrings(String texto, int ancho) {

        String[] palabrasTexto = texto.split(" ");
        List<char[]> filas = new ArrayList<>();

        StringBuilder lineaActual = new StringBuilder();
        int[] DP = new int[ancho];
        int[][] sumaRios = new int[palabrasTexto.length][ancho];
        Arrays.fill(DP, 0);
        int maxRio = 0;
        int indice = 0;
        for (String palabra : palabrasTexto) {

            
            if (palabra.length() > ancho) { // Devuelvo una matriz con nada para indicar falla
                return 0;
            }else{

                if (lineaActual.length() == 0) { // caso de que las palabras quepan en la fila actual
                    lineaActual.append(palabra);
                    //indice++;
                } else if (lineaActual.length() + 1 + palabra.length() <= ancho) {
                    int posEspacio = lineaActual.length();  // Posición del espacio
                    DPagregar(posEspacio, ancho, sumaRios, indice, DP);
                    lineaActual.append(" ").append(palabra);
                    
                    

                    
                } else {
                    filas.add(convertirLinea(lineaActual.toString(), ancho)); // agrgar palabras
                    indice++; // para seguir a la sieugnte linea
                    lineaActual = new StringBuilder(palabra);
                    
                }
            }


            
        }
        maxRio = Arrays.stream(DP).max().orElse(0);
        
        
        if(ancho>10 && ancho<40){
        System.out.println("-----");
        System.out.println("Ancho: " + ancho);
        System.out.println("Max Río: " + maxRio);
        System.out.println("-----");
        //System.out.println("-----");
        System.out.println("DP completo:");
        for (int j = 0; j < DP.length; j++) {
        System.out.print(DP[j] + " ");
        }
        System.out.println();
    }
        //System.out.println("-----");
        //System.out.println("-----");
        //System.out.println("-----");

        
        //System.out.println("-----");
        //System.out.println("Max Río: " + maxRio);   
        //System.out.println("-----");
        //System.out.println("ancho: " + ancho);  


        // Agregar última línea
        if (lineaActual.length() > 0) {
            filas.add(convertirLinea(lineaActual.toString(), ancho));
        }

        // Convertierto las filas en la matriz
        char[][] matriz = new char[filas.size()][ancho];
        for (int i = 0; i < filas.size(); i++) {

            matriz[i] = filas.get(i);
        }



        return maxRio;
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
public void DPagregar(int pos, int ancho, int[][] sumaRios, int indice, int[] DP) {

    int columna = pos;

    if (columna < 0 || columna >= ancho) {
        return;
    }

    if (indice == 0) {
        sumaRios[indice][columna] = 1;
    } else {
        int arriba = sumaRios[indice - 1][columna];

        if (arriba != 0) {
            sumaRios[indice][columna] = arriba + 1;
        } else {
            sumaRios[indice][columna] = 1;
        }
    }

    DP[columna] = Math.max(DP[columna], sumaRios[indice][columna]);
}

        public static void main(String[] args) {

        lector lector = new lector();

        String texto = "El Amazonas es el río más largo de Suramérica y el Magdalena es el más largo que fluye enteramente en Colombia Mambrú se fue a la guerra que dolor que dolor que pena Carito me habla en ingles qué bonito se le ve Carito me habla en ingles qué me dice yo no se";    
        int x = lector.rios(texto);

    }
    }
