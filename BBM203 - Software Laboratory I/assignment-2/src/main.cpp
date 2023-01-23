#include <iostream>
#include "ApartmentList.h"
#include "Street.h"
#include "App.h"

using namespace std;

int main(int argc, char **argv) {
    string inputFilename = argv[1];
    string outputFilename = argv[2];

    auto apartmentList = new ApartmentList();
    auto street = new Street(apartmentList);
    auto writer = new Writer(apartmentList, outputFilename);

    App* app = new App(street, inputFilename, writer);

    app->run();

    delete writer;
    delete apartmentList;
    delete street;
    delete app;
    return 0;
}




