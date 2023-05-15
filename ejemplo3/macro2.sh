echo
echo "Lanzando el primer cliente"
echo
java -cp . -Djava.security.policy=./simple/server.policy simple.cliente localhost 0
sleep 2
echo
echo "Lanzando el segundo cliente"
echo
java -cp . -Djava.security.policy=./simple/server.policy simple.cliente localhost 3