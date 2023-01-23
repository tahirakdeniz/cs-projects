//
// Created by muham on 29.10.2022.
//

#include "stdexcept"
#include "Matrix.h"

Matrix::Matrix(int rowNumber, int colNumber) {
    this->rowNumber = rowNumber;
    this->colNumber = colNumber;
    // create out matrix
    arr = new int*[rowNumber];

    // initialize inner arrays
    for (int i = 0; i < rowNumber; i++) {
        arr[i] = new int[colNumber];
    }
}

int Matrix::getValue(int rowIndex, int colIndex) {
    return arr[rowIndex][colIndex];
}

void Matrix::setValue(int rowIndex, int colIndex, int val) {
    arr[rowIndex][colIndex] = val;
}

Matrix::~Matrix() {

    // to delete inner arrays
    for (int i = 0; i < rowNumber; i++)
        delete[] arr[i];

    // delete matrix
    delete[] arr;
}

int Matrix::getColNumber() const {
    return colNumber;
}

int Matrix::getRowNumber() const {
    return rowNumber;
}
