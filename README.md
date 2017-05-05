# DS-TransactionServer
Final Project for a Distributed Systems class

to compile all java files in the folder:

### Linux
$ find -name "*.java" > sources.txt

$ javac @sources.txt

### Windows
> dir /s /B *.java > sources.txt

> javac @sources.txt


### to run

$ java -cp . TransactionServer
$ java -cp . Client
