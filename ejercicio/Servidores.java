import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.rmi.registry.*;

public class Servidores {
    public static void main (String[] args) {
    
        if (System.getSecurityManager() == null)
            System.setSecurityManager( new SecurityManager() );

        try {
            String s1 = "Server_1", s2="Server_2";
            server r1=new server(s1);
            server r2=new server(s2);

            r1.setBR(r2);
            r2.setBR(r1);

            MiInter replica1 = r1;
            MiInter replica2 = r2;

            try {
                UnicastRemoteObject.unexportObject(replica1, false);
                UnicastRemoteObject.unexportObject(replica2, false);
            } catch (RemoteException ex) {
            }
        

            MiInter stub1 = (MiInter) UnicastRemoteObject.exportObject (replica1, 0);
            MiInter stub2 = (MiInter) UnicastRemoteObject.exportObject (replica2, 0);
            
            Registry registry = LocateRegistry.getRegistry("localhost",1099);

            registry.rebind (s1, stub1);
            System.out.println ("Servidor 1 activo");

            registry.rebind (s2, stub2);
            System.out.println ("Servidor 2 activo");
            
        } catch (Exception e) {
            System.err.println ("Servidor exception: ");
            e.printStackTrace ();
        }
    }
}
