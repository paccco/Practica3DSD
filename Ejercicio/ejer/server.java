package Ejercicio.ejer;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class server extends UnicastRemoteObject implements MiInter{

    ArrayList<String> registrados;
    ArrayList<Donacion> donaciones;
    int total;

    server() throws RemoteException {
        super();

        registrados=new ArrayList<String>();
        donaciones=new ArrayList<Donacion>();
        total=0;
    }

    public String registrarse(String usuario) throws RemoteException{
        if(registrados.contains(usuario)){
            return "Usuario registrado previamente";
        }else{
            registrados.add(usuario);
            return "Usuario registrado con exito";
        }
    }

    public void donar(Donacion d) throws RemoteException{
        
    }

    public int consultar() throws RemoteException{
        int aux=total;
        return aux;
    }
}
