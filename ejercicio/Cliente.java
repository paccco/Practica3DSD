import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import java.util.Scanner;

public class Cliente {
    public static void main (String args[]) {

		if (args.length!=3) {
			System.out.println ("Parámetros: ./Cliente <usuario> <id_servidor[1,2]> "+args[2]);
			System.exit(1);
		}

		/* Variables */
		Scanner cin = new Scanner (System.in);
		String respuesta;

		/* Crea el gestor de seguridad */
		if (System.getSecurityManager() == null)
			System.setSecurityManager( new SecurityManager() );

		try {
			String nombre_objeto_remoto = "Server_"+args[2];
			Registry registry = LocateRegistry.getRegistry (args[0]);
			MiInter instancia_local = (MiInter) registry.lookup (nombre_objeto_remoto);

			do {
				System.out.println ("\nMenú:\n"
                + "R: Registro\n"
                + "D: Donar\n"
                + "C: Consultar total donado\n"
				+ "P: Consultar ranking\n"
                + "S: Salir\n");
				respuesta = cin.next();
				char aux=respuesta.charAt(0);

				if ( (aux!='R') && (aux!='D') && (aux!='C') && (aux!='S') && (aux!='P') )
					System.out.println ("Opción no válida");
				else {
					String mens="";

					switch (respuesta) {
						case "R":
							mens=instancia_local.registrarse( args[1] );
							
						break;

						case "D":
							System.out.print("Cuanto quiere donar: ");
							int don=cin.nextInt();
							mens=instancia_local.donar(args[1], don);
						break;

						case "C":
							int c=instancia_local.consultar(args[1]);
							if(c>=0)
								System.out.print("Cantidad total donada: "+c+" €");
							else if(c==-1)
								System.out.print("Realize una donacion para ver el total");
							else
								System.out.print("Registrese por favor");
							
						break;

						case "P":
							System.out.print(instancia_local.consulta_Ranking());
						break;

						case "S":
							System.out.println ("Fin del programa");
						break;
					}

					if(mens!=""){
						System.out.println(mens);
					}
				}

			} while (respuesta.charAt(0)!='S');
        cin.close();
		} catch (Exception e) {
			System.err.println ("Cliente exception: ");
			e.printStackTrace();
		}
	}
}
