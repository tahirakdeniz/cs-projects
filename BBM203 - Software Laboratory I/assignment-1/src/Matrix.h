//
// Created by muham on 29.10.2022.
//

#ifndef ASSIGNMENT1_MATRIX_H
#define ASSIGNMENT1_MATRIX_H

/**
 * a class to represent matrix.
 * */
class Matrix {
    int colNumber;
    int rowNumber;
    int ** arr;
public:
    Matrix(int rowNumber, int colNumber);

    /* returns value of the given coordinate in matrix*/
    int getValue(int rowIndex, int colIndex);

    /* sets the value of the given coordinates in matrix*/
    void setValue(int rowIndex, int colIndex, int val);

    virtual ~Matrix();

    int getColNumber() const;

    int getRowNumber() const;
};


#endif //ASSIGNMENT1_MATRIX_H
