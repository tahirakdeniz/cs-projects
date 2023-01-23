//
// Created by muham on 21.11.2022.
//

#ifndef ASS203_2_APP_H
#define ASS203_2_APP_H
#include <iostream>
#include "Street.h"
#include "Writer.h"

class App {
    Street * street;
    Writer * writer;
    string inputFileName;
    fstream * inputStream;
public:
    App(Street *street, const string &inputFileName, Writer *writer);
    void run();

    virtual ~App();

private:
    // Methods to read the command in the input file and calls the methods in the street.
    void readAddApartmentCommand();
    void readAddFlatCommand();
    void readRemoveApartmentCommand();
    void readMakeFlatEmptyCommand();
    void readMergeTwoApartmentsCommand();
    void readRelocateFlatsToSameApartmentCommand();
};


#endif //ASS203_2_APP_H
