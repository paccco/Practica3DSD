#!/bin/sh -e
# ejecutar = Macro para compilacion y ejecucion del programa ejemplo
# en una sola maquina Unix de nombre localhost.
echo
echo "Lanzando el ligador de RMI … "
rmiregistry &
echo
echo "Compilando con javac ..."
javac ./simple2/*.java
sleep 2
echo
echo "Lanzando el servidor"
java -cp . -Djava.rmi.server.codebase=file:./simple/ -Djava.rmi.server.hostname=localhost -Djava.security.policy=./simple2/server.policy simple2.Ejemplo &
sleep 2
echo
echo "Lanzando el primer cliente"
echo
java -cp . -Djava.security.policy=./simple2/server.policy simple2.Cliente_Ejemplo_Multi_Threaded localhost 0
sleep 2
echo
echo "Lanzando el segundo cliente"
echo
java -cp . -Djava.security.policy=./simple2/server.policy simple2.Cliente_Ejemplo_Multi_Threaded localhost 3
pkill java
pkill rmiregistry