package Ejercicio.ejer;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MiInter extends Remote{
    public String registrarse(String usuario) throws RemoteException;
    public void donar(Donacion d) throws RemoteException;
    public int consultar() throws RemoteException;
}
