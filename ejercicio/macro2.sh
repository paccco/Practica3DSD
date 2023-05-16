javac ./*.java
echo
echo "Lanzando el primer cliente"
java -cp . -Djava.security.policy=./server.policy Cliente localhost $1 $2
sleep 2