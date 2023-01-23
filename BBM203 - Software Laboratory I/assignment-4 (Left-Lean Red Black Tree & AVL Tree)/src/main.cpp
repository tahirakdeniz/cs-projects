#include <iostream>
#include <vector>
#include <fstream>
#include <sstream>
#include "PrimaryTree.h"
#include "App.h"

int main(int argc, char* argv[]) {

    std::string inputFile = argv[1];
    std::string firstOutput = argv[2];
    std::string secondOutput = argv[3];

    App app(inputFile, firstOutput, secondOutput);
    app.run();
    return 0;
}
