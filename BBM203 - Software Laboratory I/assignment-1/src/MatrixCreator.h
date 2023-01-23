//
// Created by muham on 30.10.2022.
//

#ifndef ASSIGNMENT1_MATRIXCREATOR_H
#define ASSIGNMENT1_MATRIXCREATOR_H


#include <string>
#include "Matrix.h"

/**
 * a class to creates Matrix by reading the given file.
 * */
class MatrixCreator {
    int rowNumber;
    int colNumber;
    std::string *fileName;
public:
    explicit MatrixCreator(std::string *fileName, int rowNumber, int colNumber);
    /**
     * opens the file, reads line by line, creates matrix and sets values.
     * */
    Matrix * createMatrix();
};


#endif //ASSIGNMENT1_MATRIXCREATOR_H
