//
// Created by muham on 30.10.2022.
//

#include <sstream>
#include "Input.h"

Input::Input(char **argv) {

    // get map matrix sizes
    std::string mapsSize = argv[1];
    std::stringstream mapsSizeStream(mapsSize);
    std::string numberStr;

    bool  isFirst = true;

    // separate the string
    while (std::getline(mapsSizeStream, numberStr, 'x')) {
        if (isFirst)
        {
            mapMatrixRowNumber = std::stoi(numberStr);
            isFirst = false;
        }
        else mapMatrixColNumber = std::stoi(numberStr);
    }

    // get other inputs
    keyMatrixSize = std::stoi(argv[2]);
    mapInputFileName = new std::string (argv[3]);
    keyInputFileName = new std::string (argv[4]);
    outputFileName = new std::string (argv[5]);

}


// Getter:
int Input::getMapMatrixRowNumber() const {
    return mapMatrixRowNumber;
}

int Input::getKeyMatrixSize() const {
    return keyMatrixSize;
}

int Input::getMapMatrixColNumber() const {
    return mapMatrixColNumber;
}

std::string *Input::getMapInputFileName() const {
    return mapInputFileName;
}

std::string *Input::getKeyInputFileName() const {
    return keyInputFileName;
}

std::string *Input::getOutputFileName() const {
    return outputFileName;
}
//----

Input::~Input() {
    delete mapInputFileName;
    delete keyInputFileName;
    delete outputFileName;
}


