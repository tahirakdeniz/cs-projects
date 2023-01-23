//
// Created by muham on 30.10.2022.
//

#include <iostream>
#include "TreasureFinder.h"

TreasureFinder::TreasureFinder(Matrix *mapMatrix, Matrix *keyMatrix, OutputWriter *outputWriter) : mapMatrix(mapMatrix), keyMatrix(keyMatrix), outputWriter(outputWriter) {
    keyMatrixSize = keyMatrix->getRowNumber();
}

void TreasureFinder::run() {
    find(0, 0);
}

void TreasureFinder::find(int rowNumber, int colNumber) {

    // Calculate Multiplication of matrices
    int sum = 0;

    for (int i = 0; i < keyMatrixSize; i++) {
        for (int j = 0; j < keyMatrixSize; j++) {
            int mapMatrixValue = mapMatrix->getValue(rowNumber + i, colNumber + j);
            int keyMatrixValue = keyMatrix->getValue(i, j);
            sum += mapMatrixValue * keyMatrixValue;
        }
    }

    // Calculate center of sub matrix;
    int centerRowIndex = rowNumber + (keyMatrixSize - 1)/2;
    int centerColumnIndex = colNumber + (keyMatrixSize - 1) / 2;

    while(sum < 0) {
        sum += 5;
    }

    // Write output result
    outputWriter->writeOutput(centerRowIndex, centerColumnIndex, sum);

    // Calculate result
    int result = sum % 5;

    // Move to next direction by giving result
    switch (result) {
        case 0:
            return;
        case 1:
            if (rowNumber - keyMatrixSize >= 0)
                find(rowNumber - keyMatrixSize, colNumber);
            else
                find(rowNumber + keyMatrixSize, colNumber);
            break;
        case 2:
            if(rowNumber + keyMatrixSize <= mapMatrix->getRowNumber() - 1)
                find(rowNumber + keyMatrixSize, colNumber);
            else
                find(rowNumber - keyMatrixSize, colNumber);
            break;
        case 3:
            if(colNumber + keyMatrixSize <= mapMatrix->getColNumber() - 1)
                find(rowNumber, colNumber + keyMatrixSize);
            else
                find(rowNumber, colNumber - keyMatrixSize);
            break;
        case 4:
            if(colNumber - keyMatrixSize >= 0)
                find(rowNumber, colNumber - keyMatrixSize);
            else
                find(rowNumber, colNumber + keyMatrixSize);
            break;
        default:
            break;
    }

}


