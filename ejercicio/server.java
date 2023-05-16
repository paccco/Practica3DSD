import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.rmi.registry.*;

public class server extends UnicastRemoteObject implements MiInter,conexion {

    private ArrayList<String> registrados;
    private ArrayList<Donacion> donaciones;
    private ArrayList<String> donadores;
    private int subTotal;
    private String name;
    private server br;

    server(String n) throws RemoteException {
        super();

        registrados=new ArrayList<String>();
        donaciones=new ArrayList<Donacion>();
        donadores=new ArrayList<String>();
        subTotal=0;
        name=n;
    }

    public void setBR(server replic){
        br=replic;
    }

    public int cantidadTotal(){
        return br.subTotal+subTotal;
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
            if(!aux.donadores.contains(donante))
                aux.donadores.add(donante);
            aux.donaciones.add(new Donacion(donante, cant));
            aux.subTotal+=cant;

            return "GRACIAS POR SU DONACION!! "+aux.name;
        }
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
}

