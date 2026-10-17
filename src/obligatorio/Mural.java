package obligatorio;
import java.util.ArrayList;

/**
 * MARIANO ALBORNOZ 357964
 * MAURICIO PEREZ
 */
public class Mural {
    private String nombre;
    private Disenador disenador;
    private Ficha[][] mural;
    private Ficha[][] original;
    
    public Mural(String nombre, Disenador disenador, String formato, Ficha f1, Ficha f2, Ficha vacia){
        this.nombre = nombre;
        this.disenador = disenador;
        this.mural = new Ficha[10][12];
        this.original = new Ficha[10][12];
        this.llenar(formato, f1, f2, vacia);
        this.guardarOriginal();
    }
    
    //CREACION DE MURALES 
    private void llenar(String formato, Ficha f1, Ficha f2, Ficha vacia){
        for(int fil = 0; fil < mural.length;fil++){
            for(int col = 0; col < mural[0].length;col++){
                Ficha f = vacia;
                
                //MURAL ESTILO DAMERO
                if(formato.equals("D")){
                    if((fil + col) % 2 == 0){
                        f = f1;
                    }else{
                        f = f2;
                    }
                    
                //MURAL RANDOM
                }else if(formato.equals("R")){
                    int n = (int) (Math.random() * 3);
                    if(n == 1){
                        f = f1;
                    }else if(n == 2){
                        f = f2;
                    }
                    
                //MURAL ALTERNADO
                }else if(formato.equals("F")){
                    if(fil % 2 == 0){
                        f = f1;
                    }else{
                        f = f2;
                    }
                    
                //MURAL BORDE EXTERIOR
                }else if(formato.equals("B")){
                    if(fil == 0 || col == 0 || fil == mural.length - 1 || col == mural[0].length - 1){
                        f = f1;
                    }else{
                        f = f2;
                    }
                }
                //MURAL MANUAL
                mural[fil][col] = f;
            }
        }
    }
    //COPIA MURAL
    public void guardarOriginal(){
        for(int fil = 0; fil < mural.length; fil++){
            for(int col = 0; col < mural[0].length;col++){
                original[fil][col] = mural[fil][col];
            }
        }
    }
    //COLOCA FICHA
    public void colocar(int fila, int columna, Ficha f){
        mural[fila - 1][columna - 1] = f;
    }
    //DEVUELVE MURAL CON COLOR
    public String visualizar(int tamano){
        String reset = "\u001B[0m";
        String texto = "";
        for(int fil = 0; fil < mural.length;fil++){
            for(int linea = 0; linea < tamano;linea++){
                for(int col = 0; col < mural[0].length;col++){
                    Ficha f = mural[fil][col];
                    texto += f.getCodigoAnsi() + f.getLinea(tamano, linea) + reset;
                }
                texto += "\n";
            }
        }
        return texto;
    }
    
    //RESTAURA MURAL
    public void restaurar(){
        for(int fil = 0; fil < mural.length;fil++){
            for(int col = 0; col < mural[0].length;col++){
                mural[fil][col] = original[fil][col];
            }
        }
    }
    
    //CAMBIA APARICIONES DE UNA FICHA POR OTRA
    public void reemplazarFicha(Ficha vieja, Ficha nueva){
        for(int fil = 0; fil < mural.length;fil++){
            for(int col = 0; col < mural[0].length;col++){
                if(mural[fil][col].getNombre().equals(vieja.getNombre())){
                    mural[fil][col] = nueva;
                }
            }
        }
    }
    
    //DEVUELVE FICHAS DIFERNETES QUE TIENE EL MURAL
    public ArrayList<Ficha> fichasUsadas(){
        ArrayList<Ficha> usadas = new ArrayList<Ficha>();
        for(int fil = 0; fil < mural.length;fil++){
            for(int col = 0; col < mural[0].length;col++){
                Ficha f = mural[fil][col];
                boolean esta = false;
                for(Ficha u : usadas){
                    if(u.getNombre().equals(f.getNombre())){
                        esta = true;
                    }
                }
                if(!esta){
                    usadas.add(f);
                }
            }
        }
        return usadas;
    }
    
    //DEVUELVE FICHA DE UNA POSICIONM
    public Ficha getFicha(int fila, int columna){
        return mural[fila - 1][columna - 1];
    }
    
    //BUSCA EN MURAL UN AREA IGUAL A LA ORIGEN
    public int[] buscarArea(Mural origen, int fila, int columna, int filas, int columnas){
        int[] posicion = null;
        for(int f = 0; f <= mural.length - filas && posicion == null;f++){
            for(int c = 0; c <= mural[0].length - columnas && posicion == null;c++){
                boolean igual = true;
                for(int i = 0; i < filas && igual;i++){
                    for(int j = 0; j < columnas &&igual;j++){
                        Ficha propia = mural[f+i][c+j];
                        Ficha buscada = origen.getFicha(fila+i, columna+j);
                        if(!propia.getNombre().equals(buscada.getNombre())){
                            igual = false;
                        }
                    }
                }
                if(igual){
                    posicion = new int[2];
                    posicion[0] = f+1;
                    posicion[1] = c+1;
                }
            }
        }
        return posicion;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public Disenador getDisenador(){
        return disenador;
    }
}
