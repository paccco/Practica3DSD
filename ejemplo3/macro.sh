#!/bin/sh -e
# ejecutar = Macro para compilacion y ejecucion del programa ejemplo
# en una sola maquina Unix de nombre localhost.
echo
echo "Lanzando el ligador de RMI … "

# Verificar si se encontró el proceso
if pkill rmiregistry; then
  echo "El proceso ha sido terminado exitosamente."
else
  echo "No se encontró el proceso"
fi
rmiregistry 2099 &
echo
echo "Compilando con javac ..."
javac ./simple/*.java
sleep 2
echo
echo "Lanzando el servidor"
java -cp . -Djava.rmi.server.codebase=file:./simple/ -Djava.rmi.server.hostname=localhost -Djava.security.policy=./simple/server.policy simple.servidor &
sleep 2
echo
echo "Lanzando el primer cliente"
echo
java -cp . -Djava.security.policy=./simple/server.policy simple.cliente localhost 0
sleep 2
echo
echo "Lanzando el segundo cliente"
echo
java -cp . -Djava.security.policy=./simple/server.policy simple.cliente localhost 3
pkill java
pkill rmiregistry