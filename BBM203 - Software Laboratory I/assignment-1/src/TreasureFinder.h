//
// Created by muham on 30.10.2022.
//

#ifndef ASSIGNMENT1_TREASUREFINDER_H
#define ASSIGNMENT1_TREASUREFINDER_H


#include "Matrix.h"
#include "OutputWriter.h"

class TreasureFinder {
    Matrix *mapMatrix;
    Matrix *keyMatrix;
    int keyMatrixSize;
    OutputWriter *outputWriter;
public:
    TreasureFinder(Matrix *mapMatrix, Matrix *keyMatrix, OutputWriter *outputWriter);
    void run();

private:
    void find(int rowNumber, int colNumber);
};


#endif //ASSIGNMENT1_TREASUREFINDER_H
