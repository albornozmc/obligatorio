package obligatorio;
import java.util.ArrayList;

/**
 * MARIANO ALBORNOZ 357964
 * MAURICIO PEREZ 
 */
public class Disenador {
        private String nombre;
        private String direccion;
        private String mail;
        private ArrayList<Mural> murales;
        
        public Disenador(String nombre, String direccion, String mail){
           this.nombre = nombre;
           this.direccion = direccion;
           this.mail = mail;
           this.murales = new ArrayList<Mural>();
        }
        
        public String getNombre(){
            return nombre;
        }
        
        public String getDireccion(){
            return direccion;
        }
        
        public String getMail(){
            return mail;
        }
        
        public ArrayList<Mural> getMurales(){
            return murales;
        }
        
        public void agregarMural(Mural m){
            murales.add(m);
        }
        
        //FICHAS DIFERENTES
        public ArrayList<Ficha> fichasDiferentes(){
            ArrayList<Ficha> usadas = new ArrayList<Ficha>();
            for(Mural m : murales){
                for(Ficha f : m.fichasUsadas()){
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
        
        //CANT DE COLORES DIFERENTES DE FICHAS
        public int cantidadColores(){
            ArrayList<Ficha> fichas = fichasDiferentes();
            int cantidad = 0;
            for(int i = 0; i < fichas.size();i++){
                boolean repetido = false;
                for(int j = 0; j < i;j++){
                    if(fichas.get(j).getColor() == fichas.get(i).getColor()){
                        repetido = true;
                    }
                }
                if(!repetido){
                    cantidad++;
                }
            }
            return cantidad;
        }
        
        @Override
        public String toString(){
            return "Disenador: " + nombre + ", direccion: " + direccion + ", mail: " + mail;
        }
    }
