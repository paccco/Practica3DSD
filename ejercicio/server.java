import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.rmi.registry.*;

public class server extends UnicastRemoteObject implements MiInter,conexion {

    private ArrayList<String> registrados;
    private ArrayList<Donacion> donaciones;
    private ArrayList<String> donadores;
    private ArrayList<Donacion> ranking;
    private int subTotal;
    private String name;
    private server br;


    server(String n) throws RemoteException {
        super();

        registrados=new ArrayList<String>();
        donaciones=new ArrayList<Donacion>();
        donadores=new ArrayList<String>();
        ranking=new ArrayList<Donacion>();
        subTotal=0;
        name=n;
    }

    private void updateR(){
        //BubbleSort
        boolean swapped;

        for (int i=0; i<ranking.size()-1; i++) {
            swapped = false;

            for (int j=0; j < ranking.size()-i-1; j++) {
                if (ranking.get(j+1).esMayor(ranking.get(j))) {
                    // Intercambiar los elementos
                    Donacion temp = ranking.get(j);
                    ranking.set(j,ranking.get(j+1));
                    ranking.set(j+1, temp);
                    swapped = true;
                }
            }
            // Si no hubo intercambios en esta pasada, el array ya está ordenado
            if (!swapped) {
                break;
            }
        }

    }

    public void setBR(server replic){
        br=replic;
    }

    public server menosEntidades(){
        if(this.registrados.size()<=br.registrados.size()){
            return this;
        }else{
            return br;
        }
    }

    public server registradoEn(String usuario){
        if(this.registrados.contains(usuario))
            return this;
        else if(br.registrados.contains(usuario))
            return br;
        
        return null;
    }

    public String registrarse(String usuario) throws RemoteException{

        server aux=registradoEn(usuario);

        if(aux!=null){
            return "Usuario registrado previamente en "+aux.name+" ,actualmente "+this.name;
        }else{
            try{
                aux=menosEntidades();
                aux.registrados.add(usuario);
                return "Usuario registrado con exito en "+aux.name+", actualemente "+this.name;
            }catch(Exception e){
                e.getStackTrace();
                return "Servidor excepcion - Registro1";
            }
        }
    }

    public String donar(String donante,int cant) throws RemoteException{

        server aux=registradoEn(donante);

        if(aux==null){
            return "Registrese previamente por favor";
        }else{
            if(!aux.donadores.contains(donante)){
                aux.donadores.add(donante);
                aux.ranking.add(new Donacion(donante, cant));
            }else{
                for (Donacion d : aux.ranking)
                    if (d.getDonante().equals(donante)) {
                        d.suma(cant);
                        break;
                    }
            }
        }
            aux.donaciones.add(new Donacion(donante, cant));
            aux.subTotal+=cant;

            return "GRACIAS POR SU DONACION!! "+aux.name;
        }

    public int consultar(String usuario) throws RemoteException{

        server aux=registradoEn(usuario);

        try{
            if(aux.donadores.contains(usuario))
                return br.subTotal+subTotal;
            else
                return -1;
        }catch(NullPointerException  e){
            return -2;
        }
    }

    public String consulta_Ranking(){
        String out="";

        if(!ranking.isEmpty() || !br.ranking.isEmpty()){

            if(!ranking.isEmpty())
                updateR();
            if(!br.ranking.isEmpty())
                br.updateR();

            for(int i=0,j=0;i<ranking.size() || j<br.ranking.size();){
                if(i>=ranking.size()){
                    System.out.print("1\n");
                    out+=br.ranking.get(j).toString();
                    j++;
                }
                else if(j>=br.ranking.size()){
                    System.out.print("2\n");
                    out+=ranking.get(i).toString();
                    i++;
                }
                else{
                    if(ranking.get(i).esMayor(br.ranking.get(j))){
                        System.out.print(ranking.get(i).getCantidad()+" "+br.ranking.get(j).getCantidad()+"\n");
                        out+=ranking.get(i).toString();
                        i++;
                    }else{
                        System.out.print("4\n");
                        out+=br.ranking.get(j).toString();
                        j++;
                    }
                }
            }
        }else{
            out="Rankings vacios";
        }

        return out;
    }
}

