public class Donacion {
    private String donante;
    private int cantidad;
    
    public void suma(int c) {
        this.cantidad += c;
    }

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

    public boolean esMayor(Donacion d){
        return cantidad>=d.cantidad;
    }

    public boolean compare(Donacion d){
        return donante.equals(d.donante);
    }

    @Override
    public String toString(){
        return donante+" --> "+cantidad+" €\n";
    }
}
