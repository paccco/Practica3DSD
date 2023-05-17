import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MiInter extends Remote{
    public String registrarse(String usuario) throws RemoteException;
    public String donar(String donante,int cant) throws RemoteException;
    public int consultar(String usuario) throws RemoteException;
    public String consulta_Ranking()throws RemoteException;
}
