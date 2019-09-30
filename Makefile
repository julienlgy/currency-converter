##
## EPITECH PROJECT, 2018
## Makefile JAV
## File description:
## Makefile
##

NAME	=	CurrencyConverter1.0.jar

run:
	mvn exec:java -Dexec.mainClass="Main"

test:
	mvn test

package:
	mvn package
	cp ./target/java-archive-1.0-SNAPSHOT-jar-with-dependencies.jar $(NAME)

build:
	mvn compile

deps:
	mvn dependency:resolve

clean:
	mvn clean
	rm -f $(NAME)

all:	clean
	make package
	chmod +x $(NAME)
