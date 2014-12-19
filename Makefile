
all:
	javac Main.java Torre.java Avion.java

run: all
	java Main

.PHONY: clean

clean:
	rm -f *.class
