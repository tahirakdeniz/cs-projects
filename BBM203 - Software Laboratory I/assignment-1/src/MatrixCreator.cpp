//
// Created by muham on 30.10.2022.
//

#include <fstream>
#include <sstream>
#include <iostream>
#include "MatrixCreator.h"
using namespace std;

MatrixCreator::MatrixCreator(string *fileName, int rowNumber, int colNumber) {
    this->fileName = fileName;
    this->rowNumber = rowNumber;
    this->colNumber = colNumber;
}

Matrix* MatrixCreator::createMatrix() {
    int row = 0;
    int col;

    Matrix *matrix = new Matrix(rowNumber, colNumber);

    string line;

    // Read matrix from the file
    ifstream matrixFile(*fileName);

    // read file line by line
    while (getline (matrixFile, line)) {
        col = 0;
        stringstream strStream(line);
        string numStr;

        // split line by space
        while (getline(strStream, numStr, ' ')) {
            int num = stoi(numStr);

            matrix->setValue(row, col, num);
            col++;
        }
        row++;
    }

    // Close the file
    matrixFile.close();


    return matrix;
}
