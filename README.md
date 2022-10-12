# Seat:Code:Mowers

Application created to control SEAT Mowers through any grass plateau 
Mowers will move through plateau controlling out of bounds.
Mowers are able to go forward heading position, rotate left and right.
Oce program Starts it will stop when one all the Mowers have finished.
After all Mowers have finished, the program will print out all Mowers positions trough console.

## Authors
- [Ivan Canas](https://github.com/icanas)

## Requirements

Maven installed (https://maven.apache.org/index.html) <br>
Java JDK (https://www.oracle.com/java/technologies/downloads/)

## Input File Structure
First line is the Plateau size (0,0)->(x,y)  <br>
From here, next 2 lines define information for 1 Mower being:
1. Mower first position in the grid
2. Instruction set

In this exaple we have 1 Plateau, 2 Mowers
    
```bash
5 5
1 2 N
LMLMLMLMM
3 3 E
MMRMMRMRRM
```

## Installation
Add the input files with all information eg.(input.txt) inside ./src/main/resources/input directory<br>
Multiple files can be added (there is already one example)<br>
Go to mowers directory where pom.xml file is located. <br>
Execute <br>

```bash
mvn clean install
```

## Usage
Execute the desire file adding the filename at the end of the command
```bash
java -cp .\target\mowers-1.0-SNAPSHOT.jar com.seat.code.Main <file>
```


## Contributing
Pull requests are welcome for improving application
## License
[MIT](https://choosealicense.com/licenses/mit/)