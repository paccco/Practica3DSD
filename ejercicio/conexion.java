import java.rmi.RemoteException;

public interface conexion {
    public server menosEntidades() throws RemoteException;
    public server registradoEn(String usuario) throws RemoteException;
}
