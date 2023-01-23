OBJS	= PricePriortyQueue.o main.o DiscreteEventSystem.o App.o Barista.o BaristaSystem.o Cashier.o Coffee.o CoffeeMode1.o CoffeeMode2.o
SOURCE	= PricePriortyQueue.cpp main.cpp DiscreteEventSystem.cpp App.cpp Barista.cpp BaristaSystem.cpp Cashier.cpp Coffee.cpp CoffeeMode1.cpp CoffeeMode2.cpp
HEADER	= Queue.h PricePriortyQueue.h Order.h DiscreteEventSystem.h App.h Barista.h BaristaSystem.h Cashier.h Coffee.h CoffeeMode1.h CoffeeMode2.h
OUT	= Assignment3
CC	 = g++ -std=c++11
FLAGS	 = -g -c -Wall
LFLAGS	 =

all: $(OBJS)
	$(CC) -g $(OBJS) -o $(OUT) $(LFLAGS)

PricePriortyQueue.o: PricePriortyQueue.cpp
	$(CC) $(FLAGS) PricePriortyQueue.cpp

main.o: main.cpp
	$(CC) $(FLAGS) main.cpp

DiscreteEventSystem.o: DiscreteEventSystem.cpp
	$(CC) $(FLAGS) DiscreteEventSystem.cpp

App.o: App.cpp
	$(CC) $(FLAGS) App.cpp

Barista.o: Barista.cpp
	$(CC) $(FLAGS) Barista.cpp

BaristaSystem.o: BaristaSystem.cpp
	$(CC) $(FLAGS) BaristaSystem.cpp

Cashier.o: Cashier.cpp
	$(CC) $(FLAGS) Cashier.cpp

Coffee.o: Coffee.cpp
	$(CC) $(FLAGS) Coffee.cpp

CoffeeMode1.o: CoffeeMode1.cpp
	$(CC) $(FLAGS) CoffeeMode1.cpp

CoffeeMode2.o: CoffeeMode2.cpp
	$(CC) $(FLAGS) CoffeeMode2.cpp


clean:
	rm -f $(OBJS) $(OUT)