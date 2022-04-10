run: compile
	echo "Add command to run the program here"	
	java VetManagerFrontEnd
test:compile
	java -cp .:junit5.jar VetAppTests
compile:
	echo "Use this rule to compile all necessary java source files"

	javac TextUITester.java
	javac DogNode.java
	javac RedBlackTree.java
	javac DogDatabase.java
	javac VetManagerFrontEnd.java
	javac -cp .:junit5.jar VetAppTests.java -Xlint

