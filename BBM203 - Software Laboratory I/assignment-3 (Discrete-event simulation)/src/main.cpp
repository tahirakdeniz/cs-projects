#include <iostream>
#include "DiscreteEventSystem.h"
#include "Queue.h"
#include "Order.h"
#include "CoffeeMode1.h"
#include "CoffeeMode2.h"
#include "App.h"

void f();
int main(int argc, char* argv[]) {

    std::string inputFileName = argv[1];
    std::string outputFileName = argv[2];

    App reader(inputFileName, outputFileName);
    reader.run();

    return 0;
}
