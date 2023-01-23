OBJS	= main.o ApartmentList.o ApartmentNode.o FlatList.o FlatNode.o App.o Street.o Writer.o
SOURCE	= main.cpp ApartmentList.cpp ApartmentNode.cpp FlatList.cpp FlatNode.cpp App.cpp Street.cpp Writer.cpp
HEADER	= ApartmentList.h ApartmentNode.h FlatList.h FlatNode.h App.h Street.h Writer.h
OUT	= networkmap
CC	 = g++ -std=c++11
FLAGS	 = -g -c -Wall
LFLAGS	 =

all: $(OBJS)
	$(CC) -g $(OBJS) -o $(OUT) $(LFLAGS)

main.o: main.cpp
	$(CC) $(FLAGS) main.cpp

ApartmentList.o: ApartmentList.cpp
	$(CC) $(FLAGS) ApartmentList.cpp

ApartmentNode.o: ApartmentNode.cpp
	$(CC) $(FLAGS) ApartmentNode.cpp

FlatList.o: FlatList.cpp
	$(CC) $(FLAGS) FlatList.cpp

FlatNode.o: FlatNode.cpp
	$(CC) $(FLAGS) FlatNode.cpp

App.o: App.cpp
	$(CC) $(FLAGS) App.cpp

Street.o: Street.cpp
	$(CC) $(FLAGS) Street.cpp

Writer.o: Writer.cpp
	$(CC) $(FLAGS) Writer.cpp


clean:
	rm -f $(OBJS) $(OUT)
