import java.rmi.RemoteException;

public interface conexion {
    public int cantidadTotal() throws RemoteException;
    public server menosEntidades() throws RemoteException;
    public server registradoEn(String usuario) throws RemoteException;
}
