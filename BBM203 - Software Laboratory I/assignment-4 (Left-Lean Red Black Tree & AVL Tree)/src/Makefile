OBJS	= App.o AVLTree.o main.o PrimaryTree.o RedBlackTree.o
SOURCE	= App.cpp AVLTree.cpp main.cpp PrimaryTree.cpp RedBlackTree.cpp
HEADER	= App.h AVLTree.h PrimaryTree.h RedBlackTree.h SecondaryTree.h
OUT	= webstore
CC	 = g++ -std=c++11

FLAGS	 = -g -c -Wall
LFLAGS	 =

all: $(OBJS)
	$(CC) -g $(OBJS) -o $(OUT) $(LFLAGS)

App.o: App.cpp
	$(CC) $(FLAGS) App.cpp

AVLTree.o: AVLTree.cpp
	$(CC) $(FLAGS) AVLTree.cpp

main.o: main.cpp
	$(CC) $(FLAGS) main.cpp

PrimaryTree.o: PrimaryTree.cpp
	$(CC) $(FLAGS) PrimaryTree.cpp

RedBlackTree.o: RedBlackTree.cpp
	$(CC) $(FLAGS) RedBlackTree.cpp


clean:
	rm -f $(OBJS) $(OUT)