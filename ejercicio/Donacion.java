public class Donacion {
    private String donante;
    private int cantidad;
    
    public String getDonante() {
        return donante;
    }

    public int getCantidad() {
        return cantidad;
    }

    Donacion(String nombre,int don){
        donante=nombre;
        cantidad=don;
    }
}
