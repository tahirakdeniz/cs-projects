findtreasure:main.o App.o Input.o Matrix.o MatrixCreator.o OutputWriter.o TreasureFinder.o
	g++ -std=c++11 main.o App.o Input.o Matrix.o MatrixCreator.o OutputWriter.o TreasureFinder.o -o findtreasure

TreasureFinder.o: MatrixCreator.h OutputWriter.h TreasureFinder.h
	g++ -std=c++11 -c TreasureFinder.cpp TreasureFinder.h

OutputWriter.o: OutputWriter.h
	g++ -std=c++11 -c OutputWriter.cpp OutputWriter.h

MatrixCreator.o: MatrixCreator.h Matrix.h
	g++ -std=c++11 -c MatrixCreator.cpp MatrixCreator.h

Matrix.o: Matrix.h
	g++ -std=c++11 -c Matrix.cpp Matrix.h
Input.o: Input.h
	g++ -std=c++11 -c Input.cpp Input.h

App.o: App.h Input.h
	g++ -std=c++11 -c App.cpp App.h

main.o: main.cpp Input.h App.h
	g++ -std=c++11 -c main.cpp
