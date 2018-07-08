# Floor Plan

## Application Overview
Basic Java application that allows a user to place tables across a floor plan. 

## Application Development
Application is developed in Java and utilizes the Java Swing package components.

## Setting up and building application
Application can be run as a jar file in any Java virtual machine. To generate the .jar file, run the following command in a terminal from the root of the project.

```
mvn clean package 
```

Then to run the .jar file and execute the program, run the command in the same terminal

```
java -jar target/floorplan-0.1.jar
```

## Accepted Development/Task/Wish List for next Pull Request into master branch
Below are a list of wish list items for updates to the application. 

- [ ] Remove all commented version history and add to markdown README
- [ ] Ability to save a table layout
- [ ] Ability to select a specific floor plan type

## Updates List

###### 08 July 2018
Added Maven plugin for generating an executable .jar file.

###### 29 March 2018
Basic project commited to git. Features include:
- Ability to launch a Java Swing application that lets you place 'tables', signified
as red squares, through out a 'floor' panel.
- Tables can not be placed to overlap each other
- Ability to unplace tables in reverse chronological order
- Application can be packages as a .jar and run on any Java virtual machines.
