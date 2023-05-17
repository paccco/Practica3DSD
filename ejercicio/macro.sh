#!/bin/sh -e
# ejecutar = Macro para compilacion y ejecucion del programa ejemplo
# en una sola maquina Unix de nombre localhost.
echo
echo "Lanzando el ligador de RMI … "
rmiregistry &
echo
echo "Compilando con javac ..."
javac ./*.java
sleep 2
echo
echo "Lanzando el servidor"
java -cp . -Djava.rmi.server.codebase=file:./ -Djava.rmi.server.hostname=localhost -Djava.security.policy=./server.policy Servidores &
sleep 2
echo "Escriba EXIT para salir del programa"
echo
cadena=""
while [ "$cadena" != "EXIT" ]; do
    read -p "Nombre de usuario: " user
    read -p "Servidor a conectar [1-2]: " id

    echo "Lanzando el primer cliente"
    java -cp . -Djava.security.policy=./server.policy Cliente localhost $user $id
    sleep 1

    read -p "Ingresa una cadena (EXIT para salir): " cadena
done
echo "Fin del ejercicio"
pkill java
pkill rmiregistry