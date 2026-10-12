/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * MARIANO ALBORNOZ 357964
 * MAURiCIO PEREZ
 */
public class ficha {
    
    //CREACION DE FICHA
        private String nombre;
        private char color;
        private char[][] chico;
        private char[][] grande;
        
        public ficha(String nombre, char color, String matChica, String matGrande){
            
            this.nombre = nombre;
            this.color = color;
            this.chico = armadoMatriz(matChica, 3);
            this.grande = armadoMatriz(matGrande, 5);
        }
        
        // ARMADO DE MATRIZ
        private char[][] armadoMatriz(String texto, int n){
            char[][] m = new char[n][n];
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n;j++){
                    char c = texto.charAt(i*n+j);
                    m[i][j] = (c == '.') ? ' ' :c;
                }
            }
            return m;
        }
    
    
    //VALIDACION COLOR
        
    public static boolean colorValido(char c){
        return "RABNMVGC".indexOf(c) >= 0;
    }
    
    // CONSULTAS
    
    public String getNombre(){
        return nombre;
    }
    
    public char getColor(){
        return color;
    }
    
    //CONSULTA DE COLOR
    public String getCodigoAnsi(){
        switch (color) {
            case 'R': return "\u001B[31m"; // rojo
            case 'V': return "\u001B[32m"; // verde
            case 'M': return "\u001B[33m"; // amarillo
            case 'A': return "\u001B[34m"; // azul
            case 'G': return "\u001B[35m"; // magenta
            case 'C': return "\u001B[36m"; // celeste
            case 'B': return "\u001B[37m"; // blanco
            default:  return "\u001B[30m"; // negro (N)
        }
    }
    
    //DEVUELVE FILA DEL DIESENO
    public String getLinea(int tamano, int fila){
        char[][] m = (tamano == 3) ? chico : grande;
        return new String(m[fila]);
    }
}
